package com.bpham.ctci.stringsandarrays;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * #3
 *
 * Write a method to replace all spaces in a string with '%20'.
 * You may assume that the string has sufficient space at the end to hold the additional characters,
 * and that you are given the "true" length of the string.
 * (Note: If implementing in Java, please use a character array so that you can perform this operation
 * in place.)
 *
 * Questions:
 * 1. ASCII chars?
 * 2. How to handle empty string?
 */

public class URLify {
    public String convertSol1(char[] s, int trueLength) {
        int lastCharIndex = trueLength - 1;
        int lastPos = s.length - 1;
        for (; lastCharIndex >= 0; lastCharIndex--) {
            if (s[lastCharIndex] != ' ') {
                s[lastPos] = s[lastCharIndex];
                lastPos--;
            } else {
                s[lastPos] = '0';
                s[lastPos-1] = '2';
                s[lastPos-2] = '%';
                lastPos -= 3;
            }
        }
        return new String(s);
    }

    @Test
    public void testSol1() {
        assertEquals("Mr%20John%20Smith", convertSol1("Mr John Smith    ".toCharArray(), 13));
    }
}
