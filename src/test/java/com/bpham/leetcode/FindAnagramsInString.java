package com.bpham.leetcode;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertTrue;

public class FindAnagramsInString {
    public List<Integer> findAnagrams(String source, String target) {
        List<Integer> result = new ArrayList<>();
        Map<Character, Integer> charCount = new HashMap<>();
        for (char letter : target.toCharArray()) {
            charCount.put(letter, charCount.getOrDefault(letter, 0) + 1);
        }
        int counter = charCount.size();
        int left = 0;
        int right = 0;

        while (right < source.length()) {
            char rightLetter = source.charAt(right);
            if (charCount.containsKey(rightLetter)) {
                int newRightCharCount = charCount.get(rightLetter) - 1;
                charCount.put(rightLetter, newRightCharCount);
                if (newRightCharCount == 0) {
                    counter--;
                }
            }

            while (counter == 0) {
                char leftLetter = source.charAt(left);
                int currentLength = right - left + 1;
                if (currentLength == target.length()) {
                    result.add(left);
                }
                if (charCount.containsKey(leftLetter)) {
                    int newLeftCharCount = charCount.get(leftLetter) + 1;
                    charCount.put(leftLetter, newLeftCharCount);
                    if (newLeftCharCount > 0) {
                        counter++;
                    }
                }
                left++;
            }
            right++;
        }

        return result;
    }

    @Test
    public void testABCBAxAB() {
        List<Integer> result = findAnagrams("abcba", "ab");
        assertTrue(result.contains(0));
        assertTrue(result.contains(3));
    }

    @Test
    public void testABCCAxAB() {
        List<Integer> result = findAnagrams("abcca", "ab");
        assertTrue(result.contains(0));
    }

    @Test
    public void testABABxAB() {
        List<Integer> result = findAnagrams("abab", "ab");
        assertTrue(result.contains(0));
        assertTrue(result.contains(1));
        assertTrue(result.contains(2));
    }
}
