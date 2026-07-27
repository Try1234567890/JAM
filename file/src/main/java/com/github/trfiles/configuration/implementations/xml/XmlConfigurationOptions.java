package com.github.trfiles.configuration.implementations.xml;

import com.github.trfiles.configuration.FileConfigurationOptions;
import com.github.utilities.options.BooleanOption;
import com.github.utilities.options.StringOption;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.MapperFeature;
import tools.jackson.databind.SerializationFeature;
import tools.jackson.dataformat.xml.XmlMapper;
import tools.jackson.dataformat.xml.XmlReadFeature;
import tools.jackson.dataformat.xml.XmlWriteFeature;

public class XmlConfigurationOptions extends FileConfigurationOptions {
    public final BooleanOption READ_AUTO_DETECT_XSI_TYPE = new BooleanOption(true);
    public final BooleanOption EMPTY_ELEMENT_AS_NULL = new BooleanOption(false);
    public final BooleanOption ENFORCE_ROOT_ELEMENT_NAME = new BooleanOption(false);
    public final BooleanOption PROCESS_XSI_NIL = new BooleanOption(true);
    public final BooleanOption SKIP_UNKNOWN_XSI_ATTRIBUTES = new BooleanOption(false);
    public final BooleanOption WRAP_ROOT_ELEMENT_NAME = new BooleanOption(false);
    public final BooleanOption WRITE_AUTO_DETECT_XSI_TYPE = new BooleanOption(true);
    public final BooleanOption FAIL_ON_NESTED_ARRAYS = new BooleanOption(true);
    public final BooleanOption UNWRAP_ROOT_OBJECT_NODE = new BooleanOption(true);
    public final BooleanOption WRITE_XML_DECLARATION = new BooleanOption(false);
    public final BooleanOption WRITE_XML_1_1 = new BooleanOption(false);
    public final BooleanOption WRITE_STANDALONE_YES_TO_XML_DECLARATION = new BooleanOption(false);
    public final BooleanOption WRITE_NULLS_AS_XSI_NIL = new BooleanOption(true);
    public final BooleanOption WRITE_XML_SCHEMA_CONFORMING_FLOATS = new BooleanOption(true);
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
    public final StringOption ROOT_ELEMENT_NAME = new StringOption("");

    // ==========================================
    // Setters for XML Options
    // ==========================================

    public XmlConfigurationOptions setReadAutoDetectXsiType(boolean state) {
        READ_AUTO_DETECT_XSI_TYPE.set(state);
        return this;
    }

    public XmlConfigurationOptions readAutoDetectXsiType() {
        return setReadAutoDetectXsiType(true);
    }

    public XmlConfigurationOptions notReadAutoDetectXsiType() {
        return setReadAutoDetectXsiType(false);
    }

    public XmlConfigurationOptions setEmptyElementAsNull(boolean state) {
        EMPTY_ELEMENT_AS_NULL.set(state);
        return this;
    }

    public XmlConfigurationOptions emptyElementAsNull() {
        return setEmptyElementAsNull(true);
    }

    public XmlConfigurationOptions notEmptyElementAsNull() {
        return setEmptyElementAsNull(false);
    }

    public XmlConfigurationOptions setEnforceRootElementName(boolean state) {
        ENFORCE_ROOT_ELEMENT_NAME.set(state);
        return this;
    }

    public XmlConfigurationOptions enforceRootElementName() {
        return setEnforceRootElementName(true);
    }

    public XmlConfigurationOptions notEnforceRootElementName() {
        return setEnforceRootElementName(false);
    }

    public XmlConfigurationOptions setProcessXsiNil(boolean state) {
        PROCESS_XSI_NIL.set(state);
        return this;
    }

    public XmlConfigurationOptions processXsiNil() {
        return setProcessXsiNil(true);
    }

    public XmlConfigurationOptions notProcessXsiNil() {
        return setProcessXsiNil(false);
    }

    public XmlConfigurationOptions setSkipUnknownXsiAttributes(boolean state) {
        SKIP_UNKNOWN_XSI_ATTRIBUTES.set(state);
        return this;
    }

    public XmlConfigurationOptions skipUnknownXsiAttributes() {
        return setSkipUnknownXsiAttributes(true);
    }

    public XmlConfigurationOptions notSkipUnknownXsiAttributes() {
        return setSkipUnknownXsiAttributes(false);
    }

    public XmlConfigurationOptions setWrapRootElementName(boolean state) {
        WRAP_ROOT_ELEMENT_NAME.set(state);
        return this;
    }

    public XmlConfigurationOptions wrapRootElementName() {
        return setWrapRootElementName(true);
    }

    public XmlConfigurationOptions notWrapRootElementName() {
        return setWrapRootElementName(false);
    }

