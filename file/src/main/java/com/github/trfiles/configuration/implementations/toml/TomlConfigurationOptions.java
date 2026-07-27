package com.github.trfiles.configuration.implementations.toml;

import com.github.trfiles.configuration.FileConfigurationOptions;
import com.github.utilities.options.BooleanOption;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.MapperFeature;
import tools.jackson.databind.SerializationFeature;
import tools.jackson.dataformat.toml.TomlMapper;
import tools.jackson.dataformat.toml.TomlReadFeature;
import tools.jackson.dataformat.toml.TomlWriteFeature;

public class TomlConfigurationOptions extends FileConfigurationOptions {
    public final BooleanOption PARSE_JAVA_TIME = new BooleanOption(false);
    public final BooleanOption FAIL_ON_NULL_WRITE = new BooleanOption(false);
    public final BooleanOption USE_BIG_DECIMAL_FOR_FLOATS = new BooleanOption(false);
    public final BooleanOption USE_BIG_INTEGER_FOR_INTS = new BooleanOption(false);
    public final BooleanOption USE_LONG_FOR_INTS = new BooleanOption(false);
    public final BooleanOption USE_JAVA_ARRAY_FOR_JSON_ARRAY = new BooleanOption(false);
    public final BooleanOption FAIL_ON_UNKNOWN_PROPERTIES = new BooleanOption(true);
    public final BooleanOption FAIL_ON_NULL_FOR_PRIMITIVES = new BooleanOption(false);
    public final BooleanOption FAIL_ON_INVALID_SUBTYPE = new BooleanOption(true);
    public final BooleanOption FAIL_ON_READING_DUP_TREE_KEY = new BooleanOption(false);
    public final BooleanOption FAIL_ON_IGNORED_PROPERTIES = new BooleanOption(false);
    public final BooleanOption FAIL_ON_UNRESOLVED_OBJECT_IDS = new BooleanOption(true);
    public final BooleanOption FAIL_ON_MISSING_CREATOR_PROPERTIES = new BooleanOption(false);
    public final BooleanOption FAIL_ON_NULL_CREATOR_PROPERTIES = new BooleanOption(false);
    public final BooleanOption FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY = new BooleanOption(true);
    public final BooleanOption FAIL_ON_TRAILING_TOKENS = new BooleanOption(false);
    public final BooleanOption FAIL_ON_UNEXPECTED_VIEW_PROPERTIES = new BooleanOption(false);
    public final BooleanOption ACCEPT_SINGLE_VALUE_AS_ARRAY = new BooleanOption(false);
    public final BooleanOption UNWRAP_SINGLE_VALUE_ARRAYS = new BooleanOption(false);
    public final BooleanOption UNWRAP_ROOT_VALUE = new BooleanOption(false);
    public final BooleanOption ACCEPT_EMPTY_STRING_AS_NULL_OBJECT = new BooleanOption(false);
    public final BooleanOption ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT = new BooleanOption(false);
    public final BooleanOption ACCEPT_FLOAT_AS_INT = new BooleanOption(true);
    public final BooleanOption EAGER_DESERIALIZER_FETCH = new BooleanOption(true);
    public final BooleanOption USE_ANNOTATIONS = new BooleanOption(true);
    public final BooleanOption USE_GETTERS_AS_SETTERS = new BooleanOption(true);
    public final BooleanOption PROPAGATE_TRANSIENT_MARKER = new BooleanOption(false);
    public final BooleanOption REQUIRE_SETTERS_FOR_GETTERS = new BooleanOption(false);
    public final BooleanOption ALLOW_FINAL_FIELDS_AS_MUTATORS = new BooleanOption(true);
    public final BooleanOption INFER_PROPERTY_MUTATORS = new BooleanOption(true);
    public final BooleanOption INFER_CREATOR_FROM_CONSTRUCTOR_PROPERTIES = new BooleanOption(true);
    public final BooleanOption ALLOW_VOID_VALUED_PROPERTIES = new BooleanOption(false);
    public final BooleanOption CAN_OVERRIDE_ACCESS_MODIFIERS = new BooleanOption(true);
    public final BooleanOption OVERRIDE_PUBLIC_ACCESS_MODIFIERS = new BooleanOption(true);
    public final BooleanOption USE_STATIC_TYPING = new BooleanOption(false);
    public final BooleanOption USE_BASE_TYPE_AS_DEFAULT_IMPL = new BooleanOption(false);
    public final BooleanOption INFER_BUILDER_TYPE_BINDINGS = new BooleanOption(true);
    public final BooleanOption REQUIRE_TYPE_ID_FOR_SUBTYPES = new BooleanOption(true);
    public final BooleanOption DEFAULT_VIEW_INCLUSION = new BooleanOption(true);
    public final BooleanOption SORT_PROPERTIES_ALPHABETICALLY = new BooleanOption(false);
    public final BooleanOption SORT_CREATOR_PROPERTIES_FIRST = new BooleanOption(true);
    public final BooleanOption ACCEPT_CASE_INSENSITIVE_PROPERTIES = new BooleanOption(false);
    public final BooleanOption ACCEPT_CASE_INSENSITIVE_ENUMS = new BooleanOption(false);
    public final BooleanOption ACCEPT_CASE_INSENSITIVE_VALUES = new BooleanOption(false);
    public final BooleanOption USE_WRAPPER_NAME_AS_PROPERTY_NAME = new BooleanOption(false);
    public final BooleanOption ALLOW_EXPLICIT_PROPERTY_RENAMING = new BooleanOption(false);
    public final BooleanOption ALLOW_IS_GETTERS_FOR_NON_BOOLEAN = new BooleanOption(false);
    public final BooleanOption ALLOW_COERCION_OF_SCALARS = new BooleanOption(true);
    public final BooleanOption IGNORE_MERGE_FOR_UNMERGEABLE = new BooleanOption(true);
    public final BooleanOption APPLY_DEFAULT_VALUES = new BooleanOption(true);
    public final BooleanOption WRAP_ROOT_VALUE = new BooleanOption(false);
    public final BooleanOption INDENT_OUTPUT = new BooleanOption(false);
    public final BooleanOption FAIL_ON_EMPTY_BEANS = new BooleanOption(true);
    public final BooleanOption FAIL_ON_SELF_REFERENCES = new BooleanOption(true);
    public final BooleanOption WRAP_EXCEPTIONS = new BooleanOption(true);
    public final BooleanOption FAIL_ON_UNWRAPPED_TYPE_IDENTIFIERS = new BooleanOption(true);
    public final BooleanOption WRITE_SELF_REFERENCES_AS_NULL = new BooleanOption(false);
    public final BooleanOption CLOSE_CLOSEABLE = new BooleanOption(false);
    public final BooleanOption FLUSH_AFTER_WRITE_VALUE = new BooleanOption(true);
    public final BooleanOption WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS = new BooleanOption(false);
    public final BooleanOption WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED = new BooleanOption(false);
    public final BooleanOption ORDER_MAP_ENTRIES_BY_KEYS = new BooleanOption(false);
    public final BooleanOption EAGER_SERIALIZER_FETCH = new BooleanOption(true);
    public final BooleanOption USE_EQUALITY_FOR_OBJECT_ID = new BooleanOption(false);

