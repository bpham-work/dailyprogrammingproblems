package com.bpham.ctci.linkedlists;

import com.bpham.ctci.models.Node;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * #6
 *
 * Implement a function to check if a linked list is a palindrome.
 */

public class Palindrome {
    public boolean isPalindrome(Node<String> n) {
        Node<String> curr = n;
        int length = 0;
        while (curr != null) {
            length++;
            curr = curr.next;
        }

        Deque<String> stack = new ArrayDeque<>();
        for (int i = 0; i < length / 2; i++) {
            stack.push(n.val);
            n = n.next;
        }
        if (length % 2 != 0) {
            n = n.next;
        }
        while (!stack.isEmpty()) {
            if (!n.val.equals(stack.pop())) {
                return false;
            }
            n = n.next;
        }
        return true;
    }

    @Test
    public void testIsPalindromeOddLengthString() {
        Node<String> n1 = new Node<>("a");
        Node<String> n2 = new Node<>("b");
        Node<String> n3 = new Node<>("a");
        n1.next = n2;
        n2.next = n3;

        assertTrue(isPalindrome(n1));
    }

    @Test
    public void testIsPalindromeEvenLengthString() {
        Node<String> n1 = new Node<>("a");
        Node<String> n2 = new Node<>("b");
        Node<String> n3 = new Node<>("b");
        Node<String> n4 = new Node<>("a");
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;

        assertTrue(isPalindrome(n1));
    }

    @Test
    public void testIsNotPalindromeOddLengthString() {
        Node<String> n1 = new Node<>("a");
        Node<String> n2 = new Node<>("b");
        Node<String> n3 = new Node<>("c");
        n1.next = n2;
        n2.next = n3;

        assertFalse(isPalindrome(n1));
    }

    @Test
    public void testIsNotPalindromeEvenLengthString() {
        Node<String> n1 = new Node<>("a");
        Node<String> n2 = new Node<>("b");
        n1.next = n2;

        assertFalse(isPalindrome(n1));
    }
}
