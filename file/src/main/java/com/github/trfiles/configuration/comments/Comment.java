package com.github.trfiles.configuration.comments;

public interface Comment {

    /**
     * Retrieves the lines of the comment
     *
     * @return the lines of the comment
     */
    String[] text();

    /**
     * Retrieves the content of the comment as a single string.
     * <p>Or in other words the {@link #text()} joined by newlines.</p>
     *
     * @return the content of the comment as a single string.
     */
    default String getContent() {
        return String.join("\n", text());
    }

    /**
     * Retrieve the line where the comment starts inside the configuration.
     *
     * @return the line where the comment starts.
     * @see #end()
     */
    int start();

    /**
     * Retrieve the line where the comment ends inside the configuration.
     *
     * @return the line where the comment ends.
     * @see #start()
     */
    int end();

}
