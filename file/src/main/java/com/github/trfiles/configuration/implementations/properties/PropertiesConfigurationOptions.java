package com.github.trfiles.configuration.implementations.properties;

import com.github.trfiles.configuration.FileConfigurationOptions;
import com.github.utilities.options.BooleanOption;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.MapperFeature;
import tools.jackson.databind.SerializationFeature;
import tools.jackson.dataformat.javaprop.JavaPropsMapper;

public class PropertiesConfigurationOptions extends FileConfigurationOptions {
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


    public PropertiesConfigurationOptions setUseBigDecimalForFloats(boolean state) {
        USE_BIG_DECIMAL_FOR_FLOATS.set(state);
        return this;
    }

    public PropertiesConfigurationOptions useBigDecimalForFloats() {
        return setUseBigDecimalForFloats(true);
    }

    public PropertiesConfigurationOptions notUseBigDecimalForFloats() {
        return setUseBigDecimalForFloats(false);
    }

    public PropertiesConfigurationOptions setUseBigIntegerForInts(boolean state) {
        USE_BIG_INTEGER_FOR_INTS.set(state);
        return this;
    }

    public PropertiesConfigurationOptions useBigIntegerForInts() {
        return setUseBigIntegerForInts(true);
    }

    public PropertiesConfigurationOptions notUseBigIntegerForInts() {
        return setUseBigIntegerForInts(false);
    }

    public PropertiesConfigurationOptions setUseLongForInts(boolean state) {
        USE_LONG_FOR_INTS.set(state);
        return this;
    }

    public PropertiesConfigurationOptions useLongForInts() {
        return setUseLongForInts(true);
    }

    public PropertiesConfigurationOptions notUseLongForInts() {
        return setUseLongForInts(false);
    }

    public PropertiesConfigurationOptions setUseJavaArrayForJsonArray(boolean state) {
        USE_JAVA_ARRAY_FOR_JSON_ARRAY.set(state);
        return this;
    }

    public PropertiesConfigurationOptions useJavaArrayForJsonArray() {
        return setUseJavaArrayForJsonArray(true);
    }

    public PropertiesConfigurationOptions notUseJavaArrayForJsonArray() {
        return setUseJavaArrayForJsonArray(false);
    }

    public PropertiesConfigurationOptions setFailOnUnknownProperties(boolean state) {
        FAIL_ON_UNKNOWN_PROPERTIES.set(state);
        return this;
    }

    public PropertiesConfigurationOptions failOnUnknownProperties() {
        return setFailOnUnknownProperties(true);
    }

    public PropertiesConfigurationOptions notFailOnUnknownProperties() {
        return setFailOnUnknownProperties(false);
    }

    public PropertiesConfigurationOptions setFailOnNullForPrimitives(boolean state) {
        FAIL_ON_NULL_FOR_PRIMITIVES.set(state);
        return this;
    }

    public PropertiesConfigurationOptions failOnNullForPrimitives() {
        return setFailOnNullForPrimitives(true);
    }

    public PropertiesConfigurationOptions notFailOnNullForPrimitives() {
        return setFailOnNullForPrimitives(false);
    }

    public PropertiesConfigurationOptions setFailOnInvalidSubtype(boolean state) {
        FAIL_ON_INVALID_SUBTYPE.set(state);
        return this;
    }

    public PropertiesConfigurationOptions failOnInvalidSubtype() {
        return setFailOnInvalidSubtype(true);
    }

    public PropertiesConfigurationOptions notFailOnInvalidSubtype() {
        return setFailOnInvalidSubtype(false);
    }

    public PropertiesConfigurationOptions setFailOnReadingDupTreeKey(boolean state) {
        FAIL_ON_READING_DUP_TREE_KEY.set(state);
        return this;
    }

    public PropertiesConfigurationOptions failOnReadingDupTreeKey() {
        return setFailOnReadingDupTreeKey(true);
    }

    public PropertiesConfigurationOptions notFailOnReadingDupTreeKey() {
        return setFailOnReadingDupTreeKey(false);
    }

    public PropertiesConfigurationOptions setFailOnIgnoredProperties(boolean state) {
        FAIL_ON_IGNORED_PROPERTIES.set(state);
        return this;
    }

