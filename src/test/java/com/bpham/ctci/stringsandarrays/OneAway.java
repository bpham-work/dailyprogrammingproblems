package com.bpham.ctci.stringsandarrays;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * #5
 *
 * There are three types of edits that can be performed on strings: insert a character, remove a character, or replace a character.
 * Given two strings, write a function to check if they are one edit (or zero edits) away.
 *
 * EXAMPLE
 * pale, ple -> true
 * pales, pale -> true
 * pale, bale -> true
 * pale, bake -> false
 */

public class OneAway {
    public boolean isOneAwaySol1(String s1, String s2) {
        if (Math.abs(s1.length() - s2.length()) > 1) {
            return false;
        }

        if (s1.length() == s2.length()) {
            return needReplacement(s1, s2);
        } else {
            return needsInsertOrRemove(s1, s2);
        }
    }

    public boolean needReplacement(String s1, String s2) {
        int numDiff = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                numDiff++;
            }
        }
        return numDiff <= 1;
    }

    public boolean needsInsertOrRemove(String s1, String s2) {
        String longer = s1.length() > s2.length() ? s1 : s2;
        String shorter = s1.length() > s2.length() ? s2 : s1;
        int longIndex = 0;
        int shortIndex = 0;
        int numDiff = 0;
        while (shortIndex < shorter.length()) {
            if (shorter.charAt(shortIndex) != longer.charAt(longIndex)) {
                numDiff++;
            } else {
                shortIndex++;
            }
            longIndex++;
        }
        return numDiff <= 1;
    }

    @Test
    public void isOneAway_Sol1() {
        assertTrue(isOneAwaySol1("pale", "ple"));
        assertTrue(isOneAwaySol1("pales", "pale"));
        assertTrue(isOneAwaySol1("pale", "bale"));
        assertTrue(isOneAwaySol1("herp", "derp"));
        assertTrue(isOneAwaySol1("pale", "pple"));
        assertTrue(isOneAwaySol1("pale", "pale"));
    }

    @Test
    public void isNotOneAway_Sol1() {
        assertFalse(isOneAwaySol1("ppale", "ple"));
        assertFalse(isOneAwaySol1("pale", "bake"));
        assertFalse(isOneAwaySol1("no", "ye"));
        assertFalse(isOneAwaySol1("pale", "elap"));
    }
}
