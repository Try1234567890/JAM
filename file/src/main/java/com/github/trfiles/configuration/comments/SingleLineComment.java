package com.github.trfiles.configuration.comments;

public class SingleLineComment implements Comment {
    private final String[] line;
    private final int index;

    public SingleLineComment(String line, int index) {
        this.line = new String[]{line};
        this.index = index;
    }

    public String getLine() {
        return line[0];
    }

    @Override
    public String[] getLines() {
        return line;
    }

    @Override
    public int start() {
        return index;
    }

    @Override
    public int end() {
        return index;
    }
}
