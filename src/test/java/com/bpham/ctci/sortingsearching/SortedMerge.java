package com.bpham.ctci.sortingsearching;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * #1
 *
 * You are given two sorted arrays, A and B, where A has a large enough buffer at the
 * end to hold B. Write a method to merge B into A in sorted order.
 */

public class SortedMerge {
    public void sort(int[] A, int[] B, int aEnd) {
        int i = A.length-1;
        int aPtr = aEnd;
        int bPtr = B.length-1;
        while (i >= 0) {
            if (aPtr < 0) {
                A[i] = B[bPtr--];
            } else if (bPtr < 0) {
                A[i] = A[aPtr--];
            } else if (A[aPtr] >= B[bPtr]) {
                A[i] = A[aPtr--];
            } else {
                A[i] = B[bPtr--];
            }
            i--;
        }
    }

    @Test
    public void test1() {
        int[] A = new int[] { 4, 5, 0, 0 };
        int[] B = new int[] { 1, 2 };

        sort(A, B, 1);

        assertEquals(1, A[0]);
        assertEquals(2, A[1]);
        assertEquals(4, A[2]);
        assertEquals(5, A[3]);
    }

    @Test
    public void test2() {
        int[] A = new int[] { 4, 10, 0, 0 };
        int[] B = new int[] { 5, 6 };

        sort(A, B, 1);

        assertEquals(4, A[0]);
        assertEquals(5, A[1]);
        assertEquals(6, A[2]);
        assertEquals(10, A[3]);
    }

    @Test
    public void test3() {
        int[] A = new int[] { 1, 4, 4, 5, 10, 50, 0, 0 };
        int[] B = new int[] { 5, 12 };

        sort(A, B, 5);

        assertEquals(1, A[0]);
        assertEquals(4, A[1]);
        assertEquals(4, A[2]);
        assertEquals(5, A[3]);
        assertEquals(5, A[4]);
        assertEquals(10, A[5]);
        assertEquals(12, A[6]);
        assertEquals(50, A[7]);
    }
}
