package com.bpham.ctci.linkedlists;

import com.bpham.ctci.models.Node;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * #7
 *
 * Given two (singly) linked lists, determine if the two lists intersect.
 * Return the intersecting node. Note that the intersection is defined
 * based on reference, not value. That is, if the kth node of the first
 * linked list is the exact same node (by reference) as the jth node of
 * the second linked list, then they are intersecting.
 *
 * Questions:
 * 1. What to return if not intersection? null?
 */

public class Intersection {
    public Node<String> intersectingNodeSol1(Node<String> n1, Node<String> n2) {
        Set<Node<String>> visited = new HashSet<>();
        Node<String> curr = n1;
        while (curr != null) {
            visited.add(curr);
            curr = curr.next;
        }
        curr = n2;
        while (curr != null) {
            if (visited.contains(curr)) {
                return curr;
            }
            curr = curr.next;
        }
        return null;
    }

    /**
     * Time: O(A+B)
     * Space: O(1)
     *
     * @param n1
     * @param n2
     * @return
     */
    public Node<String> intersectingNodeSol2(Node<String> n1, Node<String> n2) {
        Node<String> lastCommonNode = null;
        boolean found = false;
        if (n1.equals(n2)) {
            return n1;
        }
        while (!found) {
            Node<String> n1End = n1;
            while (n1End.next != lastCommonNode) {
                n1End = n1End.next;
            }
            Node<String> n2End = n2;
            while (n2End.next != lastCommonNode) {
                n2End = n2End.next;
            }
            if (!n1End.equals(n2End)) {
                found = true;
            } else {
                lastCommonNode = n1End;
            }
        }
        return lastCommonNode;
    }

    @Test
    public void intersects() {
        Node<String> a1 = new Node<>("1");
        Node<String> a2 = new Node<>("1");
        Node<String> a3 = new Node<>("1");
        a1.next = a2;
        a2.next = a3;

        Node<String> b1 = new Node<>("1");
        Node<String> b2 = new Node<>("1");
        Node<String> b3 = new Node<>("1");
        b1.next = b2;
        b2.next = a3;

        assertEquals(a3, intersectingNodeSol1(a1, b1));
        assertEquals(a3, intersectingNodeSol2(a1, b1));
    }

    @Test
    public void intersects2() {
        Node<String> a1 = new Node<>("1");
        Node<String> a2 = new Node<>("1");
        Node<String> a3 = new Node<>("1");
        a1.next = a2;
        a2.next = a3;

        Node<String> b1 = new Node<>("1");
        Node<String> b2 = new Node<>("1");
        Node<String> b3 = new Node<>("1");
        b1.next = b2;
        b2.next = a3;

        assertEquals(a3, intersectingNodeSol1(b1, a1));
        assertEquals(a3, intersectingNodeSol2(b1, a1));
    }

    @Test
    public void noIntersects() {
        Node<String> a1 = new Node<>("1");
        Node<String> b2 = new Node<>("1");

        assertNull(intersectingNodeSol1(a1, b2));
        assertNull(intersectingNodeSol2(a1, b2));
    }
}
