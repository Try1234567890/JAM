package com.github.trfiles.management.io.downloaders.toStream;

import com.github.utilities.validators.Preconditions;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URLConnection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/**
 * Downloads a remote {@link URI} onto an already opened {@link OutputStream}, reading the response body a
 * chunk at a time and writing each chunk immediately — the same "constant memory footprint" idea as
 * {@link com.github.trfiles.management.io.streamers.toStream.FromStreamToStream}, applied to a source that
 * is remote and only reachable over the network instead of local.
 * <p>
 * This is the only class in the {@code downloaders} package that actually opens a network connection, and
 * therefore the only one that needs to defend against a hostile or misbehaving remote endpoint. Two
 * distinct classes of attack are mitigated, mirroring the two mitigations {@code ZIStoPathUnzipper} applies
 * against a hostile ZIP archive:
 * <ul>
 *     <li><b>Disallowed scheme / SSRF via redirect</b>: {@code source}'s scheme (and the scheme of every
 *     redirect hop, if {@link URIStreamDownloaderConfiguration#isFollowRedirects() followRedirects} is on)
 *     is checked against {@link URIStreamDownloaderConfiguration#getAllowedSchemes()} <b>before</b> a
 *     connection is opened for it — {@code http}/{@code https} only by default, rejecting {@code file://},
 *     {@code jar:}, or any other scheme that could otherwise be abused to read local resources instead of
 *     performing a genuine network download. Redirects are followed manually (not via
 *     {@link HttpURLConnection#setInstanceFollowRedirects}) precisely so each hop can be re-validated and
 *     the hop count can be capped by {@link URIStreamDownloaderConfiguration#getMaxRedirects()}.</li>
 *     <li><b>Download bombs</b>: a server can advertise (or simply send) an arbitrarily large response
 *     body. {@link URIStreamDownloaderConfiguration#getMaxDownloadSize()} exposes a configurable ceiling;
 *     the check is performed against the number of bytes <b>actually read</b> from the response stream, not
 *     against the (server-controlled and therefore untrustworthy) {@code Content-Length} header — see
 *     {@link com.github.trfiles.management.io.downloaders.InstanceDownloader} class docs for why.</li>
 * </ul>
 */
public class URItoStreamDownloader extends StreamDownloader<URI> {
    private URIStreamDownloaderConfiguration configuration;

    private URItoStreamDownloader(URI source, OutputStream destination, URIStreamDownloaderConfiguration configuration) {
        super(source, destination);
        this.configuration = configuration != null ? configuration : new URIStreamDownloaderConfiguration();
    }

    public static URItoStreamDownloader newInstance(URI source, OutputStream destination) {
        return new URItoStreamDownloader(source, destination, new URIStreamDownloaderConfiguration());
    }

    public static URItoStreamDownloader newInstance(URI source, OutputStream destination, URIStreamDownloaderConfiguration configuration) {
        return new URItoStreamDownloader(source, destination, configuration);
    }

    @Override
    public URIStreamDownloaderConfiguration getConfiguration() {
        return configuration;
    }

    public URItoStreamDownloader withConfiguration(URIStreamDownloaderConfiguration configuration) {
        this.configuration = configuration;
        return this;
    }

