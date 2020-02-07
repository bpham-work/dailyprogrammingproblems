package com.bpham.ctci;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * #3
 *
 * A magic index in an array A[e... n-1] is defined to be an index
 * such that A[ i] = i. Given a sorted array of distinct integers,
 * write a method to find a magic index, if one exists, in array A.
 *
 * FOLLOW UP
 * What if the values are not distinct?
 */

public class MagicIndex {
    public int getMagicIndex(int[] arr) {
        return getMagicIndex2(arr, 0, arr.length-1);
    }

    public int getMagicIndex2(int[] arr, int lo, int hi) {
        if (lo > hi) return -1;
        int mid = (hi + lo) / 2;
        if (mid == arr[mid]) return mid;
        int left = getMagicIndex2(arr, lo, Math.min(mid-1, arr[mid]));
        int right = getMagicIndex2(arr, Math.max(mid+1, arr[mid]), hi);
        if (left > -1) return left;
        if (right > -1) return right;
        return -1;
    }

    public int getMagicIndex(int[] arr, int lo, int hi) {
        if (lo > hi) return -1;
        int mid = (hi + lo) / 2;
        if (mid == arr[mid]) {
            return mid;
        }
        if (mid > arr[mid]) {
            return getMagicIndex(arr, mid + 1, hi);
        } else {
            return getMagicIndex(arr, lo, mid-1);
        }
    }


    @Test
    public void test() {
        assertEquals(3, getMagicIndex(new int[] {-1, 0, 1, 3, 5}));
        assertEquals(1, getMagicIndex(new int[] {-1, 1, 3, 4, 5}));
        assertEquals(0, getMagicIndex(new int[] {0, 2, 3, 4, 5}));
        assertEquals(-1, getMagicIndex(new int[] {5, 4, 3, 2, 1}));

        assertEquals(0, getMagicIndex(new int[] {0, 0, 0, 0, 5}));
        assertEquals(4, getMagicIndex(new int[] {-1, 4, 4, 4, 4}));
    }
}
