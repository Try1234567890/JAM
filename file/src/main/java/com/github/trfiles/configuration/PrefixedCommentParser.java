package com.github.trfiles.configuration;

import com.github.trfiles.Utility;
import com.github.trfiles.configuration.comments.Comment;
import com.github.trfiles.configuration.comments.SingleLineComment;

import java.util.ArrayList;
import java.util.List;

public class PrefixedCommentParser {
    private final String prefix;

    public PrefixedCommentParser(String prefix) {
        this.prefix = prefix;
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
            String line = lines[i];
            if (line.startsWith(prefix)) {
                /* The YAML configuration can't really have multi-line comments,
                 * maybe we can consider 'multi line comments' if we found consecutive
                 * single line comments. But for now, we will just consider only single line
                 * comments.
                 */
                Comment comment = new SingleLineComment(line, i);
                comments.add(comment);
            }
        }

        return comments;
    }
}
