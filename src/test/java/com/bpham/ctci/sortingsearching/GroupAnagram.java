package com.bpham.ctci.sortingsearching;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * #2
 *
 * Write a method to sort an array of strings so that all the anagrams are next to each other.
 */

public class GroupAnagram {
    public List<String> group(List<String> strings) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strings) {
            char[] charArr = str.toCharArray();
            Arrays.sort(charArr);
            String sorted = new String(charArr);
            List<String> list = map.getOrDefault(sorted, new ArrayList<>());
            list.add(str);
            map.put(sorted, list);
        }
        return map.values().stream().flatMap(List::stream).collect(Collectors.toList());
    }

    @Test
    public void test1() {
        System.out.println(group(Arrays.asList("abc", "xyz", "cba", "yzx")));
    }
}
