package com.bpham.ctci.linkedlists;

import com.bpham.ctci.models.Node;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * #2
 *
 * Implement an algorithm to find the kth to last element of a singly linked list.
 */

public class KthToLast {
    public String getKthToLast(Node<String> head, int k) {
        if (k == 0) {
            throw new IllegalArgumentException();
        }
        int index = 0;
        Node<String> start = head;
        Node<String> end = head;
        while (index < k) {
            index++;
            if (end == null) {
                throw new IllegalArgumentException();
            }
            end = end.next;
        }
        while (end != null) {
            start = start.next;
            end = end.next;
        }
        return start.val;
    }

    public String getKthToLastSol2(Node<String> head, int k) {
        int length = 0;
        Node<String> curr = head;
        while (curr != null) {
            length++;
            curr = curr.next;
        }

        int targetIndex = length - k + 1;

        if (targetIndex < 1 || targetIndex > length) {
            throw new IllegalArgumentException();
        }

        curr = head;
        while (targetIndex != 1) {
            curr = curr.next;
            targetIndex--;
        }

        return curr.val;
    }

    private Node<String> head;

    @Before
    public void setup() {
        this.head = new Node<>("a1");
        Node<String> a2 = new Node<>("a2");
        Node<String> a3 = new Node<>("a3");
        Node<String> a4 = new Node<>("a4");
        Node<String> b1 = new Node<>("b1");
        Node<String> b2 = new Node<>("b2");
        Node<String> b3 = new Node<>("b3");
        Node<String> b4 = new Node<>("b4");
        head.next = a2;
        a2.next = a3;
        a3.next = a4;
        a4.next = b1;
        b1.next = b2;
        b2.next = b3;
        b3.next = b4;
    }

    @Test
    public void get1stToLast() {
        assertEquals("b4", getKthToLast(head, 1));
    }

    @Test
    public void get2ndToLast() {
        assertEquals("b3", getKthToLast(head, 2));
    }

    @Test
    public void getFirst() {
        assertEquals("a1", getKthToLast(head, 8));
    }

    @Test(expected = IllegalArgumentException.class)
    public void outOfBounds0() {
        getKthToLast(head, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void outOfBounds9() {
        getKthToLast(head, 9);
    }
}
