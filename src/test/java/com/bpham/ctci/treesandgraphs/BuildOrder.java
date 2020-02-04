package com.bpham.ctci.treesandgraphs;

import org.junit.Test;

import java.util.*;

/**
 * #7
 *
 * You are given a list of projects and a list of dependencies
 * (which is a list of pairs of projects, where the second project
 * is dependent on the first project). All of a project's dependencies
 * must be built before the project is. Find a build order that will
 * allow the projects to be built. If there is no valid build order,
 * return an error.
 *
 * EXAMPLE
 * Input:
 * projects: a, b, c, d, e, f
 * dependencies: (a, d), (f, b), (b, d), (f, a), (d, c)
 *
 * Output: f, e, a, b, d, c
 */

public class BuildOrder {
    public List<String> getBuildOrder(List<Tuple> deps, List<String> projects) {
        Map<String, List<String>> parentsToChild = new HashMap<>();
        Map<String, List<String>> childToParent = new HashMap<>();
        List<String> result = new ArrayList<>();
        projects.stream().forEach(project -> {
            parentsToChild.put(project, new ArrayList<>());
            childToParent.put(project, new ArrayList<>());
        });
        deps.forEach(dep -> {
            parentsToChild.get(dep.parent).add(dep.child);
            childToParent.get(dep.child).add(dep.parent);
        });

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        childToParent.forEach((k, v) -> {
            if (v.size() == 0) {
                queue.add(k);
            }
        });

        if (queue.isEmpty()) {
            throw new IllegalArgumentException("No valid path");
        }

        while (!queue.isEmpty()) {
            String curr = queue.poll();
            if (visited.containsAll(childToParent.get(curr)) && !visited.contains(curr)) {
                visited.add(curr);
                queue.addAll(parentsToChild.get(curr));
                result.add(curr);
            } else if (!visited.containsAll(childToParent.get(curr))) {
                queue.add(curr);
            }
        }
        if (!visited.containsAll(projects)) {
            throw new IllegalArgumentException("No valid path");
        }

        return result;
    }

    @Test
    public void test1() {
        Tuple d1 = new Tuple("a", "d");
        Tuple d2 = new Tuple("b", "d");
        Tuple d3 = new Tuple("f", "b");
        Tuple d4 = new Tuple("f", "a");
        Tuple d5 = new Tuple("d", "c");

        List<String> result = getBuildOrder(Arrays.asList(d1, d2, d3, d4, d5),
                Arrays.asList("a", "b", "c", "d", "e", "f"));

        System.out.println(result.toString());
    }

    @Test
    public void test2() {
        Tuple d1 = new Tuple("a", "d");
        Tuple d2 = new Tuple("b", "d");
        Tuple d3 = new Tuple("f", "b");
        Tuple d4 = new Tuple("f", "a");
        Tuple d5 = new Tuple("d", "c");
        Tuple d6 = new Tuple("e", "b");

        List<String> result = getBuildOrder(Arrays.asList(d1, d2, d3, d4, d5, d6),
                Arrays.asList("a", "b", "c", "d", "e", "f"));

        System.out.println(result.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLoop() {
        Tuple d1 = new Tuple("a", "d");
        Tuple d2 = new Tuple("b", "d");
        Tuple d3 = new Tuple("f", "b");
        Tuple d4 = new Tuple("f", "a");
        Tuple d5 = new Tuple("d", "c");
        Tuple d6 = new Tuple("c", "f");

        List<String> result = getBuildOrder(Arrays.asList(d1, d2, d3, d4, d5, d6),
                Arrays.asList("a", "b", "c", "d", "e", "f"));

        System.out.println(result.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNoStart() {
        Tuple d1 = new Tuple("a", "d");
        Tuple d2 = new Tuple("b", "d");
        Tuple d3 = new Tuple("f", "b");
        Tuple d4 = new Tuple("f", "a");
        Tuple d5 = new Tuple("d", "c");
        Tuple d6 = new Tuple("c", "f");
        Tuple d7 = new Tuple("c", "e");

        List<String> result = getBuildOrder(Arrays.asList(d1, d2, d3, d4, d5, d6, d7),
                Arrays.asList("a", "b", "c", "d", "e", "f"));

        System.out.println(result.toString());
    }

    private class Tuple {
        public String parent;
        public String child;

        public Tuple(String parent, String child) {
            this.parent = parent;
            this.child = child;
        }
    }
}
