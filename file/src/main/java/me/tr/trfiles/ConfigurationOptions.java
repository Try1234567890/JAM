package me.tr.trfiles;

import me.tr.trfiles.options.BooleanOption;
import me.tr.trfiles.options.CharacterOption;

/**
 * Various settings for controlling the input and output of a {@link
 * Configuration}
 */
public class ConfigOptions {
    public final CharacterOption PATH_SEPARATOR = new CharacterOption('.');
    public final BooleanOption NULLS_VALUES_REMOVE_THE_ENTRY = new BooleanOption(false);


    public ConfigOptions() {
    }
}
