package com.bpham.practice;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * https://leetcode.com/problems/search-a-2d-matrix-ii/
 */

public class Search2DMatrix2 {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) return false;
        int m = matrix.length;
        int n = matrix[0].length;
        int col = n - 1;
        int row = 0;

        while (col >= 0 && row < m && target != matrix[row][col]) {
            if (target < matrix[row][col]) {
                col--;
            } else {
                row++;
            }
        }

        if (col < 0 || row >= m) return false;
        return true;
    }

    @Test
    public void test1() {
        int[][] map = new int[][]{
                {1,   4,  7, 11, 15},
                  {2,   5,  8, 12, 19},
                  {3,   6,  9, 16, 22},
                  {10, 13, 14, 17, 24},
                  {18, 21, 23, 26, 30}
        };

        assertTrue(searchMatrix(map, 5));
        assertTrue(searchMatrix(map, 1));
        assertTrue(searchMatrix(map, 18));
        assertTrue(searchMatrix(map, 30));
        assertFalse(searchMatrix(map, 20));
    }

    @Test
    public void test2() {
        int[][] map = new int[][]{
                {-4, -1,  7, 11, 15}
        };

        assertFalse(searchMatrix(map, 5));
        assertTrue(searchMatrix(map, -1));
        assertTrue(searchMatrix(map, -4));
        assertFalse(searchMatrix(map, 18));
        assertFalse(searchMatrix(map, 30));
        assertFalse(searchMatrix(map, 20));
    }

    @Test
    public void test3() {
        int[][] map = new int[][]{
                {1,   4,  7, 11, 15},
                {2,   5,  8, 12, 19},
        };

        assertTrue(searchMatrix(map, 5));
        assertTrue(searchMatrix(map, 1));
        assertFalse(searchMatrix(map, 18));
        assertFalse(searchMatrix(map, 30));
        assertFalse(searchMatrix(map, 20));
    }

    @Test
    public void test4() {
        int[][] map = new int[][]{};

        assertFalse(searchMatrix(map, 5));
        assertFalse(searchMatrix(map, 1));
        assertFalse(searchMatrix(map, 18));
        assertFalse(searchMatrix(map, 30));
        assertFalse(searchMatrix(map, 20));
    }

    @Test
    public void test5() {
        int[][] map = new int[][]{
                {-19, -12, -8, -5, -2},
                {-15, -11, -7, -4, -1}
        };

        assertTrue(searchMatrix(map, -19));
        assertTrue(searchMatrix(map, -2));
        assertTrue(searchMatrix(map, -1));
        assertTrue(searchMatrix(map, -15));
        assertFalse(searchMatrix(map, 5));
        assertFalse(searchMatrix(map, 1));
        assertFalse(searchMatrix(map, 18));
        assertFalse(searchMatrix(map, 30));
        assertFalse(searchMatrix(map, 20));
    }
}
