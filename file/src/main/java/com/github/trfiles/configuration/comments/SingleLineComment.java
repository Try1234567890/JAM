package com.github.trfiles.configuration.comments;

public record SingleLineComment(String[] text, int line) implements Comment {
    public SingleLineComment(String text, int line) {
        this(new String[]{text}, line);
    }

    /**
     * Retrieves the line of this comment.
     *
     * @return the line of this comment.
     */
    public String getLine() {
        return text[0];
    }

    /**
     * Retrieves the line number of this comment.
     *
     * @return the line number of this comment.
     */
    @Override
    public int line() {
        return line;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String[] text() {
        return text;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int start() {
        return line;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int end() {
        return line;
    }
}
