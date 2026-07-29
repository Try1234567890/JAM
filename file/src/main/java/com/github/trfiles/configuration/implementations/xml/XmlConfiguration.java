package com.github.trfiles.configuration.implementations.xml;

import com.github.trfiles.configuration.FileConfiguration;
import com.github.trfiles.configuration.FileConfigurationOptions;
import com.github.trfiles.configuration.PrefixedCommentParser;
import com.github.trfiles.configuration.comments.Comment;
import com.github.trfiles.configuration.registry.ConfigurationEntry;
import com.github.trfiles.exceptions.InvalidConfigurationException;
import com.github.trfiles.management.FileExtension;
import com.github.utilities.validators.Preconditions;
import tools.jackson.core.FormatSchema;
import tools.jackson.dataformat.xml.XmlMapper;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class XmlConfiguration extends FileConfiguration {
    private static final String COMMENT_PREFIX = "#";
    public static final XmlEntry ENTRY = XmlEntry.getInstance();
    private final XmlMapper mapper;

    protected XmlConfiguration(Path path, XmlConfigurationOptions options) {
        super(path, Preconditions.simpleNotNull(options, new XmlConfigurationOptions()));
        this.mapper = options.buildMapper();
    }

    protected XmlConfiguration(Path path) {
        this(path, new XmlConfigurationOptions());
    }

    @Override
    protected void parseContent(String content) throws InvalidConfigurationException {
        try {
            Map<?, ?> map = mapper
                    .readerFor(Map.class)
                    .withRootName(getOptions().ROOT_ELEMENT_NAME.get())
                    .readValue(content);
            withValues(toSections.convert(map));
        } catch (Throwable throwable) {
            throw new InvalidConfigurationException("An error occurs while loading XML configuration from " + getPath(), throwable);
        }
    }

    @Override
    protected String dumpContent() {
        try {
            Map<String, Object> map = toMaps.convert();
            return mapper.writer().writeValueAsString(map);
        } catch (Throwable e) {
            throw new RuntimeException("An error occurs while dumping the XML configuration.", e);
        }
    }

    @Override
    protected List<Comment> parseComments(String content) {
        return new PrefixedCommentParser(COMMENT_PREFIX).parse(content);
    }

    @Override
    public ConfigurationEntry getEntry() {
        return ENTRY;
    }

    @Override
    public XmlConfigurationOptions getOptions() {
        return (XmlConfigurationOptions) super.getOptions();
    }

    public static final class XmlEntry implements ConfigurationEntry {
        private XmlEntry() {
        }

        private record Holder() {
            private static final XmlEntry INSTANCE = new XmlEntry();
        }

        public static XmlEntry getInstance() {
            return Holder.INSTANCE;
        }

        @Override
        public FileConfiguration newInstance(Path path) {
            return new XmlConfiguration(path);
        }

        @Override
        public Set<FileExtension> extensions() {
            return Set.of(new FileExtension("xml"));
        }

        @Override
        public String getEmpty() {
            return "<config></config>";
        }
    }
}
