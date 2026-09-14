package com.github.trfiles.management.io.uploaders.fromStream;

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
 * Uploads an already opened {@link InputStream} onto a remote {@link URI}, reading the source a chunk at a
 * time and writing each chunk immediately to the request body — the same "constant memory footprint" idea
 * as {@link com.github.trfiles.management.io.streamers.toStream.FromStreamToStream}, applied to a
 * destination that is remote and only reachable over the network instead of local.
 * <p>
 * This is the only class in the {@code uploaders} package that actually opens a network connection, and
 * therefore the only one that needs to defend against a hostile or misconfigured destination. Mirroring
 * the mitigations {@code URItoStreamDownloader} applies on the read side:
 * <ul>
 *     <li><b>Disallowed scheme</b>: {@code destination}'s scheme is checked against
 *     {@link URIStreamUploaderConfiguration#getAllowedSchemes()} <b>before</b> a connection is opened —
 *     {@code http}/{@code https} only by default, rejecting {@code file://} or any other scheme that could
 *     otherwise be abused to write to a local resource instead of performing a genuine network upload.
 *     Unlike {@code URItoStreamDownloader}, redirects are <b>not</b> followed automatically here: an
 *     upload's request body comes from an {@link InputStream}, which cannot in general be re-read from the
 *     start once partially consumed, so transparently resending it to a redirect target would silently
 *     upload a truncated body. A redirect response is therefore surfaced as a plain {@link IOException} for
 *     the caller to handle explicitly (e.g. by restarting the upload against the new location with a fresh
 *     stream), instead of being retried automatically.</li>
 *     <li><b>Oversized uploads</b>: {@link URIStreamUploaderConfiguration#getMaxUploadSize()} exposes a
 *     configurable ceiling on how many bytes may be sent, checked against the number of bytes
 *     <b>actually read</b> from {@code source}. This is not a defense against a hostile destination (unlike
 *     the analogous check in {@code URItoStreamDownloader}, which defends against a hostile server): it is
 *     a safety limit against an unexpectedly huge or effectively unbounded local {@code source} (e.g. a
 *     stream piped from a live process) being sent by mistake. For this reason, unlike
 *     {@code getMaxDownloadSize()}, it defaults to {@link URIStreamUploaderConfiguration#NO_LIMIT}: an
 *     upload's source is trusted local data chosen by the caller, not attacker-controlled input.</li>
 * </ul>
 * When the amount of bytes to send is known upfront (i.e. {@code length != }{@link
 * com.github.trfiles.management.io.uploaders.Uploader#UNBOUNDED}), the connection is switched into
 * {@link HttpURLConnection#setFixedLengthStreamingMode(long)} instead of
 * {@link HttpURLConnection#setChunkedStreamingMode(int)}, letting the destination know the exact
 * {@code Content-Length} upfront rather than receiving a chunked transfer.
 */
public class StreamtoURIUploader extends StreamUploader<URI> {
    private URIStreamUploaderConfiguration configuration;

    private StreamtoURIUploader(InputStream source, URI destination, URIStreamUploaderConfiguration configuration) {
        super(source, destination);
        this.configuration = configuration != null ? configuration : new URIStreamUploaderConfiguration();
    }

    public static StreamtoURIUploader newInstance(InputStream source, URI destination) {
        return new StreamtoURIUploader(source, destination, new URIStreamUploaderConfiguration());
    }

    public static StreamtoURIUploader newInstance(InputStream source, URI destination, URIStreamUploaderConfiguration configuration) {
        return new StreamtoURIUploader(source, destination, configuration);
    }

    @Override
    public URIStreamUploaderConfiguration getConfiguration() {
        return configuration;
    }

    public StreamtoURIUploader withConfiguration(URIStreamUploaderConfiguration configuration) {
        this.configuration = configuration;
        return this;
    }

    @Override
    protected void uploadFromStream(InputStream source, URI destination, long offset, long length) throws Exception {
        URIStreamUploaderConfiguration config = getConfiguration();
        checkAllowedScheme(destination, config);

        URLConnection connection = destination.toURL().openConnection();
        connection.setConnectTimeout(config.getConnectTimeoutMillis());
        connection.setReadTimeout(config.getReadTimeoutMillis());
        connection.setDoOutput(true);

        if (!(connection instanceof HttpURLConnection http)) {
            throw new IOException("Refusing to upload to \"" + destination + "\": only HTTP(S) connections are supported.");
        }

        http.setRequestMethod(config.getMethod());
        if (config.getContentType() != null) {
            http.setRequestProperty("Content-Type", config.getContentType());
        }

        long declaredLength = length == UNBOUNDED ? -1L : length - offset;
        if (declaredLength >= 0) {
            http.setFixedLengthStreamingMode(declaredLength);
        } else {
            http.setChunkedStreamingMode(config.getBufferSize());
        }

        try {
            try (OutputStream out = http.getOutputStream()) {
                copy(source, out, offset, length, config);
            }

            int status = http.getResponseCode();
            if (isRedirect(status)) {
                throw new IOException("Upload to " + destination + " was redirected (HTTP " + status +
                        "): redirects are not followed automatically for uploads, since the request body " +
                        "may not be re-readable from the start. Retry against the target of the " +
                        "\"Location\" response header with a fresh source stream.");
            }
            if (status >= 400) {
                throw new IOException("Upload failed: " + destination + " responded with HTTP " + status + ".");
            }
        } finally {
            http.disconnect();
        }
    }

    private static boolean isRedirect(int status) {
        return status == HttpURLConnection.HTTP_MOVED_PERM
                || status == HttpURLConnection.HTTP_MOVED_TEMP
                || status == HttpURLConnection.HTTP_SEE_OTHER
                || status == 307 // Temporary Redirect
                || status == 308; // Permanent Redirect
    }

    private void checkAllowedScheme(URI uri, URIStreamUploaderConfiguration config) throws IOException {
        String scheme = uri.getScheme();
        if (scheme == null || !config.getAllowedSchemes().contains(scheme.toLowerCase(Locale.ROOT))) {
            throw new IOException("Refusing to upload to \"" + uri + "\": scheme \"" + scheme +
                    "\" is not in the allowed set " + config.getAllowedSchemes() + ".");
        }
    }

    /**
     * Copies {@code source} into the request body {@code destination} a chunk at a time, skipping the
     * first {@code offset} bytes and stopping either once {@code length} (exclusive end index,
     * consistently with {@code Streamer}'s semantics) is reached or {@code source} is exhausted (whichever
     * comes first for a {@code length} of {@link com.github.trfiles.management.io.uploaders.Uploader#UNBOUNDED}),
     * while counting the bytes actually read against {@link URIStreamUploaderConfiguration#getMaxUploadSize()}.
     */
    private void copy(InputStream source, OutputStream destination, long offset, long length,
                       URIStreamUploaderConfiguration config) throws IOException {
        long toSkip = offset;
        while (toSkip > 0) {
            long skipped = source.skip(toSkip);
            if (skipped <= 0) {
                throw new IOException("Cannot skip " + offset + " bytes of the source: the stream ended prematurely.");
            }
            toSkip -= skipped;
        }

        long maxUploadSize = config.getMaxUploadSize();
        byte[] buffer = new byte[config.getBufferSize()];
        // "length" is an exclusive end index (consistent with Streamer's semantics), not a byte count:
        // the actual amount of bytes to copy is "length - offset". UNBOUNDED keeps the loop going until
        // the source is exhausted (subject to the maxUploadSize check below).
        long remaining = length == UNBOUNDED ? UNBOUNDED : length - offset;
        long totalRead = 0L;
        int read;

        while (remaining == UNBOUNDED || remaining > 0) {
            int toRead = remaining == UNBOUNDED ? buffer.length : (int) Math.min(buffer.length, remaining);
            if ((read = source.read(buffer, 0, toRead)) == -1) {
                break;
            }

            totalRead += read;
            if (maxUploadSize != URIStreamUploaderConfiguration.NO_LIMIT && totalRead > maxUploadSize) {
                throw new IOException("Refusing to upload: the source exceeds the configured limit of " +
                        maxUploadSize + " bytes.");
            }

            destination.write(buffer, 0, read);
            if (remaining != UNBOUNDED) {
                remaining -= read;
            }
        }
    }

    public static class URIStreamUploaderConfiguration extends StreamConfiguration<URI> {
        /**
         * Sentinel value for {@link #getMaxUploadSize()} meaning "no limit". Unlike
         * {@code URIStreamDownloaderConfiguration#DEFAULT_MAX_DOWNLOAD_SIZE}, this IS the default here —
         * see the class docs on why an upload's source is not attacker-controlled the way a download's
         * response is.
         */
        public static final long NO_LIMIT = -1L;

        public static final long DEFAULT_MAX_UPLOAD_SIZE = NO_LIMIT;
        public static final int DEFAULT_CONNECT_TIMEOUT_MILLIS = 10_000;
        public static final int DEFAULT_READ_TIMEOUT_MILLIS = 30_000;
        public static final String DEFAULT_METHOD = "PUT";

        private Set<String> allowedSchemes = defaultAllowedSchemes();
        private long maxUploadSize = DEFAULT_MAX_UPLOAD_SIZE;
        private int connectTimeoutMillis = DEFAULT_CONNECT_TIMEOUT_MILLIS;
        private int readTimeoutMillis = DEFAULT_READ_TIMEOUT_MILLIS;
        private String method = DEFAULT_METHOD;
        private String contentType;

        private static Set<String> defaultAllowedSchemes() {
            Set<String> schemes = new HashSet<>();
            schemes.add("http");
            schemes.add("https");
            return schemes;
        }

        public Set<String> getAllowedSchemes() {
            return Collections.unmodifiableSet(allowedSchemes != null ? allowedSchemes : defaultAllowedSchemes());
        }

        public URIStreamUploaderConfiguration withAllowedSchemes(Set<String> allowedSchemes) {
            Set<String> normalized = new HashSet<>();
            for (String scheme : Preconditions.simpleParameterNotNull(allowedSchemes, "allowedSchemes")) {
                normalized.add(scheme.toLowerCase(Locale.ROOT));
            }
            this.allowedSchemes = normalized;
            return this;
        }

        public long getMaxUploadSize() {
            return maxUploadSize;
        }

        /**
         * Sets the maximum amount of bytes (counted as they are actually read from {@code source}) that
         * may be uploaded before the upload is aborted with an {@link IOException}. Defaults to
         * {@link #NO_LIMIT}: use this to guard against an unexpectedly huge or effectively unbounded local
         * source being uploaded by mistake.
         */
        public URIStreamUploaderConfiguration withMaxUploadSize(long maxUploadSize) {
            this.maxUploadSize = maxUploadSize;
            return this;
        }

        public int getConnectTimeoutMillis() {
            return connectTimeoutMillis;
        }

        public URIStreamUploaderConfiguration withConnectTimeoutMillis(int connectTimeoutMillis) {
            this.connectTimeoutMillis = connectTimeoutMillis;
            return this;
        }

        public int getReadTimeoutMillis() {
            return readTimeoutMillis;
        }

        public URIStreamUploaderConfiguration withReadTimeoutMillis(int readTimeoutMillis) {
            this.readTimeoutMillis = readTimeoutMillis;
            return this;
        }

        /**
         * The HTTP method used for the upload request. Defaults to {@code "PUT"} (idempotent, semantically
         * "store this representation at this URI") rather than {@code "POST"}; override with
         * {@link #withMethod(String)} if the destination expects otherwise.
         */
        public String getMethod() {
            return method;
        }

        public URIStreamUploaderConfiguration withMethod(String method) {
            this.method = Preconditions.simpleParameterNotNull(method, "method");
            return this;
        }

        public String getContentType() {
            return contentType;
        }

        public URIStreamUploaderConfiguration withContentType(String contentType) {
            this.contentType = contentType;
            return this;
        }
    }
}
