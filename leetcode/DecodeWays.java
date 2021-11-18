package leetcode;

import java.util.*;

public class DecodeWays {
    /**
     * <strong>Dynamic Programming</strong>
     * <p>Idea :- If we add an new character, then
     * <pre>
     * no. of decodings till curr_char = no. of decodings till prev_char
     *                                  + no. of decodings till 2 chars back
     *                                      [if we can decode (prev_char + curr_char)]
     * </pre>
     * Copied from <a href=https://leetcode.com/problems/decode-ways/solution/>leetcode solution</a>
     * <p>Time Complexity: O(n)
     * <br>Space Complexity: O(n);
     * we can also solve it in O(1) 'cause we
     * just need to check till 2 prev values
     *
     * @param s input
     * @return number of decodings of {@code s}
     * @see <a href=https://leetcode.com/problems/decode-ways-ii/>639. Decode Ways II</a>
     */
    public int numDecodings(String s) {
        int n = s.length();     // >= 1

        int[] dp = new int[n + 1];
        dp[0] = 1;      // we can decode for length 0
        dp[1] = (s.charAt(0) == '0') ? 0 : 1;
        for (int i = 2; i <= n; i++) {
            // we can't decode '0'
            // if we encounter element '1' to '9' we can decode it
            if (s.charAt(i - 1) != '0')
                dp[i] += dp[i - 1];

            // if we encounter '10' to '26' we can decode it as well
            int twoDig = Integer.parseInt(s.substring(i - 2, i));
            if (twoDig >= 10 && twoDig <= 26)
                dp[i] += dp[i - 2];
        }
        return dp[n];
    }

    /**
     * <strong>Recursion using memoization</strong>
     * <p>Time Complexity: O(n)
     * <br>Space Complexity: O(n)
     * <pre>only recursion will take O(2<sup>n</sup>)
     */
    public int numDecodings1(String s) {
        return countNumDecodings(s, new HashMap<>());
    }

    /**
     * @param s    current string
     * @param memo stores calculated result
     */
    private int countNumDecodings(String s, Map<String, Integer> memo) {
        // we've calculated the number of decodings for current string
        if (memo.containsKey(s))
            return memo.get(s);

        int n = s.length();
        // if we reach the end of the string, return 1 for success.
        if (n == 0)
            return 1;

        // if 1st character is zero, we can't decode it
        if (s.charAt(0) == '0')
            return 0;

        // if we have only 1 digit which is non-zero then we can decode it
        if (n == 1)
            return 1;

        String rest1 = s.substring(1, n);   // takeout 1st digit
        int ans = countNumDecodings(rest1, memo);  // calculate number of decodings for rest of the string

        int first2 = Integer.parseInt(s.substring(0, 2));       // takeout first 2 digits
        if (first2 >= 10 && first2 <= 26) {         // see whether we can decode it
            String rest2 = s.substring(2, n);
            ans += countNumDecodings(rest2, memo);  // calculate number of decodings for rest of the string
        }
        memo.put(s, ans);       // store current result
        return ans;
    }

    public static void main(String[] args) {
        String s = "2611055971756562";
        System.out.println(new DecodeWays().numDecodings(s));
    }
}
