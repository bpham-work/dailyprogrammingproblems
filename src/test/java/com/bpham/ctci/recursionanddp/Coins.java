package com.bpham.ctci.recursionanddp;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * #11
 *
 * Given an infinite number of quarters (25 cents), dimes (10 cents),
 * nickels (5 cents), and pennies (1 cent), write code to calculate
 * the number of ways of representing n cents.
 */

public class Coins {
    public int countWays(int n) {
        return countWays(n, new int[] { 25, 10, 5, 1 }, 0, new int[n+1][4]);
    }

    public int countWays(int n, int[] deno, int pos, int[][] map) {
        if (n < 0) return 0;
        if (deno[pos] == 1) return 1;
        if (n == 0) return 1;
        if (map[n][pos] > 0) return map[n][pos];
        int count = 0;
        for (int i = pos; i < deno.length; i++) {
            count += countWays(n-deno[i], deno, i, map);
        }
        map[n][pos] = count;
        return count;
    }

    @Test
    public void test() {
        assertEquals(1, countWays(1));
        assertEquals(2, countWays(5));
        assertEquals(4, countWays(10));
        assertEquals(6, countWays(15));
    }
}
