package com.bpham.leetcode;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class WordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length()+1];
        dp[0] = true;
        for (int end = 1; end < s.length()+1; end++) {
            for (int start = 0; start < end; start++) {
                if (dp[start] && wordDict.contains(s.substring(start, end))) {
                    dp[end] = true;
                }
            }
        }
        return dp[s.length()];
    }

    @Test
    public void test() {
        assertTrue(wordBreak("leetcode", Arrays.asList("leet", "code")));
        assertTrue(wordBreak("applepenapple", Arrays.asList("apple", "pen")));
        assertFalse(wordBreak("catsandog", Arrays.asList("cat", "cats", "dog", "sand", "and")));
        assertTrue(wordBreak("ccaccc", Arrays.asList("cc", "ac")));
    }

    public void amazon() {

    }
}
