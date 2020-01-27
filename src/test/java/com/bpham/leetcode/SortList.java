package com.bpham.leetcode;

import org.junit.Test;

public class SortList {
    public ListNode sortList(ListNode head) {
        if (head.next == null) return head;
        ListNode mid = getMiddle(head);
        ListNode left = head;
        ListNode right = mid.next;
        mid.next = null;
        System.out.println(left.val);
        System.out.println(right.val);
        // ListNode leftSorted = sortList(left);
        // ListNode rightSorted = sortList(right);
        // ListNode res = mergeLists(leftSorted, rightSorted);
        ListNode res = mergeLists(left, right);
        return res;
    }

    public ListNode mergeLists(ListNode a, ListNode b) {
        ListNode headPtr = null;
        ListNode currPtr = null;
        ListNode left = a;
        ListNode right = b;
        while (left != null || right != null) {
            if (currPtr == null) {
                if (left == null) {
                    currPtr = right;
                    right = right.next;
                } else if (right == null) {
                    currPtr = left;
                    left = left.next;
                } else {
                    if (left.val < right.val) {
                        currPtr = left;
                        left = left.next;
                    } else {
                        currPtr = right;
                        right = right.next;
                    }
                }
                headPtr = currPtr;
            } else {
                if (left == null) {
                    currPtr.next = right;
                    currPtr = currPtr.next;
                    right = right.next;
                } else if (right == null) {
                    currPtr.next = left;
                    currPtr = currPtr.next;
                    left = left.next;
                } else {
                    if (left.val < right.val) {
                        currPtr.next = left;
                        left = left.next;
                    } else {
                        currPtr.next = right;
                        right = right.next;
                    }
                    currPtr = currPtr.next;
                }
            }
        }
        return headPtr;
    }

    public ListNode getMiddle(ListNode head) {
        int len = 0;
        ListNode ptr = head;
        while (ptr != null) {
            ptr = ptr.next;
            len++;
        }
        int midIndex = (len - 1) / 2;
        ListNode res = head;
        for (int i = 0; i <= midIndex; i++) {
            res = res.next;
        }
        return res;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    @Test
    public void test() {

    }
}
