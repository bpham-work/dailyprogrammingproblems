package com.bpham.ctci.recursionanddp;

import org.junit.Test;

import java.util.Arrays;

/**
 * #12
 *
 * Write an algorithm to print all ways of arranging eight queens on an 8x8
 * chess board so that none of them share the same row, column, or diagonal.
 * In this case, "diagonal" means all diagonals, not just the two that bisect
 * the board.
 */

public class Queens {
    public void queens(int n) {
        String[][] board = new String[n][n];
        for (String[] row : board) {
            Arrays.fill(row, "_");
        }
        queens(n, board, 0, new boolean[n]);
    }

    public void queens(int n, String[][] board, int row, boolean[] occupiedCol) {
        if (row == n) {
            for (String[] boardRow : board) {
                System.out.println(Arrays.toString(boardRow));
            }
            System.out.println("");
        } else {
            for (int i = 0; i < n; i++) {
                if (isValid(board, row, i, occupiedCol)) {
                    occupiedCol[i] = true;
                    board[row][i] = "Q";
                    queens(n, board, row+1, occupiedCol);
                    occupiedCol[i] = false;
                    board[row][i] = "_";
                }
            }
        }
    }

    public boolean isValid(String[][] board, int row, int col, boolean[] occupiedCol) {
        if (occupiedCol[col]) {
            return false;
        }
        if (!validDiagonal(board, row, col)) {
            return false;
        }
        return true;
    }

    public boolean validDiagonal(String[][] board, int row, int col) {
        return validDiagonalLeft(board, row, col) &&
                validDiagonalRight(board, row, col);
    }

    public boolean validDiagonalLeft(String[][] board, int row, int col) {
        if (row < 0 || col < 0) {
            return true;
        } else {
            if (board[row][col].equals("Q")) {
                return false;
            }
            return validDiagonalLeft(board, row-1, col-1);
        }
    }

    public boolean validDiagonalRight(String[][] board, int row, int col) {
        if (row < 0 || col == board.length) {
            return true;
        } else {
            if (board[row][col].equals("Q")) {
                return false;
            }
            return validDiagonalRight(board, row-1, col+1);
        }
    }

    @Test
    public void test() {
        queens(8);
    }
}
