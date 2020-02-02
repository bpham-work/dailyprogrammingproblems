package com.bpham.ctci.treesandgraphs;

import com.bpham.ctci.models.BinaryNode;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * #4
 *
 * Implement a function to check if a binary tree is balanced.
 * For the purposes of this question, a balanced tree is defined
 * to be a tree such that the heights of the two subtrees of any
 * node never differ by more than one.
 */

public class CheckBalanced {
    public boolean isBalanced(BinaryNode<Integer> tree) {
        if (tree == null) return true;

        int diff = Math.abs(height(tree.left) - height(tree.right));

        return diff < 2 && isBalanced(tree.left) && isBalanced(tree.right);
    }

    public int height(BinaryNode<Integer> node) {
        if (node == null) return -1;
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    @Test
    public void testIsBalanced() {
        BinaryNode<Integer> head = new BinaryNode<>(1);
        head.left = new BinaryNode<>(1);
        head.left.left = new BinaryNode<>(1);
        head.left.right = new BinaryNode<>(1);
        head.right = new BinaryNode<>(1);

        assertTrue(isBalanced(head));
    }

    @Test
    public void testIsNotBalanced() {
        BinaryNode<Integer> head = new BinaryNode<>(1);
        head.left = new BinaryNode<>(1);
        head.right = new BinaryNode<>(1);
        head.left.left = new BinaryNode<>(1);
        head.left.left.left = new BinaryNode<>(1);
        head.right.left = new BinaryNode<>(1);

        assertFalse(isBalanced(head));
    }
}
