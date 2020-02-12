package com.bpham.ctci.recursionanddp;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * #14
 *
 */

public class BooleanEval {
    public int evalCount(String str, boolean bool, Map<String, Integer> memo) {
        if (str.length() == 1) {
            return strToBool(str) == bool ? 1 : 0;
        }
        if (memo.containsKey(str+bool)) {
            return memo.get(str+bool);
        }
        int result = 0;
        for (int i = 1; i < str.length(); i+=2) {
            char op = str.charAt(i);
            String left = str.substring(0, i);
            String right = str.substring(i+1);
            int leftTrue = evalCount(left, true, memo);
            int leftFalse = evalCount(left, false, memo);
            int rightTrue = evalCount(right, true, memo);
            int rightFalse = evalCount(right, false, memo);
            int total = (leftTrue + leftFalse) * (rightTrue + rightFalse);
            int totalTrue;
            if (op == '^') {
                totalTrue = (leftTrue * rightFalse) + (leftFalse * rightTrue);
            } else if (op == '&') {
                totalTrue = (leftTrue * rightTrue);
            } else {
                totalTrue = (leftTrue * rightTrue) + (leftFalse * rightTrue) + (leftTrue * rightFalse);
            }
            int subResult = totalTrue;
            if (bool == false) {
                subResult = total - totalTrue;
            }
            result += subResult;
        }
        memo.put(str+bool, result);
        return result;
    }

    public boolean strToBool(String str) {
        return str.equals("1");
    }

    public int evalCount2(String str, boolean bool, int level) {
//        System.out.println(str);
        if (str.length() == 3) {
//            System.out.println("DING: " + str);
            String eval = eval(str);
            return eval.equals("1") && bool || eval.equals("0") && !bool ? 1 : 0;
        }
        int count = 0;
        int start = 0;
        int end = 3;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level *4; i++) {
            sb.append(" ");
        }
        String prefix = sb.toString();

        while (end <= str.length()) {
            String tuple = str.substring(start, end);
            String before = str.substring(0, start);
            String after = str.substring(end);
            String eval = eval(tuple);
            System.out.println(prefix + before + "(" + tuple + ")" + after);
            count += evalCount2(before + eval + after, bool, level + 1);
            start += 2;
            end += 2;
        }
        return count;
    }

    public String eval(String str) {
        char op = str.charAt(1);
        char left = str.charAt(0);
        char right = str.charAt(2);
        boolean match;
        if (op == '^') {
            match = (left == '1' && right == '0' ||
                    left == '0' && right == '1');
        } else if (op == '|') {
            match = (left == '1' || right == '1');
        } else {
            match = (left == '1' && right == '1');
        }
        return match ? "1" : "0";
    }

    @Test
    public void test() {
//        assertEquals(2, evalCount("1^0|0|1", false, new HashMap<>()));
        assertEquals(10, evalCount("0&0&0&1^1|0", true, new HashMap<>()));
    }
}