    public PropertiesConfigurationOptions failOnIgnoredProperties() {
        return setFailOnIgnoredProperties(true);
    }

    public PropertiesConfigurationOptions notFailOnIgnoredProperties() {
        return setFailOnIgnoredProperties(false);
    }

    public PropertiesConfigurationOptions setFailOnUnresolvedObjectIds(boolean state) {
        FAIL_ON_UNRESOLVED_OBJECT_IDS.set(state);
        return this;
    }

    public PropertiesConfigurationOptions failOnUnresolvedObjectIds() {
        return setFailOnUnresolvedObjectIds(true);
    }

    public PropertiesConfigurationOptions notFailOnUnresolvedObjectIds() {
        return setFailOnUnresolvedObjectIds(false);
    }

    public PropertiesConfigurationOptions setFailOnMissingCreatorProperties(boolean state) {
        FAIL_ON_MISSING_CREATOR_PROPERTIES.set(state);
        return this;
    }

    public PropertiesConfigurationOptions failOnMissingCreatorProperties() {
        return setFailOnMissingCreatorProperties(true);
    }

    public PropertiesConfigurationOptions notFailOnMissingCreatorProperties() {
        return setFailOnMissingCreatorProperties(false);
    }

    public PropertiesConfigurationOptions setFailOnNullCreatorProperties(boolean state) {
        FAIL_ON_NULL_CREATOR_PROPERTIES.set(state);
        return this;
    }

    public PropertiesConfigurationOptions failOnNullCreatorProperties() {
        return setFailOnNullCreatorProperties(true);
    }

    public PropertiesConfigurationOptions notFailOnNullCreatorProperties() {
        return setFailOnNullCreatorProperties(false);
    }

    public PropertiesConfigurationOptions setFailOnMissingExternalTypeIdProperty(boolean state) {
        FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY.set(state);
        return this;
    }

    public PropertiesConfigurationOptions failOnMissingExternalTypeIdProperty() {
        return setFailOnMissingExternalTypeIdProperty(true);
    }

    public PropertiesConfigurationOptions notFailOnMissingExternalTypeIdProperty() {
        return setFailOnMissingExternalTypeIdProperty(false);
    }

    public PropertiesConfigurationOptions setFailOnTrailingTokens(boolean state) {
        FAIL_ON_TRAILING_TOKENS.set(state);
        return this;
    }

    public PropertiesConfigurationOptions failOnTrailingTokens() {
        return setFailOnTrailingTokens(true);
    }

    public PropertiesConfigurationOptions notFailOnTrailingTokens() {
        return setFailOnTrailingTokens(false);
    }

    public PropertiesConfigurationOptions setFailOnUnexpectedViewProperties(boolean state) {
        FAIL_ON_UNEXPECTED_VIEW_PROPERTIES.set(state);
        return this;
    }

    public PropertiesConfigurationOptions failOnUnexpectedViewProperties() {
        return setFailOnUnexpectedViewProperties(true);
    }

    public PropertiesConfigurationOptions notFailOnUnexpectedViewProperties() {
        return setFailOnUnexpectedViewProperties(false);
    }

    public PropertiesConfigurationOptions setAcceptSingleValueAsArray(boolean state) {
        ACCEPT_SINGLE_VALUE_AS_ARRAY.set(state);
        return this;
    }

    public PropertiesConfigurationOptions acceptSingleValueAsArray() {
        return setAcceptSingleValueAsArray(true);
    }

    public PropertiesConfigurationOptions notAcceptSingleValueAsArray() {
        return setAcceptSingleValueAsArray(false);
    }

    public PropertiesConfigurationOptions setUnwrapSingleValueArrays(boolean state) {
        UNWRAP_SINGLE_VALUE_ARRAYS.set(state);
        return this;
    }

    public PropertiesConfigurationOptions unwrapSingleValueArrays() {
        return setUnwrapSingleValueArrays(true);
    }

    public PropertiesConfigurationOptions notUnwrapSingleValueArrays() {
        return setUnwrapSingleValueArrays(false);
    }

