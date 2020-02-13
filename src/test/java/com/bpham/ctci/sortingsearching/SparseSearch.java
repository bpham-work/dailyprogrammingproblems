package com.bpham.ctci.sortingsearching;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * #5
 *
 * Given a sorted array of strings that is interspersed with empty strings,
 * write a method to find the location of a given string.
 */

public class SparseSearch {
    public int search(String[] arr, String target) {
        int hi = arr.length-1;
        int mid = hi/2;
        return search(arr, 0, hi, mid, target);
    }

    public int search(String[] arr, int lo, int hi, int mid, String target) {
        if (lo > hi) return -1;
        if (arr[mid].equals(target)) {
            return mid;
        } else if (arr[mid].isEmpty()) {
            int newMid =  mid;
            while (arr[newMid].isEmpty()) {
                newMid++;
            }
            return search(arr, lo, hi, newMid, target);
        } else if (arr[mid].compareTo(target) < 0) {
            lo = mid+1;
        } else {
            hi = mid-1;
        }
        int newMid = (lo + hi) / 2;
        return search(arr, lo, hi, newMid, target);
    }

    @Test
    public void test() {
        assertEquals(4, search(new String[] {
             "at", "", "", "", "ball", "", "", "car", "", "", "dad", "", ""
        }, "ball"));

        assertEquals(5, search(new String[]{"a", "", "", "", "", "c"}, "c"));

    }
}
