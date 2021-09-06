/*
 * 146. LRU Cache
 * https://leetcode.com/problems/lru-cache/
 */

package leetcode;

import java.util.*;
/*
// todo: null-pointer exception
class LRUCache {
    static class DLL {
        int value;
        DLL left, right;

        DLL() {
        }

        DLL(int value) {
            this.value = value;
        }
    }

    private HashMap<Integer, DLL> cache;
    private int capacity;
    private DLL first, last;

    public LRUCache(int capacity) {
        this.cache = new HashMap<>();
        this.capacity = capacity;
        this.first = new DLL();
        this.last = new DLL();
    }

    private void appendNode(DLL node) {
        this.last.right = node;
        node.left = last;
        node.right = this.first;
        last = node;            // make added node as last node
    }

    private void addToCache(int key, int value) {
        DLL newNode = new DLL(value);
        cache.put(key, newNode);
        appendNode(newNode);
    }

    private void removeNode(DLL node) {
        DLL prev = node.left;
        DLL next = node.right;
        // remove the node from its current position
        prev.right = next;
        next.left = prev;
    }

    private void removeFirst() {
        this.last.right = this.first.right;
        this.first = this.last.right;
    }

    private void moveToLast(int key, DLL node) {
        removeNode(node);       // remove current node from the list
        cache.put(key, node);   // add node into the hashmap
        // add last node into the list
        appendNode(cache.get(key));
    }

    public int get(int key) {
        DLL node = cache.get(key);
        if (node == null)
            return -1;      // if key is not present in the cache, return -1
        // if present, move the key to last of the cache list
        moveToLast(key, node);
        return node.value;
    }

    public void put(int key, int value) {
        DLL node = cache.get(key);
        if (node != null)
            removeNode(node);
        addToCache(key, value);
        if (cache.size() > capacity) {
            removeFirst();
        }
    }
}
*/
/**
 * Copied from <a href=https://leetcode.com/problems/lru-cache/solution/>leetcode solution</a>
 */
class LRUCache2 extends LinkedHashMap<Integer, Integer> {
    private int capacity;

    public LRUCache2(int capacity) {
        super(capacity, 0.75F, true);
        this.capacity = capacity;
    }

    /**
     * Time Complexity: O(1)
     */
    public int get(int key) {
        return super.getOrDefault(key, -1);
    }

    /**
     * Time Complexity: O(1)
     */
    public void put(int key, int value) {
        super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity;
    }
}

class LRUCache1 {
    private HashMap<Integer, Integer> map;      // stores key-value pair
    private LinkedList<Integer> LRU;            // stores key in last recently used manner
    private int capacity;                       // stores capacity value of the cache

    public LRUCache1(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        LRU = new LinkedList<>();
    }

    /**
     * Time Complexity: O(capacity)
     */
    public int get(int key) {
        // returns true if key exists in the LRU list and
        // hence will be removed
        if (LRU.remove((Integer) key)) {
            LRU.add(key);           // add key at the last of the LRU list
            return map.get(key);    // return value associated with that key
        }
        // key doesn't exist in the cache
        return -1;
    }

    /**
     * Time Complexity: O(capacity)
     */
    public void put(int key, int value) {
        // search for the key, if exists, remove it
        LRU.remove((Integer) key);
        LRU.add(key);           // add key at the last of the LRU list
        // if key-value pair already existed
        // in the map, replace it with new value
        // or of not existed, put it into the map
        map.put(key, value);
        // if we are overflowing
        if (LRU.size() > capacity) {
            int lastUsedKey = LRU.get(0);
            LRU.removeFirst();  // remove last assessed, which is the first element in LRU list
            map.remove(lastUsedKey);    // remove from hashmap as well
        }
    }
}