    public PropertiesConfigurationOptions setUnwrapRootValue(boolean state) {
        UNWRAP_ROOT_VALUE.set(state);
        return this;
    }

    public PropertiesConfigurationOptions unwrapRootValue() {
        return setUnwrapRootValue(true);
    }

    public PropertiesConfigurationOptions notUnwrapRootValue() {
        return setUnwrapRootValue(false);
    }

    public PropertiesConfigurationOptions setAcceptEmptyStringAsNullObject(boolean state) {
        ACCEPT_EMPTY_STRING_AS_NULL_OBJECT.set(state);
        return this;
    }

    public PropertiesConfigurationOptions acceptEmptyStringAsNullObject() {
        return setAcceptEmptyStringAsNullObject(true);
    }

    public PropertiesConfigurationOptions notAcceptEmptyStringAsNullObject() {
        return setAcceptEmptyStringAsNullObject(false);
    }

    public PropertiesConfigurationOptions setAcceptEmptyArrayAsNullObject(boolean state) {
        ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT.set(state);
        return this;
    }

    public PropertiesConfigurationOptions acceptEmptyArrayAsNullObject() {
        return setAcceptEmptyArrayAsNullObject(true);
    }

    public PropertiesConfigurationOptions notAcceptEmptyArrayAsNullObject() {
        return setAcceptEmptyArrayAsNullObject(false);
    }

    public PropertiesConfigurationOptions setAcceptFloatAsInt(boolean state) {
        ACCEPT_FLOAT_AS_INT.set(state);
        return this;
    }

    public PropertiesConfigurationOptions acceptFloatAsInt() {
        return setAcceptFloatAsInt(true);
    }

    public PropertiesConfigurationOptions notAcceptFloatAsInt() {
        return setAcceptFloatAsInt(false);
    }

    public PropertiesConfigurationOptions setEagerDeserializerFetch(boolean state) {
        EAGER_DESERIALIZER_FETCH.set(state);
        return this;
    }

    public PropertiesConfigurationOptions eagerDeserializerFetch() {
        return setEagerDeserializerFetch(true);
    }

    public PropertiesConfigurationOptions notEagerDeserializerFetch() {
        return setEagerDeserializerFetch(false);
    }

    public PropertiesConfigurationOptions setUseAnnotations(boolean state) {
        USE_ANNOTATIONS.set(state);
        return this;
    }

    public PropertiesConfigurationOptions useAnnotations() {
        return setUseAnnotations(true);
    }

    public PropertiesConfigurationOptions notUseAnnotations() {
        return setUseAnnotations(false);
    }

    public PropertiesConfigurationOptions setUseGettersAsSetters(boolean state) {
        USE_GETTERS_AS_SETTERS.set(state);
        return this;
    }

    public PropertiesConfigurationOptions useGettersAsSetters() {
        return setUseGettersAsSetters(true);
    }

    public PropertiesConfigurationOptions notUseGettersAsSetters() {
        return setUseGettersAsSetters(false);
    }

    public PropertiesConfigurationOptions setPropagateTransientMarker(boolean state) {
        PROPAGATE_TRANSIENT_MARKER.set(state);
        return this;
    }

    public PropertiesConfigurationOptions propagateTransientMarker() {
        return setPropagateTransientMarker(true);
    }

    public PropertiesConfigurationOptions notPropagateTransientMarker() {
        return setPropagateTransientMarker(false);
    }

    public PropertiesConfigurationOptions setRequireSettersForGetters(boolean state) {
        REQUIRE_SETTERS_FOR_GETTERS.set(state);
        return this;
    }

    public PropertiesConfigurationOptions requireSettersForGetters() {
        return setRequireSettersForGetters(true);
    }

    public PropertiesConfigurationOptions notRequireSettersForGetters() {
        return setRequireSettersForGetters(false);
    }

    public PropertiesConfigurationOptions setAllowFinalFieldsAsMutators(boolean state) {
        ALLOW_FINAL_FIELDS_AS_MUTATORS.set(state);
        return this;
    }

    public PropertiesConfigurationOptions allowFinalFieldsAsMutators() {
        return setAllowFinalFieldsAsMutators(true);
    }

