package com.bpham.dailycodingproblem;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Good morning! Here's your coding interview problem for today.
 *
 * This problem was asked by Uber.
 *
 * Given an array of integers, return a new array such that each element at index i of the new array is the product of all the numbers in the original array except the one at i.
 *
 * For example, if our input was [1, 2, 3, 4, 5], the expected output would be [120, 60, 40, 30, 24]. If our input was [3, 2, 1], the expected output would be [2, 3, 6].
 *
 * Follow-up: what if you can't use division?
 */

public class Problem2Hard {

    /**
     * Time: O()
     * Space: O()
     */
    public int[] products(int[] nums) {
        int[] result = new int[nums.length];
        int[] left = new int[nums.length];
        int[] right = new int[nums.length];
        int leftProd = 1;
        int rightProd = 1;
        for (int i = 0; i < nums.length; i++) {
            leftProd *= nums[i];
            rightProd *= nums[nums.length-1-i];
            left[i] = leftProd;
            right[i] = rightProd;
        }
        for (int i = 0; i < nums.length; i++) {
            int leftIndex = i-1;
            int rightIndex = nums.length - (i + 1) - 1;
            int prod = 1;
            if (leftIndex > -1) {
                prod *= left[leftIndex];
            }
            if (rightIndex > -1) {
                prod *= right[rightIndex];
            }
            result[i] = prod;
        }
        return result;
    }

    /**
     * Time: O(n)
     * Space: O(1)
     */
    public int[] products3(int[] nums) {
        int[] result = new int[nums.length];
        int totalProduct = 1;
        for (int num : nums) {
            totalProduct *= num;
        }
        for (int i = 0; i < nums.length; i++) {
            result[i] = totalProduct / nums[i];
        }
        return result;
    }

    /**
     * Time: O(n^2)
     * Space: O(1)
     */
    public int[] products2(int[] nums) {
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int prod = 1;
            for (int k = 0; k < nums.length; k++) {
                if (i == k) {
                    continue;
                }
                prod *= nums[k];
            }
            result[i] = prod;
        }
        return result;
    }

    @Test
    public void test1() {
        int[] input = new int[] {1, 2, 3, 4, 5};

        int[] result = products(input);

        assertEquals(120, result[0]);
        assertEquals(60, result[1]);
        assertEquals(40, result[2]);
        assertEquals(30, result[3]);
        assertEquals(24, result[4]);
    }

    @Test
    public void test2() {
        int[] input = new int[] {3, 2, 1};

        int[] result = products(input);

        assertEquals(2, result[0]);
        assertEquals(3, result[1]);
        assertEquals(6, result[2]);
    }
}
