package com.bpham.ctci.stringsandarrays;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * #1
 *
 * Implement an algorithm to determine if a string has all unique characters.
 * What if you cannot use additional data structures?
 */

public class UniqueCharacters {
    /**
     * Time: O(n)
     * Space: O(n)
     *
     * @param str
     * @return
     */
    public boolean hasUniqueCharsSol1(String str) {
        if (str.isEmpty()) return false;
        Set<Character> chars = new HashSet<>();
        for (Character letter : str.toCharArray()) {
            if (chars.contains(letter)) {
                return false;
            }
            chars.add(letter);
        }
        return true;
    }

    /**
     * No additional data structures
     *
     * Time: O(nlogn)
     * Space: O(1)
     *
     * @param str
     * @return
     */
    public boolean hasUniqueCharsSol2(String str) {
        if (str.isEmpty()) return false;
        char[] sorted = str.toCharArray();
        Arrays.sort(sorted);
        Character current = null;
        for (char letter : sorted) {
            if (current != null && letter == current) {
                return false;
            }
            current = letter;
        }
        return true;
    }

    @Test
    public void sol1_hasUniqueChars() {
        assertTrue(hasUniqueCharsSol1("abc"));
    }

    @Test
    public void sol2_hasUniqueChars() {
        assertTrue(hasUniqueCharsSol2("abc"));
    }

    @Test
    public void sol1_noUniqueChars() {
        assertFalse(hasUniqueCharsSol1("aba"));
    }

    @Test
    public void sol2_noUniqueChars() {
        assertFalse(hasUniqueCharsSol2("aba"));
    }

    @Test
    public void sol1_emptyString() {
        assertFalse(hasUniqueCharsSol1(""));
    }

    @Test
    public void sol2_emptyString() {
        assertFalse(hasUniqueCharsSol2(""));
    }

    @Test
    public void sol1_hasUniqueNonAlphabeticChars() {
        assertTrue(hasUniqueCharsSol1("~abc"));
    }

    @Test
    public void sol2_hasUniqueNonAlphabeticChars() {
        assertTrue(hasUniqueCharsSol2("~abc"));
    }

    @Test
    public void sol1_noUniqueNonAlphabeticChars() {
        assertFalse(hasUniqueCharsSol1("~abc~"));
    }

    @Test
    public void sol2_noUniqueNonAlphabeticChars() {
        assertFalse(hasUniqueCharsSol2("~abc~"));
    }
}
