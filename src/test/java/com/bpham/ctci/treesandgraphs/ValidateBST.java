package com.bpham.ctci.treesandgraphs;

import com.bpham.ctci.models.BinaryNode;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * #5
 *
 * Implement a function to check if a binary tree is a binary search tree.
 */

public class ValidateBST {
    private int last = Integer.MIN_VALUE;

    public boolean isBST(BinaryNode<Integer> tree) {
        if (tree == null) return true;
        boolean isLeftBst = isBST(tree.left);
        if (tree.value < last) {
            return false;
        }
        last = tree.value;
        boolean isRightBst = isBST(tree.right);
        return isLeftBst && isRightBst;
    }

    @Test
    public void testIsBST() {
        BinaryNode<Integer> head = new BinaryNode<>(10);
        head.left = new BinaryNode<>(5);
        head.left.right = new BinaryNode<>(7);
        head.right = new BinaryNode<>(20);

        assertTrue(isBST(head));
    }

    @Test
    public void testIsBST2() {
        BinaryNode<Integer> head = new BinaryNode<>(10);
        head.left = new BinaryNode<>(5);
        head.right = new BinaryNode<>(20);
        head.right.right = new BinaryNode<>(20);

        assertTrue(isBST(head));
    }

    @Test
    public void testIsNotBST() {
        BinaryNode<Integer> head = new BinaryNode<>(10);
        head.left = new BinaryNode<>(5);
        head.left.right = new BinaryNode<>(7);
        head.left.right.right = new BinaryNode<>(15);
        head.right = new BinaryNode<>(20);

        assertFalse(isBST(head));
    }

    @Test
    public void testIsNotBST2() {
        BinaryNode<Integer> head = new BinaryNode<>(10);
        head.left = new BinaryNode<>(5);
        head.left.right = new BinaryNode<>(7);
        head.right = new BinaryNode<>(20);
        head.right.right = new BinaryNode<>(15);

        assertFalse(isBST(head));
    }
}