    public XmlConfigurationOptions setWriteAutoDetectXsiType(boolean state) {
        WRITE_AUTO_DETECT_XSI_TYPE.set(state);
        return this;
    }

    public XmlConfigurationOptions writeAutoDetectXsiType() {
        return setWriteAutoDetectXsiType(true);
    }

    public XmlConfigurationOptions notWriteAutoDetectXsiType() {
        return setWriteAutoDetectXsiType(false);
    }

    public XmlConfigurationOptions setFailOnNestedArrays(boolean state) {
        FAIL_ON_NESTED_ARRAYS.set(state);
        return this;
    }

    public XmlConfigurationOptions failOnNestedArrays() {
        return setFailOnNestedArrays(true);
    }

    public XmlConfigurationOptions notFailOnNestedArrays() {
        return setFailOnNestedArrays(false);
    }

    public XmlConfigurationOptions setUnwrapRootObjectNode(boolean state) {
        UNWRAP_ROOT_OBJECT_NODE.set(state);
        return this;
    }

    public XmlConfigurationOptions unwrapRootObjectNode() {
        return setUnwrapRootObjectNode(true);
    }

    public XmlConfigurationOptions notUnwrapRootObjectNode() {
        return setUnwrapRootObjectNode(false);
    }

    public XmlConfigurationOptions setWriteXmlDeclaration(boolean state) {
        WRITE_XML_DECLARATION.set(state);
        return this;
    }

    public XmlConfigurationOptions writeXmlDeclaration() {
        return setWriteXmlDeclaration(true);
    }

    public XmlConfigurationOptions notWriteXmlDeclaration() {
        return setWriteXmlDeclaration(false);
    }

    public XmlConfigurationOptions setWriteXml11(boolean state) {
        WRITE_XML_1_1.set(state);
        return this;
    }

    public XmlConfigurationOptions writeXml11() {
        return setWriteXml11(true);
    }

    public XmlConfigurationOptions notWriteXml11() {
        return setWriteXml11(false);
    }

    public XmlConfigurationOptions setWriteStandaloneYesToXmlDeclaration(boolean state) {
        WRITE_STANDALONE_YES_TO_XML_DECLARATION.set(state);
        return this;
    }

    public XmlConfigurationOptions writeStandaloneYesToXmlDeclaration() {
        return setWriteStandaloneYesToXmlDeclaration(true);
    }

    public XmlConfigurationOptions notWriteStandaloneYesToXmlDeclaration() {
        return setWriteStandaloneYesToXmlDeclaration(false);
    }

    public XmlConfigurationOptions setWriteNullsAsXsiNil(boolean state) {
        WRITE_NULLS_AS_XSI_NIL.set(state);
        return this;
    }

    public XmlConfigurationOptions writeNullsAsXsiNil() {
        return setWriteNullsAsXsiNil(true);
    }

    public XmlConfigurationOptions notWriteNullsAsXsiNil() {
        return setWriteNullsAsXsiNil(false);
    }

    public XmlConfigurationOptions setWriteXmlSchemaConformingFloats(boolean state) {
        WRITE_XML_SCHEMA_CONFORMING_FLOATS.set(state);
        return this;
    }

    public XmlConfigurationOptions writeXmlSchemaConformingFloats() {
        return setWriteXmlSchemaConformingFloats(true);
    }

    public XmlConfigurationOptions notWriteXmlSchemaConformingFloats() {
        return setWriteXmlSchemaConformingFloats(false);
    }

    public XmlConfigurationOptions setUseBigDecimalForFloats(boolean state) {
        USE_BIG_DECIMAL_FOR_FLOATS.set(state);
        return this;
    }

    public XmlConfigurationOptions useBigDecimalForFloats() {
        return setUseBigDecimalForFloats(true);
    }

    public XmlConfigurationOptions notUseBigDecimalForFloats() {
        return setUseBigDecimalForFloats(false);
    }

    public XmlConfigurationOptions setUseBigIntegerForInts(boolean state) {
        USE_BIG_INTEGER_FOR_INTS.set(state);
        return this;
    }

    public XmlConfigurationOptions useBigIntegerForInts() {
        return setUseBigIntegerForInts(true);
    }

    public XmlConfigurationOptions notUseBigIntegerForInts() {
        return setUseBigIntegerForInts(false);
    }

    public XmlConfigurationOptions setUseLongForInts(boolean state) {
        USE_LONG_FOR_INTS.set(state);
        return this;
    }

    public XmlConfigurationOptions useLongForInts() {
        return setUseLongForInts(true);
    }

    public XmlConfigurationOptions notUseLongForInts() {
        return setUseLongForInts(false);
    }

    public XmlConfigurationOptions setUseJavaArrayForJsonArray(boolean state) {
        USE_JAVA_ARRAY_FOR_JSON_ARRAY.set(state);
        return this;
    }

