package com.bpham.practice;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

/**
 * https://leetcode.com/discuss/interview-question/373202
 */

public class OptimalUtilization {
    public List<List<Integer>> op(List<List<Integer>> a, List<List<Integer>> b, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (a == null || b == null) return result;
        Collections.sort(a, (a1, a2) -> a1.get(1) - a2.get(1));
        Collections.sort(b, (b1, b2) -> b1.get(1) - b2.get(1));
        int lo = 0;
        int hi = b.size() - 1;
        int max = Integer.MIN_VALUE;
        while (lo < a.size() && hi >= 0) {
            int sum = a.get(lo).get(1) + b.get(hi).get(1);
            if (sum > target) {
                hi--;
                continue;
            }
            if (sum > max) {
                max = sum;
                result.clear();
            }
            result.add(Arrays.asList(a.get(lo).get(0), b.get(hi).get(0)));
            lo++;
        }
        return result;
    }

    @Test
    public void test1() {
        List<List<Integer>> a = Arrays.asList(Arrays.asList(1,2), Arrays.asList(2,4), Arrays.asList(3,6));
        List<List<Integer>> b = Arrays.asList(Arrays.asList(1,2));
        assertEquals(Arrays.asList(Arrays.asList(2, 1)), op(a, b, 7));
    }

    @Test
    public void test2() {
        List<List<Integer>> a = Arrays.asList(Arrays.asList(1,3), Arrays.asList(2,5), Arrays.asList(3,7), Arrays.asList(4,10));
        List<List<Integer>> b = Arrays.asList(Arrays.asList(1,2), Arrays.asList(2,3), Arrays.asList(3,4), Arrays.asList(4,5));
        assertEquals(Arrays.asList(Arrays.asList(2, 4), Arrays.asList(3,2)), op(a, b, 10));
    }

    @Test
    public void test3() {
        List<List<Integer>> a = new ArrayList<>();
        List<List<Integer>> b = new ArrayList<>();
        assertTrue(op(a, b, 10).isEmpty());
    }

    @Test
    public void test4() {
        List<List<Integer>> a = Arrays.asList(Arrays.asList(1,3), Arrays.asList(2,5), Arrays.asList(3,7), Arrays.asList(4,10));
        List<List<Integer>> b = Arrays.asList(Arrays.asList(1,3), Arrays.asList(2,5), Arrays.asList(3,7), Arrays.asList(4,10));
        assertTrue(op(a, b, 1).isEmpty());
    }

    @Test
    public void test5() {
        List<List<Integer>> a = Arrays.asList(Arrays.asList(1,3), Arrays.asList(2,5), Arrays.asList(3,7), Arrays.asList(4,10));
        List<List<Integer>> b = Arrays.asList(Arrays.asList(1,3), Arrays.asList(2,5), Arrays.asList(3,7), Arrays.asList(4,10));
        assertEquals(Arrays.asList(Arrays.asList(1, 1)), op(a, b, 6));
    }

    @Test
    public void test6() {
        List<List<Integer>> a = Arrays.asList(Arrays.asList(1,3), Arrays.asList(2,9), Arrays.asList(3,6));
        List<List<Integer>> b = Arrays.asList(Arrays.asList(1,3), Arrays.asList(2,-3), Arrays.asList(3,0));
        List<List<Integer>> res = op(a, b, 6);
        assertTrue(res.containsAll(Arrays.asList(Arrays.asList(1, 1) , Arrays.asList(2, 2), Arrays.asList(3, 3))));
    }

    @Test
    public void test7() {
        List<List<Integer>> a = Arrays.asList(Arrays.asList(-1,1), Arrays.asList(1,3), Arrays.asList(2,9), Arrays.asList(3,6));
        List<List<Integer>> b = Arrays.asList(Arrays.asList(-1,4), Arrays.asList(1,3), Arrays.asList(2,-3), Arrays.asList(3,0));
        List<List<Integer>> res = op(a, b, 6);
        assertTrue(res.containsAll(Arrays.asList(Arrays.asList(1, 1) , Arrays.asList(2, 2), Arrays.asList(3, 3))));
    }

    @Test
    public void test8() {
        List<List<Integer>> a = Arrays.asList(Arrays.asList(-1,1), Arrays.asList(1,8), Arrays.asList(2,8), Arrays.asList(3,8));
        List<List<Integer>> b = Arrays.asList(Arrays.asList(-1,4), Arrays.asList(1,8), Arrays.asList(2,8), Arrays.asList(3,8));
        List<List<Integer>> res = op(a, b, 6);
        assertTrue(res.containsAll(Arrays.asList(Arrays.asList(-1, -1))));
    }

    @Test
    public void test9() {
        List<List<Integer>> res = op(null, null, 6);
        assertTrue(res.isEmpty());
    }
}
