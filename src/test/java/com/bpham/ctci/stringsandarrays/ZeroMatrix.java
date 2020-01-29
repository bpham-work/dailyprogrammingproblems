package com.bpham.ctci.stringsandarrays;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * #8
 *
 * Write an algorithm such that if an element in an MxN matrix is 0,
 * its entire row and column are set to O.
 *
 * Questions:
 * 1. In place?
 */

public class ZeroMatrix {
    public void zeroMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        Set<Integer> rows = new HashSet<>();
        Set<Integer> cols = new HashSet<>();
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                int elem = matrix[r][c];
                if (elem == 0) {
                    rows.add(r);
                    cols.add(c);
                }
            }
        }

        for (int rowIndex : rows) {
            for (int i = 0; i < n; i++) {
                matrix[rowIndex][i] = 0;
            }
        }
        for (int colIndex : cols) {
            for (int i = 0; i < m; i++) {
                matrix[i][colIndex] = 0;
            }
        }
    }

    @Test
    public void test() {
        int[][] matrix = {
                {1, 0},
                {0, 1}
        };

        zeroMatrix(matrix);

        assertEquals(0, matrix[0][0]);
        assertEquals(0, matrix[0][1]);
        assertEquals(0, matrix[1][0]);
        assertEquals(0, matrix[1][1]);
    }

    @Test
    public void test2() {
        int[][] matrix = {
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1}
        };

        zeroMatrix(matrix);

        assertEquals(1, matrix[0][0]);
        assertEquals(1, matrix[0][1]);
        assertEquals(1, matrix[0][2]);
        assertEquals(1, matrix[1][0]);
        assertEquals(1, matrix[1][1]);
        assertEquals(1, matrix[1][2]);
        assertEquals(1, matrix[2][0]);
        assertEquals(1, matrix[2][1]);
        assertEquals(1, matrix[2][2]);
    }

    @Test
    public void test3() {
        int[][] matrix = {
                {0, 1, 1},
                {1, 1, 1},
                {1, 1, 1}
        };

        zeroMatrix(matrix);

        assertEquals(0, matrix[0][0]);
        assertEquals(0, matrix[0][1]);
        assertEquals(0, matrix[0][2]);
        assertEquals(0, matrix[1][0]);
        assertEquals(1, matrix[1][1]);
        assertEquals(1, matrix[1][2]);
        assertEquals(0, matrix[2][0]);
        assertEquals(1, matrix[2][1]);
        assertEquals(1, matrix[2][2]);
    }
}
