package leetcode;

import java.util.*;

/**
 * Created at : 19/08/21
 * <p>
 * <a href=https://leetcode.com/problems/word-ladder/>Word Ladder</a>
 *
 * @see <a href=https://www.geeksforgeeks.org/word-ladder-set-2-bi-directional-bfs/>Word Ladder – Set 2</a>
 * @author Himanshu Shekhar
 */

public class WordLadder {
    static class Pair {
        String str;
        Integer val;

        Pair(String str, Integer val) {
            this.str = str;
            this.val = val;
        }
    }

    /**
     * <strong>Using Bi-directional BFS</strong>
     */
    private int L;
    private final Map<String, List<String>> allComboDict;

    {
        this.L = 0;

        // Dictionary to hold combination of words that can be formed,
        // from any given word. By changing one letter at a time.
        this.allComboDict = new HashMap<>();
    }

    private int visitWordNode(
            Queue<Pair> Q,
            Map<String, Integer> visited,
            Map<String, Integer> othersVisited) {

        Pair node = Q.remove();
        String word = node.str;
        int level = node.val;

        for (int i = 0; i < this.L; i++) {

            // Intermediate words for current word
            String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);

            // Next states are all the words which share the same intermediate state.
            for (String adjacentWord : this.allComboDict.getOrDefault(newWord, new ArrayList<>())) {
                // If at any point if we find what we are looking for
                // i.e. the end word - we can return with the answer.
                if (othersVisited.containsKey(adjacentWord)) {
                    return level + othersVisited.get(adjacentWord);
                }

                if (!visited.containsKey(adjacentWord)) {

                    // Save the level as the value of the dictionary, to save number of hops.
                    visited.put(adjacentWord, level + 1);
                    Q.add(new Pair(adjacentWord, level + 1));
                }
            }
        }
        return -1;
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        if (!wordList.contains(endWord)) {
            return 0;
        }

        // Since all words are of same length.
        this.L = beginWord.length();