    public XmlConfigurationOptions useJavaArrayForJsonArray() {
        return setUseJavaArrayForJsonArray(true);
    }

    public XmlConfigurationOptions notUseJavaArrayForJsonArray() {
        return setUseJavaArrayForJsonArray(false);
    }

    public XmlConfigurationOptions setFailOnUnknownProperties(boolean state) {
        FAIL_ON_UNKNOWN_PROPERTIES.set(state);
        return this;
    }

    public XmlConfigurationOptions failOnUnknownProperties() {
        return setFailOnUnknownProperties(true);
    }

    public XmlConfigurationOptions notFailOnUnknownProperties() {
        return setFailOnUnknownProperties(false);
    }

    public XmlConfigurationOptions setFailOnNullForPrimitives(boolean state) {
        FAIL_ON_NULL_FOR_PRIMITIVES.set(state);
        return this;
    }

    public XmlConfigurationOptions failOnNullForPrimitives() {
        return setFailOnNullForPrimitives(true);
    }

    public XmlConfigurationOptions notFailOnNullForPrimitives() {
        return setFailOnNullForPrimitives(false);
    }

    public XmlConfigurationOptions setFailOnInvalidSubtype(boolean state) {
        FAIL_ON_INVALID_SUBTYPE.set(state);
        return this;
    }

    public XmlConfigurationOptions failOnInvalidSubtype() {
        return setFailOnInvalidSubtype(true);
    }

    public XmlConfigurationOptions notFailOnInvalidSubtype() {
        return setFailOnInvalidSubtype(false);
    }

    public XmlConfigurationOptions setFailOnReadingDupTreeKey(boolean state) {
        FAIL_ON_READING_DUP_TREE_KEY.set(state);
        return this;
    }

    public XmlConfigurationOptions failOnReadingDupTreeKey() {
        return setFailOnReadingDupTreeKey(true);
    }

    public XmlConfigurationOptions notFailOnReadingDupTreeKey() {
        return setFailOnReadingDupTreeKey(false);
    }

    public XmlConfigurationOptions setFailOnIgnoredProperties(boolean state) {
        FAIL_ON_IGNORED_PROPERTIES.set(state);
        return this;
    }

    public XmlConfigurationOptions failOnIgnoredProperties() {
        return setFailOnIgnoredProperties(true);
    }

    public XmlConfigurationOptions notFailOnIgnoredProperties() {
        return setFailOnIgnoredProperties(false);
    }

    public XmlConfigurationOptions setFailOnUnresolvedObjectIds(boolean state) {
        FAIL_ON_UNRESOLVED_OBJECT_IDS.set(state);
        return this;
    }

    public XmlConfigurationOptions failOnUnresolvedObjectIds() {
        return setFailOnUnresolvedObjectIds(true);
    }

    public XmlConfigurationOptions notFailOnUnresolvedObjectIds() {
        return setFailOnUnresolvedObjectIds(false);
    }

    public XmlConfigurationOptions setFailOnMissingCreatorProperties(boolean state) {
        FAIL_ON_MISSING_CREATOR_PROPERTIES.set(state);
        return this;
    }

    public XmlConfigurationOptions failOnMissingCreatorProperties() {
        return setFailOnMissingCreatorProperties(true);
    }

    public XmlConfigurationOptions notFailOnMissingCreatorProperties() {
        return setFailOnMissingCreatorProperties(false);
    }

    public XmlConfigurationOptions setFailOnNullCreatorProperties(boolean state) {
        FAIL_ON_NULL_CREATOR_PROPERTIES.set(state);
        return this;
    }

    public XmlConfigurationOptions failOnNullCreatorProperties() {
        return setFailOnNullCreatorProperties(true);
    }

    public XmlConfigurationOptions notFailOnNullCreatorProperties() {
        return setFailOnNullCreatorProperties(false);
    }

    public XmlConfigurationOptions setFailOnMissingExternalTypeIdProperty(boolean state) {
        FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY.set(state);
        return this;
    }

    public XmlConfigurationOptions failOnMissingExternalTypeIdProperty() {
        return setFailOnMissingExternalTypeIdProperty(true);
    }

    public XmlConfigurationOptions notFailOnMissingExternalTypeIdProperty() {
        return setFailOnMissingExternalTypeIdProperty(false);
    }

    public XmlConfigurationOptions setFailOnTrailingTokens(boolean state) {
        FAIL_ON_TRAILING_TOKENS.set(state);
        return this;
    }

    public XmlConfigurationOptions failOnTrailingTokens() {
        return setFailOnTrailingTokens(true);
    }

    public XmlConfigurationOptions notFailOnTrailingTokens() {
        return setFailOnTrailingTokens(false);
    }

