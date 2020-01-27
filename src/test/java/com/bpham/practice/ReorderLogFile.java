package com.bpham.practice;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNull;

/**
 * https://leetcode.com/problems/reorder-data-in-log-files/submissions/
 */

public class ReorderLogFile {
    public String[] reorderLogFiles(String[] logs) {
        if (logs == null) return new String[0];
        List<String> letters = new ArrayList<>();
        List<String> digits = new ArrayList<>();
        for (String log : logs) {
            if (isLetterLog(log)) {
                letters.add(log);
            } else {
                digits.add(log);
            }
        }
        Collections.sort(letters, (a, b) -> re(a).compareTo(re(b)));
        letters.addAll(digits);
        return letters.toArray(new String[letters.size()]);
    }

    public boolean isLetterLog(String log) {
        return Character.isAlphabetic(log.charAt(log.indexOf(" ")+1));
    }

    public String re(String s) {
        int space = s.indexOf(" ");
        return s.substring(space+1) + s.substring(0, space);
    }

    @Test
    public void test() {
        assertArrayEquals(new String[]{"let1 art can","let3 art zero","let2 own kit dig","dig1 8 1 5 1","dig2 3 6"},
                reorderLogFiles(new String[]{"dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"}));

        assertArrayEquals(new String[]{"let1 art","let2 art","dig1 8 1 5 1","dig2 3 6"},
                reorderLogFiles(new String[]{"dig1 8 1 5 1","let1 art","dig2 3 6","let2 art"}));

        assertArrayEquals(new String[]{"let1 art","let2 art","dig1 8 1 5 1","dig1 3 6"},
                reorderLogFiles(new String[]{"dig1 8 1 5 1","let1 art","dig1 3 6","let2 art"}));

        assertArrayEquals(new String[]{"let1 art", "dig1 8 1 5 1","dig2 3 6"},
                reorderLogFiles(new String[]{"dig1 8 1 5 1","let1 art","dig2 3 6"}));

        assertArrayEquals(new String[]{"let1 art", "let1 art", "dig1 8 1 5 1","dig2 3 6"},
                reorderLogFiles(new String[]{"let1 art", "dig1 8 1 5 1","let1 art","dig2 3 6"}));

        assertArrayEquals(new String[0], reorderLogFiles(null));
    }
}
