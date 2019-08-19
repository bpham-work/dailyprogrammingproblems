package com.bpham.dailycodingproblem;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This problem was asked by Stripe.
 *
 * Given an array of integers, find the first missing positive integer in linear time and constant space.
 * In other words, find the lowest positive integer that does not exist in the array.
 * The array can contain duplicates and negative numbers as well.
 *
 * For example, the input [3, 4, -1, 1] should give 2. The input [1, 2, 0] should give 3.
 *
 * You can modify the input array in-place.
 */

public class Problem4Hard {

    /**
     * In place negating solution
     *
     * Trick: Separating negatives and positives to one side then negate for presence
     *
     * O(n) time
     * O(1) space
     */
    public int firstMissingPosInt(int[] arr) {
        int posStart = separatePosNeg(arr);
        for (int i = posStart; i < arr.length; i++) {
            if (arr[i] < arr.length) {
                arr[Math.abs(arr[i])] = -1 * Math.abs(arr[Math.abs(arr[i])]);
            }
        }
        int result = arr.length;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] >= 0) {
                result = i;
                break;
            }
        }
        return result;
    }

    public int separatePosNeg(int[] arr) {
        int left = 0;
        int right = arr.length-1;
        while (left < right) {
            while (left < arr.length && arr[left] <= 0) {
                left++;
            }
            while (right > 0 && arr[right] > 0) {
                right--;
            }
            if (left > right) {
                break;
            }
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
        }
        return left;
    }

    /**
     * Hash counting solution
     * O(n) time
     * O(n) space
     */
    public int firstMissingPosInt2(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int num : arr) {
            max = Math.max(num, max);
        }

        int[] count = new int[max+2];
        for (int num : arr) {
            if (num > 0) {
                count[num]++;
            }
        }

        int result = 1;
        for (int i = 1; i < count.length; i++) {
            if (count[i] == 0) {
                result = i;
                break;
            }
        }

        return result;
    }

    @Test
    public void test() {
        assertEquals(2, firstMissingPosInt(new int[]{3, 4, -1, 1}));
        assertEquals(2, firstMissingPosInt(new int[]{3, 4, 1, 1}));
        assertEquals(4, firstMissingPosInt(new int[]{3, 2, 1, 0}));
        assertEquals(3, firstMissingPosInt(new int[]{1, 2, 0}));
    }
}
