import java.util.HashMap;
import java.util.Map;

public class Trie {
    private final TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    /**
     * Structure of trie node
     */
    private static class TrieNode {
        Map<Character, TrieNode> children;
        boolean endOfWord;

        TrieNode() {
            children = new HashMap<>();
            endOfWord = false;
        }
    }

    /**
     * Inserts word into the trie using iteration technique
     * @param word is to be inserted
     */
    public void insert(String word) {
        TrieNode current = root;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            // if current character is not present in the trie then insert it
            if (!current.children.containsKey(c)) {
                current.children.put(c, new TrieNode());    // create new empty trieNode in "c"
            }
            // move to next trie node
            current = current.children.get(c);
        }
        // mark the end of word as true
        current.endOfWord = true;
    }

    /**
     * Searches the presence of prefix in the trie using iteration technique
     *
     * @param prefix needs to be searched
     * @return {@code true} if the prefix is found in the trie, otherwise returns {@code false}
     */
    public boolean searchPrefix(String prefix) {
        TrieNode current = root;
        boolean found = true;

        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (current.children.containsKey(c)) {
                current = current.children.get(c);
            } else {
                found = false;
                break;
            }
        }
        return found;
    }

    /**
     * Searches the presence of whole word in the trie using iteration technique
     *
     * @param word needs to be searched
     * @return {@code true} if the complete word is found in the trie, otherwise returns {@code false}
     */
    public boolean searchWord(String word) {
        TrieNode current = root;
        boolean found = true;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            // if current character is present in current children map, move to next
            if (current.children.containsKey(c)) {
                current = current.children.get(c);
            } else {
                // returns "false" if current character of "word" is not in current children map
                found = false;
                break;
            }
        }
        // if word's prefix is found, return false because the whole word is not found
        if (found && !current.endOfWord) {
            found = false;
        }
        return found;
    }

    /**
     * Deletes {@code word} from trie using recursion technique
     * @param word needs to be deleted
     */
    public void deleteWord(String word) {
        delete(root, word, 0);      // starts from 0th index
    }

    /**
     * Helper function of {@link #deleteWord}
     * @param current current node in trie
     * @param word needs to be deleted
     * @param i index in the String {@code word}
     * @return true if parent should delete the mapping of {@code word} from trie
     */
    private boolean delete(TrieNode current, String word, int i) {
        if (i == word.length()) {
            // if we reach end of "word" but end-flag is "false" i.e., "word"
            // is a prefix of any other word, which we don't need to remove
            if (!current.endOfWord)
                return false;
            // if end-flag is false, i.e., we found "word" in trie and hence
            // we need to remove "word" by making end-flag false and removes empty trie nodes
            current.endOfWord = false;
            return current.children.size() == 0;
        }
        char c = word.charAt(i);
        // if "word" is not in trie
        if (!current.children.containsKey(c)) {
            return false;
        }

        boolean shouldDeleteCurrentNode = delete(current.children.get(c), word, i + 1);

        if (shouldDeleteCurrentNode) {
            // removes the mapping of character and TrieNode reference in the HashMap
            current.children.remove(c);
            // returns true if no mapping is left in the map
            return current.children.size() == 0;
        }
        return false;
    }

    public static void main(String[] args) {
        Trie t = new Trie();
        t.insert("abcde");
        t.insert("abcdefg");
        t.insert("wxyz");
        t.insert("pqrst");
        t.insert("pqrxyz");

/*
        System.out.println(t.searchPrefix("abc"));
        System.out.println(t.searchPrefix("pqrs"));
        System.out.println(t.searchWord("wxyz"));
        System.out.println(t.searchWord("pqrs"));
        System.out.println(t.searchWord("xyz"));
*/

        t.deleteWord("abcdef");
        System.out.println(t.searchWord("abcdefg"));
        System.out.println(t.searchPrefix("abcdef"));
    }
}
