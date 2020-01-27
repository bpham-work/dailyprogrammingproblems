package com.bpham.practice;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class CriticalConnections {
    int time = 1;

    public List<int[]> getBridges(int n, List<int[]> edges) {
        if (edges == null) return null;
        List<int[]> result = new ArrayList<>();
        boolean[] visited = new boolean[n+1];
        int[] parent = new int[n+1];
        int[] disc = new int[n+1];
        int[] lo = new int[n+1];
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int[] edge : edges) {
            if (graph.containsKey(edge[0]) && graph.containsKey(edge[1])) {
                graph.get(edge[0]).add(edge[1]);
                graph.get(edge[1]).add(edge[0]);
            }
        }

        getBridges(1, graph, result, visited, parent, disc, lo);

        return result;
    }

    public void getBridges(int node, Map<Integer, List<Integer>> graph, List<int[]> result, boolean[] visited, int[] parent, int[] disc, int[] lo) {
        visited[node] = true;
        disc[node] = time;
        lo[node] = time;
        time++;
        List<Integer> adj = graph.get(node);
        for (Integer adjNode : adj) {
            if (!visited[adjNode]) {
                parent[adjNode] = node;
                getBridges(adjNode, graph, result, visited, parent, disc, lo);
                lo[node] = Math.min(lo[node], lo[adjNode]);
                if (lo[adjNode] > disc[node]) {
                    result.add(new int[]{node, adjNode});
                }

            } else if (adjNode != parent[node]) {
                lo[node] = Math.min(lo[adjNode], lo[node]);
            }
        }
    }

    @Test
    public void test1() {
        List<int[]> result = getBridges(5, Arrays.asList(new int[]{1,2}, new int[]{1,3}, new int[]{3,4}, new int[]{1,4}, new int[]{4,5}));
        assertArrayEquals(new int[]{1,2}, result.get(0));
        assertArrayEquals(new int[]{4,5}, result.get(1));
    }

    @Test
    public void test2() {
        List<int[]> result = getBridges(6, Arrays.asList(new int[]{1,2}, new int[]{1,3}, new int[]{2,3}, new int[]{2,4}, new int[]{2,5}, new int[]{4,6}, new int[]{5,6}));
        assertEquals(0, result.size());
    }

    @Test
    public void test3() {
        List<int[]> result = getBridges(6, null);
        assertNull(result);
    }

    @Test
    public void test4() {
        List<int[]> result = getBridges(2, Arrays.asList(new int[]{1,2}));
        assertArrayEquals(new int[]{1,2}, result.get(0));
    }

    @Test
    public void test5() {
        List<int[]> result = getBridges(2, Arrays.asList(new int[]{4,3}));
        assertEquals(0, result.size());
    }
}