    public TomlConfigurationOptions setParseJavaTime(boolean state) {
        PARSE_JAVA_TIME.set(state);
        return this;
    }

    public TomlConfigurationOptions parseJavaTime() {
        return setParseJavaTime(true);
    }

    public TomlConfigurationOptions notParseJavaTime() {
        return setParseJavaTime(false);
    }

    public TomlConfigurationOptions setFailOnNullWrite(boolean state) {
        FAIL_ON_NULL_WRITE.set(state);
        return this;
    }

    public TomlConfigurationOptions failOnNullWrite() {
        return setFailOnNullWrite(true);
    }

    public TomlConfigurationOptions notFailOnNullWrite() {
        return setFailOnNullWrite(false);
    }

    public TomlConfigurationOptions setUseBigDecimalForFloats(boolean state) {
        USE_BIG_DECIMAL_FOR_FLOATS.set(state);
        return this;
    }

    public TomlConfigurationOptions useBigDecimalForFloats() {
        return setUseBigDecimalForFloats(true);
    }

    public TomlConfigurationOptions notUseBigDecimalForFloats() {
        return setUseBigDecimalForFloats(false);
    }

    public TomlConfigurationOptions setUseBigIntegerForInts(boolean state) {
        USE_BIG_INTEGER_FOR_INTS.set(state);
        return this;
    }

    public TomlConfigurationOptions useBigIntegerForInts() {
        return setUseBigIntegerForInts(true);
    }

    public TomlConfigurationOptions notUseBigIntegerForInts() {
        return setUseBigIntegerForInts(false);
    }

    public TomlConfigurationOptions setUseLongForInts(boolean state) {
        USE_LONG_FOR_INTS.set(state);
        return this;
    }

    public TomlConfigurationOptions useLongForInts() {
        return setUseLongForInts(true);
    }

    public TomlConfigurationOptions notUseLongForInts() {
        return setUseLongForInts(false);
    }

    public TomlConfigurationOptions setUseJavaArrayForJsonArray(boolean state) {
        USE_JAVA_ARRAY_FOR_JSON_ARRAY.set(state);
        return this;
    }

    public TomlConfigurationOptions useJavaArrayForJsonArray() {
        return setUseJavaArrayForJsonArray(true);
    }

    public TomlConfigurationOptions notUseJavaArrayForJsonArray() {
        return setUseJavaArrayForJsonArray(false);
    }

    public TomlConfigurationOptions setFailOnUnknownProperties(boolean state) {
        FAIL_ON_UNKNOWN_PROPERTIES.set(state);
        return this;
    }

    public TomlConfigurationOptions failOnUnknownProperties() {
        return setFailOnUnknownProperties(true);
    }

    public TomlConfigurationOptions notFailOnUnknownProperties() {
        return setFailOnUnknownProperties(false);
    }

    public TomlConfigurationOptions setFailOnNullForPrimitives(boolean state) {
        FAIL_ON_NULL_FOR_PRIMITIVES.set(state);
        return this;
    }

    public TomlConfigurationOptions failOnNullForPrimitives() {
        return setFailOnNullForPrimitives(true);
    }

    public TomlConfigurationOptions notFailOnNullForPrimitives() {
        return setFailOnNullForPrimitives(false);
    }

    public TomlConfigurationOptions setFailOnInvalidSubtype(boolean state) {
        FAIL_ON_INVALID_SUBTYPE.set(state);
        return this;
    }

    public TomlConfigurationOptions failOnInvalidSubtype() {
        return setFailOnInvalidSubtype(true);
    }

