package com.bpham.ctci.recursionanddp;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collector;

/**
 * #8
 *
 * Write a method to compute all permutations of a string whose characters are not
 * necessarily unique. The list of permutations should not have duplicates.
 */

public class PermNoDups {
    public List<String> perms(String str) {
        Map<Character, Integer> counts = new HashMap<>();
        for (char letter : str.toCharArray()) {
            counts.put(letter, counts.getOrDefault(letter, 0)+1);
        }
        List<String> result = new ArrayList<>();
        perms(counts, "", result, str.length());
        return result;
    }

    public void perms(Map<Character, Integer> counts, String prefix, List<String> result, int max) {
        if (prefix.length() == max) result.add(prefix);
        else {
            for (Character letter : counts.keySet()) {
                int letterCount = counts.get(letter);
                if (letterCount > 0) {
                    counts.put(letter, letterCount-1);
                    perms(counts, letter+prefix, result, max);
                }
                counts.put(letter, letterCount);
            }
        }
    }

    public Set<String> perms2(String str) {
        Set<String> res = new HashSet<>();
        backtrack(str.toCharArray(), res, new HashSet<>(), new Wrapper());
        return res;
    }

    public void backtrack(char[] str, Set<String> res, Set<Integer> visited,
                          Wrapper curr) {
        if (curr.size() == str.length && !res.contains(curr.toString())) {
            res.add(curr.toString());
        } else {
            for (int i = 0; i < str.length; i++) {
                if (!visited.contains(i)) {
                    curr.push(str[i]);
                    visited.add(i);
                    backtrack(str, res, visited, curr);
                    visited.remove(i);
                    curr.pop();
                }
            }
        }
    }

    private class Wrapper {
        Deque<Character> stack = new LinkedList<>();

        public void push(Character character) { stack.push(character); }
        public Character pop() { return stack.pop(); }
        public String toString() {
            return stack.stream()
                    .collect(Collector.of(
                            StringBuilder::new,
                            StringBuilder::append,
                            StringBuilder::append,
                            StringBuilder::toString));
        }
        public int size() { return stack.size(); }
    }

    @Test
    public void test() {
        System.out.println(perms("abc"));
//        System.out.println(perms("abcdefghijk"));
        System.out.println(perms("aab"));
    }
}
