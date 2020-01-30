package com.bpham.ctci.linkedlists;

import org.junit.Test;

/**
 * #4
 *
 * Write code to partition a linked list around a value x,
 * such that all nodes less than x come before all nodes greater than or equal to x.
 * lf x is contained within the list, the values of x only need to be after the
 * elements less than x (see below).
 * The partition element x can appear anywhere in the "right partition";
 * it does not need to appear between the left and right partitions.
 *
 * EXAMPLE
 * Input: 3 -> 5 -> 8 -> 5 ->10 -> 2 -> 1[partition=5]
 * Output: 3 -> 1 -> 2 -> 10 -> 5 -> 5 -> 8
 *
 * Questions:
 * 1. Return new head? -- Must return new head if in place partitioning
 * 2. What data type? How large can numbers be?
 *
 * Takeaways:
 * 1. Separate/encapsulate current node from list by nulling next ref.
 *      This prevents circular reference with current list
 */

public class Partition {
    public Node<Integer> partitionList(Node<Integer> head, int partition) {
        Node<Integer> lessHead = null;
        Node<Integer> lessTail = null;
        Node<Integer> greaterHead = null;
        Node<Integer> greaterTail = null;

        Node<Integer> curr = head;
        while (curr != null) {
            Node<Integer> next = curr.next;
            curr.next = null;
            if (curr.val < partition) {
                if (lessHead == null) {
                    lessHead = curr;
                    lessTail = curr;
                } else {
                    lessTail.next = curr;
                    lessTail = curr;
                }
            } else {
                if (greaterHead == null) {
                    greaterHead = curr;
                    greaterTail = curr;
                } else {
                    greaterTail.next = curr;
                    greaterTail = curr;
                }
            }
            curr = next;
        }

        if (lessTail == null) {
            return greaterHead;
        } else {
            lessTail.next = greaterHead;
            return lessHead;
        }
    }

    @Test
    public void test() {
        Node<Integer> n1 = new Node<>(3);
        Node<Integer> n2 = new Node<>(5);
        Node<Integer> n3 = new Node<>(8);
        Node<Integer> n4 = new Node<>(5);
        Node<Integer> n5 = new Node<>(10);
        Node<Integer> n6 = new Node<>(2);
        Node<Integer> n7 = new Node<>(1);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;

        System.out.println("PARTITION: 5");
        Node<Integer> newHead = partitionList(n1, 5);

        System.out.println(newHead.toString());
    }

    @Test
    public void testEvenList() {
        Node<Integer> n1 = new Node<>(3);
        Node<Integer> n2 = new Node<>(5);
        Node<Integer> n3 = new Node<>(8);
        Node<Integer> n4 = new Node<>(5);
        Node<Integer> n5 = new Node<>(10);
        Node<Integer> n6 = new Node<>(2);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;

        System.out.println("PARTITION: 6");
        Node<Integer> newHead = partitionList(n1, 6);

        System.out.println(newHead.toString());
    }

    @Test
    public void testAllLessThanPartition() {
        Node<Integer> n1 = new Node<>(3);
        Node<Integer> n2 = new Node<>(5);
        Node<Integer> n3 = new Node<>(8);
        Node<Integer> n4 = new Node<>(5);
        Node<Integer> n5 = new Node<>(10);
        Node<Integer> n6 = new Node<>(2);
        Node<Integer> n7 = new Node<>(1);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;

        System.out.println("PARTITION: 0");
        Node<Integer> newHead = partitionList(n1, 0);

        System.out.println(newHead.toString());
    }

    @Test
    public void testAllGreaterThanPartition() {
        Node<Integer> n1 = new Node<>(3);
        Node<Integer> n2 = new Node<>(5);
        Node<Integer> n3 = new Node<>(8);
        Node<Integer> n4 = new Node<>(5);
        Node<Integer> n5 = new Node<>(10);
        Node<Integer> n6 = new Node<>(2);
        Node<Integer> n7 = new Node<>(1);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;

        System.out.println("PARTITION: 20");
        Node<Integer> newHead = partitionList(n1, 20);

        System.out.println(newHead.toString());
    }
}
