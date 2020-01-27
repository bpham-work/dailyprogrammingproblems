package com.bpham.practice;

import org.junit.Test;

import java.util.PriorityQueue;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/discuss/interview-question/344677
 */

public class MinCostConnectRopes {
    public int cost(int[] ropes) {
        if (ropes == null) return 0;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(ropes.length, (a, b) -> a - b);
        for (int rope : ropes) {
            minHeap.add(rope);
        }
        int result = 0;
        while (!minHeap.isEmpty()) {
            int connect = minHeap.poll();
            if (!minHeap.isEmpty())
                connect += minHeap.poll();
            result += connect;
            if (!minHeap.isEmpty())
                minHeap.add(connect);
        }
        return result;
    }

    @Test
    public void test() {
        assertEquals(58, cost(new int[]{8, 4, 6, 12}));
        assertEquals(20, cost(new int[]{2, 2, 3, 3}));
        assertEquals(54, cost(new int[]{20, 4, 8, 2}));
        assertEquals(224, cost(new int[]{1, 2, 5, 10, 35, 89}));
        assertEquals(0, cost(new int[]{0}));
        assertEquals(-2, cost(new int[]{-1, -1}));
        assertEquals(-1, cost(new int[]{-1}));
        assertEquals(-2, cost(new int[]{-1, -1, 2}));
        assertEquals(0, cost(new int[]{-1, -1, 2, 2}));
        assertEquals(0, cost(null));
        assertEquals(33, cost(new int[]{1, 2, 3, 4, 5}));
    }
}