    public TomlConfigurationOptions notFailOnInvalidSubtype() {
        return setFailOnInvalidSubtype(false);
    }

    public TomlConfigurationOptions setFailOnReadingDupTreeKey(boolean state) {
        FAIL_ON_READING_DUP_TREE_KEY.set(state);
        return this;
    }

    public TomlConfigurationOptions failOnReadingDupTreeKey() {
        return setFailOnReadingDupTreeKey(true);
    }

    public TomlConfigurationOptions notFailOnReadingDupTreeKey() {
        return setFailOnReadingDupTreeKey(false);
    }

    public TomlConfigurationOptions setFailOnIgnoredProperties(boolean state) {
        FAIL_ON_IGNORED_PROPERTIES.set(state);
        return this;
    }

    public TomlConfigurationOptions failOnIgnoredProperties() {
        return setFailOnIgnoredProperties(true);
    }

    public TomlConfigurationOptions notFailOnIgnoredProperties() {
        return setFailOnIgnoredProperties(false);
    }

    public TomlConfigurationOptions setFailOnUnresolvedObjectIds(boolean state) {
        FAIL_ON_UNRESOLVED_OBJECT_IDS.set(state);
        return this;
    }

    public TomlConfigurationOptions failOnUnresolvedObjectIds() {
        return setFailOnUnresolvedObjectIds(true);
    }

    public TomlConfigurationOptions notFailOnUnresolvedObjectIds() {
        return setFailOnUnresolvedObjectIds(false);
    }

    public TomlConfigurationOptions setFailOnMissingCreatorProperties(boolean state) {
        FAIL_ON_MISSING_CREATOR_PROPERTIES.set(state);
        return this;
    }

    public TomlConfigurationOptions failOnMissingCreatorProperties() {
        return setFailOnMissingCreatorProperties(true);
    }

    public TomlConfigurationOptions notFailOnMissingCreatorProperties() {
        return setFailOnMissingCreatorProperties(false);
    }

    public TomlConfigurationOptions setFailOnNullCreatorProperties(boolean state) {
        FAIL_ON_NULL_CREATOR_PROPERTIES.set(state);
        return this;
    }

    public TomlConfigurationOptions failOnNullCreatorProperties() {
        return setFailOnNullCreatorProperties(true);
    }

    public TomlConfigurationOptions notFailOnNullCreatorProperties() {
        return setFailOnNullCreatorProperties(false);
    }

    public TomlConfigurationOptions setFailOnMissingExternalTypeIdProperty(boolean state) {
        FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY.set(state);
        return this;
    }

    public TomlConfigurationOptions failOnMissingExternalTypeIdProperty() {
        return setFailOnMissingExternalTypeIdProperty(true);
    }

    public TomlConfigurationOptions notFailOnMissingExternalTypeIdProperty() {
        return setFailOnMissingExternalTypeIdProperty(false);
    }

    public TomlConfigurationOptions setFailOnTrailingTokens(boolean state) {
        FAIL_ON_TRAILING_TOKENS.set(state);
        return this;
    }

    public TomlConfigurationOptions failOnTrailingTokens() {
        return setFailOnTrailingTokens(true);
    }

    public TomlConfigurationOptions notFailOnTrailingTokens() {
        return setFailOnTrailingTokens(false);
    }

    public TomlConfigurationOptions setFailOnUnexpectedViewProperties(boolean state) {
        FAIL_ON_UNEXPECTED_VIEW_PROPERTIES.set(state);
        return this;
    }

    public TomlConfigurationOptions failOnUnexpectedViewProperties() {
        return setFailOnUnexpectedViewProperties(true);
    }

    public TomlConfigurationOptions notFailOnUnexpectedViewProperties() {
        return setFailOnUnexpectedViewProperties(false);
    }

    public TomlConfigurationOptions setAcceptSingleValueAsArray(boolean state) {
        ACCEPT_SINGLE_VALUE_AS_ARRAY.set(state);
        return this;
    }

    public TomlConfigurationOptions acceptSingleValueAsArray() {
        return setAcceptSingleValueAsArray(true);
    }

    public TomlConfigurationOptions notAcceptSingleValueAsArray() {
        return setAcceptSingleValueAsArray(false);
    }

    public TomlConfigurationOptions setUnwrapSingleValueArrays(boolean state) {
        UNWRAP_SINGLE_VALUE_ARRAYS.set(state);
        return this;
    }

    public TomlConfigurationOptions unwrapSingleValueArrays() {
        return setUnwrapSingleValueArrays(true);
    }

    public TomlConfigurationOptions notUnwrapSingleValueArrays() {
        return setUnwrapSingleValueArrays(false);
    }

    public TomlConfigurationOptions setUnwrapRootValue(boolean state) {
        UNWRAP_ROOT_VALUE.set(state);
        return this;
    }

    public TomlConfigurationOptions unwrapRootValue() {
        return setUnwrapRootValue(true);
    }

    public TomlConfigurationOptions notUnwrapRootValue() {
        return setUnwrapRootValue(false);
    }

    public TomlConfigurationOptions setAcceptEmptyStringAsNullObject(boolean state) {
        ACCEPT_EMPTY_STRING_AS_NULL_OBJECT.set(state);
        return this;
    }

    public TomlConfigurationOptions acceptEmptyStringAsNullObject() {
        return setAcceptEmptyStringAsNullObject(true);
    }

    public TomlConfigurationOptions notAcceptEmptyStringAsNullObject() {
        return setAcceptEmptyStringAsNullObject(false);
    }

