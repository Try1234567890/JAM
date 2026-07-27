package com.github.trfiles.configuration.implementations.yaml;

import com.github.trfiles.configuration.FileConfigurationOptions;
import com.github.utilities.options.*;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.serializer.AnchorGenerator;
import org.yaml.snakeyaml.serializer.NumberAnchorGenerator;

import java.util.Map;
import java.util.TimeZone;

public class YamlConfigurationOptions extends FileConfigurationOptions {
    // Options for YAML Loading
    public final BooleanOption ALLOW_DUPLICATE_KEYS = new BooleanOption(true);
    public final BooleanOption ALLOW_RECURSIVE_KEYS = new BooleanOption(false);
    public final BooleanOption ENUM_CASE_SENSITIVE = new BooleanOption(false);
    public final BooleanOption MERGE_ON_COMPOSE = new BooleanOption(false);
    public final IntegerOption MAX_ALIASES_FOR_COLLECTION = new IntegerOption(50);
    public final IntegerOption NESTING_DEPTH_LIMIT = new IntegerOption(50);
    public final IntegerOption CODE_POINT_LIMIT = new IntegerOption(3 * 1024 * 1024); // 3 MB

    // Options for YAML Dumping
    public final EnumOption<DumperOptions.ScalarStyle> SCALAR_STYLE = new EnumOption<>(DumperOptions.ScalarStyle.PLAIN);
    public final EnumOption<DumperOptions.FlowStyle> FLOW_STYLE = new EnumOption<>(DumperOptions.FlowStyle.AUTO);
    public final BooleanOption CANONICAL = new BooleanOption(false);
    public final BooleanOption ALLOW_UNICODE = new BooleanOption(true);
    public final BooleanOption ALLOW_READ_ONLY_PROPERTIES = new BooleanOption(false);
    public final IntegerOption INTENT = new IntegerOption(2);
    public final IntegerOption INTENT_INDICATOR = new IntegerOption(0);
    public final BooleanOption INTENT_WITH_INDICATOR = new BooleanOption(false);
    public final IntegerOption WIDTH = new IntegerOption(50);
    public final BooleanOption SPLIT_LINES = new BooleanOption(true);
    public final EnumOption<DumperOptions.LineBreak> LINE_BREAK = new EnumOption<>(DumperOptions.LineBreak.UNIX);
    public final BooleanOption EXPLICIT_START = new BooleanOption(false);
    public final BooleanOption EXPLICIT_END = new BooleanOption(false);
    public final Option<TimeZone> TIME_ZONE = new Option<>(null);
    public final IntegerOption MAX_SIMPLE_KEY_LENGTH = new IntegerOption(128);
    public final EnumOption<DumperOptions.NonPrintableStyle> NON_PRINTABLE_STYLE = new EnumOption<>(DumperOptions.NonPrintableStyle.BINARY);
    public final EnumOption<DumperOptions.Version> VERSION = new EnumOption<>(null);
    public final MapOption<String, String> TAGS = new MapOption<>(null);
    public final BooleanOption PRETTY_FLOW = new BooleanOption(false);
    public final Option<AnchorGenerator> ANCHOR_GENERATOR = new Option<>(new NumberAnchorGenerator(0));
    public final BooleanOption DEREFERENCE_ALIASES = new BooleanOption(false);

    // ==========================================
    // Setters for Loading Options
    // ==========================================

    public YamlConfigurationOptions setAllowDuplicateKeys(boolean state) {
        ALLOW_DUPLICATE_KEYS.set(state);
        return this;
    }

    public YamlConfigurationOptions allowDuplicateKeys() {
        return setAllowDuplicateKeys(true);
    }

    public YamlConfigurationOptions notAllowDuplicateKeys() {
        return setAllowDuplicateKeys(false);
    }

    public YamlConfigurationOptions setAllowRecursiveKeys(boolean state) {
        ALLOW_RECURSIVE_KEYS.set(state);
        return this;
    }

    public YamlConfigurationOptions allowRecursiveKeys() {
        return setAllowRecursiveKeys(true);
    }

    public YamlConfigurationOptions notAllowRecursiveKeys() {
        return setAllowRecursiveKeys(false);
    }

    public YamlConfigurationOptions setEnumCaseSensitive(boolean state) {
        ENUM_CASE_SENSITIVE.set(state);
        return this;
    }

    public YamlConfigurationOptions enumCaseSensitive() {
        return setEnumCaseSensitive(true);
    }

    public YamlConfigurationOptions notEnumCaseSensitive() {
        return setEnumCaseSensitive(false);
    }

    public YamlConfigurationOptions setMergeOnCompose(boolean state) {
        MERGE_ON_COMPOSE.set(state);
        return this;
    }

