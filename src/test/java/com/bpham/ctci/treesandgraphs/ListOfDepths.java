package com.bpham.ctci.treesandgraphs;

import com.bpham.ctci.models.BinaryNode;
import com.bpham.ctci.models.Node;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * #3
 *
 * Given a binary tree, design an algorithm which creates a linked
 * list of all the nodes at each depth
 * (e.g., if you have a tree with depth 0, you'll have 0 linked lists).
 */

public class ListOfDepths {
    public List<Node<Integer>> depthLists(BinaryNode<Integer> tree) {
        List<Node<Integer>> res = new ArrayList<>();
        Queue<BinaryNode<Integer>> q = new LinkedList<>();
        int count = 1;
        q.add(tree);
        while (!q.isEmpty()) {
            int numChild = 0;
            Node<Integer> head = null;
            Node<Integer> tail = null;
            for (int i = 0; i < count; i++) {
                BinaryNode<Integer> treeNode = q.poll();
                Node<Integer> n = new Node<>(treeNode.value);
                if (head == null) {
                    head = tail = n;
                } else {
                    tail.next = n;
                    tail = n;
                }
                if (treeNode.left != null) {
                    numChild++;
                    q.add(treeNode.left);
                }
                if (treeNode.right != null) {
                    numChild++;
                    q.add(treeNode.right);
                }
            }
            count = numChild;
            res.add(head);
        }
        return res;
    }

    @Test
    public void test1() {
        BinaryNode<Integer> head = new BinaryNode<>(1);
        head.left = new BinaryNode<>(2);
        head.right = new BinaryNode<>(3);
        head.left.left = new BinaryNode<>(4);
        head.left.right = new BinaryNode<>(5);
        head.right.left = new BinaryNode<>(6);
        head.right.right = new BinaryNode<>(7);

        List<Node<Integer>> res = depthLists(head);

        res.forEach(list -> System.out.println(list.toString()));
    }
}
