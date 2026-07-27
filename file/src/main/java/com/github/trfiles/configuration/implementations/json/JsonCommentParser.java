package com.github.trfiles.configuration.implementations.json;

import com.github.trfiles.Utility;
import com.github.trfiles.configuration.comments.Comment;
import com.github.trfiles.configuration.comments.MultiLineComment;
import com.github.trfiles.configuration.comments.SingleLineComment;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class JsonCommentParser {
    public static final Set<String> COMMENTS_PREFIXES = Set.of("//", "/*");
    public static final String COMMENTS_SUFFIX = "*/";
    private final JsonConfiguration configuration;

    public JsonCommentParser(JsonConfiguration configuration) {
        this.configuration = configuration;
    }

    public JsonConfiguration getConfiguration() {
        return configuration;
    }

    public List<Comment> parse(String content) {
        List<Comment> comments = new ArrayList<>();
        if (content == null
                || content.isEmpty()) {
            return comments;
        }


        // We keep the empty lines to preserve the line numbers.
        String[] lines = Utility.getAllLines(content);

        for (int i = 0; i < lines.length; i++) {
            String line = lines[i].strip();

            // Check if the line starts with a comment symbol
            if (COMMENTS_PREFIXES.stream().anyMatch(line::startsWith)) {
                CommentLineType type = CommentLineType.getStartCommentType(line);

                if (CommentLineType.START_MULTI_LINE.equals(type)) {
                    MultiLineComment comment = parseMultiLineComment(lines, i);
                    comments.add(comment);
                    i = comment.end(); // Update index to the end of the multi-line comment.
                } else {
                    comments.add(new SingleLineComment(line, i));
                }
            }
        }
        return comments;
    }

    private MultiLineComment parseMultiLineComment(String[] lines, int start) {
        List<String> commentsLines = new ArrayList<>();
        int index = start;

        while (index < lines.length) {
            String currLine = lines[index];
            commentsLines.add(currLine); // Always add the current line first

            if (currLine.contains(COMMENTS_SUFFIX)) {
                break; // Stop if this line closes the comment
            }

            index++;
        }

        // Safeguard index to prevent it from going past the array bounds
        if (index >= lines.length) {
            index = lines.length - 1;
        }

        return new MultiLineComment(commentsLines.toArray(String[]::new), start, index);
    }

    private enum CommentLineType {
        START_MULTI_LINE,
        SINGLE_LINE_COMMENT;

        public static CommentLineType getStartCommentType(String line) {
            if (line.startsWith("/*")) return CommentLineType.START_MULTI_LINE;
            return CommentLineType.SINGLE_LINE_COMMENT;
        }
    }
}