        wordList.forEach(
                word -> {
                    for (int i = 0; i < L; i++) {
                        // Key is the generic word
                        // Value is a list of words which have the same intermediate generic word.
                        String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);
                        List<String> transformations =
                                this.allComboDict.getOrDefault(newWord, new ArrayList<>());
                        transformations.add(word);
                        this.allComboDict.put(newWord, transformations);
                    }
                });

        // Queues for bi-directional BFS
        // BFS starting from beginWord
        Queue<Pair> Q_begin = new LinkedList<>();
        // BFS starting from endWord
        Queue<Pair> Q_end = new LinkedList<>();
        Q_begin.add(new Pair(beginWord, 1));
        Q_end.add(new Pair(endWord, 1));

        // Visited to make sure we don't repeat processing same word.
        Map<String, Integer> visitedBegin = new HashMap<>();
        Map<String, Integer> visitedEnd = new HashMap<>();
        visitedBegin.put(beginWord, 1);
        visitedEnd.put(endWord, 1);

        while (!Q_begin.isEmpty() && !Q_end.isEmpty()) {

            // One hop from begin word
            int ans = visitWordNode(Q_begin, visitedBegin, visitedEnd);
            if (ans > -1) {
                return ans;
            }

            // One hop from end word
            ans = visitWordNode(Q_end, visitedEnd, visitedBegin);
            if (ans > -1) {
                return ans;
            }
        }

        return 0;
    }


    /**
     * <strong>Using BFS</strong>
     */
    public int ladderLength3(String beginWord, String endWord, List<String> wordList) {

        // Since all words are of same length.
        int L = beginWord.length();

        // Dictionary to hold combination of words that can be formed,
        // from any given word. By changing one letter at a time.
        Map<String, List<String>> allComboDict = new HashMap<>();

        wordList.forEach(
                word -> {
                    for (int i = 0; i < L; i++) {
                        // Key is the generic word
                        // Value is a list of words which have the same intermediate generic word.
                        String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);
                        List<String> transformations = allComboDict.getOrDefault(newWord, new ArrayList<>());
                        transformations.add(word);
                        allComboDict.put(newWord, transformations);
                    }
                });

        // Queue for BFS
        Queue<Pair> Q = new LinkedList<>();
        Q.add(new Pair(beginWord, 1));

        // Visited to make sure we don't repeat processing same word.
        Map<String, Boolean> visited = new HashMap<>();
        visited.put(beginWord, true);

        while (!Q.isEmpty()) {
            Pair node = Q.remove();
            String word = node.str;
            int len = node.val;
            for (int i = 0; i < L; i++) {

                // Intermediate words for current word
                String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);

                // Next states are all the words which share the same intermediate state.
                for (String adjacentWord : allComboDict.getOrDefault(newWord, new ArrayList<>())) {
                    // If at any point if we find what we are looking for
                    // i.e. the end word - we can return with the answer.
                    if (adjacentWord.equals(endWord)) {
                        return len + 1;
                    }
                    // Otherwise, add it to the BFS Queue. Also mark it visited
                    if (!visited.containsKey(adjacentWord)) {
                        visited.put(adjacentWord, true);
                        Q.add(new Pair(adjacentWord, len + 1));
                    }
                }
            }
        }

        return 0;
    }

    /**
     * The idea is to replace each character one-by-one with all other
     * 25 alphabets and check whether we reached the {@code endWord}
     * (Here we are taking the benefit that next word is differ by only 1 character)
     * Copied from <a href=https://leetcode.com/problems/word-ladder/solution/242075>leetcode discuss comment</a>
     *
     * @param beginWord start word, need not be present in the {@code wordList}
     * @param endWord   target word
     * @param wordList  dictionary of words available to us
     * @return steps needed to reach {@code endWord} starting from
     * {@code beginWord}, choosing words from the {@code wordList}
     */
    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<>(wordList);
        Queue<String> q = new LinkedList<>();
        q.add(beginWord);

        int len = 1;
        while (!q.isEmpty()) {
            int size = q.size();
            // for all words this rounds
            for (int i = 0; i < size; i++) {
                char[] currWord = q.poll().toCharArray();
                // change each character one-by-one
                for (int j = 0; j < currWord.length; j++) {
                    char temp = currWord[j];
                    // look for all 26 alphabets
                    for (char c = 'a'; c <= 'z'; c++) {
//                        if (currWord[j] == c)
//                            continue;
                        currWord[j] = c;
                        String nextWord = new String(currWord);
                        if (set.contains(nextWord)) {
                            // we reached the endWord
                            if (nextWord.equals(endWord))
                                return len + 1;     // we need 1 more step to reach endWord, because it's the nextWord
                            q.add(nextWord);        // process this word later
                            set.remove(nextWord);   // remove current word so that we'll not trap in infinite loop
                        }
                    }
                    // undo the change
                    currWord[j] = temp;
                }
            }
            // each round ends with 1 letter change
            len++;
        }
        return 0;
    }

    /**
     * <strong>Brute Force</strong> with Memoization
     * <p>Time Complexity: O(n<sup>n</sup>)
     * <p>Surprise: Can process upto only 29 items in {@code dict}
     * <strong>with memoization</strong> and 30 items <strong>without memoization</strong>
     *
     * @return the number of words in the shortest transformation sequence
     * from {@code start} to {@code target}, or {@code 0} if no such sequence exists
     */
    public int ladderLength1(String start, String target, List<String> dict) {
        int length = findShortestTransformationSequence(dict, start, target, new HashSet<String>(), new HashMap<String, Integer>(), 1);
        if (length == Integer.MAX_VALUE) {
            length = 0;
        }
        return length;
    }

    int minLength = Integer.MAX_VALUE;

    private int findShortestTransformationSequence(List<String> dict, String current, String target, HashSet<String> visited, HashMap<String, Integer> map, int length) {
        if (current.equals(target))
            return length;
        // use memory
        if (map.containsValue(current)) {
            return map.get(current);
        }

        for (int i = 0; i < dict.size(); i++) {
            String currentWord = dict.get(i);
            if (isDifferBy1(current, currentWord) && !visited.contains(currentWord)) {
                // add the current word to visited set
                visited.add(currentWord);
                // find length to reach the target from currentWord
                int len = findShortestTransformationSequence(dict, currentWord, target, new HashSet<>(visited), map, length + 1);
                // keep track of minimum length required to reach the target
                minLength = Math.min(len, minLength);
                // remove the current word from visited set so that
                // we can reach the target while taking different route
                visited.remove(currentWord);
                // memoization
                map.put(currentWord, minLength);
            }
        }
        return minLength;
    }

    /**
     * Find whether String {@code a} and {@code b} are differed by only one character
     */
    private boolean isDifferBy1(String a, String b) {
        int count = 0;
        for (int i = 0; i < a.length(); i++)
            if (a.charAt(i) != b.charAt(i))
                count++;
        return count == 1;
    }
}