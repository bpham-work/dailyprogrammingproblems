package com.bpham.dailycodingproblem;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

/**
 *   This problem was asked by Google.

     Given the root to a binary tree, implement serialize(root), which serializes the tree into a string, and deserialize(s), which deserializes the string back into the tree.

     For example, given the following Node class

     class Node:
     def __init__(self, val, left=None, right=None):
     self.val = val
     self.left = left
     self.right = right
     The following test should pass:

     node = Node('root', Node('left', Node('left.left')), Node('right'))
     assert deserialize(serialize(node)).left.left.val == 'left.left'
 *
 */

public class Problem3Medium {

    public String serialize(Node root) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        List<String> elems = new ArrayList<>();
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.isNull) {
                elems.add("null");
            } else {
                elems.add(node.val);
                if (node.left == null) {
                    queue.add(new Node(true));
                } else {
                    queue.add(node.left);
                }
                if (node.right == null) {
                    queue.add(new Node(true));
                } else {
                    queue.add(node.right);
                }
            }
        }
        return elems.stream().collect(Collectors.joining(","));
    }

    public Node deserialize(String str) {
        List<String> list = new LinkedList<>(Arrays.asList(str.split(",")));
        Queue<Node> queue = new LinkedList<>();
        list.add(0, "");
        Node root = null;
        for (int i = 1; i < list.size(); i++) {
            String nodeVal = list.get(i);
            if (nodeVal.equals("null")) {
                continue;
            }
            if (i == 1) {
                root = new Node(nodeVal);
                queue.add(root);
            }

            Node currNode = queue.poll();

            String leftChild = list.get(i * 2);
            String rightChild = list.get(i * 2 + 1);
            if (!leftChild.equals("null")) {
                currNode.left = new Node(leftChild);
                queue.add(currNode.left);
            }
            if (!rightChild.equals("null")) {
                currNode.right = new Node(rightChild);
                queue.add(currNode.right);
            }
        }
        return root;
    }

    @Test
    public void serializeTest() {
        assertEquals("root,left,right,left.left,null,null,null,null,null", serialize(new Node("root", new Node("left", new Node("left.left"), null), new Node("right"))));
        assertEquals("root,left,right,left.left,left.right,right.left,right.right,null,null,null,null,null,null,null,null", serialize(
                new Node("root",
                        new Node("left", new Node("left.left"), new Node("left.right")),
                        new Node("right", new Node("right.left"), new Node("right.right")))));
    }

    @Test
    public void deserializeTest() {
        Node root1 = deserialize("root,left,right,left.left,null,null,null,null,null");
        Node root2 = deserialize("root,left,right,left.left,left.right,right.left,right.right,null,null,null,null,null,null,null,null");

        assertEquals("left.left", root1.left.left.val);
        assertEquals("root", root2.val);
        assertEquals("left", root2.left.val);
        assertEquals("right", root2.right.val);
        assertEquals("left.left", root2.left.left.val);
        assertEquals("left.right", root2.left.right.val);
        assertEquals("right.left", root2.right.left.val);
        assertEquals("right.right", root2.right.right.val);
    }

    private class Node {
        public Node left;
        public Node right;
        public String val;
        public boolean isNull = false;

        public Node(String val) {
            this.val = val;
            if (val.equals("null")) {
                this.isNull = true;
            }
        }
        public Node(boolean isNull) {
            this.isNull = isNull;
            this.val = "null";
        }
        public Node(String val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
