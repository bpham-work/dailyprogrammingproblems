package com.bpham.ctci.linkedlists;

import com.bpham.ctci.models.Node;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Weaving {
    public void weave(Node<String> node) {
        Node<String> list1 = node;
        Node<String> list2 = getHeadOfSecondList(node);

        Node<String> n = node;
        while (n.next != list2) {
            n = n.next;
        }
        n.next = null;

        while (list2 != null) {
            Node<String> nextlist1 = list1.next;
            Node<String> nextlist2 = list2.next;
            list1.next = list2;
            list1.next.next = nextlist1;
            list1 = nextlist1;
            list2 = nextlist2;
        }
    }

    public Node<String> getHeadOfSecondList(Node<String> node) {
        Node<String> fast = node;
        Node<String> slow = node;
        while (fast != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    @Test
    public void test() {
        Node<String> a1 = new Node<>("a1");
        Node<String> a2 = new Node<>("a2");
        Node<String> a3 = new Node<>("a3");
        Node<String> a4 = new Node<>("a4");
        Node<String> b1 = new Node<>("b1");
        Node<String> b2 = new Node<>("b2");
        Node<String> b3 = new Node<>("b3");
        Node<String> b4 = new Node<>("b4");
        a1.next = a2;
        a2.next = a3;
        a3.next = a4;
        a4.next = b1;
        b1.next = b2;
        b2.next = b3;
        b3.next = b4;

        weave(a1);

        assertEquals("a1->b1->a2->b2->a3->b3->a4->b4", a1.toString());
    }
}
