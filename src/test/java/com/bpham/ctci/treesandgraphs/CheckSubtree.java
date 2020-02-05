package com.bpham.ctci.treesandgraphs;

import com.bpham.ctci.models.BinaryNode;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * #10
 *
 * T1 and T2 are two very large binary trees, with T1 much bigger than T2. Create an
 * algorithm to determine if T2 is a subtree of Tl.
 * A tree T2 is a subtree ofT i if there exists a node n in T i such that the subtree
 * of n is identical to T2.
 * That is, if you cut off the tree at node n, the two trees would be identical.
 */

public class CheckSubtree {
    public boolean isSubtree(BinaryNode<Integer> t1, BinaryNode<Integer> t2) {
        boolean matches = matches(t1, t2);
        if (matches) return true;
        boolean left = false;
        boolean right = false;
        if (t1 != null) {
            left = isSubtree(t1.left, t2);
            right = isSubtree(t1.right, t2);
        }
        return left || right;
    }

    public boolean matches(BinaryNode<Integer> t1, BinaryNode<Integer> t2) {
        if (t1 == null && t2 != null) return false;
        if (t1 != null && t2 == null) return false;
        if (t1 == null && t2 == null) return true;

        return matches(t1.left, t2.left) &&
                matches(t1.right, t2.right) &&
                t1.value.equals(t2.value);
    }

    @Test
    public void test1() {
        BinaryNode<Integer> t1 = new BinaryNode<>(1);
        t1.left = new BinaryNode<>(2);
        t1.left.left = new BinaryNode<>(4);
        t1.left.right = new BinaryNode<>(5);
        t1.right = new BinaryNode<>(3);
        t1.right.left = new BinaryNode<>(6);
        t1.right.right = new BinaryNode<>(7);

        BinaryNode<Integer> t2 = new BinaryNode<>(2);
        t2.left = new BinaryNode<>(4);
        t2.right = new BinaryNode<>(5);

        assertTrue(isSubtree(t1, t2));
    }

    @Test
    public void test2() {
        BinaryNode<Integer> t1 = new BinaryNode<>(1);
        t1.left = new BinaryNode<>(2);
        t1.left.left = new BinaryNode<>(4);
        t1.left.right = new BinaryNode<>(5);
        t1.right = new BinaryNode<>(3);
        t1.right.left = new BinaryNode<>(6);
        t1.right.right = new BinaryNode<>(7);

        BinaryNode<Integer> t2 = new BinaryNode<>(2);
        t2.left = new BinaryNode<>(4);
        t2.right = new BinaryNode<>(10);

        assertFalse(isSubtree(t1, t2));
    }

    @Test
    public void test3() {
        BinaryNode<Integer> t1 = new BinaryNode<>(1);

        BinaryNode<Integer> t2 = new BinaryNode<>(1);

        assertTrue(isSubtree(t1, t2));
    }

    @Test
    public void test4() {
        BinaryNode<Integer> t1 = new BinaryNode<>(1);
        t1.right = new BinaryNode<>(1);
        t1.right.right = new BinaryNode<>(1);
        t1.right.right.left = new BinaryNode<>(2);

        BinaryNode<Integer> t2 = new BinaryNode<>(1);
        t2.left = new BinaryNode<>(2);

        assertTrue(isSubtree(t1, t2));
    }
}
