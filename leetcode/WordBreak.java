package leetcode;

import java.util.*;

/**
 * Created at : 01/09/21
 * <p>
 * <a href=https://leetcode.com/problems/word-break/>139. Word Break</a>
 *
 * @author Himanshu Shekhar
 */

public class WordBreak {
    /**
     * <strong>Dynamic Programming - Bottom Up</strong>
     * <p>Time Complexity: O(n<sup>3</sup>)
     * <br>Space Complexity: O(n)
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        // dp[i] states, whether it is possible to break
        // till s[i - 1] a/c to rules of the question
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;   // string with length 0 is always possible to make
        for (int i = 1; i <= n; i++) {
            // since we can use any word any number of times
            // we have to iterate whole set of words each time
            for (String word : wordDict) {
                int start = i - word.length();
                // if any word is present in dict
                if (start >= 0 && s.substring(start, i).equals(word))
                    dp[i] = dp[i] || dp[start];
            }
        }
        return dp[n];
    }

    /**
     * <strong>Recursion with memoization</strong>
     * <p>Time Complexity: O(n<sup>3</sup>)
     * <br>Space Complexity: O(n)
     *
     * @see <a href=https://leetcode.com/problems/word-break/solution/>leetcode solution video</a>
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
     * <br>Space Complexity: O(n)
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