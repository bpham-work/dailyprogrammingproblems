package com.bpham.ctci.linkedlists;

import com.bpham.ctci.models.Node;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * #3
 *
 * Implement an algorithm to delete a node in the middle
 * (i.e., any node but the first and last node, not necessarily the exact middle)
 * of a singly linked list, given only access to that node.
 *
 * EXAMPLE
 * Input: the node c from the linked list a - >b- >c - >d - >e- >f
 * Result: nothing is returned, but the new linked list looks like a - >b- >d - >e- >f
 */

public class DeleteMiddleNode {
    public void deleteMiddleNode(Node<String> node) {
        Node<String> curr = node;
        while (curr.next.next != null) {
            curr.val = curr.next.val;
            curr = curr.next;
        }
        curr.val = curr.next.val;
        curr.next = null;
    }

    @Test
    public void test() {
        Node<String> a = new Node<>("a");
        Node<String> b = new Node<>("b");
        Node<String> c = new Node<>("c");
        Node<String> d = new Node<>("d");
        Node<String> e = new Node<>("e");
        Node<String> f = new Node<>("f");
        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        e.next = f;

        deleteMiddleNode(c);

        assertEquals("a->b->d->e->f", a.toString());
    }

    @Test
    public void test2() {
        Node<String> a = new Node<>("a");
        Node<String> b = new Node<>("b");
        Node<String> c = new Node<>("c");
        a.next = b;
        b.next = c;

        deleteMiddleNode(b);

        assertEquals("a->c", a.toString());
    }

    @Test
    public void test3() {
        Node<String> a = new Node<>("a");
        Node<String> b = new Node<>("b");
        Node<String> c = new Node<>("c");
        Node<String> d = new Node<>("d");
        Node<String> e = new Node<>("e");
        Node<String> f = new Node<>("f");
        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        e.next = f;

        deleteMiddleNode(a);

        assertEquals("b->c->d->e->f", a.toString());
    }
}