    public PropertiesConfigurationOptions notAllowFinalFieldsAsMutators() {
        return setAllowFinalFieldsAsMutators(false);
    }

    public PropertiesConfigurationOptions setInferPropertyMutators(boolean state) {
        INFER_PROPERTY_MUTATORS.set(state);
        return this;
    }

    public PropertiesConfigurationOptions inferPropertyMutators() {
        return setInferPropertyMutators(true);
    }

    public PropertiesConfigurationOptions notInferPropertyMutators() {
        return setInferPropertyMutators(false);
    }

    public PropertiesConfigurationOptions setInferCreatorFromConstructorProperties(boolean state) {
        INFER_CREATOR_FROM_CONSTRUCTOR_PROPERTIES.set(state);
        return this;
    }

    public PropertiesConfigurationOptions inferCreatorFromConstructorProperties() {
        return setInferCreatorFromConstructorProperties(true);
    }

    public PropertiesConfigurationOptions notInferCreatorFromConstructorProperties() {
        return setInferCreatorFromConstructorProperties(false);
    }

    public PropertiesConfigurationOptions setAllowVoidValuedProperties(boolean state) {
        ALLOW_VOID_VALUED_PROPERTIES.set(state);
        return this;
    }

    public PropertiesConfigurationOptions allowVoidValuedProperties() {
        return setAllowVoidValuedProperties(true);
    }

    public PropertiesConfigurationOptions notAllowVoidValuedProperties() {
        return setAllowVoidValuedProperties(false);
    }

    public PropertiesConfigurationOptions setCanOverrideAccessModifiers(boolean state) {
        CAN_OVERRIDE_ACCESS_MODIFIERS.set(state);
        return this;
    }

    public PropertiesConfigurationOptions canOverrideAccessModifiers() {
        return setCanOverrideAccessModifiers(true);
    }

    public PropertiesConfigurationOptions notCanOverrideAccessModifiers() {
        return setCanOverrideAccessModifiers(false);
    }

    public PropertiesConfigurationOptions setOverridePublicAccessModifiers(boolean state) {
        OVERRIDE_PUBLIC_ACCESS_MODIFIERS.set(state);
        return this;
    }

    public PropertiesConfigurationOptions overridePublicAccessModifiers() {
        return setOverridePublicAccessModifiers(true);
    }

    public PropertiesConfigurationOptions notOverridePublicAccessModifiers() {
        return setOverridePublicAccessModifiers(false);
    }

    public PropertiesConfigurationOptions setUseStaticTyping(boolean state) {
        USE_STATIC_TYPING.set(state);
        return this;
    }

    public PropertiesConfigurationOptions useStaticTyping() {
        return setUseStaticTyping(true);
    }

    public PropertiesConfigurationOptions notUseStaticTyping() {
        return setUseStaticTyping(false);
    }

    public PropertiesConfigurationOptions setUseBaseTypeAsDefaultImpl(boolean state) {
        USE_BASE_TYPE_AS_DEFAULT_IMPL.set(state);
        return this;
    }

    public PropertiesConfigurationOptions useBaseTypeAsDefaultImpl() {
        return setUseBaseTypeAsDefaultImpl(true);
    }

    public PropertiesConfigurationOptions notUseBaseTypeAsDefaultImpl() {
        return setUseBaseTypeAsDefaultImpl(false);
    }

    public PropertiesConfigurationOptions setInferBuilderTypeBindings(boolean state) {
        INFER_BUILDER_TYPE_BINDINGS.set(state);
        return this;
    }

    public PropertiesConfigurationOptions inferBuilderTypeBindings() {
        return setInferBuilderTypeBindings(true);
    }

    public PropertiesConfigurationOptions notInferBuilderTypeBindings() {
        return setInferBuilderTypeBindings(false);
    }

    public PropertiesConfigurationOptions setRequireTypeIdForSubtypes(boolean state) {
        REQUIRE_TYPE_ID_FOR_SUBTYPES.set(state);
        return this;
    }

    public PropertiesConfigurationOptions requireTypeIdForSubtypes() {
        return setRequireTypeIdForSubtypes(true);
    }

