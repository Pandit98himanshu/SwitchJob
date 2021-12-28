package leetcode;

import java.util.*;

/**
 * Created at : 07/12/21
 * <p>
 * <a href=https://leetcode.com/problems/word-break-ii/>140. Word Break II</a>
 *
 * @author Himanshu Shekhar
 */

public class WordBreakII {
    // DP Top-down
    private static List<String> ans;
    public List<String> wordBreak(String s, List<String> wordDict) {
        ans = new ArrayList<>();

        recur(s, wordDict, 0, new String());
        return ans;
    }

    private static void recur(String s, List<String> dict, int i, String temp) {
        // edge case
        if (i > s.length())
            return;
        // base case
        if (i == s.length()) {
            ans.add(temp.trim());
            return;
        }
        // iterate for all dict words each time
        for (String word : dict) {
            int end = i + word.length();
            if (end <= s.length() && s.substring(i, end).equals(word)) {
                recur(s, dict, end, temp + word + " ");
            }
        }
    }
}