    public XmlConfigurationOptions setFailOnUnexpectedViewProperties(boolean state) {
        FAIL_ON_UNEXPECTED_VIEW_PROPERTIES.set(state);
        return this;
    }

    public XmlConfigurationOptions failOnUnexpectedViewProperties() {
        return setFailOnUnexpectedViewProperties(true);
    }

    public XmlConfigurationOptions notFailOnUnexpectedViewProperties() {
        return setFailOnUnexpectedViewProperties(false);
    }

    public XmlConfigurationOptions setAcceptSingleValueAsArray(boolean state) {
        ACCEPT_SINGLE_VALUE_AS_ARRAY.set(state);
        return this;
    }

    public XmlConfigurationOptions acceptSingleValueAsArray() {
        return setAcceptSingleValueAsArray(true);
    }

    public XmlConfigurationOptions notAcceptSingleValueAsArray() {
        return setAcceptSingleValueAsArray(false);
    }

    public XmlConfigurationOptions setUnwrapSingleValueArrays(boolean state) {
        UNWRAP_SINGLE_VALUE_ARRAYS.set(state);
        return this;
    }

    public XmlConfigurationOptions unwrapSingleValueArrays() {
        return setUnwrapSingleValueArrays(true);
    }

    public XmlConfigurationOptions notUnwrapSingleValueArrays() {
        return setUnwrapSingleValueArrays(false);
    }

    public XmlConfigurationOptions setUnwrapRootValue(boolean state) {
        UNWRAP_ROOT_VALUE.set(state);
        return this;
    }

    public XmlConfigurationOptions unwrapRootValue() {
        return setUnwrapRootValue(true);
    }

    public XmlConfigurationOptions notUnwrapRootValue() {
        return setUnwrapRootValue(false);
    }

    public XmlConfigurationOptions setAcceptEmptyStringAsNullObject(boolean state) {
        ACCEPT_EMPTY_STRING_AS_NULL_OBJECT.set(state);
        return this;
    }

    public XmlConfigurationOptions acceptEmptyStringAsNullObject() {
        return setAcceptEmptyStringAsNullObject(true);
    }

    public XmlConfigurationOptions notAcceptEmptyStringAsNullObject() {
        return setAcceptEmptyStringAsNullObject(false);
    }

    public XmlConfigurationOptions setAcceptEmptyArrayAsNullObject(boolean state) {
        ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT.set(state);
        return this;
    }

    public XmlConfigurationOptions acceptEmptyArrayAsNullObject() {
        return setAcceptEmptyArrayAsNullObject(true);
    }

    public XmlConfigurationOptions notAcceptEmptyArrayAsNullObject() {
        return setAcceptEmptyArrayAsNullObject(false);
    }

    public XmlConfigurationOptions setAcceptFloatAsInt(boolean state) {
        ACCEPT_FLOAT_AS_INT.set(state);
        return this;
    }

    public XmlConfigurationOptions acceptFloatAsInt() {
        return setAcceptFloatAsInt(true);
    }

    public XmlConfigurationOptions notAcceptFloatAsInt() {
        return setAcceptFloatAsInt(false);
    }

    public XmlConfigurationOptions setEagerDeserializerFetch(boolean state) {
        EAGER_DESERIALIZER_FETCH.set(state);
        return this;
    }

    public XmlConfigurationOptions eagerDeserializerFetch() {
        return setEagerDeserializerFetch(true);
    }

    public XmlConfigurationOptions notEagerDeserializerFetch() {
        return setEagerDeserializerFetch(false);
    }

    public XmlConfigurationOptions setUseAnnotations(boolean state) {
        USE_ANNOTATIONS.set(state);
        return this;
    }

    public XmlConfigurationOptions useAnnotations() {
        return setUseAnnotations(true);
    }

    public XmlConfigurationOptions notUseAnnotations() {
        return setUseAnnotations(false);
    }

    public XmlConfigurationOptions setUseGettersAsSetters(boolean state) {
        USE_GETTERS_AS_SETTERS.set(state);
        return this;
    }

    public XmlConfigurationOptions useGettersAsSetters() {
        return setUseGettersAsSetters(true);
    }

    public XmlConfigurationOptions notUseGettersAsSetters() {
        return setUseGettersAsSetters(false);
    }

    public XmlConfigurationOptions setPropagateTransientMarker(boolean state) {
        PROPAGATE_TRANSIENT_MARKER.set(state);
        return this;
    }

    public XmlConfigurationOptions propagateTransientMarker() {
        return setPropagateTransientMarker(true);
    }

    public XmlConfigurationOptions notPropagateTransientMarker() {
        return setPropagateTransientMarker(false);
    }

