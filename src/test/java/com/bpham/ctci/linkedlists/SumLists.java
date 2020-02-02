package com.bpham.ctci.linkedlists;

import com.bpham.ctci.models.Node;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

import static org.junit.Assert.assertEquals;

/**
 * #5
 *
 * You have two numbers represented by a linked list,
 * where each node contains a single digit.
 * The digits are stored in reverse order,
 * such that the 1's digit is at the head of the list.
 * Write a function that adds the two numbers and returns the sum as a linked list.
 *
 * EXAMPLE
 * Input: (7 -> 1 -> 6) + (5 -> 9 -> 2).That is, 617 + 295.
 * Output: 2 -> 1 -> 9. That is, 912.
 *
 * FOLLOW UP
 * Suppose the digits are stored in forward order. Repeat the above problem.
 * EXAMPLE
 * Input: (6 -> 1 -> 7) + (2 -> 9 -> 5). That is, 617 + 295.
 * Output: 9 -> 1 -> 2. That is, 912.
 *
 * Questions:
 * 1. Return new list or in place?
 */

public class SumLists {
    public Node<Integer> sumSol1(Node<Integer> n1, Node<Integer> n2) {
        return sumSol1(n1, n2, 0);
    }

    public Node<Integer> sumSol1(Node<Integer> n1, Node<Integer> n2, int carry) {
        int sum = 0;
        if (n1 == null && n2 == null && carry == 0) {
            return null;
        } else if (n1 == null && n2 == null) {
            sum = carry;
        } else if (n1 == null && n2 != null) {
            sum = n2.val + carry;
        } else if (n1 != null && n2 == null) {
            sum = n1.val + carry;
        } else {
            sum = n1.val + n2.val + carry;
        }
        if (sum > 9) {
            carry = 1;
            sum -= 10;
        } else {
            carry = 0;
        }
        Node<Integer> res = new Node<>(sum);
        Node<Integer> nextn1 = n1 != null ? n1.next : null;
        Node<Integer> nextn2 = n2 != null ? n2.next : null;
        res.next = sumSol1(nextn1, nextn2, carry);
        return res;
    }

    int carry = 0;

    public Node<Integer> sumSol2(Node<Integer> n1, Node<Integer> n2) {
        int n1Length = 0;
        int n2Length = 0;
        Node<Integer> curr = n1;
        while (curr != null) {
            n1Length++;
            curr = curr.next;
        }
        curr = n2;
        while (curr != null) {
            n2Length++;
            curr = curr.next;
        }

        if (n1Length < n2Length) {
            for (int i = 0; i < n2Length - n1Length; i++) {
                Node<Integer> temp = n1;
                n1 = new Node<>(0);
                n1.next = temp;
            }
        } else if (n1Length > n2Length) {
            for (int i = 0; i < n1Length - n2Length; i++) {
                Node<Integer> temp = n2;
                n2 = new Node<>(0);
                n2.next = temp;
            }
        }
        Node<Integer> res = sumRecursionSol2(n1, n2);
        if (carry > 0) {
            Node<Integer> temp = res;
            res = new Node<>(1);
            res.next = temp;
        }
        return res;
    }

    public Node<Integer> sumRecursionSol2(Node<Integer> n1, Node<Integer> n2) {
        if (n1 == null && n2 == null) {
            return null;
        }
        Node<Integer> child = sumRecursionSol2(n1.next, n2.next);
        int sum = n1.val + n2.val + carry;
        if (sum > 9) {
            sum -= 10;
            carry = 1;
        } else {
            carry = 0;
        }
        Node<Integer> curr = new Node<>(sum);
        curr.next = child;
        return curr;
    }

    public Node<Integer> sumSol3(Node<Integer> n1, Node<Integer> n2) {
        int carry = 0;
        Deque<Integer> n1Stack = new ArrayDeque<>();
        Deque<Integer> n2Stack = new ArrayDeque<>();
        while (n1 != null) {
            n1Stack.push(n1.val);
            n1 = n1.next;
        }
        while (n2 != null) {
            n2Stack.push(n2.val);
            n2 = n2.next;
        }

        Deque<Integer> resultStack = new ArrayDeque<>();
        while (!n1Stack.isEmpty() || !n2Stack.isEmpty()) {
            int sum = 0;
            if (n1Stack.isEmpty() && !n2Stack.isEmpty()) {
                sum = n2Stack.pop() + carry;
            } else if (!n1Stack.isEmpty() && n2Stack.isEmpty()) {
                sum = n1Stack.pop() + carry;
            } else {
                sum = n1Stack.pop() + n2Stack.pop() + carry;
            }
            if (sum > 9) {
                sum -= 10;
                carry = 1;
            } else {
                carry = 0;
            }
            resultStack.push(sum);
        }

        if (carry > 0) {
            resultStack.push(carry);
        }

        Node<Integer> res = null;
        Node<Integer> tail = null;
        while (!resultStack.isEmpty()) {
            Node<Integer> node = new Node<>(resultStack.pop());
            if (res == null) {
                res = node;
                tail = node;
            }
            else {
                tail.next = node;
                tail = node;
            }
        }
        return res;
    }

    @Test
    public void testEvenLengthNoCarryoverSol1() {
        Node<Integer> a1 = new Node<>(1);
        Node<Integer> a2 = new Node<>(2);
        a1.next = a2;

        Node<Integer> b1 = new Node<>(1);
        Node<Integer> b2 = new Node<>(2);
        b1.next = b2;

        System.out.println("sol1 21+21");
        Node<Integer> result = sumSol1(a1, b1);

        assertEquals("2->4", result.toString());
    }

