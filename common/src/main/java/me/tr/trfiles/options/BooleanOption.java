package me.tr.trfiles.options;

/**
 * Represent an option that contains a {@link Boolean}.
 */
public class BooleanOption extends Option<Boolean> {
    public BooleanOption() {
    }

    public BooleanOption(Boolean value) {
        super(value);
    }

    @Override
    public String toString() {
        return get() == null ? "None" : (get() ? "Yes" : "No");
    }
}
