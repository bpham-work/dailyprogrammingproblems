package com.bpham.ctci.recursionanddp;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

/**
 * #6
 */

public class TowersOfHanoi {
    public void move(int n, Tower origin, Tower dest, Tower aux) {
        if (n < 1) return;
        move(n-1, origin, aux, dest);
        dest.push(origin.pop());
        move(n-1, aux, dest, origin);
    }


    private class Tower {
        Deque<Integer> stack = new LinkedList<>();

        public void push(int x) {
            if (!stack.isEmpty() && x > stack.peek()) {
                throw new IllegalArgumentException("Illegal push");
            }
            stack.push(x);
        }

        public Integer pop() {
            return stack.pop();
        }
    }

    @Test
    public void test1() {
        Tower origin = new Tower();
        Tower aux = new Tower();
        Tower dest = new Tower();
        origin.push(1);

        move(1, origin, dest, aux);

        assertEquals((Integer) 1, dest.pop());
    }

    @Test
    public void test2() {
        Tower origin = new Tower();
        Tower aux = new Tower();
        Tower dest = new Tower();
        origin.push(2);
        origin.push(1);

        move(2, origin, dest, aux);

        assertEquals((Integer) 1, dest.pop());
        assertEquals((Integer) 2, dest.pop());
    }

    @Test
    public void test3() {
        Tower origin = new Tower();
        Tower aux = new Tower();
        Tower dest = new Tower();
        origin.push(3);
        origin.push(2);
        origin.push(1);

        move(3, origin, dest, aux);

        assertEquals((Integer) 1, dest.pop());
        assertEquals((Integer) 2, dest.pop());
        assertEquals((Integer) 3, dest.pop());
    }
}
