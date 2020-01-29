package com.bpham.ctci.linkedlists;

public class Node<T> {
    public T val;
    public Node<T> next;

    public Node(T val) {
        this.val = val;
    }

    public String toString() {
        Node<T> curr = this;
        StringBuilder sb = new StringBuilder();
        while (curr != null) {
            sb.append(curr.val);
            curr = curr.next;
            if (curr != null) {
                sb.append("->");
            }
        }
        return sb.toString();
    }
}
