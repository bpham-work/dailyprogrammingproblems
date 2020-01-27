package com.bpham.dynamicprogramming;

import org.junit.Test;

import java.util.*;

public class PossibleParentheses {
    public List<String> possibleParentheses(int n) {
        List<List<String>> combos = new ArrayList<>();
        combos.add(Arrays.asList("()"));
        for (int i = 1; i < n; i++) {
            Set<String> set = new HashSet<>();
            for (String perm : combos.get(i-1)) {
                set.add("()" + perm);
                set.add(perm + "()");
                set.add("(" + perm + ")");
            }
            combos.add(new ArrayList<>(set));
        }
        return combos.get(n-1);
    }

    @Test
    public void test() {
        System.out.println(possibleParentheses(3));
        System.out.println(possibleParentheses(4));
        System.out.println(possibleParentheses(5));
    }
}
