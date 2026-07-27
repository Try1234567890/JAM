package me.tr.trfiles.options;

/**
 * Represent an option that contains a {@link Enum}.
 */
public class EnumOption<E extends Enum<E>> extends Option<E> {

    public EnumOption() {
    }

    public EnumOption(E value) {
        super(value);
    }
}