    public XmlConfigurationOptions setRequireSettersForGetters(boolean state) {
        REQUIRE_SETTERS_FOR_GETTERS.set(state);
        return this;
    }

    public XmlConfigurationOptions requireSettersForGetters() {
        return setRequireSettersForGetters(true);
    }

    public XmlConfigurationOptions notRequireSettersForGetters() {
        return setRequireSettersForGetters(false);
    }

    public XmlConfigurationOptions setAllowFinalFieldsAsMutators(boolean state) {
        ALLOW_FINAL_FIELDS_AS_MUTATORS.set(state);
        return this;
    }

    public XmlConfigurationOptions allowFinalFieldsAsMutators() {
        return setAllowFinalFieldsAsMutators(true);
    }

    public XmlConfigurationOptions notAllowFinalFieldsAsMutators() {
        return setAllowFinalFieldsAsMutators(false);
    }

    public XmlConfigurationOptions setInferPropertyMutators(boolean state) {
        INFER_PROPERTY_MUTATORS.set(state);
        return this;
    }

    public XmlConfigurationOptions inferPropertyMutators() {
        return setInferPropertyMutators(true);
    }

    public XmlConfigurationOptions notInferPropertyMutators() {
        return setInferPropertyMutators(false);
    }

    public XmlConfigurationOptions setInferCreatorFromConstructorProperties(boolean state) {
        INFER_CREATOR_FROM_CONSTRUCTOR_PROPERTIES.set(state);
        return this;
    }

    public XmlConfigurationOptions inferCreatorFromConstructorProperties() {
        return setInferCreatorFromConstructorProperties(true);
    }

    public XmlConfigurationOptions notInferCreatorFromConstructorProperties() {
        return setInferCreatorFromConstructorProperties(false);
    }

    public XmlConfigurationOptions setAllowVoidValuedProperties(boolean state) {
        ALLOW_VOID_VALUED_PROPERTIES.set(state);
        return this;
    }

    public XmlConfigurationOptions allowVoidValuedProperties() {
        return setAllowVoidValuedProperties(true);
    }

    public XmlConfigurationOptions notAllowVoidValuedProperties() {
        return setAllowVoidValuedProperties(false);
    }

    public XmlConfigurationOptions setCanOverrideAccessModifiers(boolean state) {
        CAN_OVERRIDE_ACCESS_MODIFIERS.set(state);
        return this;
    }

    public XmlConfigurationOptions canOverrideAccessModifiers() {
        return setCanOverrideAccessModifiers(true);
    }

    public XmlConfigurationOptions notCanOverrideAccessModifiers() {
        return setCanOverrideAccessModifiers(false);
    }

    public XmlConfigurationOptions setOverridePublicAccessModifiers(boolean state) {
        OVERRIDE_PUBLIC_ACCESS_MODIFIERS.set(state);
        return this;
    }

    public XmlConfigurationOptions overridePublicAccessModifiers() {
        return setOverridePublicAccessModifiers(true);
    }

    public XmlConfigurationOptions notOverridePublicAccessModifiers() {
        return setOverridePublicAccessModifiers(false);
    }

    public XmlConfigurationOptions setUseStaticTyping(boolean state) {
        USE_STATIC_TYPING.set(state);
        return this;
    }

    public XmlConfigurationOptions useStaticTyping() {
        return setUseStaticTyping(true);
    }

    public XmlConfigurationOptions notUseStaticTyping() {
        return setUseStaticTyping(false);
    }

    public XmlConfigurationOptions setUseBaseTypeAsDefaultImpl(boolean state) {
        USE_BASE_TYPE_AS_DEFAULT_IMPL.set(state);
        return this;
    }

    public XmlConfigurationOptions useBaseTypeAsDefaultImpl() {
        return setUseBaseTypeAsDefaultImpl(true);
    }

    public XmlConfigurationOptions notUseBaseTypeAsDefaultImpl() {
        return setUseBaseTypeAsDefaultImpl(false);
    }

    public XmlConfigurationOptions setInferBuilderTypeBindings(boolean state) {
        INFER_BUILDER_TYPE_BINDINGS.set(state);
        return this;
    }

    public XmlConfigurationOptions inferBuilderTypeBindings() {
        return setInferBuilderTypeBindings(true);
    }

    public XmlConfigurationOptions notInferBuilderTypeBindings() {
        return setInferBuilderTypeBindings(false);
    }

    public XmlConfigurationOptions setRequireTypeIdForSubtypes(boolean state) {
        REQUIRE_TYPE_ID_FOR_SUBTYPES.set(state);
        return this;
    }

    public XmlConfigurationOptions requireTypeIdForSubtypes() {
        return setRequireTypeIdForSubtypes(true);
    }

    public XmlConfigurationOptions notRequireTypeIdForSubtypes() {
        return setRequireTypeIdForSubtypes(false);
    }