    public TomlConfigurationOptions setAcceptEmptyArrayAsNullObject(boolean state) {
        ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT.set(state);
        return this;
    }

    public TomlConfigurationOptions acceptEmptyArrayAsNullObject() {
        return setAcceptEmptyArrayAsNullObject(true);
    }

    public TomlConfigurationOptions notAcceptEmptyArrayAsNullObject() {
        return setAcceptEmptyArrayAsNullObject(false);
    }

    public TomlConfigurationOptions setAcceptFloatAsInt(boolean state) {
        ACCEPT_FLOAT_AS_INT.set(state);
        return this;
    }

    public TomlConfigurationOptions acceptFloatAsInt() {
        return setAcceptFloatAsInt(true);
    }

    public TomlConfigurationOptions notAcceptFloatAsInt() {
        return setAcceptFloatAsInt(false);
    }

    public TomlConfigurationOptions setEagerDeserializerFetch(boolean state) {
        EAGER_DESERIALIZER_FETCH.set(state);
        return this;
    }

    public TomlConfigurationOptions eagerDeserializerFetch() {
        return setEagerDeserializerFetch(true);
    }

    public TomlConfigurationOptions notEagerDeserializerFetch() {
        return setEagerDeserializerFetch(false);
    }

    public TomlConfigurationOptions setUseAnnotations(boolean state) {
        USE_ANNOTATIONS.set(state);
        return this;
    }

    public TomlConfigurationOptions useAnnotations() {
        return setUseAnnotations(true);
    }

    public TomlConfigurationOptions notUseAnnotations() {
        return setUseAnnotations(false);
    }

    public TomlConfigurationOptions setUseGettersAsSetters(boolean state) {
        USE_GETTERS_AS_SETTERS.set(state);
        return this;
    }

    public TomlConfigurationOptions useGettersAsSetters() {
        return setUseGettersAsSetters(true);
    }

    public TomlConfigurationOptions notUseGettersAsSetters() {
        return setUseGettersAsSetters(false);
    }

    public TomlConfigurationOptions setPropagateTransientMarker(boolean state) {
        PROPAGATE_TRANSIENT_MARKER.set(state);
        return this;
    }

    public TomlConfigurationOptions propagateTransientMarker() {
        return setPropagateTransientMarker(true);
    }

    public TomlConfigurationOptions notPropagateTransientMarker() {
        return setPropagateTransientMarker(false);
    }

    public TomlConfigurationOptions setRequireSettersForGetters(boolean state) {
        REQUIRE_SETTERS_FOR_GETTERS.set(state);
        return this;
    }

    public TomlConfigurationOptions requireSettersForGetters() {
        return setRequireSettersForGetters(true);
    }

    public TomlConfigurationOptions notRequireSettersForGetters() {
        return setRequireSettersForGetters(false);
    }

    public TomlConfigurationOptions setAllowFinalFieldsAsMutators(boolean state) {
        ALLOW_FINAL_FIELDS_AS_MUTATORS.set(state);
        return this;
    }

    public TomlConfigurationOptions allowFinalFieldsAsMutators() {
        return setAllowFinalFieldsAsMutators(true);
    }

    public TomlConfigurationOptions notAllowFinalFieldsAsMutators() {
        return setAllowFinalFieldsAsMutators(false);
    }

    public TomlConfigurationOptions setInferPropertyMutators(boolean state) {
        INFER_PROPERTY_MUTATORS.set(state);
        return this;
    }

    public TomlConfigurationOptions inferPropertyMutators() {
        return setInferPropertyMutators(true);
    }

    public TomlConfigurationOptions notInferPropertyMutators() {
        return setInferPropertyMutators(false);
    }

    public TomlConfigurationOptions setInferCreatorFromConstructorProperties(boolean state) {
        INFER_CREATOR_FROM_CONSTRUCTOR_PROPERTIES.set(state);
        return this;
    }

    public TomlConfigurationOptions inferCreatorFromConstructorProperties() {
        return setInferCreatorFromConstructorProperties(true);
    }

    public TomlConfigurationOptions notInferCreatorFromConstructorProperties() {
        return setInferCreatorFromConstructorProperties(false);
    }

    public TomlConfigurationOptions setAllowVoidValuedProperties(boolean state) {
        ALLOW_VOID_VALUED_PROPERTIES.set(state);
        return this;
    }

    public TomlConfigurationOptions allowVoidValuedProperties() {
        return setAllowVoidValuedProperties(true);
    }

    public TomlConfigurationOptions notAllowVoidValuedProperties() {
        return setAllowVoidValuedProperties(false);
    }

    public TomlConfigurationOptions setCanOverrideAccessModifiers(boolean state) {
        CAN_OVERRIDE_ACCESS_MODIFIERS.set(state);
        return this;
    }

    public TomlConfigurationOptions canOverrideAccessModifiers() {
        return setCanOverrideAccessModifiers(true);
    }

    public TomlConfigurationOptions notCanOverrideAccessModifiers() {
        return setCanOverrideAccessModifiers(false);
    }

    public TomlConfigurationOptions setOverridePublicAccessModifiers(boolean state) {
        OVERRIDE_PUBLIC_ACCESS_MODIFIERS.set(state);
        return this;
    }

    public TomlConfigurationOptions overridePublicAccessModifiers() {
        return setOverridePublicAccessModifiers(true);
    }

