package com.bpham.ctci.stringsandarrays;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * #4
 *
 * Given a string, write a function to check if it is a permutation of a palin- drome.
 * A palindrome is a word or phrase that is the same forwards and backwards.
 * A permutation is a rearrangement of letters.
 * The palindrome does not need to be limited to just dictionary words.
 *   EXAMPLE
 *   Input: Tact Coa
 *   Output: True (permutations: "taco cat". "atco cta". etc.)
 *
 * Questions:
 * 1. Case sensitive?
 * 2. Do whitespaces matter? - no
 *
 * tactcoa
 */

public class PalindromePermutation {
    /**
     * Time: O(n)
     * Space: O(n)
     *
     * @param word
     * @return
     */
    public boolean isPermutationOfPalindromeSol1(String word) {
        word = word.toLowerCase();
        word = word.replaceAll("\\s+", "");
        Set<Character> lettersWithNoPair = new HashSet<>();
        for (char letter : word.toCharArray()) {
            if (lettersWithNoPair.contains(letter)) {
                lettersWithNoPair.remove(letter);
            } else {
                lettersWithNoPair.add(letter);
            }
        }
        boolean isEven = word.length() % 2 == 0;
        return isEven && lettersWithNoPair.size() == 0 || !isEven && lettersWithNoPair.size() == 1;
    }

    @Test
    public void isPalindromeSol1() {
        assertTrue(isPermutationOfPalindromeSol1("Tact Coa"));
        assertTrue(isPermutationOfPalindromeSol1("taco cat"));
        assertTrue(isPermutationOfPalindromeSol1("abba"));
    }

    @Test
    public void isNotPalindromeSol1() {
        assertFalse(isPermutationOfPalindromeSol1("cat"));
        assertFalse(isPermutationOfPalindromeSol1("ab"));
    }
}
