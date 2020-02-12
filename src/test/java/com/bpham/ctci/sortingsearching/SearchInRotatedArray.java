package com.bpham.ctci.sortingsearching;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * #3
 *
 *  Given a sorted array of n integers that has been rotated an unknown
 *  number of times, write code to find an element in the array.
 *  You may assume that the array was
 *  originally sorted in increasing order.
 *
 *  EXAMPLE
 *  Input: find 5 in {15, 16, 19, 20, 25, 1, 3,4,5,7,10, 14}
 *  Output 8 (the index of 5 in the array)
 */

public class SearchInRotatedArray {
    public int searchRotated(int[] arr, int target) {
        return searchRotated(arr, target, 0, arr.length-1);
    }

    public int searchRotated(int[] arr, int target, int lo, int hi) {
        if ((lo == hi && arr[lo] != target) || lo > hi) return -1;
        int mid = (lo + hi) / 2;
        if (arr[mid] == target) return mid;
        else if (arr[mid] < arr[hi]) {
            // right is sorted
            if (arr[mid] < target && arr[hi] >= target) {
                return searchRotated(arr, target, mid+1, hi);
            } else {
                return searchRotated(arr, target, lo, mid-1);
            }
        } else if (arr[mid] > arr[lo]) {
            // left is sorted
            if (arr[lo] <= target && arr[mid] > target) {
                return searchRotated(arr, target, lo, mid-1);
            } else {
                return searchRotated(arr, target, mid+1, hi);
            }
        } else {
            return Math.max(searchRotated(arr, target, lo, mid-1),
                    searchRotated(arr, target, mid+1, hi));
        }
    }

    @Test
    public void test1() {
        assertEquals(8, searchRotated(new int[] {15,16,19,20,25,1,3,4,5,7,10,14}, 5));
        assertEquals(1, searchRotated(new int[] {4,5,1,2,3}, 5));
        assertEquals(3, searchRotated(new int[] {2,3,4,5,1}, 5));
        assertEquals(1, searchRotated(new int[] {2,3,1,1,1}, 3));
        assertEquals(1, searchRotated(new int[] {1,3,1,1,1}, 3));
    }
}
