package com.bpham.dailycodingproblem;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This problem was asked by Jane Street.
 *
 * cons(a, b) constructs a pair, and car(pair) and cdr(pair) returns the first and last element of that pair.
 * For example, car(cons(3, 4)) returns 3, and cdr(cons(3, 4)) returns 4.
 *
 * Given this implementation of cons:
 *
 * def cons(a, b):
 *  def pair(f):
 *      return f(a, b)
 * return pair
 *
 * Implement car and cdr.
 */

public class Problem5Medium {
    public int[] cons(int a, int b) {
        return new int[]{a,b};
    }

    public int car(int[] pair) {
        return pair[0];
    }

    public int cdr(int[] pair) {
        return pair[1];
    }

    @Test
    public void test() {
        assertEquals(3, car(cons(3, 4)));
        assertEquals(4, cdr(cons(3, 4)));
    }
}
