package com.bpham.ctci.treesandgraphs;

import com.bpham.ctci.models.BinaryNode;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * #2
 *
 * Given a sorted (increasing order) array with unique integer elements,
 * write an algorithm to create a binary search tree with minimal height.
 */

public class ConvertArrayToBst {
    public BinaryNode<Integer> arrayToBst(int[] arr, int min, int max) {
        if (min > max) return null;
        if (min == max) return new BinaryNode<>(arr[min]);
        int mid = (min + max) / 2;
        BinaryNode<Integer> res = new BinaryNode<>(arr[mid]);
        res.left = arrayToBst(arr, min, mid - 1);
        res.right = arrayToBst(arr, mid + 1, max);
        return res;
    }

    @Test
    public void test1() {
        int[] arr = { 0, 1, 2, 3, 4 };

        BinaryNode<Integer> res = arrayToBst(arr, 0, arr.length - 1);

        assertEquals((Integer) 2, res.value);

        assertEquals((Integer) 0, res.left.value);
        assertEquals(null, res.left.left);
        assertEquals((Integer) 1, res.left.right.value);

        assertEquals((Integer) 3, res.right.value);
        assertEquals(null, res.right.left);
        assertEquals((Integer) 4, res.right.right.value);
    }

    @Test
    public void test2() {
        int[] arr = { 0, 1, 2, 3, 4, 5 };

        BinaryNode<Integer> res = arrayToBst(arr, 0, arr.length - 1);

        assertEquals((Integer) 2, res.value);

        assertEquals((Integer) 0, res.left.value);
        assertEquals(null, res.left.left);
        assertEquals((Integer) 1, res.left.right.value);

        assertEquals((Integer) 4, res.right.value);
        assertEquals((Integer) 3, res.right.left.value);
        assertEquals((Integer) 5, res.right.right.value);
    }
}
