package com.bpham.ctci.recursionanddp;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * #13
 *
 * You have a stack of n boxes, with widths Wi' heights hi' and depths di.
 * The boxes cannot be rotated and can only be stacked on top of one another
 * if each box in the stack is strictly larger than the box above it in width,
 * height, and depth. Implement a method to compute the height of the tallest
 * possible stack.The height of a stack is the sum of the heights of each box.
 */

public class StackBoxes {
    public int height(List<Box> boxes) {
//        return height(boxes, 0, 0, new LinkedList<>());
        Collections.sort(boxes, (box1, box2) -> Integer.compare(box2.h, box1.h));
        return height2(boxes, new int[boxes.size()], 0);
    }

    public int height2(List<Box> boxes, int[] memo, int base) {
        if (memo[base] > 0) {
            return memo[base];
        }
        Box currBox = boxes.get(base);
        int maxHeight = 0;
        for (int i = base + 1; i < boxes.size(); i++) {
            if (currBox.isBigger(boxes.get(i))) {
                int aboveHeight = height2(boxes, memo, i);
                maxHeight = Math.max(aboveHeight, maxHeight);
            }
        }
        maxHeight += currBox.h;
        memo[base] = maxHeight;
        return maxHeight;
    }

    public int height(Collection<Box> boxes, int maxHeight, int currHeight, Deque<Box> curr) {
        Set<Box> boxesClone = new HashSet<>(boxes);
        for (Box box : boxes) {
            if (curr.isEmpty() || curr.peek().isBigger(box)) {
                curr.push(box);
                maxHeight = Math.max(maxHeight, currHeight + box.h);
                boxesClone.remove(box);
                maxHeight = height(boxesClone, maxHeight, currHeight + box.h, curr);
                curr.pop();
                boxesClone.add(box);
            }
        }
        return maxHeight;
    }

    @Test
    public void test1() {
        Box b1 = new Box(5, 5, 5);
        Box b2 = new Box(6, 6, 6);
        Box b3 = new Box(1, 1, 1);
        List<Box> boxes = Arrays.asList(b3, b1, b2);

        assertEquals(12, height(boxes));
    }

    @Test
    public void test2() {
        Box b1 = new Box(5, 1, 5);
        Box b2 = new Box(6, 6, 6);
        Box b3 = new Box(1, 1, 1);
        List<Box> boxes = Arrays.asList(b3, b1, b2);

        assertEquals(7, height(boxes));
    }

    @Test
    public void test3() {
        Box b1 = new Box(10, 10, 5);
        Box b2 = new Box(4, 6, 6);
        Box b3 = new Box(1, 1, 20);
        List<Box> boxes = Arrays.asList(b3, b1, b2);

        assertEquals(10, height(boxes));
    }

    private class Box {
        public int w;
        public int h;
        public int d;

        public Box(int w, int h, int d) {
            this.w = w;
            this.h = h;
            this.d = d;
        }

        public boolean isBigger(Box box) {
            return w > box.w &&
                    h > box.h &&
                    d > box.d;
        }
    }
}
