package com.bpham.dailycodingproblem;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 *
 * Good morning! Here's your coding interview problem for today.
 *
 * This problem was recently asked by Google.
 *
 * Given a list of numbers and a number k, return whether any two numbers from the list add up to k.
 *
 * For example, given [10, 15, 3, 7] and k of 17, return true since 10 + 7 is 17.
 *
 * Bonus: Can you do this in one pass?
 * */

public class Problem1Easy {

    /**
     * Time: O(n)
     * Space: O(n)
     *
     * Where n is the size of array
     * */
    public boolean sumExists(int[] nums, int k) {
        boolean result = false;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            int comp = k - num;
            if (set.contains(comp)) {
                result = true;
                break;
            }
            set.add(num);
        }
        return result;
    }

    @Test
    public void test() {
        assertTrue(sumExists(new int[]{10, 15, 3, 7}, 17));
        assertFalse(sumExists(new int[]{10, 15, 3, 7}, 50));
        assertFalse(sumExists(new int[]{}, 50));
    }
}
