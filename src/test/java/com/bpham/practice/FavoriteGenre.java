package com.bpham.practice;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FavoriteGenre {

    public Map<String, List<String>> getFavs(Map<String, List<String>> users, Map<String, List<String>> genres) {
        Map<String, List<String>> res = new HashMap<>();

        users.entrySet().stream().forEach(userEntry -> {
            PriorityQueue<Tuple> maxHeap = new PriorityQueue<>((a, b) -> b.count - a.count);
            genres.entrySet().stream().forEach(genreEntry -> {
                Set<String> songs = new HashSet<>(userEntry.getValue());
                int sizeBefore = songs.size();
                songs.removeAll(genreEntry.getValue());
                int sizeAfter = songs.size();
                int numRemoved = sizeBefore - sizeAfter;
                if (numRemoved > 1) {
                    maxHeap.add(new Tuple(genreEntry.getKey(), numRemoved));
                }
            });
            if (maxHeap.isEmpty()) {
                res.put(userEntry.getKey(), new ArrayList<>());
            } else {
                int maxCount = maxHeap.peek().count;
                while (!maxHeap.isEmpty() && maxHeap.peek().count == maxCount) {
                    Tuple genreCount = maxHeap.poll();
                    List<String> favGenres = res.getOrDefault(userEntry.getKey(), new ArrayList<>());
                    favGenres.add(genreCount.genre);
                    res.put(userEntry.getKey(), favGenres);
                }
            }
        });

        return res;
    }

    static class Tuple {
        public String genre;
        public int count;

        Tuple(String genre, int count) {
            this.genre = genre;
            this.count = count;
        }
    }

    @Test
    public void test1() {
        Map<String, List<String>> users = new HashMap<>();
        users.put("David", Arrays.asList("song1", "song2", "song3", "song4", "song8"));
        users.put("Emma", Arrays.asList("song5", "song6", "song7"));
        Map<String, List<String>> genres = new HashMap<>();
        genres.put("Rock", Arrays.asList("song1", "song3"));
        genres.put("Techno", Arrays.asList("song2", "song4"));
        genres.put("Pop", Arrays.asList("song5", "song6"));
        genres.put("Jazz", Arrays.asList("song8", "song9"));
        genres.put("Dubstep", Arrays.asList("song7"));

        Map<String, List<String>> favs = getFavs(users, genres);

        assertEquals(Arrays.asList("Rock", "Techno"), favs.get("David"));
        assertEquals(Arrays.asList("Pop"), favs.get("Emma"));
    }

    @Test
    public void test2() {
        Map<String, List<String>> users = new HashMap<>();
        users.put("David", Arrays.asList("song1", "song2"));
        users.put("Emma", Arrays.asList("song3", "song4"));
        Map<String, List<String>> genres = new HashMap<>();

        Map<String, List<String>> favs = getFavs(users, genres);

        assertTrue(favs.get("David").isEmpty());
        assertTrue(favs.get("Emma").isEmpty());
    }

    @Test
    public void test3() {
        Map<String, List<String>> users = new HashMap<>();
        users.put("David", Arrays.asList("song1", "song2", "song3", "song4", "song8"));
        users.put("Emma", Arrays.asList("song3", "song4"));
        Map<String, List<String>> genres = new HashMap<>();
        genres.put("Rock", Arrays.asList("song1", "song3"));
        genres.put("Techno", Arrays.asList("song2", "song4"));
        genres.put("Pop", Arrays.asList("song5", "song6"));
        genres.put("Jazz", Arrays.asList("song8", "song9"));
        genres.put("Dubstep", Arrays.asList("song7"));

        Map<String, List<String>> favs = getFavs(users, genres);

        assertEquals(Arrays.asList("Rock", "Techno"), favs.get("David"));
        assertTrue(favs.get("Emma").isEmpty());
    }

    @Test
    public void test4() {
        Map<String, List<String>> users = new HashMap<>();
        Map<String, List<String>> genres = new HashMap<>();
        genres.put("Rock", Arrays.asList("song1", "song3"));
        genres.put("Techno", Arrays.asList("song2", "song4"));
        genres.put("Pop", Arrays.asList("song5", "song6"));
        genres.put("Jazz", Arrays.asList("song8", "song9"));
        genres.put("Dubstep", Arrays.asList("song7"));

        Map<String, List<String>> favs = getFavs(users, genres);

        assertTrue(favs.isEmpty());
    }

    @Test
    public void test5() {
        Map<String, List<String>> users = new HashMap<>();
        users.put("David", Arrays.asList("song1", "song2", "song3", "song4", "song8", "randomsong1"));
        users.put("Emma", Arrays.asList("song3", "song4"));
        Map<String, List<String>> genres = new HashMap<>();
        genres.put("Rock", Arrays.asList("song1", "song3"));
        genres.put("Techno", Arrays.asList("song2", "song4"));
        genres.put("Pop", Arrays.asList("song5", "song6"));
        genres.put("Jazz", Arrays.asList("song8", "song9"));
        genres.put("Dubstep", Arrays.asList("song7"));

        Map<String, List<String>> favs = getFavs(users, genres);

        assertEquals(Arrays.asList("Rock", "Techno"), favs.get("David"));
        assertTrue(favs.get("Emma").isEmpty());
    }

    @Test
    public void test6() {
        Map<String, List<String>> users = new HashMap<>();
        users.put("David", Arrays.asList("song1", "song2", "song3", "song4", "song8", "randomsong1"));
        Map<String, List<String>> genres = new HashMap<>();
        genres.put("Rock", Arrays.asList("song1", "song3", "randomsong1"));
        genres.put("Techno", Arrays.asList("song2", "song4"));
        genres.put("Pop", Arrays.asList("song5", "song6"));
        genres.put("Jazz", Arrays.asList("song8", "song9"));
        genres.put("Dubstep", Arrays.asList("song7"));

        Map<String, List<String>> favs = getFavs(users, genres);

        assertEquals(Arrays.asList("Rock"), favs.get("David"));
    }

    @Test
    public void test7() {
        Map<String, List<String>> users = new HashMap<>();
        Map<String, List<String>> genres = new HashMap<>();

        Map<String, List<String>> favs = getFavs(users, genres);

        assertTrue(favs.isEmpty());
    }
}
