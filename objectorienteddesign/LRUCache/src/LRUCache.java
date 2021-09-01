package objectorienteddesign.LRUCache.src;

import java.util.*;

public class LRUCache {
    Map<Integer, Integer> map;
    int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>(capacity + 1);
    }

    public int get(int key) {
        Integer val = map.get(key);
        if (val == null) {
            return -1;
        }
        map.remove(key);
        map.put(key, val);
        return val;
    }

    public void set(int key, int value) {
        map.remove(key);
        map.put(key, value);
        if (map.size() > capacity) {
            map.remove(map.entrySet().iterator().next().getKey());
        }
    }
}
