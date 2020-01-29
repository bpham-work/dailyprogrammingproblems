package com.bpham.ctci.stringsandarrays;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * #9
 *
 * Assume you have a method isSubstring which checks if one
 * word is a substring of another. Given two strings, s1 and s2,
 * write code to check if s2 is a rotation of s1 using only one call to
 * isSubstring (e.g., "waterbottle" is a rotation of "erbottlewat").
 */

public class StringRotation {
    public boolean isRotation(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        String concat = s2 + s2;
        return isSubstring(concat, s1);
    }

    public boolean isSubstring(String s1, String s2) {
        return s1.contains(s2);
    }

    @Test
    public void test() {
        assertTrue(isRotation("waterbottle", "erbottlewat"));
        assertFalse(isRotation("waterbottle", "erbottlezzz"));
        assertFalse(isRotation("wateerbottl", "erbottlewat"));
        assertFalse(isRotation("aa","bb"));
        assertFalse(isRotation("aaaa","b"));
    }
}
