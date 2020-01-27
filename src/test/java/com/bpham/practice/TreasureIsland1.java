package com.bpham.practice;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/discuss/interview-question/347457
 */

public class TreasureIsland1 {
    static class Coord {
        public int m;
        public int n;

        Coord(int m, int n) {
            this.m = m;
            this.n = n;
        }
    }
    public int steps(char[][] map) {
        int res = -1;
        int m = map.length;
        int n = map[0].length;
        boolean[][] visited = new boolean[m][n];
        Queue<Coord> q = new LinkedList<>();
        q.add(new Coord(0, 0));
        int[][] dir = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        boolean found = false;
        while (!q.isEmpty()) {
            res++;
            int toPop = q.size();
            for (int i = 0; i < toPop; i++) {
                Coord coord = q.poll();
                visited[coord.m][coord.n] = true;
                if (map[coord.m][coord.n] == 'X') return res;
                for (int[] d : dir) {
                    int newM = coord.m + d[0];
                    int newN = coord.n + d[1];
                    if (newM >= 0 && newM < m && newN >= 0 && newN < n && map[newM][newN] != 'D' && !visited[newM][newN]) {
                        q.add(new Coord(newM, newN));
                    }
                }
            }
        }
        if (!found) return -1;
        return res;
    }

    public int steps2(char[][] map) {
        int m = map.length;
        int n = map[0].length;
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0});
        visited[0][0] = true;
        int res = -1;
        int toPop = 1;
        boolean found = false;
        while (!q.isEmpty() && !found) {
            res++;
            int childrenAdded = 0;
            for (int i = 0; i < toPop; i++) {
                int[] node = q.poll();
                visited[node[0]][node[1]] = true;
                if (map[node[0]][node[1]] == 'X') {
                    found = true;
                    break;
                } else if (map[node[0]][node[1]] == 'D') {
                    continue;
                } else {
                    int up = node[0] - 1;
                    int down = node[0] + 1;
                    int left = node[1] - 1;
                    int right = node[1] + 1;
                    if (up >= 0 && !visited[up][node[1]]) {
                        q.add(new int[]{up, node[1]});
                        childrenAdded++;
                    }
                    if (down < m && !visited[down][node[1]]) {
                        q.add(new int[]{down, node[1]});
                        childrenAdded++;
                    }
                    if (left >= 0 && !visited[node[0]][left]) {
                        q.add(new int[]{node[0], left});
                        childrenAdded++;
                    }
                    if (right < n && !visited[node[0]][right]) {
                        q.add(new int[]{node[0], right});
                        childrenAdded++;
                    }
                }
            }
            toPop = childrenAdded;
        }
        return res;
    }

    @Test
    public void test1() {
        char[][] map = new char[][]{
            new char[] {'0', '0', '0', '0'},
            new char[] {'D', '0', 'D', '0'},
            new char[] {'0', '0', '0', '0'},
            new char[] {'X', 'D', 'D', '0'}
        };
        assertEquals(5, steps(map));
    }

    @Test
    public void test2() {
        char[][] map = new char[][]{
                new char[] {'0', '0', '0', 'X'},
                new char[] {'D', '0', 'D', '0'},
                new char[] {'0', '0', '0', '0'},
                new char[] {'D', 'D', 'D', '0'}
        };
        assertEquals(3, steps(map));
    }

    @Test
    public void test3() {
        char[][] map = new char[][]{
                new char[] {'0', '0', '0', '0'},
                new char[] {'D', '0', 'D', '0'},
                new char[] {'0', '0', '0', '0'},
                new char[] {'D', 'D', 'D', 'X'}
        };
        assertEquals(6, steps(map));
    }

    @Test
    public void test4() {
        char[][] map = new char[][]{
                new char[] {'0', 'D', '0', '0'},
                new char[] {'0', '0', 'D', '0'},
                new char[] {'D', '0', 'D', '0'},
                new char[] {'D', '0', '0', 'X'}
        };
        assertEquals(6, steps(map));
    }

    @Test
    public void test6() {
        char[][] map = new char[][]{
                new char[] {'0', 'D', '0', '0'},
                new char[] {'0', '0', 'D', '0'},
                new char[] {'D', '0', 'D', '0'},
                new char[] {'D', '0', 'D', 'X'}
        };
        assertEquals(-1, steps(map));
    }
}
