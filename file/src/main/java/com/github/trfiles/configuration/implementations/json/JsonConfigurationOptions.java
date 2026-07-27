package com.github.trfiles.configuration.implementations.json;

import com.github.trfiles.configuration.FileConfigurationOptions;
import com.github.utilities.options.*;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.List;
import java.util.Map;

public class JsonConfigurationOptions extends FileConfigurationOptions {
    public final ListOption<ExclusionStrategy> EXCLUSION_STRATEGIES = new ListOption<>();
    public final Option<FieldNamingStrategy> FIELD_NAMING_STRATEGY = new Option<>(FieldNamingPolicy.IDENTITY);
    public final MapOption<Type, InstanceCreator<?>> INSTANCE_CREATORS = new MapOption<>();
    public final BooleanOption SERIALIZE_NULLS = new BooleanOption(false);
    public final BooleanOption COMPLEX_MAP_KEY_SERIALIZATION = new BooleanOption(false);
    public final BooleanOption GENERATE_NON_EXECUTABLE_GSON = new BooleanOption(false);
    public final BooleanOption HTML_SAFE = new BooleanOption(true);
    public final Option<FormattingStyle> FORMATTING_STYLE = new Option<>(FormattingStyle.COMPACT);
    public final Option<Strictness> STRICTNESS = new Option<>(Strictness.LENIENT);
    public final BooleanOption SERIALIZE_SPECIAL_FLOATING_POINT_VALUES = new BooleanOption(false);
    public final BooleanOption USE_JDK_UNSAFE = new BooleanOption(false);
    public final Option<LongSerializationPolicy> LONG_SERIALIZATION_POLICY = new Option<>(LongSerializationPolicy.DEFAULT);
    public final StringOption DATE_PATTERN = new StringOption(null);
    public final IntegerOption DATE_STYLE = new IntegerOption(DateFormat.MEDIUM);
    public final IntegerOption TIME_STYLE = new IntegerOption(DateFormat.MEDIUM);
    public final ListOption<TypeAdapterFactory> BUILDER_FACTORIES = new ListOption<>();
    public final Option<ToNumberStrategy> OBJECT_TO_NUMBER_STRATEGY = new Option<>();
    public final Option<ToNumberStrategy> NUMBER_TO_NUMBER_STRATEGY = new Option<>();
    public final ListOption<ReflectionAccessFilter> REFLECTION_FILTERS = new ListOption<>();

    public JsonConfigurationOptions setExclusionStrategies(List<ExclusionStrategy> strategies) {
        EXCLUSION_STRATEGIES.set(strategies);
        return this;
    }

    public JsonConfigurationOptions newExclusionStrategy(ExclusionStrategy strategy) {
        EXCLUSION_STRATEGIES.add(strategy);
        return this;
    }


    public JsonConfigurationOptions setFieldNamingStrategy(FieldNamingStrategy strategy) {
        FIELD_NAMING_STRATEGY.set(strategy);
        return this;
    }

    public JsonConfigurationOptions setIdentityFieldNamingStrategy() {
        return setFieldNamingStrategy(FieldNamingPolicy.IDENTITY);
    }

    public JsonConfigurationOptions setUpperCamelCaseFieldNamingStrategy() {
        return setFieldNamingStrategy(FieldNamingPolicy.UPPER_CAMEL_CASE);
    }

    public JsonConfigurationOptions setUpperCamelCaseWithSpacesFieldNamingStrategy() {
        return setFieldNamingStrategy(FieldNamingPolicy.UPPER_CAMEL_CASE_WITH_SPACES);
    }

    public JsonConfigurationOptions setUpperCaseWithUnderscoresFieldNamingStrategy() {
        return setFieldNamingStrategy(FieldNamingPolicy.UPPER_CASE_WITH_UNDERSCORES);
    }

    public JsonConfigurationOptions setLowerCaseWithUnderscoresFieldNamingStrategy() {
        return setFieldNamingStrategy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
    }