    public PropertiesConfigurationOptions notRequireTypeIdForSubtypes() {
        return setRequireTypeIdForSubtypes(false);
    }

    public PropertiesConfigurationOptions setDefaultViewInclusion(boolean state) {
        DEFAULT_VIEW_INCLUSION.set(state);
        return this;
    }

    public PropertiesConfigurationOptions defaultViewInclusion() {
        return setDefaultViewInclusion(true);
    }

    public PropertiesConfigurationOptions notDefaultViewInclusion() {
        return setDefaultViewInclusion(false);
    }

    public PropertiesConfigurationOptions setSortPropertiesAlphabetically(boolean state) {
        SORT_PROPERTIES_ALPHABETICALLY.set(state);
        return this;
    }

    public PropertiesConfigurationOptions sortPropertiesAlphabetically() {
        return setSortPropertiesAlphabetically(true);
    }

    public PropertiesConfigurationOptions notSortPropertiesAlphabetically() {
        return setSortPropertiesAlphabetically(false);
    }

    public PropertiesConfigurationOptions setSortCreatorPropertiesFirst(boolean state) {
        SORT_CREATOR_PROPERTIES_FIRST.set(state);
        return this;
    }

    public PropertiesConfigurationOptions sortCreatorPropertiesFirst() {
        return setSortCreatorPropertiesFirst(true);
    }

    public PropertiesConfigurationOptions notSortCreatorPropertiesFirst() {
        return setSortCreatorPropertiesFirst(false);
    }

    public PropertiesConfigurationOptions setAcceptCaseInsensitiveProperties(boolean state) {
        ACCEPT_CASE_INSENSITIVE_PROPERTIES.set(state);
        return this;
    }

    public PropertiesConfigurationOptions acceptCaseInsensitiveProperties() {
        return setAcceptCaseInsensitiveProperties(true);
    }

    public PropertiesConfigurationOptions notAcceptCaseInsensitiveProperties() {
        return setAcceptCaseInsensitiveProperties(false);
    }

    public PropertiesConfigurationOptions setAcceptCaseInsensitiveEnums(boolean state) {
        ACCEPT_CASE_INSENSITIVE_ENUMS.set(state);
        return this;
    }

    public PropertiesConfigurationOptions acceptCaseInsensitiveEnums() {
        return setAcceptCaseInsensitiveEnums(true);
    }

    public PropertiesConfigurationOptions notAcceptCaseInsensitiveEnums() {
        return setAcceptCaseInsensitiveEnums(false);
    }

    public PropertiesConfigurationOptions setAcceptCaseInsensitiveValues(boolean state) {
        ACCEPT_CASE_INSENSITIVE_VALUES.set(state);
        return this;
    }

    public PropertiesConfigurationOptions acceptCaseInsensitiveValues() {
        return setAcceptCaseInsensitiveValues(true);
    }

    public PropertiesConfigurationOptions notAcceptCaseInsensitiveValues() {
        return setAcceptCaseInsensitiveValues(false);
    }

    public PropertiesConfigurationOptions setUseWrapperNameAsPropertyName(boolean state) {
        USE_WRAPPER_NAME_AS_PROPERTY_NAME.set(state);
        return this;
    }

    public PropertiesConfigurationOptions useWrapperNameAsPropertyName() {
        return setUseWrapperNameAsPropertyName(true);
    }

    public PropertiesConfigurationOptions notUseWrapperNameAsPropertyName() {
        return setUseWrapperNameAsPropertyName(false);
    }

    public PropertiesConfigurationOptions setAllowExplicitPropertyRenaming(boolean state) {
        ALLOW_EXPLICIT_PROPERTY_RENAMING.set(state);
        return this;
    }

    public PropertiesConfigurationOptions allowExplicitPropertyRenaming() {
        return setAllowExplicitPropertyRenaming(true);
    }

    public PropertiesConfigurationOptions notAllowExplicitPropertyRenaming() {
        return setAllowExplicitPropertyRenaming(false);
    }

    public PropertiesConfigurationOptions setAllowIsGettersForNonBoolean(boolean state) {
        ALLOW_IS_GETTERS_FOR_NON_BOOLEAN.set(state);
        return this;
    }

