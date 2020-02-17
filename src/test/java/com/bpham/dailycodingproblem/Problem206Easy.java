package com.bpham.dailycodingproblem;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This problem was asked by Twitter.
 *
 * A permutation can be specified by an array P, where P[i] represents the location of
 * the element at i in the permutation. For example, [2, 1, 0] represents the permutation
 * where elements at the index 0 and 2 are swapped.
 *
 * Given an array and a permutation, apply the permutation to the array. For example,
 * given the array ["a", "b", "c"] and the permutation [2, 1, 0], return ["c", "b", "a"].
 */

public class Problem206Easy {
    public char[] permute(char[] letters, int[] permutation) {
        char[] result = new char[letters.length];
        for (int i = 0; i < letters.length; i++) {
            int destination = permutation[i];
            char letter = letters[i];
            result[destination] = letter;
        }
        return result;
    }

    @Test
    public void test() {
        char[] result1 = permute(new char[]{'a', 'b', 'c'}, new int[]{2, 1, 0});
        assertEquals('c', result1[0]);
        assertEquals('b', result1[1]);
        assertEquals('a', result1[2]);

        char[] result2 = permute(new char[]{'a', 'b', 'c'}, new int[]{0, 1, 2});
        assertEquals('a', result2[0]);
        assertEquals('b', result2[1]);
        assertEquals('c', result2[2]);
    }
}
