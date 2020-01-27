package com.bpham.practice;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNull;

/**
 * https://leetcode.com/discuss/interview-question/356960
 */

public class FindPairWithGivenSum {
    public int[] find(int[] nums, int target) {
        if (nums == null) return null;
        int[] res = new int[2];
        target -= 30;
        int max = Integer.MIN_VALUE;
        Map<Integer, Integer> hash = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int comp = target - nums[i];
            if (hash.containsKey(nums[i])) {
                int firstIndex = hash.get(nums[i]);
                if (nums[i] > max || nums[firstIndex] > max) {
                    max = Math.max(nums[i], nums[firstIndex]);
                    res[0] = firstIndex;
                    res[1] = i;
                }
            } else {
                hash.put(comp, i);
            }
        }
        return res;
    }

    @Test
    public void test() {
        assertArrayEquals(new int[]{2, 3}, find(new int[]{1, 10, 25, 35, 60}, 90));
        assertArrayEquals(new int[]{1, 5}, find(new int[]{20, 50, 40, 25, 30, 10}, 90));
        assertArrayEquals(new int[]{0, 1}, find(new int[]{1, 1}, 32));
        assertNull(find(null, 32));
        assertArrayEquals(new int[]{0, 1}, find(new int[]{-1, 1}, 30));
        assertArrayEquals(new int[]{0, 1}, find(new int[]{-15, -15}, 0));
        assertArrayEquals(new int[]{2, 3}, find(new int[]{-15, -15, -60, 30}, 0));
        assertArrayEquals(new int[]{2, 3}, find(new int[]{-15, -15, 0, -30}, 0));
        assertArrayEquals(new int[]{2, 3}, find(new int[]{-15, -15, 0, -60}, -30));
        assertArrayEquals(new int[]{0, 1}, find(new int[]{-30, -30}, -30));
        assertArrayEquals(new int[]{0, 1}, find(new int[]{-45, -15, -30, -30}, -30));

    }
}
