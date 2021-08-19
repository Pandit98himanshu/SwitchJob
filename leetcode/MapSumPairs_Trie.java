/*
 * 677. Map Sum Pairs
 * https://leetcode.com/problems/map-sum-pairs/
 */

package leetcode;

import java.util.*;

public class MapSumPairs_Trie {
    static class MapSum {
        static class TrieNode {
            Map<Character, TrieNode> children;
            boolean isEnd;
            int value;

            public TrieNode() {
                children = new HashMap<>();
                isEnd = false;
                value = 0;
            }

            public TrieNode(Map<Character, TrieNode> children, int value) {
                this.children = children;
                this.isEnd = false;
                this.value = value;
            }
        }

        TrieNode trie;

        public MapSum() {
            trie = new TrieNode();
        }

        public void insert(String key, int val) {

        }

        public int sum(String prefix) {
            return 0;
        }
    }

    public static void main(String[] args) {
        MapSum obj = new MapSum();

        obj.insert("apple", 3);
        System.out.println(obj.sum("ap"));
        obj.insert("app", 2);
        System.out.println(obj.sum("app"));
    }
}
