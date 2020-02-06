package com.bpham.ctci.treesandgraphs;

import com.bpham.ctci.models.BinaryNode;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * #12
 *
 * You are given a binary tree in which each node contains an integer value
 * (which might be positive or negative). Design an algorithm to count the
 * number of paths that sum to a given value. The path does not need to start
 * or end at the root or a leaf, but it must go downwards (traveling only from
 * parent nodes to child nodes).
 */

public class PathSum {
    public int countPaths(BinaryNode<Integer> tree, int target) {
        if (tree == null) return 0;

        return countPaths(tree, target, 0) +
                countPaths(tree.left, target, 0) +
                countPaths(tree.right, target, 0);
    }

    public int countPaths(BinaryNode<Integer> tree, int target, int sum) {
        if (tree == null) return 0;
        int curr = target == sum + tree.value ? 1 : 0;
        return countPaths(tree.left, target,sum + tree.value) +
                countPaths(tree.right, target,sum + tree.value)
                + curr;
    }

    @Test
    public void test1() {
        BinaryNode<Integer> tree = new BinaryNode<>(1);
        tree.left = new BinaryNode<>(-1);
        tree.left.left = new BinaryNode<>(-4);
        tree.left.right = new BinaryNode<>(5);
        tree.right = new BinaryNode<>(3);

        assertEquals(2, countPaths(tree, 4));
        assertEquals(0, countPaths(tree, 10));
        assertEquals(1, countPaths(tree, -1));
        assertEquals(1, countPaths(tree, 1));
    }
}
