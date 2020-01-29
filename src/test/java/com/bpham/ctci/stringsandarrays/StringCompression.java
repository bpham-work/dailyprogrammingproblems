package com.bpham.ctci.stringsandarrays;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * #6
 *
 * Implement a method to perform basic string compression using the counts of repeated characters.
 * For example, the string aabcccccaaa would become a2b1c5a3.
 * If the "compressed" string would not become smaller than the original string,
 * your method should return the original string.
 * You can assume the string has only uppercase and lowercase letters (a - z).
 */

public class StringCompression {
    public String compressSol1(String s) {
        StringBuilder sb = new StringBuilder();
        char curr = s.charAt(0);
        int count = 0;
        for (char letter : s.toCharArray()) {
            if (letter == curr) {
                count++;
            } else {
                sb.append(curr);
                sb.append(count);
                count = 1;
                curr = letter;
            }
        }
        sb.append(curr);
        sb.append(count);
        String result = sb.toString();
        return result.length() < s.length() ? result : s;
    }

    @Test
    public void test() {
        assertEquals("a2b1c5a3", compressSol1("aabcccccaaa"));
        assertEquals("aab", compressSol1("aab"));
    }
}