    @Test
    public void testEvenLengthCarryoverSol1() {
        Node<Integer> a1 = new Node<>(2);
        Node<Integer> a2 = new Node<>(2);
        a1.next = a2;

        Node<Integer> b1 = new Node<>(9);
        Node<Integer> b2 = new Node<>(2);
        b1.next = b2;

        System.out.println("sol1 22+29");
        Node<Integer> result = sumSol1(a1, b1);

        assertEquals("1->5", result.toString());
    }

    @Test
    public void testEvenLengthCarryover2Sol1() {
        Node<Integer> a1 = new Node<>(2);
        Node<Integer> a2 = new Node<>(2);
        a1.next = a2;

        Node<Integer> b1 = new Node<>(9);
        Node<Integer> b2 = new Node<>(7);
        b1.next = b2;

        System.out.println("sol1 22+79");
        Node<Integer> result = sumSol1(a1, b1);

        assertEquals("1->0->1", result.toString());
    }

    @Test
    public void testEvenLengthCarryover3Sol1() {
        Node<Integer> a1 = new Node<>(7);
        Node<Integer> a2 = new Node<>(1);
        Node<Integer> a3 = new Node<>(6);
        a1.next = a2;
        a2.next = a3;

        Node<Integer> b1 = new Node<>(5);
        Node<Integer> b2 = new Node<>(9);
        Node<Integer> b3 = new Node<>(2);
        b1.next = b2;
        b2.next = b3;

        System.out.println("sol1 617+295");
        Node<Integer> result = sumSol1(a1, b1);

        assertEquals("2->1->9", result.toString());
    }

    @Test
    public void testOddLengthNoCarryoverSol1() {
        Node<Integer> a1 = new Node<>(2);

        Node<Integer> b1 = new Node<>(2);
        Node<Integer> b2 = new Node<>(9);
        b1.next = b2;

        System.out.println("sol1 2+92");
        Node<Integer> result = sumSol1(a1, b1);

        assertEquals("4->9", result.toString());
    }

    @Test
    public void testOddLengthCarryoverSol1() {
        Node<Integer> a1 = new Node<>(2);

        Node<Integer> b1 = new Node<>(9);
        Node<Integer> b2 = new Node<>(2);
        b1.next = b2;

        System.out.println("sol1 2+29");
        Node<Integer> result = sumSol1(a1, b1);

        assertEquals("1->3", result.toString());
    }

    @Test
    public void testOddLengthCarryoverSol3() {
        Node<Integer> a1 = new Node<>(2);

        Node<Integer> b1 = new Node<>(9);
        Node<Integer> b2 = new Node<>(2);
        Node<Integer> b3 = new Node<>(2);
        b1.next = b2;
        b2.next = b3;

        System.out.println("sol1 2+229");
        Node<Integer> result = sumSol1(a1, b1);

        assertEquals("1->3->2", result.toString());
    }

    @Test
    public void testEvenLengthNoCarryoverSol2() {
        Node<Integer> a1 = new Node<>(2);
        Node<Integer> a2 = new Node<>(2);
        a1.next = a2;

        Node<Integer> b1 = new Node<>(2);
        Node<Integer> b2 = new Node<>(2);
        b1.next = b2;

        System.out.println("sol2 22+22");
        Node<Integer> result = sumSol2(a1, b1);

        assertEquals("4->4", result.toString());
    }

    @Test
    public void testEvenLengthCarryoverSol2() {
        Node<Integer> a1 = new Node<>(2);
        Node<Integer> a2 = new Node<>(2);
        a1.next = a2;

        Node<Integer> b1 = new Node<>(2);
        Node<Integer> b2 = new Node<>(9);
        b1.next = b2;

        System.out.println("sol2 22+29");
        Node<Integer> result = sumSol2(a1, b1);

        assertEquals("5->1", result.toString());
    }

    @Test
    public void testEvenLengthCarryover2Sol2() {
        Node<Integer> a1 = new Node<>(9);
        Node<Integer> a2 = new Node<>(2);
        a1.next = a2;

        Node<Integer> b1 = new Node<>(2);
        Node<Integer> b2 = new Node<>(2);
        b1.next = b2;

        System.out.println("sol2 92+22");
        Node<Integer> result = sumSol2(a1, b1);

        assertEquals("1->1->4", result.toString());
    }

    @Test
    public void testOddLengthNoCarryoverSol2() {
        Node<Integer> a1 = new Node<>(2);

        Node<Integer> b1 = new Node<>(2);
        Node<Integer> b2 = new Node<>(2);
        b1.next = b2;

        System.out.println("sol1 2+22");
        Node<Integer> result = sumSol2(a1, b1);

        assertEquals("2->4", result.toString());
    }

    @Test
    public void testOddLengthCarryoverSol2() {
        Node<Integer> a1 = new Node<>(2);

        Node<Integer> b1 = new Node<>(2);
        Node<Integer> b2 = new Node<>(9);
        b1.next = b2;

        System.out.println("sol1 2+29");
        Node<Integer> result = sumSol2(a1, b1);

        assertEquals("3->1", result.toString());
    }

    @Test
    public void testEvenLengthCarryover3Sol2() {
        Node<Integer> a1 = new Node<>(6);
        Node<Integer> a2 = new Node<>(1);
        Node<Integer> a3 = new Node<>(7);
        a1.next = a2;
        a2.next = a3;

        Node<Integer> b1 = new Node<>(2);
        Node<Integer> b2 = new Node<>(9);
        Node<Integer> b3 = new Node<>(5);
        b1.next = b2;
        b2.next = b3;

        System.out.println("sol1 617+295");
        Node<Integer> result = sumSol2(a1, b1);

        assertEquals("9->1->2", result.toString());
    }
}