    public YamlConfigurationOptions mergeOnCompose() {
        return setMergeOnCompose(true);
    }

    public YamlConfigurationOptions notMergeOnCompose() {
        return setMergeOnCompose(false);
    }

    public YamlConfigurationOptions setMaxAliasesForCollection(int limit) {
        MAX_ALIASES_FOR_COLLECTION.set(limit);
        return this;
    }

    public YamlConfigurationOptions setNestingDepthLimit(int limit) {
        NESTING_DEPTH_LIMIT.set(limit);
        return this;
    }

    public YamlConfigurationOptions setCodePointLimit(int limit) {
        CODE_POINT_LIMIT.set(limit);
        return this;
    }

    // ==========================================
    // Setters for Dumping Options
    // ==========================================

    public YamlConfigurationOptions setScalarStyle(DumperOptions.ScalarStyle style) {
        SCALAR_STYLE.set(style);
        return this;
    }

    public YamlConfigurationOptions setFlowStyle(DumperOptions.FlowStyle style) {
        FLOW_STYLE.set(style);
        return this;
    }

    public YamlConfigurationOptions setCanonical(boolean state) {
        CANONICAL.set(state);
        return this;
    }

    public YamlConfigurationOptions canonical() {
        return setCanonical(true);
    }

    public YamlConfigurationOptions notCanonical() {
        return setCanonical(false);
    }

    public YamlConfigurationOptions setAllowUnicode(boolean state) {
        ALLOW_UNICODE.set(state);
        return this;
    }

    public YamlConfigurationOptions allowUnicode() {
        return setAllowUnicode(true);
    }

    public YamlConfigurationOptions notAllowUnicode() {
        return setAllowUnicode(false);
    }

    public YamlConfigurationOptions setAllowReadOnlyProperties(boolean state) {
        ALLOW_READ_ONLY_PROPERTIES.set(state);
        return this;
    }

    public YamlConfigurationOptions allowReadOnlyProperties() {
        return setAllowReadOnlyProperties(true);
    }

    public YamlConfigurationOptions notAllowReadOnlyProperties() {
        return setAllowReadOnlyProperties(false);
    }

    public YamlConfigurationOptions setIntent(int indent) {
        INTENT.set(indent);
        return this;
    }

    public YamlConfigurationOptions setIntentIndicator(int indicatorIndent) {
        INTENT_INDICATOR.set(indicatorIndent);
        return this;
    }

    public YamlConfigurationOptions setIntentWithIndicator(boolean state) {
        INTENT_WITH_INDICATOR.set(state);
        return this;
    }

    public YamlConfigurationOptions intentWithIndicator() {
        return setIntentWithIndicator(true);
    }

    public YamlConfigurationOptions notIntentWithIndicator() {
        return setIntentWithIndicator(false);
    }

    public YamlConfigurationOptions setWidth(int width) {
        WIDTH.set(width);
        return this;
    }

    public YamlConfigurationOptions setSplitLines(boolean state) {
        SPLIT_LINES.set(state);
        return this;
    }

    public YamlConfigurationOptions splitLines() {
        return setSplitLines(true);
    }

    public YamlConfigurationOptions notSplitLines() {
        return setSplitLines(false);
    }

    public YamlConfigurationOptions setLineBreak(DumperOptions.LineBreak lineBreak) {
        LINE_BREAK.set(lineBreak);
        return this;
    }

    public YamlConfigurationOptions setExplicitStart(boolean state) {
        EXPLICIT_START.set(state);
        return this;
    }

    public YamlConfigurationOptions explicitStart() {
        return setExplicitStart(true);
    }

    public YamlConfigurationOptions notExplicitStart() {
        return setExplicitStart(false);
    }

    public YamlConfigurationOptions setExplicitEnd(boolean state) {
        EXPLICIT_END.set(state);
        return this;
    }

    public YamlConfigurationOptions explicitEnd() {
        return setExplicitEnd(true);
    }

    public YamlConfigurationOptions notExplicitEnd() {
        return setExplicitEnd(false);
    }

    public YamlConfigurationOptions setTimeZone(TimeZone timeZone) {
        TIME_ZONE.set(timeZone);
        return this;
    }

    public YamlConfigurationOptions setMaxSimpleKeyLength(int length) {
        MAX_SIMPLE_KEY_LENGTH.set(length);
        return this;
    }

    public YamlConfigurationOptions setNonPrintableStyle(DumperOptions.NonPrintableStyle style) {
        NON_PRINTABLE_STYLE.set(style);
        return this;
    }

    public YamlConfigurationOptions setVersion(DumperOptions.Version version) {
        VERSION.set(version);
        return this;
    }

