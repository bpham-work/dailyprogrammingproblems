package com.bpham.ctci.recursionanddp;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * #1
 *
 * A child is running up a staircase with n steps and can hop either 1 step,
 * 2 steps, or 3 steps at a time. Implement a method to count how many possible
 * ways the child can run up the stairs.
 */

public class TripleStep {
    public int countUnoptimized(int n) {
        if (n == 0) return 0;
        return countHelper(n);
    }

    public int countHelper(int n) {
        if (n == 0) return 1;
        if (n < 0) return 0;
        return countHelper(n-3) +
                countHelper(n-2) +
                countHelper(n-1);
    }

    public int count(int n) {
        if (n == 0) return 0;
        return countMemo(n, new int[n+1]);
    }

    public int countMemo(int n, int[] memo) {
        if (n < 0) return 0;
        if (n == 0) return 1;
        if (memo[n] > 0) return memo[n];
        memo[n] = countMemo(n-3, memo) + countMemo(n-2, memo) + countMemo(n-1, memo);
        return memo[n];
    }

    @Test
    public void test() {
        assertEquals(0, count(0));
        assertEquals(1, count(1));
        assertEquals(2, count(2));
        assertEquals(4, count(3));
        assertEquals(13, count(5));
    }
}
