package com.bpham.ctci.recursionanddp;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * #9
 *
 * Implement an algorithm to print all valid (e.g., properly opened and closed)
 * combinations of n pairs of parentheses.
 *
 * EXAMPLE
 * Input: 3
 * Output: ((())), (()()), (())(), ()(()), ()()()
 */

public class Parens {
    public List<String> parens(int n) {
        List<String> result = new ArrayList<>();
        parens(new char[n*2], n, n, 0, result);
        return result;
    }

    public void parens(char[] arr, int left, int right, int i, List<String> result) {
        if (left == 0 && right == 0) {
            StringBuilder sb = new StringBuilder();
            for (Character letter : arr) sb.append(letter);
            result.add(sb.toString());
        }
        else {
            if (left > 0) {
                arr[i] = '(';
                parens(arr, left-1, right, i+1, result);
            }

            if (right > left) {
                arr[i] = ')';
                parens(arr, left, right-1, i+1, result);
            }
        }
    }

    @Test
    public void test() {
        System.out.println(parens(1));
        System.out.println(parens(2));
        System.out.println(parens(3));
        System.out.println(parens(4));
    }
}