    public TomlConfigurationOptions notOverridePublicAccessModifiers() {
        return setOverridePublicAccessModifiers(false);
    }

    public TomlConfigurationOptions setUseStaticTyping(boolean state) {
        USE_STATIC_TYPING.set(state);
        return this;
    }

    public TomlConfigurationOptions useStaticTyping() {
        return setUseStaticTyping(true);
    }

    public TomlConfigurationOptions notUseStaticTyping() {
        return setUseStaticTyping(false);
    }

    public TomlConfigurationOptions setUseBaseTypeAsDefaultImpl(boolean state) {
        USE_BASE_TYPE_AS_DEFAULT_IMPL.set(state);
        return this;
    }

    public TomlConfigurationOptions useBaseTypeAsDefaultImpl() {
        return setUseBaseTypeAsDefaultImpl(true);
    }

    public TomlConfigurationOptions notUseBaseTypeAsDefaultImpl() {
        return setUseBaseTypeAsDefaultImpl(false);
    }

    public TomlConfigurationOptions setInferBuilderTypeBindings(boolean state) {
        INFER_BUILDER_TYPE_BINDINGS.set(state);
        return this;
    }

    public TomlConfigurationOptions inferBuilderTypeBindings() {
        return setInferBuilderTypeBindings(true);
    }

    public TomlConfigurationOptions notInferBuilderTypeBindings() {
        return setInferBuilderTypeBindings(false);
    }

    public TomlConfigurationOptions setRequireTypeIdForSubtypes(boolean state) {
        REQUIRE_TYPE_ID_FOR_SUBTYPES.set(state);
        return this;
    }

    public TomlConfigurationOptions requireTypeIdForSubtypes() {
        return setRequireTypeIdForSubtypes(true);
    }

    public TomlConfigurationOptions notRequireTypeIdForSubtypes() {
        return setRequireTypeIdForSubtypes(false);
    }

    public TomlConfigurationOptions setDefaultViewInclusion(boolean state) {
        DEFAULT_VIEW_INCLUSION.set(state);
        return this;
    }

    public TomlConfigurationOptions defaultViewInclusion() {
        return setDefaultViewInclusion(true);
    }

    public TomlConfigurationOptions notDefaultViewInclusion() {
        return setDefaultViewInclusion(false);
    }

    public TomlConfigurationOptions setSortPropertiesAlphabetically(boolean state) {
        SORT_PROPERTIES_ALPHABETICALLY.set(state);
        return this;
    }

    public TomlConfigurationOptions sortPropertiesAlphabetically() {
        return setSortPropertiesAlphabetically(true);
    }

    public TomlConfigurationOptions notSortPropertiesAlphabetically() {
        return setSortPropertiesAlphabetically(false);
    }

    public TomlConfigurationOptions setSortCreatorPropertiesFirst(boolean state) {
        SORT_CREATOR_PROPERTIES_FIRST.set(state);
        return this;
    }

    public TomlConfigurationOptions sortCreatorPropertiesFirst() {
        return setSortCreatorPropertiesFirst(true);
    }

    public TomlConfigurationOptions notSortCreatorPropertiesFirst() {
        return setSortCreatorPropertiesFirst(false);
    }

    public TomlConfigurationOptions setAcceptCaseInsensitiveProperties(boolean state) {
        ACCEPT_CASE_INSENSITIVE_PROPERTIES.set(state);
        return this;
    }

    public TomlConfigurationOptions acceptCaseInsensitiveProperties() {
        return setAcceptCaseInsensitiveProperties(true);
    }

    public TomlConfigurationOptions notAcceptCaseInsensitiveProperties() {
        return setAcceptCaseInsensitiveProperties(false);
    }

    public TomlConfigurationOptions setAcceptCaseInsensitiveEnums(boolean state) {
        ACCEPT_CASE_INSENSITIVE_ENUMS.set(state);
        return this;
    }

    public TomlConfigurationOptions acceptCaseInsensitiveEnums() {
        return setAcceptCaseInsensitiveEnums(true);
    }

    public TomlConfigurationOptions notAcceptCaseInsensitiveEnums() {
        return setAcceptCaseInsensitiveEnums(false);
    }

    public TomlConfigurationOptions setAcceptCaseInsensitiveValues(boolean state) {
        ACCEPT_CASE_INSENSITIVE_VALUES.set(state);
        return this;
    }

    public TomlConfigurationOptions acceptCaseInsensitiveValues() {
        return setAcceptCaseInsensitiveValues(true);
    }

    public TomlConfigurationOptions notAcceptCaseInsensitiveValues() {
        return setAcceptCaseInsensitiveValues(false);
    }

    public TomlConfigurationOptions setUseWrapperNameAsPropertyName(boolean state) {
        USE_WRAPPER_NAME_AS_PROPERTY_NAME.set(state);
        return this;
    }

    public TomlConfigurationOptions useWrapperNameAsPropertyName() {
        return setUseWrapperNameAsPropertyName(true);
    }

    public TomlConfigurationOptions notUseWrapperNameAsPropertyName() {
        return setUseWrapperNameAsPropertyName(false);
    }

    public TomlConfigurationOptions setAllowExplicitPropertyRenaming(boolean state) {
        ALLOW_EXPLICIT_PROPERTY_RENAMING.set(state);
        return this;
    }

    public TomlConfigurationOptions allowExplicitPropertyRenaming() {
        return setAllowExplicitPropertyRenaming(true);
    }

