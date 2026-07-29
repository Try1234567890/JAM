package com.github.trfiles.configuration.comments;

public record MultiLineComment(String[] text, int start, int end) implements Comment {
}
