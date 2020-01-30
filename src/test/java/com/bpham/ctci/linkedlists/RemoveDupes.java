package com.bpham.ctci.linkedlists;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * #1
 *
 * Write code to remove duplicates from an unsorted linked list.
 *
 * FOLLOW UP
 * How would you solve this problem if a temporary buffer is not allowed?
 */

public class RemoveDupes {
    /**
     * Time: O(n^2)
     * Space: O(1)
     *
     * @param list
     */
    public void removeDupesSol1(Node<String> list) {
        Node<String> curr = list;
        while (curr.next != null) {
            Node<String> iter = curr;
            while (iter.next != null) {
                if (iter.next.val.equals(curr.val)) {
                    iter.next = iter.next.next;
                }
                iter = iter.next;
            }
            curr = curr.next;
        }
    }

    /**
     * Time: O(n)
     * Space: O(n)
     *
     * @param list
     */
    public void removeDupesSol2(Node<String> list) {
        Set<String> visited = new HashSet<>();
        Node<String> curr = list;
        while (curr.next != null) {
            visited.add(curr.val);
            if (visited.contains(curr.next.val)) {
                curr.next = curr.next.next;
            }
            curr = curr.next;
        }
    }

    @Test
    public void test_sol1() {
        Node<String> a1 = new Node<>("a");
        Node<String> a2 = new Node<>("a");
        Node<String> a3 = new Node<>("b");
        a1.next = a2;
        a2.next = a3;

        removeDupesSol1(a1);

        assertEquals("a->b", a1.toString());
    }

    @Test
    public void test2_sol1() {
        Node<String> a1 = new Node<>("a");
        Node<String> a2 = new Node<>("b");
        a1.next = a2;

        removeDupesSol1(a1);

        assertEquals("a->b", a1.toString());
    }

    @Test
    public void test_sol2() {
        Node<String> a1 = new Node<>("a");
        Node<String> a2 = new Node<>("a");
        Node<String> a3 = new Node<>("b");
        a1.next = a2;
        a2.next = a3;

        removeDupesSol2(a1);

        assertEquals("a->b", a1.toString());
    }

    @Test
    public void test2_sol2() {
        Node<String> a1 = new Node<>("a");
        Node<String> a2 = new Node<>("b");
        a1.next = a2;

        removeDupesSol2(a1);

        assertEquals("a->b", a1.toString());
    }
}