    public TomlConfigurationOptions notAllowExplicitPropertyRenaming() {
        return setAllowExplicitPropertyRenaming(false);
    }

    public TomlConfigurationOptions setAllowIsGettersForNonBoolean(boolean state) {
        ALLOW_IS_GETTERS_FOR_NON_BOOLEAN.set(state);
        return this;
    }

    public TomlConfigurationOptions allowIsGettersForNonBoolean() {
        return setAllowIsGettersForNonBoolean(true);
    }

    public TomlConfigurationOptions notAllowIsGettersForNonBoolean() {
        return setAllowIsGettersForNonBoolean(false);
    }

    public TomlConfigurationOptions setAllowCoercionOfScalars(boolean state) {
        ALLOW_COERCION_OF_SCALARS.set(state);
        return this;
    }

    public TomlConfigurationOptions allowCoercionOfScalars() {
        return setAllowCoercionOfScalars(true);
    }

    public TomlConfigurationOptions notAllowCoercionOfScalars() {
        return setAllowCoercionOfScalars(false);
    }

    public TomlConfigurationOptions setIgnoreMergeForUnmergeable(boolean state) {
        IGNORE_MERGE_FOR_UNMERGEABLE.set(state);
        return this;
    }

    public TomlConfigurationOptions ignoreMergeForUnmergeable() {
        return setIgnoreMergeForUnmergeable(true);
    }

    public TomlConfigurationOptions notIgnoreMergeForUnmergeable() {
        return setIgnoreMergeForUnmergeable(false);
    }

    public TomlConfigurationOptions setApplyDefaultValues(boolean state) {
        APPLY_DEFAULT_VALUES.set(state);
        return this;
    }

    public TomlConfigurationOptions applyDefaultValues() {
        return setApplyDefaultValues(true);
    }

    public TomlConfigurationOptions notApplyDefaultValues() {
        return setApplyDefaultValues(false);
    }

    public TomlConfigurationOptions setWrapRootValue(boolean state) {
        WRAP_ROOT_VALUE.set(state);
        return this;
    }

    public TomlConfigurationOptions wrapRootValue() {
        return setWrapRootValue(true);
    }

    public TomlConfigurationOptions notWrapRootValue() {
        return setWrapRootValue(false);
    }

    public TomlConfigurationOptions setIndentOutput(boolean state) {
        INDENT_OUTPUT.set(state);
        return this;
    }

    public TomlConfigurationOptions indentOutput() {
        return setIndentOutput(true);
    }

    public TomlConfigurationOptions notIndentOutput() {
        return setIndentOutput(false);
    }

    public TomlConfigurationOptions setFailOnEmptyBeans(boolean state) {
        FAIL_ON_EMPTY_BEANS.set(state);
        return this;
    }

    public TomlConfigurationOptions failOnEmptyBeans() {
        return setFailOnEmptyBeans(true);
    }

    public TomlConfigurationOptions notFailOnEmptyBeans() {
        return setFailOnEmptyBeans(false);
    }

    public TomlConfigurationOptions setFailOnSelfReferences(boolean state) {
        FAIL_ON_SELF_REFERENCES.set(state);
        return this;
    }

    public TomlConfigurationOptions failOnSelfReferences() {
        return setFailOnSelfReferences(true);
    }

    public TomlConfigurationOptions notFailOnSelfReferences() {
        return setFailOnSelfReferences(false);
    }

    public TomlConfigurationOptions setWrapExceptions(boolean state) {
        WRAP_EXCEPTIONS.set(state);
        return this;
    }

    public TomlConfigurationOptions wrapExceptions() {
        return setWrapExceptions(true);
    }

    public TomlConfigurationOptions notWrapExceptions() {
        return setWrapExceptions(false);
    }

    public TomlConfigurationOptions setFailOnUnwrappedTypeIdentifiers(boolean state) {
        FAIL_ON_UNWRAPPED_TYPE_IDENTIFIERS.set(state);
        return this;
    }

    public TomlConfigurationOptions failOnUnwrappedTypeIdentifiers() {
        return setFailOnUnwrappedTypeIdentifiers(true);
    }

    public TomlConfigurationOptions notFailOnUnwrappedTypeIdentifiers() {
        return setFailOnUnwrappedTypeIdentifiers(false);
    }

    public TomlConfigurationOptions setWriteSelfReferencesAsNull(boolean state) {
        WRITE_SELF_REFERENCES_AS_NULL.set(state);
        return this;
    }

    public TomlConfigurationOptions writeSelfReferencesAsNull() {
        return setWriteSelfReferencesAsNull(true);
    }

    public TomlConfigurationOptions notWriteSelfReferencesAsNull() {
        return setWriteSelfReferencesAsNull(false);
    }

    public TomlConfigurationOptions setCloseCloseable(boolean state) {
        CLOSE_CLOSEABLE.set(state);
        return this;
    }

    public TomlConfigurationOptions closeCloseable() {
        return setCloseCloseable(true);
    }

    public TomlConfigurationOptions notCloseCloseable() {
        return setCloseCloseable(false);
    }

    public TomlConfigurationOptions setFlushAfterWriteValue(boolean state) {
        FLUSH_AFTER_WRITE_VALUE.set(state);
        return this;
    }

    public TomlConfigurationOptions flushAfterWriteValue() {
        return setFlushAfterWriteValue(true);
    }

    public TomlConfigurationOptions notFlushAfterWriteValue() {
        return setFlushAfterWriteValue(false);
    }

