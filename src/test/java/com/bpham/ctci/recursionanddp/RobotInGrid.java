package com.bpham.ctci.recursionanddp;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * #2
 *
 * Imagine a robot sitting on the upper left corner of grid with r rows and c columns.
 * The robot can only move in two directions, right and down, but certain cells are
 * "off limits" such that the robot cannot step on them. Design an algorithm to find
 * a path for the robot from the top left to the bottom right.
 *
 * QUESTIONS:
 * 1. Should return first path found?
 * 2. What to return if no possible path?
 * 3. What represents off limits cell?
 */

public class RobotInGrid {
    public Deque<Tuple> findPath(char[][] grid, int r, int c) {
        Wrapper wrapper = findPath(grid, r, c, 0, 0, new Wrapper(), new boolean[r][c]);
        return wrapper.getResult();
    }

    public Wrapper findPath(char[][] grid, int r, int c, int rPos,
                            int cPos, Wrapper wrapper, boolean[][] visited) {
        if (rPos > r-1) return wrapper;
        if (cPos > c-1) return wrapper;
        if (visited[rPos][cPos]) return wrapper;
        if (rPos == r-1 && cPos == c-1) {
            wrapper.push(rPos, cPos);
            wrapper.setFound();
            return wrapper;
        }
        if (grid[rPos][cPos] == 'X') {
            return wrapper;
        }
        visited[rPos][cPos] = true;
        wrapper.push(rPos, cPos);
        Wrapper goRightResult = findPath(grid, r, c, rPos, cPos+1, wrapper, visited);
        if (goRightResult.found) {
            return goRightResult;
        }
        Wrapper goDownResult = findPath(grid, r, c, rPos+1, cPos, wrapper, visited);
        if (goDownResult.found) {
            return goDownResult;
        }
        wrapper.pop();
        return wrapper;
    }

    @Test
    public void test1() {
        char[][] grid = new char[3][3];
        grid[1][1] = 'X';
        System.out.println(findPath(grid, 3, 3));
    }

    @Test
    public void test2() {
        char[][] grid = new char[3][3];
        grid[0][1] = 'X';
        grid[0][2] = 'X';
        grid[1][2] = 'X';
        System.out.println(findPath(grid, 3, 3));
    }

    private class Wrapper {
        public Deque<Tuple> path = new LinkedList<>();
        public boolean found = false;

        public void pop() { path.pop(); }
        public void push(int r, int c) { path.push(new Tuple(r, c)); }
        public void setFound() { found = true; }
        public Deque<Tuple> getResult() {
            return path;
        }
    }

    private class Tuple {
        public int row;
        public int col;

        public Tuple(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public String toString() {
            return String.format("(%s,%s)", row, col);
        }
    }
}