    public JsonConfigurationOptions setLowerCaseWithDashesFieldNamingStrategy() {
        return setFieldNamingStrategy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES);
    }

    public JsonConfigurationOptions setLowerCaseWithDotsFieldNamingStrategy() {
        return setFieldNamingStrategy(FieldNamingPolicy.LOWER_CASE_WITH_DOTS);
    }

    public JsonConfigurationOptions setInstanceCreators(Map<Type, InstanceCreator<?>> creators) {
        INSTANCE_CREATORS.set(creators);
        return this;
    }

    public JsonConfigurationOptions newInstanceCreator(Type type, InstanceCreator<?> creator) {
        INSTANCE_CREATORS.put(type, creator);
        return this;
    }

    public JsonConfigurationOptions setSerializeNulls(boolean state) {
        SERIALIZE_NULLS.set(state);
        return this;
    }

    public JsonConfigurationOptions serializeNulls() {
        return setSerializeNulls(true);
    }

    public JsonConfigurationOptions notSerializeNulls() {
        return setSerializeNulls(false);
    }

    public JsonConfigurationOptions setComplexMapKeySerialization(boolean state) {
        COMPLEX_MAP_KEY_SERIALIZATION.set(state);
        return this;
    }

    public JsonConfigurationOptions complexMapKeySerialization() {
        return setComplexMapKeySerialization(true);
    }

    public JsonConfigurationOptions notComplexMapKeySerialization() {
        return setComplexMapKeySerialization(false);
    }

    public JsonConfigurationOptions setGenerateNonExecutableGson(boolean state) {
        GENERATE_NON_EXECUTABLE_GSON.set(state);
        return this;
    }

    public JsonConfigurationOptions generateNonExecutableGson() {
        return setGenerateNonExecutableGson(true);
    }

    public JsonConfigurationOptions notGenerateNonExecutableGson() {
        return setGenerateNonExecutableGson(false);
    }

    public JsonConfigurationOptions setHtmlSafe(boolean state) {
        HTML_SAFE.set(state);
        return this;
    }

    public JsonConfigurationOptions htmlSafe() {
        return setHtmlSafe(true);
    }

    public JsonConfigurationOptions notHtmlSafe() {
        return setHtmlSafe(false);
    }

    public JsonConfigurationOptions setFormattingStyle(FormattingStyle style) {
        FORMATTING_STYLE.set(style);
        return this;
    }

    public JsonConfigurationOptions setCompactFormattingStyle() {
        return setFormattingStyle(FormattingStyle.COMPACT);
    }

    public JsonConfigurationOptions setPrettyFormattingStyle() {
        return setFormattingStyle(FormattingStyle.PRETTY);
    }

    public JsonConfigurationOptions setStrictness(Strictness strictness) {
        STRICTNESS.set(strictness);
        return this;
    }

    public JsonConfigurationOptions setLenientStrictness() {
        return setStrictness(Strictness.LENIENT);
    }

    public JsonConfigurationOptions setLegacyStrictStrictness() {
        return setStrictness(Strictness.LEGACY_STRICT);
    }

    public JsonConfigurationOptions setStrictStrictness() {
        return setStrictness(Strictness.STRICT);
    }

    public JsonConfigurationOptions setSerializeSpecialFloatingPointValues(boolean state) {
        SERIALIZE_SPECIAL_FLOATING_POINT_VALUES.set(state);
        return this;
    }

    public JsonConfigurationOptions serializeSpecialFloatingPointValues() {
        return setSerializeSpecialFloatingPointValues(true);
    }

    public JsonConfigurationOptions notSerializeSpecialFloatingPointValues() {
        return setSerializeSpecialFloatingPointValues(false);
    }

    public JsonConfigurationOptions setUseJdkUnsafe(boolean state) {
        USE_JDK_UNSAFE.set(state);
        return this;
    }

    public JsonConfigurationOptions useJdkUnsafe() {
        return setUseJdkUnsafe(true);
    }

    public JsonConfigurationOptions notUseJdkUnsafe() {
        return setUseJdkUnsafe(false);
    }

    public JsonConfigurationOptions setLongSerializationPolicy(LongSerializationPolicy policy) {
        LONG_SERIALIZATION_POLICY.set(policy);
        return this;
    }

    public JsonConfigurationOptions setDefaultLongSerializationPolicy() {
        return setLongSerializationPolicy(LongSerializationPolicy.DEFAULT);
    }

    public JsonConfigurationOptions setStringLongSerializationPolicy() {
        return setLongSerializationPolicy(LongSerializationPolicy.STRING);
    }

    public JsonConfigurationOptions setDatePattern(String state) {
        DATE_PATTERN.set(state);
        return this;
    }

    public JsonConfigurationOptions setDateStyle(int state) {
        DATE_STYLE.set(state);
        return this;
    }

    public JsonConfigurationOptions setTimeStyle(int state) {
        TIME_STYLE.set(state);
        return this;
    }

    public JsonConfigurationOptions setBuilderFactories(List<TypeAdapterFactory> factories) {
        BUILDER_FACTORIES.set(factories);
        return this;
    }

    public JsonConfigurationOptions newBuilderFactory(TypeAdapterFactory factory) {
        BUILDER_FACTORIES.add(factory);
        return this;
    }

    public JsonConfigurationOptions setObjectToNumberStrategy(ToNumberStrategy strategy) {
        OBJECT_TO_NUMBER_STRATEGY.set(strategy);
        return this;
    }

    public JsonConfigurationOptions setNumberToNumberStrategy(ToNumberStrategy strategy) {
        NUMBER_TO_NUMBER_STRATEGY.set(strategy);
        return this;
    }

    public JsonConfigurationOptions setReflectionFilters(List<ReflectionAccessFilter> filters) {
        REFLECTION_FILTERS.set(filters);
        return this;
    }

    public JsonConfigurationOptions newReflectionFilter(ReflectionAccessFilter filter) {
        REFLECTION_FILTERS.add(filter);
        return this;
    }

    public JsonConfigurationOptions setParseComment(boolean state) {
        PARSE_COMMENT.set(state);
        return this;
    }

    public JsonConfigurationOptions parseComment() {
        return setParseComment(true);
    }

    public JsonConfigurationOptions notParseComment() {
        return setParseComment(false);
    }

    public JsonConfigurationOptions setPerformFileValidation(boolean state) {
        FILE_VALIDATION.set(state);
        return this;
    }

    public JsonConfigurationOptions performFileValidation() {
        return setPerformFileValidation(true);
    }

    public JsonConfigurationOptions notPerformFileValidation() {
        return setPerformFileValidation(false);
    }

    Gson buildGSON() {
        GsonBuilder builder = new GsonBuilder();

        if (EXCLUSION_STRATEGIES.hasValue())
            builder.setExclusionStrategies(EXCLUSION_STRATEGIES.get().toArray(ExclusionStrategy[]::new));
        if (FIELD_NAMING_STRATEGY.hasValue()) builder.setFieldNamingStrategy(FIELD_NAMING_STRATEGY.get());
        if (INSTANCE_CREATORS.hasValue()) {
            INSTANCE_CREATORS.get().forEach(builder::registerTypeAdapter);
        }
        if (SERIALIZE_NULLS.hasValue()) builder.serializeNulls();
        if (COMPLEX_MAP_KEY_SERIALIZATION.hasValue()) builder.enableComplexMapKeySerialization();
        if (GENERATE_NON_EXECUTABLE_GSON.hasValue()) builder.generateNonExecutableJson();
        if (HTML_SAFE.hasValue() && !HTML_SAFE.get()) builder.disableHtmlEscaping();
        if (FORMATTING_STYLE.hasValue()) {
            if (FORMATTING_STYLE.get() == FormattingStyle.PRETTY) builder.setPrettyPrinting();
        }
        if (STRICTNESS.hasValue()) {
            builder.setStrictness(STRICTNESS.get());
        }
        if (SERIALIZE_SPECIAL_FLOATING_POINT_VALUES.hasValue()) builder.serializeSpecialFloatingPointValues();
        if (!USE_JDK_UNSAFE.get()) builder.disableJdkUnsafe();
        if (LONG_SERIALIZATION_POLICY.hasValue()) builder.setLongSerializationPolicy(LONG_SERIALIZATION_POLICY.get());
        if (DATE_PATTERN.hasValue()) builder.setDateFormat(DATE_PATTERN.get());
        if (DATE_STYLE.hasValue() && TIME_STYLE.hasValue()) {
            builder.setDateFormat(DATE_STYLE.get(), TIME_STYLE.get());
        }
        if (BUILDER_FACTORIES.hasValue()) {
            BUILDER_FACTORIES.get().forEach(builder::registerTypeAdapterFactory);
        }
        if (OBJECT_TO_NUMBER_STRATEGY.hasValue()) builder.setObjectToNumberStrategy(OBJECT_TO_NUMBER_STRATEGY.get());
        if (NUMBER_TO_NUMBER_STRATEGY.hasValue()) builder.setNumberToNumberStrategy(NUMBER_TO_NUMBER_STRATEGY.get());
        if (REFLECTION_FILTERS.hasValue()) {
            REFLECTION_FILTERS.get().forEach(builder::addReflectionAccessFilter);
        }

        return builder.create();
    }
}
