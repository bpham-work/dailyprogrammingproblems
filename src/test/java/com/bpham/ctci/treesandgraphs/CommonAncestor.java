package com.bpham.ctci.treesandgraphs;

import com.bpham.ctci.models.BinaryNode;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * #8
 *
 * Design an algorithm and write code to find the first common ancestor
 * of two nodes in a binary tree. Avoid storing additional nodes in a
 * data structure.
 *
 * NOTE: This is not necessarily a binary search tree.
 */

public class CommonAncestor {
    public BinaryNode<String> getFirstCommonAncestor(BinaryNode<String> tree, BinaryNode<String> a, BinaryNode<String> b) {
        return isInSubtree(tree, a, b).ancestor;
    }

    public Wrapper isInSubtree(BinaryNode<String> tree, BinaryNode<String> a, BinaryNode<String> b) {
        if (tree == null) return new Wrapper(false);
        Wrapper inLeft = isInSubtree(tree.left, a, b);
        Wrapper inRight = isInSubtree(tree.right, a, b);
        boolean currMatches = tree == a || tree == b;
        if (inLeft.exists && inRight.exists || inLeft.exists && currMatches || inRight.exists && currMatches) {
            return new Wrapper(true, tree);
        }
        if (inLeft.foundAncestor()) return inLeft;
        if (inRight.foundAncestor()) return inRight;
        boolean exists = inLeft.exists || inRight.exists || currMatches;
        return new Wrapper(exists);
    }

    private class Wrapper {
        public boolean exists;
        public BinaryNode<String> ancestor;

        public Wrapper(boolean exists) {
            this.exists = exists;
        }

        public Wrapper(boolean exists, BinaryNode<String> ancestor) {
            this.exists = exists;
            this.ancestor = ancestor;
        }

        public boolean foundAncestor() {
            return ancestor != null;
        }
    }

    @Test
    public void test1() {
        BinaryNode<String> a = new BinaryNode<>("a");
        BinaryNode<String> b = new BinaryNode<>("b");
        BinaryNode<String> head = new BinaryNode<>("head");
        head.left = a;
        head.right = b;

        assertEquals(head, getFirstCommonAncestor(head, a, b));
    }

    @Test
    public void test2() {
        BinaryNode<String> a = new BinaryNode<>("a");
        BinaryNode<String> b = new BinaryNode<>("b");
        BinaryNode<String> head = new BinaryNode<>("head");
        head.left = new BinaryNode<>("");
        head.left.left = a;
        head.left.right = b;

        assertEquals(head.left, getFirstCommonAncestor(head, a, b));
    }

    @Test
    public void test3() {
        BinaryNode<String> a = new BinaryNode<>("a");
        BinaryNode<String> b = new BinaryNode<>("b");
        BinaryNode<String> head = new BinaryNode<>("head");
        head.left = new BinaryNode<>("");
        head.left.left = a;
        head.right = b;

        assertEquals(head, getFirstCommonAncestor(head, a, b));
    }

    @Test
    public void test4() {
        BinaryNode<String> a = new BinaryNode<>("a");
        BinaryNode<String> b = new BinaryNode<>("b");
        a.left = b;

        assertEquals(a, getFirstCommonAncestor(a, a, b));
    }

    @Test
    public void test5() {
        BinaryNode<String> a = new BinaryNode<>("a");
        BinaryNode<String> b = new BinaryNode<>("b");
        a.right = b;

        assertEquals(a, getFirstCommonAncestor(a, a, b));
    }
}