    public XmlConfigurationOptions setDefaultViewInclusion(boolean state) {
        DEFAULT_VIEW_INCLUSION.set(state);
        return this;
    }

    public XmlConfigurationOptions defaultViewInclusion() {
        return setDefaultViewInclusion(true);
    }

    public XmlConfigurationOptions notDefaultViewInclusion() {
        return setDefaultViewInclusion(false);
    }

    public XmlConfigurationOptions setSortPropertiesAlphabetically(boolean state) {
        SORT_PROPERTIES_ALPHABETICALLY.set(state);
        return this;
    }

    public XmlConfigurationOptions sortPropertiesAlphabetically() {
        return setSortPropertiesAlphabetically(true);
    }

    public XmlConfigurationOptions notSortPropertiesAlphabetically() {
        return setSortPropertiesAlphabetically(false);
    }

    public XmlConfigurationOptions setSortCreatorPropertiesFirst(boolean state) {
        SORT_CREATOR_PROPERTIES_FIRST.set(state);
        return this;
    }

    public XmlConfigurationOptions sortCreatorPropertiesFirst() {
        return setSortCreatorPropertiesFirst(true);
    }

    public XmlConfigurationOptions notSortCreatorPropertiesFirst() {
        return setSortCreatorPropertiesFirst(false);
    }

    public XmlConfigurationOptions setAcceptCaseInsensitiveProperties(boolean state) {
        ACCEPT_CASE_INSENSITIVE_PROPERTIES.set(state);
        return this;
    }

    public XmlConfigurationOptions acceptCaseInsensitiveProperties() {
        return setAcceptCaseInsensitiveProperties(true);
    }

    public XmlConfigurationOptions notAcceptCaseInsensitiveProperties() {
        return setAcceptCaseInsensitiveProperties(false);
    }

    public XmlConfigurationOptions setAcceptCaseInsensitiveEnums(boolean state) {
        ACCEPT_CASE_INSENSITIVE_ENUMS.set(state);
        return this;
    }

    public XmlConfigurationOptions acceptCaseInsensitiveEnums() {
        return setAcceptCaseInsensitiveEnums(true);
    }

    public XmlConfigurationOptions notAcceptCaseInsensitiveEnums() {
        return setAcceptCaseInsensitiveEnums(false);
    }

    public XmlConfigurationOptions setAcceptCaseInsensitiveValues(boolean state) {
        ACCEPT_CASE_INSENSITIVE_VALUES.set(state);
        return this;
    }

    public XmlConfigurationOptions acceptCaseInsensitiveValues() {
        return setAcceptCaseInsensitiveValues(true);
    }

    public XmlConfigurationOptions notAcceptCaseInsensitiveValues() {
        return setAcceptCaseInsensitiveValues(false);
    }

    public XmlConfigurationOptions setUseWrapperNameAsPropertyName(boolean state) {
        USE_WRAPPER_NAME_AS_PROPERTY_NAME.set(state);
        return this;
    }

    public XmlConfigurationOptions useWrapperNameAsPropertyName() {
        return setUseWrapperNameAsPropertyName(true);
    }

    public XmlConfigurationOptions notUseWrapperNameAsPropertyName() {
        return setUseWrapperNameAsPropertyName(false);
    }

    public XmlConfigurationOptions setAllowExplicitPropertyRenaming(boolean state) {
        ALLOW_EXPLICIT_PROPERTY_RENAMING.set(state);
        return this;
    }

    public XmlConfigurationOptions allowExplicitPropertyRenaming() {
        return setAllowExplicitPropertyRenaming(true);
    }

    public XmlConfigurationOptions notAllowExplicitPropertyRenaming() {
        return setAllowExplicitPropertyRenaming(false);
    }

    public XmlConfigurationOptions setAllowIsGettersForNonBoolean(boolean state) {
        ALLOW_IS_GETTERS_FOR_NON_BOOLEAN.set(state);
        return this;
    }

    public XmlConfigurationOptions allowIsGettersForNonBoolean() {
        return setAllowIsGettersForNonBoolean(true);
    }

    public XmlConfigurationOptions notAllowIsGettersForNonBoolean() {
        return setAllowIsGettersForNonBoolean(false);
    }

    public XmlConfigurationOptions setAllowCoercionOfScalars(boolean state) {
        ALLOW_COERCION_OF_SCALARS.set(state);
        return this;
    }

    public XmlConfigurationOptions allowCoercionOfScalars() {
        return setAllowCoercionOfScalars(true);
    }

    public XmlConfigurationOptions notAllowCoercionOfScalars() {
        return setAllowCoercionOfScalars(false);
    }

