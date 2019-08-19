package com.bpham.dynamicprogramming;

import org.junit.Test;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Link: https://www.geeksforgeeks.org/ugly-numbers/
 */

public class UglyNumbers {
    public int getNthUglyNumberDP(int n) {
        int[] uglyNums = new int[n+1];
        uglyNums[1] = 1;
        Set<Integer> inserted = new HashSet<>();
        int twoPtr = 1;
        int threePtr = 1;
        int fivePtr = 1;
        int i = 2;
        while (i < uglyNums.length) {
            if (twoPtr < uglyNums.length && threePtr >= uglyNums.length && fivePtr >= uglyNums.length) {
                int val = uglyNums[twoPtr] * 2;
                if (!inserted.contains(val)) {
                    inserted.add(val);
                    uglyNums[i] = val;
                    i++;
                }
                twoPtr++;
            }
            else if (threePtr < uglyNums.length && twoPtr >= uglyNums.length && fivePtr >= uglyNums.length) {
                int val = uglyNums[threePtr] * 3;
                if (!inserted.contains(val)) {
                    inserted.add(val);
                    uglyNums[i] = val;
                    i++;
                }
                threePtr++;
            }
            else if (fivePtr < uglyNums.length && threePtr >= uglyNums.length && twoPtr >= uglyNums.length) {
                int val = uglyNums[fivePtr] * 5;
                if (!inserted.contains(val)) {
                    inserted.add(val);
                    uglyNums[i] = val;
                    i++;
                }
                fivePtr++;
            } else {
                int twoVal = uglyNums[twoPtr] * 2;
                int threeVal = uglyNums[threePtr] * 3;
                int fiveVal = uglyNums[fivePtr] * 5;
                int val = twoVal;
                if (twoVal <= threeVal && twoVal <= fiveVal) {
                    val = twoVal;
                    twoPtr++;
                } else if (threeVal <= twoVal && threeVal <= fiveVal) {
                    val = threeVal;
                    threePtr++;
                } else if (fiveVal <= twoVal && fiveVal <= threeVal) {
                    val = fiveVal;
                    fivePtr++;
                }
                if (!inserted.contains(val)) {
                    inserted.add(val);
                    uglyNums[i] = val;
                    i++;
                }
            }
        }
        return uglyNums[n];
    }

    public int getNthUglyNumberNonDP(int n) {
        LinkedList<Integer> uglyNumbers = new LinkedList<>();
        int i = 1;
        while (uglyNumbers.size() != n) {
            if (isUglyNumber(i)) {
                uglyNumbers.push(i);
            }
            i++;
        }
        return uglyNumbers.pop();
    }

    public boolean isUglyNumber(int num) {
        while (num % 5 == 0) {
            num /= 5;
        }

        while (num % 3 == 0) {
            num /= 3;
        }

        while (num % 2 == 0) {
            num /= 2;
        }
        return num == 1;
    }

    @Test
    public void testNonDP() {
        assertEquals(8, getNthUglyNumberNonDP(7));
        assertEquals(12, getNthUglyNumberNonDP(10));
        assertEquals(24, getNthUglyNumberNonDP(15));
        assertEquals(5832, getNthUglyNumberNonDP(150));
    }

    @Test
    public void testDP() {
        assertEquals(8, getNthUglyNumberDP(7));
        assertEquals(12, getNthUglyNumberDP(10));
        assertEquals(24, getNthUglyNumberDP(15));
        assertEquals(5832, getNthUglyNumberDP(150));
    }
}
