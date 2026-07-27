package com.github.trfiles.management;

import com.github.utilities.validators.Preconditions;

public record FileExtension(String extension) {

    /**
     * @param extension the extension without the dot;
     */
    public FileExtension(String extension) {
        Preconditions.parameterNotNull(extension, "extension");
        this.extension = extension.startsWith(".") ? extension.substring(1) : extension;
    }

    public String get() {
        return extension;
    }

    public String getWithDot() {
        return "." + extension;
    }

    public boolean is(String ext) {
        if (ext.startsWith(".")) {
            return ext.equalsIgnoreCase(getWithDot());
        } else {
            return ext.equalsIgnoreCase(get());
        }
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof FileExtension(String ext))) return false;
        return extension.equalsIgnoreCase(ext);
    }
}