    public TomlConfigurationOptions setWriteCharArraysAsJsonArrays(boolean state) {
        WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS.set(state);
        return this;
    }

    public TomlConfigurationOptions writeCharArraysAsJsonArrays() {
        return setWriteCharArraysAsJsonArrays(true);
    }

    public TomlConfigurationOptions notWriteCharArraysAsJsonArrays() {
        return setWriteCharArraysAsJsonArrays(false);
    }

    public TomlConfigurationOptions setWriteSingleElemArraysUnwrapped(boolean state) {
        WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED.set(state);
        return this;
    }

    public TomlConfigurationOptions writeSingleElemArraysUnwrapped() {
        return setWriteSingleElemArraysUnwrapped(true);
    }

    public TomlConfigurationOptions notWriteSingleElemArraysUnwrapped() {
        return setWriteSingleElemArraysUnwrapped(false);
    }

    public TomlConfigurationOptions setOrderMapEntriesByKeys(boolean state) {
        ORDER_MAP_ENTRIES_BY_KEYS.set(state);
        return this;
    }

    public TomlConfigurationOptions orderMapEntriesByKeys() {
        return setOrderMapEntriesByKeys(true);
    }

    public TomlConfigurationOptions notOrderMapEntriesByKeys() {
        return setOrderMapEntriesByKeys(false);
    }

    public TomlConfigurationOptions setEagerSerializerFetch(boolean state) {
        EAGER_SERIALIZER_FETCH.set(state);
        return this;
    }

    public TomlConfigurationOptions eagerSerializerFetch() {
        return setEagerSerializerFetch(true);
    }

    public TomlConfigurationOptions notEagerSerializerFetch() {
        return setEagerSerializerFetch(false);
    }

    public TomlConfigurationOptions setUseEqualityForObjectId(boolean state) {
        USE_EQUALITY_FOR_OBJECT_ID.set(state);
        return this;
    }

    public TomlConfigurationOptions useEqualityForObjectId() {
        return setUseEqualityForObjectId(true);
    }

    public TomlConfigurationOptions notUseEqualityForObjectId() {
        return setUseEqualityForObjectId(false);
    }

    public TomlConfigurationOptions setParseComment(boolean state) {
        PARSE_COMMENT.set(state);
        return this;
    }

    public TomlConfigurationOptions parseComment() {
        return setParseComment(true);
    }

    public TomlConfigurationOptions notParseComment() {
        return setParseComment(false);
    }

    public TomlConfigurationOptions setPerformFileValidation(boolean state) {
        FILE_VALIDATION.set(state);
        return this;
    }

    public TomlConfigurationOptions performFileValidation() {
        return setPerformFileValidation(true);
    }

    public TomlConfigurationOptions notPerformFileValidation() {
        return setPerformFileValidation(false);
    }

