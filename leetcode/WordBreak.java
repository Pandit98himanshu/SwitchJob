package leetcode;

import java.util.*;

public class WordBreak {


    /**
     * <strong>Dynamic Programming</strong>
     * <p>Time Complexity: O(n<sup>3</sup>)
     * <br>Space Complexity: O(n)
     *
     * @see for complexities? go to <a href=https://leetcode.com/problems/word-break/solution/>leetcode solution video</a>
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> words = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && words.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    /**
     * <strong>Recursion with memoization</strong>
     * <p>Time Complexity: O(n<sup>3</sup>)
     * <br>Space Complexity: O(n)
     *
     * @see for complexities? go to <a href=https://leetcode.com/problems/word-break/solution/>leetcode solution video</a>
     */
    private boolean wordBreakUtil(String s, Set<String> wordDict, int l, Boolean[] memo) {
        if (l == s.length())
            return true;

        if (memo[l] != null)
            return memo[l];

        for (int r = l + 1; r <= s.length(); r++) {
            String temp = s.substring(l, r);
            if (wordDict.contains(temp) && wordBreakUtil(s, wordDict, r, memo))
                return memo[l] = true;
        }
        return memo[l] = false;
    }

    public boolean wordBreak2(String s, List<String> wordDict) {
        return wordBreakUtil(s, new HashSet<>(wordDict), 0, new Boolean[s.length()]);
    }

    /**
     * <strong>Brute Force</strong>
     * <p>Time Complexity: O(2<sup>n</sup>)
     * Space Complexity: O(n)
     */
    private boolean canBeSegmented = false;

    private void wordBreakRecur(String s, Set<String> wordDict, int l) {
        if (l == s.length()) {
            canBeSegmented = true;
            return;
        }
        for (int r = l + 1; r <= s.length(); r++) {
            String temp = s.substring(l, r);
            if (wordDict.contains(temp))
                wordBreakRecur(s, wordDict, r);
        }
    }

    public boolean wordBreak1(String s, List<String> wordDict) {
        wordBreakRecur(s, new HashSet<>(wordDict), 0);
        return canBeSegmented;
    }

    public static void main(String[] args) {
        String s = "catsandog";
        List<String> wordDict = Arrays.asList("cats", "dog", "sand", "and", "cat");

        s = "acaaaaabbbdbcccdcdaadcdccacbcccabbbbcdaaaaaadb";
        wordDict = Arrays.asList("abbcbda", "cbdaaa", "b", "dadaaad", "dccbbbc", "dccadd", "ccbdbc", "bbca", "bacbcdd", "a", "bacb", "cbc", "adc", "c", "cbdbcad", "cdbab", "db", "abbcdbd", "bcb", "bbdab", "aa", "bcadb", "bacbcb", "ca", "dbdabdb", "ccd", "acbb", "bdc", "acbccd", "d", "cccdcda", "dcbd", "cbccacd", "ac", "cca", "aaddc", "dccac", "ccdc", "bbbbcda", "ba", "adbcadb", "dca", "abd", "bdbb", "ddadbad", "badb", "ab", "aaaaa", "acba", "abbb");

        System.out.println(new WordBreak().wordBreak(s, wordDict));
    }
}