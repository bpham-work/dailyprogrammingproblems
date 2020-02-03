package com.bpham.ctci.models;

public class BinaryNodeWithParent<T> {
    public T value;
    public BinaryNodeWithParent<T> left;
    public BinaryNodeWithParent<T> right;
    public BinaryNodeWithParent<T> parent;

    public BinaryNodeWithParent(T value, BinaryNodeWithParent<T> parent) {
        this.value = value;
        this.parent = parent;
    }
}
