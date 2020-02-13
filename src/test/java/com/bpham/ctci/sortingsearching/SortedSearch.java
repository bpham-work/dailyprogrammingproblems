package com.bpham.ctci.sortingsearching;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * #4
 *
 * ou are given an array-like data structure Listy which lacks a size method.
 * It does, however, have an elementAt(i) method that returns the element at
 * index i in 0(1) time. If i is beyond the bounds of the data structure, it
 * returns -1. (For this reason, the data structure only supports positive integers.)
 *
 * Given a Listy which contains sorted, positive integers, find the index at which an
 * element x occurs. If x occurs multiple times, you may return any index.
 */

public class SortedSearch {
    public int search(List<Integer> list, int target) {
        int index = 1;
        Listy listy = new Listy(list);
        while (listy.elementAt(index) != -1) {
            index *= 2;
        }
        return binarySearch(listy, target, 0, index);
    }

    public int binarySearch(Listy list, int target, int lo, int hi) {
        if (lo > hi) return -1;
        int mid = (lo + hi) / 2;
        if (list.elementAt(mid) == target) {
            return mid;
        } else if (list.elementAt(mid) == -1 || target < list.elementAt(mid)) {
            return binarySearch(list, target, lo, mid-1);
        } else {
            return binarySearch(list, target, mid+1, hi);
        }
    }

    @Test
    public void test() {
        assertEquals(5, search(Arrays.asList(0, 1, 2, 3, 4, 5), 5));
        assertEquals(0, search(Arrays.asList(0, 1, 2, 3, 4, 5), 0));
        assertEquals(-1, search(Arrays.asList(0, 1, 2, 3, 4, 5), 10));
    }

    private class Listy {
        List<Integer> list = new ArrayList<>();

        public Listy(List<Integer> list) {
            this.list = list;
        }

        public int elementAt(int i) {
            if (i >= list.size()) return -1;
            return list.get(i);
        }
    }
}
