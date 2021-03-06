package com.bpham.ctci.recursionanddp;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * #7
 *
 * Write a method to compute all permutations of a string of unique characters.
 */

public class PermWithDups {
    public List<String> perms(String str) {
        List<String> result = new ArrayList<>();
        perms(str, "", result);
        return result;
    }

    public void perms(String str, String prefix, List<String> result) {
        if (str.length() == 0) result.add(prefix);
        else {
            for (int i = 0; i < str.length(); i++) {
                String before = str.substring(0, i);
                String after = str.substring(i+1);
                perms(before+after, prefix+str.charAt(i), result);
            }
        }
    }

    public List<String> perms2(String str) {
        List<Wrapper> init = new ArrayList<>();
        for (char letter : str.toCharArray()) {
            init.add(new Wrapper(letter));
        }
        return perms2(str, 0, init)
                .stream()
                .map(wrapper -> wrapper.string)
                .collect(Collectors.toList());
    }

    public List<Wrapper> perms2(String str, int i, List<Wrapper> res) {
        if (i < str.length()-1) {
            List<Wrapper> newRes = new ArrayList<>();
            for (Wrapper wrapper : res) {
                for (char letter : str.toCharArray()) {
                    Wrapper newWrapper = wrapper.clone();
                    if (!newWrapper.contains(letter)) {
                        newWrapper.add(letter);
                        newRes.add(newWrapper);
                    }
                }
            }
            return perms2(str, i+1, newRes);
        }
        return res;
    }

    @Test
    public void test() {
        System.out.println(perms("abc"));
        System.out.println(perms("abcd"));
        System.out.println(perms("abcd").size());
    }

    private class Wrapper {
        public String string = "";
        public Set<Character> contain = new HashSet<>();

        public Wrapper(Character letter) {
            add(letter);
        }

        public Wrapper() {}

        public Wrapper clone() {
            Wrapper wrapper = new Wrapper();
            wrapper.string = this.string;
            wrapper.contain = new HashSet<>(this.contain);
            return wrapper;
        }

        public void add(char letter) {
            string += letter;
            contain.add(letter);
        }

        public boolean contains(Character letter) {
            return contain.contains(letter);
        }
    }
}
