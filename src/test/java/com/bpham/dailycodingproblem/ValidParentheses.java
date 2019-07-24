package com.bpham.dailycodingproblem;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValidParentheses {
    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char elem : s.toCharArray()) {
            if (elem == '(') {
                stack.push(')');
            } else if (elem == '[') {
                stack.push(']');
            } else if (elem == '{') {
                stack.push('}');
            } else {
                if (stack.isEmpty() || stack.pop() != elem) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    @Test
    public void test() {
        assertTrue(isValid("()"));
        assertTrue(isValid("[]"));
        assertTrue(isValid("{}"));
        assertTrue(isValid("()[]{}"));
        assertFalse(isValid("(]"));
        assertFalse(isValid("([)]"));
        assertTrue(isValid("{[]}"));
        assertFalse(isValid("{"));
        assertFalse(isValid("}"));
    }
}