    public PropertiesConfigurationOptions allowIsGettersForNonBoolean() {
        return setAllowIsGettersForNonBoolean(true);
    }

    public PropertiesConfigurationOptions notAllowIsGettersForNonBoolean() {
        return setAllowIsGettersForNonBoolean(false);
    }

    public PropertiesConfigurationOptions setAllowCoercionOfScalars(boolean state) {
        ALLOW_COERCION_OF_SCALARS.set(state);
        return this;
    }

    public PropertiesConfigurationOptions allowCoercionOfScalars() {
        return setAllowCoercionOfScalars(true);
    }

    public PropertiesConfigurationOptions notAllowCoercionOfScalars() {
        return setAllowCoercionOfScalars(false);
    }

    public PropertiesConfigurationOptions setIgnoreMergeForUnmergeable(boolean state) {
        IGNORE_MERGE_FOR_UNMERGEABLE.set(state);
        return this;
    }

    public PropertiesConfigurationOptions ignoreMergeForUnmergeable() {
        return setIgnoreMergeForUnmergeable(true);
    }

    public PropertiesConfigurationOptions notIgnoreMergeForUnmergeable() {
        return setIgnoreMergeForUnmergeable(false);
    }

    public PropertiesConfigurationOptions setApplyDefaultValues(boolean state) {
        APPLY_DEFAULT_VALUES.set(state);
        return this;
    }

    public PropertiesConfigurationOptions applyDefaultValues() {
        return setApplyDefaultValues(true);
    }

    public PropertiesConfigurationOptions notApplyDefaultValues() {
        return setApplyDefaultValues(false);
    }

    public PropertiesConfigurationOptions setWrapRootValue(boolean state) {
        WRAP_ROOT_VALUE.set(state);
        return this;
    }

    public PropertiesConfigurationOptions wrapRootValue() {
        return setWrapRootValue(true);
    }

    public PropertiesConfigurationOptions notWrapRootValue() {
        return setWrapRootValue(false);
    }

    public PropertiesConfigurationOptions setIndentOutput(boolean state) {
        INDENT_OUTPUT.set(state);
        return this;
    }

    public PropertiesConfigurationOptions indentOutput() {
        return setIndentOutput(true);
    }

    public PropertiesConfigurationOptions notIndentOutput() {
        return setIndentOutput(false);
    }

    public PropertiesConfigurationOptions setFailOnEmptyBeans(boolean state) {
        FAIL_ON_EMPTY_BEANS.set(state);
        return this;
    }

    public PropertiesConfigurationOptions failOnEmptyBeans() {
        return setFailOnEmptyBeans(true);
    }

    public PropertiesConfigurationOptions notFailOnEmptyBeans() {
        return setFailOnEmptyBeans(false);
    }

    public PropertiesConfigurationOptions setFailOnSelfReferences(boolean state) {
        FAIL_ON_SELF_REFERENCES.set(state);
        return this;
    }

    public PropertiesConfigurationOptions failOnSelfReferences() {
        return setFailOnSelfReferences(true);
    }

    public PropertiesConfigurationOptions notFailOnSelfReferences() {
        return setFailOnSelfReferences(false);
    }

    public PropertiesConfigurationOptions setWrapExceptions(boolean state) {
        WRAP_EXCEPTIONS.set(state);
        return this;
    }

    public PropertiesConfigurationOptions wrapExceptions() {
        return setWrapExceptions(true);
    }

    public PropertiesConfigurationOptions notWrapExceptions() {
        return setWrapExceptions(false);
    }

    public PropertiesConfigurationOptions setFailOnUnwrappedTypeIdentifiers(boolean state) {
        FAIL_ON_UNWRAPPED_TYPE_IDENTIFIERS.set(state);
        return this;
    }

    public PropertiesConfigurationOptions failOnUnwrappedTypeIdentifiers() {
        return setFailOnUnwrappedTypeIdentifiers(true);
    }

    public PropertiesConfigurationOptions notFailOnUnwrappedTypeIdentifiers() {
        return setFailOnUnwrappedTypeIdentifiers(false);
    }

    public PropertiesConfigurationOptions setWriteSelfReferencesAsNull(boolean state) {
        WRITE_SELF_REFERENCES_AS_NULL.set(state);
        return this;
    }