    public YamlConfigurationOptions setTags(Map<String, String> tags) {
        TAGS.set(tags);
        return this;
    }

    public YamlConfigurationOptions setPrettyFlow(boolean state) {
        PRETTY_FLOW.set(state);
        return this;
    }

    public YamlConfigurationOptions prettyFlow() {
        return setPrettyFlow(true);
    }

    public YamlConfigurationOptions notPrettyFlow() {
        return setPrettyFlow(false);
    }

    public YamlConfigurationOptions setAnchorGenerator(AnchorGenerator anchorGenerator) {
        ANCHOR_GENERATOR.set(anchorGenerator);
        return this;
    }

    public YamlConfigurationOptions setDereferenceAliases(boolean state) {
        DEREFERENCE_ALIASES.set(state);
        return this;
    }

    public YamlConfigurationOptions dereferenceAliases() {
        return setDereferenceAliases(true);
    }

    public YamlConfigurationOptions notDereferenceAliases() {
        return setDereferenceAliases(false);
    }


    // ==========================================
    // Setters of Global Options
    // ==========================================

    public YamlConfigurationOptions setParseComment(boolean state) {
        PARSE_COMMENT.set(state);
        return this;
    }

    public YamlConfigurationOptions parseComment() {
        return setParseComment(true);
    }

    public YamlConfigurationOptions notParseComment() {
        return setParseComment(false);
    }

    public YamlConfigurationOptions setPerformFileValidation(boolean state) {
        FILE_VALIDATION.set(state);
        return this;
    }

    public YamlConfigurationOptions performFileValidation() {
        return setPerformFileValidation(true);
    }

    public YamlConfigurationOptions notPerformFileValidation() {
        return setPerformFileValidation(false);
    }


    // ==========================================
    // YAML Builders
    // ==========================================

    Yaml buildYAML() {
        LoaderOptions loaderOptions = buildLoaderOptions();
        DumperOptions dumperOptions = buildDumperOptions();
        return new Yaml(loaderOptions, dumperOptions);
    }

    private LoaderOptions buildLoaderOptions() {
        LoaderOptions loaderOptions = new LoaderOptions();
        loaderOptions.setAllowDuplicateKeys(ALLOW_DUPLICATE_KEYS.get());
        loaderOptions.setAllowRecursiveKeys(ALLOW_RECURSIVE_KEYS.get());
        loaderOptions.setEnumCaseSensitive(ENUM_CASE_SENSITIVE.get());
        loaderOptions.setMergeOnCompose(MERGE_ON_COMPOSE.get());
        loaderOptions.setMaxAliasesForCollections(MAX_ALIASES_FOR_COLLECTION.get());
        loaderOptions.setNestingDepthLimit(NESTING_DEPTH_LIMIT.get());
        loaderOptions.setCodePointLimit(CODE_POINT_LIMIT.get());
        return loaderOptions;
    }

    private DumperOptions buildDumperOptions() {
        DumperOptions dumperOptions = new DumperOptions();
        dumperOptions.setDefaultScalarStyle(SCALAR_STYLE.get());
        dumperOptions.setDefaultFlowStyle(FLOW_STYLE.get());
        dumperOptions.setCanonical(CANONICAL.get());
        dumperOptions.setAllowUnicode(ALLOW_UNICODE.get());
        dumperOptions.setAllowReadOnlyProperties(ALLOW_READ_ONLY_PROPERTIES.get());
        dumperOptions.setIndent(INTENT.get());
        dumperOptions.setIndicatorIndent(INTENT_INDICATOR.get());
        dumperOptions.setIndentWithIndicator(INTENT_WITH_INDICATOR.get());
        dumperOptions.setWidth(WIDTH.get());
        dumperOptions.setSplitLines(SPLIT_LINES.get());
        dumperOptions.setLineBreak(LINE_BREAK.get());
        dumperOptions.setExplicitStart(EXPLICIT_START.get());
        dumperOptions.setExplicitEnd(EXPLICIT_END.get());
        dumperOptions.setTimeZone(TIME_ZONE.get());
        dumperOptions.setMaxSimpleKeyLength(MAX_SIMPLE_KEY_LENGTH.get());
        dumperOptions.setNonPrintableStyle(NON_PRINTABLE_STYLE.get());
        dumperOptions.setVersion(VERSION.get());
        dumperOptions.setTags(TAGS.get());
        dumperOptions.setPrettyFlow(PRETTY_FLOW.get());
        dumperOptions.setAnchorGenerator(ANCHOR_GENERATOR.get());
        dumperOptions.setDereferenceAliases(DEREFERENCE_ALIASES.get());
        return dumperOptions;
    }
}