    public XmlConfigurationOptions setIgnoreMergeForUnmergeable(boolean state) {
        IGNORE_MERGE_FOR_UNMERGEABLE.set(state);
        return this;
    }

    public XmlConfigurationOptions ignoreMergeForUnmergeable() {
        return setIgnoreMergeForUnmergeable(true);
    }

    public XmlConfigurationOptions notIgnoreMergeForUnmergeable() {
        return setIgnoreMergeForUnmergeable(false);
    }

    public XmlConfigurationOptions setApplyDefaultValues(boolean state) {
        APPLY_DEFAULT_VALUES.set(state);
        return this;
    }

    public XmlConfigurationOptions applyDefaultValues() {
        return setApplyDefaultValues(true);
    }

    public XmlConfigurationOptions notApplyDefaultValues() {
        return setApplyDefaultValues(false);
    }

    public XmlConfigurationOptions setWrapRootValue(boolean state) {
        WRAP_ROOT_VALUE.set(state);
        return this;
    }

    public XmlConfigurationOptions wrapRootValue() {
        return setWrapRootValue(true);
    }

    public XmlConfigurationOptions notWrapRootValue() {
        return setWrapRootValue(false);
    }

    public XmlConfigurationOptions setIndentOutput(boolean state) {
        INDENT_OUTPUT.set(state);
        return this;
    }

    public XmlConfigurationOptions indentOutput() {
        return setIndentOutput(true);
    }

    public XmlConfigurationOptions notIndentOutput() {
        return setIndentOutput(false);
    }

    public XmlConfigurationOptions setFailOnEmptyBeans(boolean state) {
        FAIL_ON_EMPTY_BEANS.set(state);
        return this;
    }

    public XmlConfigurationOptions failOnEmptyBeans() {
        return setFailOnEmptyBeans(true);
    }

    public XmlConfigurationOptions notFailOnEmptyBeans() {
        return setFailOnEmptyBeans(false);
    }

    public XmlConfigurationOptions setFailOnSelfReferences(boolean state) {
        FAIL_ON_SELF_REFERENCES.set(state);
        return this;
    }

    public XmlConfigurationOptions failOnSelfReferences() {
        return setFailOnSelfReferences(true);
    }

    public XmlConfigurationOptions notFailOnSelfReferences() {
        return setFailOnSelfReferences(false);
    }

    public XmlConfigurationOptions setWrapExceptions(boolean state) {
        WRAP_EXCEPTIONS.set(state);
        return this;
    }

    public XmlConfigurationOptions wrapExceptions() {
        return setWrapExceptions(true);
    }

    public XmlConfigurationOptions notWrapExceptions() {
        return setWrapExceptions(false);
    }

    public XmlConfigurationOptions setFailOnUnwrappedTypeIdentifiers(boolean state) {
        FAIL_ON_UNWRAPPED_TYPE_IDENTIFIERS.set(state);
        return this;
    }

    public XmlConfigurationOptions failOnUnwrappedTypeIdentifiers() {
        return setFailOnUnwrappedTypeIdentifiers(true);
    }

    public XmlConfigurationOptions notFailOnUnwrappedTypeIdentifiers() {
        return setFailOnUnwrappedTypeIdentifiers(false);
    }

    public XmlConfigurationOptions setWriteSelfReferencesAsNull(boolean state) {
        WRITE_SELF_REFERENCES_AS_NULL.set(state);
        return this;
    }

    public XmlConfigurationOptions writeSelfReferencesAsNull() {
        return setWriteSelfReferencesAsNull(true);
    }

    public XmlConfigurationOptions notWriteSelfReferencesAsNull() {
        return setWriteSelfReferencesAsNull(false);
    }

    public XmlConfigurationOptions setCloseCloseable(boolean state) {
        CLOSE_CLOSEABLE.set(state);
        return this;
    }

    public XmlConfigurationOptions closeCloseable() {
        return setCloseCloseable(true);
    }

    public XmlConfigurationOptions notCloseCloseable() {
        return setCloseCloseable(false);
    }

    public XmlConfigurationOptions setFlushAfterWriteValue(boolean state) {
        FLUSH_AFTER_WRITE_VALUE.set(state);
        return this;
    }

    public XmlConfigurationOptions flushAfterWriteValue() {
        return setFlushAfterWriteValue(true);
    }

    public XmlConfigurationOptions notFlushAfterWriteValue() {
        return setFlushAfterWriteValue(false);
    }

    public XmlConfigurationOptions setWriteCharArraysAsJsonArrays(boolean state) {
        WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS.set(state);
        return this;
    }

    public XmlConfigurationOptions writeCharArraysAsJsonArrays() {
        return setWriteCharArraysAsJsonArrays(true);
    }

    public XmlConfigurationOptions notWriteCharArraysAsJsonArrays() {
        return setWriteCharArraysAsJsonArrays(false);
    }

