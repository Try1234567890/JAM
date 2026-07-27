package com.github.trfiles.configuration.comments;

public class MultiLineComment implements Comment {
    private final String[] content;
    private final int start;
    private final int end;

    public MultiLineComment(String[] content, int start, int end) {
        this.content = content;
        this.start = start;
        this.end = end;
    }

    @Override
    public String[] getLines() {
        return content;
    }

    @Override
    public int start() {
        return start;
    }

    @Override
    public int end() {
        return end;
    }
}
