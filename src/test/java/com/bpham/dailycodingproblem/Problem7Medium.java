package com.bpham.dailycodingproblem;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * This problem was asked by Facebook.
 *
 * Given the mapping a = 1, b = 2, ... z = 26, and an encoded message, count the number of ways it can be decoded.
 *
 * For example, the message '111' would give 3, since it could be decoded as 'aaa', 'ka', and 'ak'.
 *
 * You can assume that the messages are decodable. For example, '001' is not allowed.
 */

public class Problem7Medium {
    Map<String, String> map = new HashMap<>();

    public Problem7Medium() {
        map.put("1", "a");
        map.put("2", "b");
        map.put("3", "c");
        map.put("4", "d");
        map.put("5", "e");
        map.put("6", "f");
        map.put("7", "g");
        map.put("8", "h");
        map.put("9", "i");
        map.put("10", "j");
        map.put("11", "k");
        map.put("12", "l");
        map.put("13", "m");
        map.put("14", "n");
        map.put("15", "o");
        map.put("16", "p");
        map.put("17", "q");
        map.put("18", "r");
        map.put("19", "s");
        map.put("20", "t");
        map.put("21", "u");
        map.put("22", "v");
        map.put("23", "w");
        map.put("24", "x");
        map.put("25", "y");
        map.put("26", "z");
    }

    public int numOfDecodeableWays(String code) {
        return backtrack(code, 0, "", "");
    }

    public int backtrack(String code, int pos, String str, String letters) {
        if (str.length() > code.length()) {
            return 0;
        } else if (str.length() == code.length()) {
            System.out.println("Letters: " + letters);
            System.out.println("Letters: " + letters);
            return 1;
        } else {
            char firstDigit = code.charAt(pos);
            int count = backtrack(code, pos+1, str+""+firstDigit, letters+map.get(""+firstDigit));
            if (pos + 1 < code.length()) {
                String combined = "" + firstDigit + code.charAt(pos+1);
                int intVal = Integer.parseInt(combined);
                if (intVal <= 26) {
                    count += backtrack(code, pos+2, str+combined, letters+map.get(combined));
                }
            }
            return count;
        }
    }

    @Test
    public void test() {
        assertEquals(3, numOfDecodeableWays("111"));
        assertEquals(3, numOfDecodeableWays("123"));
        assertEquals(1, numOfDecodeableWays("456"));
        assertEquals(1, numOfDecodeableWays("999"));
        assertEquals(5, numOfDecodeableWays("1111"));
    }
}
