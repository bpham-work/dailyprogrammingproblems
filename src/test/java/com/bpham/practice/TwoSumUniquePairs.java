package com.bpham.practice;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/discuss/interview-question/372434
 */

public class TwoSumUniquePairs {

    public int numUniquePairs(int[] nums, int target) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }
        List<Integer> pairs = new ArrayList<>();
        for (int num : nums) {
            count.put(num, count.get(num) - 1);
            int comp = target - num;
            if (count.containsKey(comp) && count.get(comp) > 0 && !pairs.contains(num)) {
                pairs.add(comp);
                pairs.add(num);
            }
            count.put(num, count.get(num) + 1);
        }
        return pairs.size() / 2;
    }

    @Test
    public void test() {
        assertEquals(2, numUniquePairs(new int[]{1, 1, 2, 45, 46, 46}, 47));
        assertEquals(1, numUniquePairs(new int[]{1, 1}, 2));
        assertEquals(1, numUniquePairs(new int[]{1, 5, 1, 5}, 6));
    }
}
