package com.bpham.ctci.treesandgraphs;

import com.bpham.ctci.models.BinaryNodeWithParent;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * #6
 *
 * Write an algorithm to find the "next" node (i.e., in-order successor)
 * of a given node in a binary search tree. You may assume that each
 * node has a link to its parent.
 */

public class Successor {
    public BinaryNodeWithParent<Integer> getNext(BinaryNodeWithParent<Integer> node) {
        BinaryNodeWithParent<Integer> leftMostInRightTree = getLeftMost(node.right);
        if (leftMostInRightTree == null) {
            if (node.value <= node.parent.value) {
                return node.parent;
            } else {
                // find first left node
                BinaryNodeWithParent<Integer> curr = node.parent;
                while (curr.parent != null && curr.value > curr.parent.value) {
                    curr = curr.parent;
                }
                return curr.parent;
            }
        } else {
            return leftMostInRightTree;
        }
    }

    public BinaryNodeWithParent<Integer> getLeftMost(BinaryNodeWithParent<Integer> node) {
        if (node == null) return null;
        BinaryNodeWithParent<Integer> left = getLeftMost(node.left);
        if (left == null) return node;
        return left;
    }

    @Test
    public void test1() {
        BinaryNodeWithParent<Integer> head = new BinaryNodeWithParent<>(10, null);
        head.left = new BinaryNodeWithParent<>(5, head);
        head.right = new BinaryNodeWithParent<>(20, head);
        head.left.right = new BinaryNodeWithParent<>(7, head.left);

        BinaryNodeWithParent<Integer> next = getNext(head.left);

        assertEquals((Integer) 7, next.value);
    }

    @Test
    public void test2() {
        BinaryNodeWithParent<Integer> head = new BinaryNodeWithParent<>(10, null);
        head.left = new BinaryNodeWithParent<>(5, head);
        head.right = new BinaryNodeWithParent<>(20, head);
        head.left.right = new BinaryNodeWithParent<>(7, head.left);
        head.left.right.left = new BinaryNodeWithParent<>(6, head.left.right);

        BinaryNodeWithParent<Integer> next = getNext(head.left);
        BinaryNodeWithParent<Integer> next2 = getNext(head.left.right);

        assertEquals((Integer) 6, next.value);
        assertEquals((Integer) 10, next2.value);
    }

    @Test
    public void test3() {
        BinaryNodeWithParent<Integer> head = new BinaryNodeWithParent<>(10, null);
        head.left = new BinaryNodeWithParent<>(5, head);
        head.right = new BinaryNodeWithParent<>(20, head);

        BinaryNodeWithParent<Integer> next = getNext(head.left);
        BinaryNodeWithParent<Integer> next2 = getNext(head);
        BinaryNodeWithParent<Integer> next3 = getNext(head.right);

        assertEquals((Integer) 10, next.value);
        assertEquals((Integer) 20, next2.value);
        assertEquals(null, next3);
    }

    @Test
    public void test4() {
        BinaryNodeWithParent<Integer> head = new BinaryNodeWithParent<>(20, null);
        head.left = new BinaryNodeWithParent<>(8, head);
        head.left.right = new BinaryNodeWithParent<>(12, head.left);
        head.left.right.left = new BinaryNodeWithParent<>(10, head.left.right);
        head.left.right.right = new BinaryNodeWithParent<>(14, head.left.right);
        head.right = new BinaryNodeWithParent<>(22, head);

        BinaryNodeWithParent<Integer> next = getNext(head.left.right.right);

        assertEquals((Integer) 20, next.value);
    }
}
