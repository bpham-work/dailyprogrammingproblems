package com.bpham.practice;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/copy-list-with-random-pointer/discuss/?currentPage=1&orderBy=most_votes&query=
 */

public class CopyListRandomPointer {
    static class Node {
        public int val;
        public Node next;
        public Node random;

        public Node() {}

        public Node(int _val,Node _next,Node _random) {
            val = _val;
            next = _next;
            random = _random;
        }
    }

    Map<Node, Node> map = new HashMap<>();

    public Node copyRandomList(Node node) {
        if (node == null) return null;
        if (map.containsKey(node)) return map.get(node);
        Node newNode = new Node();
        map.put(node, newNode);
        newNode.val = node.val;
        newNode.next = copyRandomList(node.next);
        newNode.random = copyRandomList(node.random);
        return newNode;
    }
}
