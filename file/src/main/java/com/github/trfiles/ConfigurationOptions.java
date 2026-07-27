package com.github.trfiles;


import com.github.utilities.options.BooleanOption;
import com.github.utilities.options.CharacterOption;

/**
 * Various settings for controlling the input and output of a {@link
 * Configuration}
 */
public class ConfigurationOptions {
    public final CharacterOption PATH_SEPARATOR = new CharacterOption('.');
    public final BooleanOption NULLS_VALUES_REMOVE_THE_ENTRY = new BooleanOption(false);

}
