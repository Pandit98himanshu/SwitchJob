package leetcode;

import java.util.Arrays;

/**
 * Created at : 06/01/22
 * <p>
 * <a href=https://leetcode.com/problems/maximum-length-of-pair-chain/>646. Maximum Length of Pair Chain</a>
 *
 * @author Himanshu Shekhar
 */

public class MaximumLengthOfPairChain {
    /**
     * <strong>Dynamic Programming</strong>
     * <p>Time Complexity: O(n log n + n<sup>2</sup>)
     * <br>Space Complexity: O(n)
     * 
     * Similar pattern as {@link LongestIncreasingSubsequence#lengthOfLIS4}
     */
    public int findLongestChain(int[][] pairs) {
        // since order doesn't matter, we can sort the array
        // sort a/c to 1st column, & then a/c to 2nd column
        Arrays.sort(pairs, (a, b) -> {
            if (a[0] == b[0])
                return a[1] - b[1];
            else
                return a[0] - b[0];
        });
        // after sorting this problem boils down to find the longest increasing subsequence
        int ans = 0;
        // "dp" array stores length of the longest chain which can be formed at index "i"
        int[] dp = new int[pairs.length];
        for (int i = 0; i < pairs.length; i++) {
            dp[i] = 1;  // each element is a chain within itself
            // iterate all previous values of current index and
            // check for required condition given in the question
            for (int j = 0; j < i; j++) {
                if (pairs[j][1] < pairs[i][0]) {
                    // if pair "i" follows pair "j", then store
                    // maximum chain length which can be formed at "dp[i]"
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
                }
            }
            // keep track of max length
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
}