    @Override
    protected void downloadToStream(URI source, OutputStream destination, long offset, long length) throws Exception {
        URIStreamDownloaderConfiguration config = getConfiguration();

        URI current = Preconditions.simpleParameterNotNull(source, "source");
        int redirects = 0;

        while (true) {
            checkAllowedScheme(current, config);

            URLConnection connection = current.toURL().openConnection();
            connection.setConnectTimeout(config.getConnectTimeoutMillis());
            connection.setReadTimeout(config.getReadTimeoutMillis());

            if (connection instanceof HttpURLConnection http) {
                // redirects are followed manually below, one hop at a time, so that the scheme of every
                // hop can be re-validated and the hop count can be capped.
                http.setInstanceFollowRedirects(false);

                int status = http.getResponseCode();
                if (config.isFollowRedirects() && isRedirect(status)) {
                    String location = http.getHeaderField("Location");
                    http.disconnect();
                    if (location == null || location.isBlank()) {
                        throw new IOException("Redirect response (HTTP " + status + ") from " + current + " did not include a \"Location\" header.");
                    }
                    if (++redirects > config.getMaxRedirects()) {
                        throw new IOException("Refusing to follow redirect from " + current + ": more than " +
                                config.getMaxRedirects() + " redirects were encountered (possible redirect loop).");
                    }
                    current = current.resolve(location);
                    continue;
                }
                if (status >= 400) {
                    throw new IOException("Download failed: " + current + " responded with HTTP " + status + ".");
                }

                try (InputStream in = http.getInputStream()) {
                    copy(in, destination, offset, length, config);
                } finally {
                    http.disconnect();
                }
                return;
            }

            try (InputStream in = connection.getInputStream()) {
                copy(in, destination, offset, length, config);
            }
            return;
        }
    }

    private static boolean isRedirect(int status) {
        return status == HttpURLConnection.HTTP_MOVED_PERM
                || status == HttpURLConnection.HTTP_MOVED_TEMP
                || status == HttpURLConnection.HTTP_SEE_OTHER
                || status == 307 // Temporary Redirect
                || status == 308; // Permanent Redirect
    }

    private void checkAllowedScheme(URI uri, URIStreamDownloaderConfiguration config) throws IOException {
        String scheme = uri.getScheme();
        if (scheme == null || !config.getAllowedSchemes().contains(scheme.toLowerCase(Locale.ROOT))) {
            throw new IOException("Refusing to download from \"" + uri + "\": scheme \"" + scheme +
                    "\" is not in the allowed set " + config.getAllowedSchemes() + ".");
        }
    }

    /**
     * Copies {@code in} into {@code destination} a chunk at a time, skipping the first {@code offset}
     * bytes and stopping either once {@code length} (exclusive end index, consistently with
     * {@code Streamer}'s semantics) is reached or {@code in} is exhausted (whichever comes first for a
     * {@code length} of {@link com.github.trfiles.management.io.downloaders.Downloader#UNBOUNDED}), while
     * counting the bytes actually read against {@link URIStreamDownloaderConfiguration#getMaxDownloadSize()}.
     */
    private void copy(InputStream in, OutputStream destination, long offset, long length,
                       URIStreamDownloaderConfiguration config) throws IOException {
        long toSkip = offset;
        while (toSkip > 0) {
            long skipped = in.skip(toSkip);
            if (skipped <= 0) {
                throw new IOException("Cannot skip " + offset + " bytes of the response body: the stream ended prematurely.");
            }
            toSkip -= skipped;
        }

        long maxDownloadSize = config.getMaxDownloadSize();
        byte[] buffer = new byte[config.getBufferSize()];
        // "length" is an exclusive end index (consistent with Streamer's semantics), not a byte count:
        // the actual amount of bytes to copy is "length - offset". UNBOUNDED keeps the loop going until
        // the response is exhausted (subject to the maxDownloadSize check below).
        long remaining = length == UNBOUNDED ? UNBOUNDED : length - offset;
        long totalRead = 0L;
        int read;

        while (remaining == UNBOUNDED || remaining > 0) {
            int toRead = remaining == UNBOUNDED ? buffer.length : (int) Math.min(buffer.length, remaining);
            if ((read = in.read(buffer, 0, toRead)) == -1) {
                break;
            }

            totalRead += read;
            if (maxDownloadSize != URIStreamDownloaderConfiguration.NO_LIMIT && totalRead > maxDownloadSize) {
                throw new IOException("Refusing to download: the response body exceeds the configured limit of " +
                        maxDownloadSize + " bytes (possible download bomb / unbounded response).");
            }

            destination.write(buffer, 0, read);
            if (remaining != UNBOUNDED) {
                remaining -= read;
            }
        }
    }