    public XmlConfigurationOptions setWriteSingleElemArraysUnwrapped(boolean state) {
        WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED.set(state);
        return this;
    }

    public XmlConfigurationOptions writeSingleElemArraysUnwrapped() {
        return setWriteSingleElemArraysUnwrapped(true);
    }

    public XmlConfigurationOptions notWriteSingleElemArraysUnwrapped() {
        return setWriteSingleElemArraysUnwrapped(false);
    }

    public XmlConfigurationOptions setOrderMapEntriesByKeys(boolean state) {
        ORDER_MAP_ENTRIES_BY_KEYS.set(state);
        return this;
    }

    public XmlConfigurationOptions orderMapEntriesByKeys() {
        return setOrderMapEntriesByKeys(true);
    }

    public XmlConfigurationOptions notOrderMapEntriesByKeys() {
        return setOrderMapEntriesByKeys(false);
    }

    public XmlConfigurationOptions setEagerSerializerFetch(boolean state) {
        EAGER_SERIALIZER_FETCH.set(state);
        return this;
    }

    public XmlConfigurationOptions eagerSerializerFetch() {
        return setEagerSerializerFetch(true);
    }

    public XmlConfigurationOptions notEagerSerializerFetch() {
        return setEagerSerializerFetch(false);
    }

    public XmlConfigurationOptions setUseEqualityForObjectId(boolean state) {
        USE_EQUALITY_FOR_OBJECT_ID.set(state);
        return this;
    }

    public XmlConfigurationOptions useEqualityForObjectId() {
        return setUseEqualityForObjectId(true);
    }

    public XmlConfigurationOptions notUseEqualityForObjectId() {
        return setUseEqualityForObjectId(false);
    }

    public XmlConfigurationOptions setRootElementName(String name) {
        ROOT_ELEMENT_NAME.set(name);
        return this;
    }

    // ==========================================
    // Setters of Global Options
    // ==========================================

    public XmlConfigurationOptions setParseComment(boolean state) {
        PARSE_COMMENT.set(state);
        return this;
    }

    public XmlConfigurationOptions parseComment() {
        return setParseComment(true);
    }

    public XmlConfigurationOptions notParseComment() {
        return setParseComment(false);
    }

    public XmlConfigurationOptions setPerformFileValidation(boolean state) {
        FILE_VALIDATION.set(state);
        return this;
    }

    public XmlConfigurationOptions performFileValidation() {
        return setPerformFileValidation(true);
    }

    public XmlConfigurationOptions notPerformFileValidation() {
        return setPerformFileValidation(false);
    }

    XmlMapper buildMapper() {
        return XmlMapper.builder()
                .configure(XmlReadFeature.AUTO_DETECT_XSI_TYPE, READ_AUTO_DETECT_XSI_TYPE.get())
                .configure(XmlReadFeature.EMPTY_ELEMENT_AS_NULL, EMPTY_ELEMENT_AS_NULL.get())
                .configure(XmlReadFeature.ENFORCE_ROOT_ELEMENT_NAME, ENFORCE_ROOT_ELEMENT_NAME.get())
                .configure(XmlReadFeature.PROCESS_XSI_NIL, PROCESS_XSI_NIL.get())
                .configure(XmlReadFeature.SKIP_UNKNOWN_XSI_ATTRIBUTES, SKIP_UNKNOWN_XSI_ATTRIBUTES.get())
                .configure(XmlReadFeature.WRAP_ROOT_ELEMENT_NAME, WRAP_ROOT_ELEMENT_NAME.get())
                .configure(XmlWriteFeature.AUTO_DETECT_XSI_TYPE, WRITE_AUTO_DETECT_XSI_TYPE.get())
                .configure(XmlWriteFeature.FAIL_ON_NESTED_ARRAYS, FAIL_ON_NESTED_ARRAYS.get())
                .configure(XmlWriteFeature.UNWRAP_ROOT_OBJECT_NODE, UNWRAP_ROOT_OBJECT_NODE.get())
                .configure(XmlWriteFeature.WRITE_XML_DECLARATION, WRITE_XML_DECLARATION.get())
                .configure(XmlWriteFeature.WRITE_XML_1_1, WRITE_XML_1_1.get())
                .configure(XmlWriteFeature.WRITE_STANDALONE_YES_TO_XML_DECLARATION, WRITE_STANDALONE_YES_TO_XML_DECLARATION.get())
                .configure(XmlWriteFeature.WRITE_NULLS_AS_XSI_NIL, WRITE_NULLS_AS_XSI_NIL.get())
                .configure(XmlWriteFeature.WRITE_XML_SCHEMA_CONFORMING_FLOATS, WRITE_XML_SCHEMA_CONFORMING_FLOATS.get())
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