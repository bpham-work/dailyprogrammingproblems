package com.bpham.practice;

/**
 * https://leetcode.com/problems/subtree-of-another-tree/
 */

public class SubtreeOfAnotherSubtree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null) return false;
        boolean isEqual = isEquals(s, t);
        if (isEqual) return true;
        else return isSubtree(s.left, t) || isSubtree(s.right, t);
    }

    public boolean isEquals(TreeNode s, TreeNode t) {
        if (s == null && t == null) return true;
        if ((s != null && t == null) || (s == null && t != null)) return false;
        if (s.val == t.val) {
            return isEquals(s.left, t.left) && isEquals(s.right, t.right);
        }
        return false;
    }
}
