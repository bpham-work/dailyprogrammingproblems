package com.bpham.dynamicprogramming;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * 0 1 Knapsack problem
 */

public class Knapsack {

    public int knapsack(int[] values, int[] weights, int capacity) {
        int[][] dp = new int[values.length+1][capacity+1];
        for (int i = 0; i <= values.length; i++) {
            for (int k = 0; k <= capacity; k++) {
                if (i == 0 || k == 0) {
                    dp[i][k] = 0;
                } else if (weights[i-1] <= k) {
                    dp[i][k] = Math.max(dp[i-1][k], values[i-1] + dp[i-1][k - weights[i-1]]);
                } else {
                    dp[i][k] = dp[i-1][k];
                }
            }
        }
        return dp[values.length][capacity];
    }

    @Test
    public void test() {
        assertEquals(50, knapsack(new int[] {10, 20, 30}, new int[]{1, 2, 3}, 5));
        assertEquals(220, knapsack(new int[] {60, 100, 120}, new int[]{10, 20, 30}, 50));
    }
}
