package com.github.trfiles.configuration;

import com.github.trfiles.ConfigurationOptions;
import com.github.utilities.options.BooleanOption;

public class FileConfigurationOptions extends ConfigurationOptions {
    public final BooleanOption PARSE_COMMENT = new BooleanOption(true);
    public final BooleanOption FILE_VALIDATION = new BooleanOption(true);

    public FileConfigurationOptions setParseComment(boolean state) {
        PARSE_COMMENT.set(state);
        return this;
    }

    public FileConfigurationOptions parseComment() {
        return setParseComment(true);
    }

    public FileConfigurationOptions notParseComment() {
        return setParseComment(false);
    }

    public FileConfigurationOptions setPerformFileValidation(boolean state) {
        FILE_VALIDATION.set(state);
        return this;
    }

    public FileConfigurationOptions performFileValidation() {
        return setPerformFileValidation(true);
    }

    public FileConfigurationOptions notPerformFileValidation() {
        return setPerformFileValidation(false);
    }
}
