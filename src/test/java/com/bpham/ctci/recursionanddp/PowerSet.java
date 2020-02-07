package com.bpham.ctci.recursionanddp;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * #4
 *
 * Write a method to return all subsets of a set.
 */

public class PowerSet {
    public List<List<Integer>> powerset(int[] arr) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        powerset(arr, res, 0);
        return res;
    }

    public void powerset(int[] arr, List<List<Integer>> res, int pos) {
        if (pos < arr.length) {
            int stop = res.size();
            for (int i = 0; i < stop; i++) {
                List<Integer> prevResult = res.get(i);
                List<Integer> newResult = new ArrayList<>(prevResult);
                newResult.add(arr[pos]);
                res.add(newResult);
            }
            powerset(arr, res, pos+1);
        }
    }

    public List<List<Integer>> powerset2(int[] arr) {
        List<List<Integer>> res = new ArrayList<>();
        powerset2(arr, 0, res, new LinkedList<>());
        return res;
    }

    public void powerset2(int[] arr, int pos,
                                  List<List<Integer>> result, Deque<Integer> temp) {
        result.add(new ArrayList<>(temp));
        if (pos < arr.length) {
            for (int i = pos; i < arr.length; i++) {
                temp.push(arr[i]);
                powerset2(arr, i+1, result, temp);
                temp.pop();
            }
        }
    }


    @Test
    public void test() {
        System.out.println(powerset(new int[] {1, 2, 3}));
        System.out.println(powerset(new int[] {1, 2, 3, 4}));
    }
}