    public static class URIStreamDownloaderConfiguration extends StreamConfiguration<URI> {
        /**
         * Sentinel value for {@link #getMaxDownloadSize()} meaning "no limit" — must be set explicitly,
         * it is never the default.
         */
        public static final long NO_LIMIT = -1L;

        public static final long DEFAULT_MAX_DOWNLOAD_SIZE = 1L << 30; // 1 GiB
        public static final int DEFAULT_CONNECT_TIMEOUT_MILLIS = 10_000;
        public static final int DEFAULT_READ_TIMEOUT_MILLIS = 30_000;
        public static final int DEFAULT_MAX_REDIRECTS = 5;

        private Set<String> allowedSchemes = defaultAllowedSchemes();
        private long maxDownloadSize = DEFAULT_MAX_DOWNLOAD_SIZE;
        private int connectTimeoutMillis = DEFAULT_CONNECT_TIMEOUT_MILLIS;
        private int readTimeoutMillis = DEFAULT_READ_TIMEOUT_MILLIS;
        private boolean followRedirects = true;
        private int maxRedirects = DEFAULT_MAX_REDIRECTS;

        private static Set<String> defaultAllowedSchemes() {
            Set<String> schemes = new HashSet<>();
            schemes.add("http");
            schemes.add("https");
            return schemes;
        }

        public Set<String> getAllowedSchemes() {
            return Collections.unmodifiableSet(allowedSchemes != null ? allowedSchemes : defaultAllowedSchemes());
        }

        public URIStreamDownloaderConfiguration withAllowedSchemes(Set<String> allowedSchemes) {
            Set<String> normalized = new HashSet<>();
            for (String scheme : Preconditions.simpleParameterNotNull(allowedSchemes, "allowedSchemes")) {
                normalized.add(scheme.toLowerCase(Locale.ROOT));
            }
            this.allowedSchemes = normalized;
            return this;
        }

        public long getMaxDownloadSize() {
            return maxDownloadSize;
        }

        /**
         * Sets the maximum amount of bytes (counted as they are actually read from the response, never
         * from a {@code Content-Length} header) that may be downloaded before the download is aborted with
         * an {@link IOException}. Use {@link #NO_LIMIT} to disable the check entirely — this must be an
         * explicit, deliberate choice, it is not the default.
         */
        public URIStreamDownloaderConfiguration withMaxDownloadSize(long maxDownloadSize) {
            this.maxDownloadSize = maxDownloadSize;
            return this;
        }

        public int getConnectTimeoutMillis() {
            return connectTimeoutMillis;
        }

        public URIStreamDownloaderConfiguration withConnectTimeoutMillis(int connectTimeoutMillis) {
            this.connectTimeoutMillis = connectTimeoutMillis;
            return this;
        }

        public int getReadTimeoutMillis() {
            return readTimeoutMillis;
        }

        public URIStreamDownloaderConfiguration withReadTimeoutMillis(int readTimeoutMillis) {
            this.readTimeoutMillis = readTimeoutMillis;
            return this;
        }

        public boolean isFollowRedirects() {
            return followRedirects;
        }

        public URIStreamDownloaderConfiguration setFollowRedirects(boolean followRedirects) {
            this.followRedirects = followRedirects;
            return this;
        }

        public URIStreamDownloaderConfiguration followRedirects() {
            return setFollowRedirects(true);
        }

        public URIStreamDownloaderConfiguration notFollowRedirects() {
            return setFollowRedirects(false);
        }

        public int getMaxRedirects() {
            return maxRedirects;
        }

        public URIStreamDownloaderConfiguration withMaxRedirects(int maxRedirects) {
            this.maxRedirects = maxRedirects;
            return this;
        }
    }
}