    TomlMapper buildToml() {
        return TomlMapper.builder()
                .configure(TomlReadFeature.PARSE_JAVA_TIME, PARSE_JAVA_TIME.get())
                .configure(TomlWriteFeature.FAIL_ON_NULL_WRITE, FAIL_ON_NULL_WRITE.get())
                .configure(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS, USE_BIG_DECIMAL_FOR_FLOATS.get())
                .configure(DeserializationFeature.USE_BIG_INTEGER_FOR_INTS, USE_BIG_INTEGER_FOR_INTS.get())
                .configure(DeserializationFeature.USE_LONG_FOR_INTS, USE_LONG_FOR_INTS.get())
                .configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, USE_JAVA_ARRAY_FOR_JSON_ARRAY.get())
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, FAIL_ON_UNKNOWN_PROPERTIES.get())
                .configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, FAIL_ON_NULL_FOR_PRIMITIVES.get())
                .configure(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE, FAIL_ON_INVALID_SUBTYPE.get())
                .configure(DeserializationFeature.FAIL_ON_READING_DUP_TREE_KEY, FAIL_ON_READING_DUP_TREE_KEY.get())
                .configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, FAIL_ON_IGNORED_PROPERTIES.get())
                .configure(DeserializationFeature.FAIL_ON_UNRESOLVED_OBJECT_IDS, FAIL_ON_UNRESOLVED_OBJECT_IDS.get())
                .configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, FAIL_ON_MISSING_CREATOR_PROPERTIES.get())
                .configure(DeserializationFeature.FAIL_ON_NULL_CREATOR_PROPERTIES, FAIL_ON_NULL_CREATOR_PROPERTIES.get())
                .configure(DeserializationFeature.FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY, FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY.get())
                .configure(DeserializationFeature.FAIL_ON_TRAILING_TOKENS, FAIL_ON_TRAILING_TOKENS.get())
                .configure(DeserializationFeature.FAIL_ON_UNEXPECTED_VIEW_PROPERTIES, FAIL_ON_UNEXPECTED_VIEW_PROPERTIES.get())
                .configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, ACCEPT_SINGLE_VALUE_AS_ARRAY.get())
                .configure(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS, UNWRAP_SINGLE_VALUE_ARRAYS.get())
                .configure(DeserializationFeature.UNWRAP_ROOT_VALUE, UNWRAP_ROOT_VALUE.get())
                .configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, ACCEPT_EMPTY_STRING_AS_NULL_OBJECT.get())
                .configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT.get())
                .configure(DeserializationFeature.ACCEPT_FLOAT_AS_INT, ACCEPT_FLOAT_AS_INT.get())
                .configure(DeserializationFeature.EAGER_DESERIALIZER_FETCH, EAGER_DESERIALIZER_FETCH.get())
                .configure(MapperFeature.USE_ANNOTATIONS, USE_ANNOTATIONS.get())
                .configure(MapperFeature.USE_GETTERS_AS_SETTERS, USE_GETTERS_AS_SETTERS.get())
                .configure(MapperFeature.PROPAGATE_TRANSIENT_MARKER, PROPAGATE_TRANSIENT_MARKER.get())
                .configure(MapperFeature.REQUIRE_SETTERS_FOR_GETTERS, REQUIRE_SETTERS_FOR_GETTERS.get())
                .configure(MapperFeature.ALLOW_FINAL_FIELDS_AS_MUTATORS, ALLOW_FINAL_FIELDS_AS_MUTATORS.get())
                .configure(MapperFeature.INFER_PROPERTY_MUTATORS, INFER_PROPERTY_MUTATORS.get())
                .configure(MapperFeature.INFER_CREATOR_FROM_CONSTRUCTOR_PROPERTIES, INFER_CREATOR_FROM_CONSTRUCTOR_PROPERTIES.get())
                .configure(MapperFeature.ALLOW_VOID_VALUED_PROPERTIES, ALLOW_VOID_VALUED_PROPERTIES.get())
                .configure(MapperFeature.CAN_OVERRIDE_ACCESS_MODIFIERS, CAN_OVERRIDE_ACCESS_MODIFIERS.get())
                .configure(MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS, OVERRIDE_PUBLIC_ACCESS_MODIFIERS.get())
                .configure(MapperFeature.USE_STATIC_TYPING, USE_STATIC_TYPING.get())
                .configure(MapperFeature.USE_BASE_TYPE_AS_DEFAULT_IMPL, USE_BASE_TYPE_AS_DEFAULT_IMPL.get())
                .configure(MapperFeature.INFER_BUILDER_TYPE_BINDINGS, INFER_BUILDER_TYPE_BINDINGS.get())
                .configure(MapperFeature.REQUIRE_TYPE_ID_FOR_SUBTYPES, REQUIRE_TYPE_ID_FOR_SUBTYPES.get())
                .configure(MapperFeature.DEFAULT_VIEW_INCLUSION, DEFAULT_VIEW_INCLUSION.get())
                .configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, SORT_PROPERTIES_ALPHABETICALLY.get())
                .configure(MapperFeature.SORT_CREATOR_PROPERTIES_FIRST, SORT_CREATOR_PROPERTIES_FIRST.get())
                .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, ACCEPT_CASE_INSENSITIVE_PROPERTIES.get())
                .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS, ACCEPT_CASE_INSENSITIVE_ENUMS.get())
                .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_VALUES, ACCEPT_CASE_INSENSITIVE_VALUES.get())
                .configure(MapperFeature.USE_WRAPPER_NAME_AS_PROPERTY_NAME, USE_WRAPPER_NAME_AS_PROPERTY_NAME.get())
                .configure(MapperFeature.ALLOW_EXPLICIT_PROPERTY_RENAMING, ALLOW_EXPLICIT_PROPERTY_RENAMING.get())
                .configure(MapperFeature.ALLOW_IS_GETTERS_FOR_NON_BOOLEAN, ALLOW_IS_GETTERS_FOR_NON_BOOLEAN.get())
                .configure(MapperFeature.ALLOW_COERCION_OF_SCALARS, ALLOW_COERCION_OF_SCALARS.get())
                .configure(MapperFeature.IGNORE_MERGE_FOR_UNMERGEABLE, IGNORE_MERGE_FOR_UNMERGEABLE.get())
                .configure(MapperFeature.APPLY_DEFAULT_VALUES, APPLY_DEFAULT_VALUES.get())
                .configure(SerializationFeature.WRAP_ROOT_VALUE, WRAP_ROOT_VALUE.get())
                .configure(SerializationFeature.INDENT_OUTPUT, INDENT_OUTPUT.get())
                .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, FAIL_ON_EMPTY_BEANS.get())
                .configure(SerializationFeature.FAIL_ON_SELF_REFERENCES, FAIL_ON_SELF_REFERENCES.get())
                .configure(SerializationFeature.WRAP_EXCEPTIONS, WRAP_EXCEPTIONS.get())
                .configure(SerializationFeature.FAIL_ON_UNWRAPPED_TYPE_IDENTIFIERS, FAIL_ON_UNWRAPPED_TYPE_IDENTIFIERS.get())
                .configure(SerializationFeature.WRITE_SELF_REFERENCES_AS_NULL, WRITE_SELF_REFERENCES_AS_NULL.get())
                .configure(SerializationFeature.CLOSE_CLOSEABLE, CLOSE_CLOSEABLE.get())
                .configure(SerializationFeature.FLUSH_AFTER_WRITE_VALUE, FLUSH_AFTER_WRITE_VALUE.get())
                .configure(SerializationFeature.WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS, WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS.get())
                .configure(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED, WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED.get())
                .configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, ORDER_MAP_ENTRIES_BY_KEYS.get())
                .configure(SerializationFeature.EAGER_SERIALIZER_FETCH, EAGER_SERIALIZER_FETCH.get())
                .configure(SerializationFeature.USE_EQUALITY_FOR_OBJECT_ID, USE_EQUALITY_FOR_OBJECT_ID.get())
                .build();
    }

}
