package com.bpham.ctci.stringsandarrays;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Given two strings, write a method to decide if one is a permutation of the other
 *
 * Questions:
 * 1. Does case matter? e.g. is Dog permutation of dog? Assume yes
 */

public class CheckPermutation {
    /**
     * Time: O(nlogn) where n = length of longest string
     * Space: O(n)? from sorting
     *
     * @param s1
     * @param s2
     * @return
     */
    public boolean isPermutationSol1(String s1, String s2) {
        char[] s1CharArray = s1.toCharArray();
        char[] s2CharArray = s2.toCharArray();
        Arrays.sort(s1CharArray);
        Arrays.sort(s2CharArray);
        return Arrays.equals(s1CharArray, s2CharArray);
    }

    /**
     * Time: O(n)
     * Space: O(n)
     *
     * @param s1
     * @param s2
     * @return
     */
    public boolean isPermutationSol2(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        Map<Character, Integer> s1Letters = new HashMap<>();
        for (char s1Letter : s1.toCharArray()) {
            int count = s1Letters.getOrDefault(s1Letter, 0) + 1;
            s1Letters.put(s1Letter, count);
        }
        for (char s2Letter : s2.toCharArray()) {
            if (!s1Letters.containsKey(s2Letter)) return false;
            int count = s1Letters.get(s2Letter);
            if (count == 0) {
                return false;
            }
            s1Letters.put(s2Letter, count-1);
        }
        return true;
    }

    /**
     * Time: O(n)
     * Space: O(1) - set with never be more than 128 bc only 128 possible ascii characters
     *
     * @param s1
     * @param s2
     * @return
     */
    public boolean isPermutationSol3(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        int[] letters = new int[128];
        for (char s1Letter : s1.toCharArray()) {
            letters[s1Letter]++;
        }
        for (char s2Letter : s2.toCharArray()) {
            if (letters[s2Letter] == 0) {
                return false;
            }
            letters[s2Letter]--;
        }
        return true;
    }

    @Test
    public void isPermutation_Sol1() {
        assertTrue(isPermutationSol1("abc", "cba"));
        assertTrue(isPermutationSol1("a", "a"));
        assertTrue(isPermutationSol1("", ""));
    }

    @Test
    public void isNotPermutation_Sol1() {
        assertFalse(isPermutationSol1("abc", "cbx"));
        assertFalse(isPermutationSol1("abc", "cbxd"));
        assertFalse(isPermutationSol2("abc", "ABC"));
        assertFalse(isPermutationSol1("abc", "cbaa"));
        assertFalse(isPermutationSol1("abc", "cbb"));
        assertFalse(isPermutationSol1("a", "c"));
        assertFalse(isPermutationSol1("a", ""));
    }

    @Test
    public void isPermutation_Sol2() {
        assertTrue(isPermutationSol2("abc", "cba"));
        assertTrue(isPermutationSol2("a", "a"));
        assertTrue(isPermutationSol2("", ""));
    }

    @Test
    public void isNotPermutation_Sol2() {
        assertFalse(isPermutationSol2("abc", "cbx"));
        assertFalse(isPermutationSol2("abc", "ABC"));
        assertFalse(isPermutationSol2("abc", "cbxd"));
        assertFalse(isPermutationSol2("abc", "cbaa"));
        assertFalse(isPermutationSol2("abc", "cbb"));
        assertFalse(isPermutationSol2("a", "c"));
        assertFalse(isPermutationSol2("a", ""));
    }

    @Test
    public void isPermutation_Sol3() {
        assertTrue(isPermutationSol2("abc", "cba"));
        assertTrue(isPermutationSol2("a", "a"));
        assertTrue(isPermutationSol2("", ""));
    }

    @Test
    public void isNotPermutation_Sol3() {
        assertFalse(isPermutationSol3("abc", "cbx"));
        assertFalse(isPermutationSol3("abc", "cbxd"));
        assertFalse(isPermutationSol3("abc", "cbaa"));
        assertFalse(isPermutationSol2("abc", "ABC"));
        assertFalse(isPermutationSol3("abc", "cbb"));
        assertFalse(isPermutationSol3("a", "c"));
        assertFalse(isPermutationSol3("a", ""));
    }
}