    public PropertiesConfigurationOptions writeSelfReferencesAsNull() {
        return setWriteSelfReferencesAsNull(true);
    }

    public PropertiesConfigurationOptions notWriteSelfReferencesAsNull() {
        return setWriteSelfReferencesAsNull(false);
    }

    public PropertiesConfigurationOptions setCloseCloseable(boolean state) {
        CLOSE_CLOSEABLE.set(state);
        return this;
    }

    public PropertiesConfigurationOptions closeCloseable() {
        return setCloseCloseable(true);
    }

    public PropertiesConfigurationOptions notCloseCloseable() {
        return setCloseCloseable(false);
    }

    public PropertiesConfigurationOptions setFlushAfterWriteValue(boolean state) {
        FLUSH_AFTER_WRITE_VALUE.set(state);
        return this;
    }

    public PropertiesConfigurationOptions flushAfterWriteValue() {
        return setFlushAfterWriteValue(true);
    }

    public PropertiesConfigurationOptions notFlushAfterWriteValue() {
        return setFlushAfterWriteValue(false);
    }

    public PropertiesConfigurationOptions setWriteCharArraysAsJsonArrays(boolean state) {
        WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS.set(state);
        return this;
    }

    public PropertiesConfigurationOptions writeCharArraysAsJsonArrays() {
        return setWriteCharArraysAsJsonArrays(true);
    }

    public PropertiesConfigurationOptions notWriteCharArraysAsJsonArrays() {
        return setWriteCharArraysAsJsonArrays(false);
    }

    public PropertiesConfigurationOptions setWriteSingleElemArraysUnwrapped(boolean state) {
        WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED.set(state);
        return this;
    }

    public PropertiesConfigurationOptions writeSingleElemArraysUnwrapped() {
        return setWriteSingleElemArraysUnwrapped(true);
    }

    public PropertiesConfigurationOptions notWriteSingleElemArraysUnwrapped() {
        return setWriteSingleElemArraysUnwrapped(false);
    }

    public PropertiesConfigurationOptions setOrderMapEntriesByKeys(boolean state) {
        ORDER_MAP_ENTRIES_BY_KEYS.set(state);
        return this;
    }

    public PropertiesConfigurationOptions orderMapEntriesByKeys() {
        return setOrderMapEntriesByKeys(true);
    }

    public PropertiesConfigurationOptions notOrderMapEntriesByKeys() {
        return setOrderMapEntriesByKeys(false);
    }

    public PropertiesConfigurationOptions setEagerSerializerFetch(boolean state) {
        EAGER_SERIALIZER_FETCH.set(state);
        return this;
    }

    public PropertiesConfigurationOptions eagerSerializerFetch() {
        return setEagerSerializerFetch(true);
    }

    public PropertiesConfigurationOptions notEagerSerializerFetch() {
        return setEagerSerializerFetch(false);
    }

    public PropertiesConfigurationOptions setUseEqualityForObjectId(boolean state) {
        USE_EQUALITY_FOR_OBJECT_ID.set(state);
        return this;
    }

    public PropertiesConfigurationOptions useEqualityForObjectId() {
        return setUseEqualityForObjectId(true);
    }

    public PropertiesConfigurationOptions notUseEqualityForObjectId() {
        return setUseEqualityForObjectId(false);
    }

    public PropertiesConfigurationOptions setParseComment(boolean state) {
        PARSE_COMMENT.set(state);
        return this;
    }

    public PropertiesConfigurationOptions parseComment() {
        return setParseComment(true);
    }

    public PropertiesConfigurationOptions notParseComment() {
        return setParseComment(false);
    }

    public PropertiesConfigurationOptions setPerformFileValidation(boolean state) {
        FILE_VALIDATION.set(state);
        return this;
    }

    public PropertiesConfigurationOptions performFileValidation() {
        return setPerformFileValidation(true);
    }

    public PropertiesConfigurationOptions notPerformFileValidation() {
        return setPerformFileValidation(false);
    }

    JavaPropsMapper buildMapper() {
        return JavaPropsMapper.builder()
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

