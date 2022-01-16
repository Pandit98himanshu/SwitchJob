package leetcode;

import java.util.Arrays;

/**
 * Created at : 04/01/22
 * <p>
 * <a href=https://leetcode.com/problems/russian-doll-envelopes/>354. Russian Doll Envelopes</a>
 * <br><br>
 * Variant of {@link LongestIncreasingSubsequence}
 *
 * @author Himanshu Shekhar
 */

public class RussianDollEnvelopes {
    /**
     * <strong>Dynamic Programming</strong>
     * <p>Time Complexity: O(n log n) : 22 ms
     * 
     * <p>Copied from <a href=https://leetcode.com/problems/russian-doll-envelopes/discuss/82763/Java-NLogN-Solution-with-Explanation>leetcode discuss</a>
     * <p>Explanation for the same in <a href=https://leetcode.com/problems/russian-doll-envelopes/discuss/82763/Java-NLogN-Solution-with-Explanation/87015>leetcode discuss comment</a>
     * 
     * @see LongestIncreasingSubsequence#lengthOfLIS
     */
    public int maxEnvelopes(int[][] envelopes) {
        // sort 1st column in ascending and
        // 2nd column in descending if 1st column values are same
        Arrays.sort(envelopes, (a, b) -> {
            if (a[0] == b[0])
                return b[1] - a[1];
            else
                return a[0] - b[0];
        });

        // since 1st column is sorted, the problem boils-
        // down to LongestIncreasingSubsequence on 2nd column
        int size = 0;
        int[] tails = new int[envelopes.length];
        for (int[] x : envelopes) {

            // binary search for correct position
            // of current element in subsequence
            int i = 0, j = size;
            while (i != j) {
                int mid = (i + j) / 2;
                if (tails[mid] < x[1])
                    i = mid + 1;
                else
                    j = mid;
            }
            tails[i] = x[1];
            if (i == size)
                size++;
        }
        return size;
    }

    /**
     * <strong>Dynamic Programming</strong>
     * <p>Time Complexity: O(n log n + n<sup>2</sup>) : 240 ms
     *
     * @see LongestIncreasingSubsequence#lengthOfLIS4
     */
    public int maxEnvelopes1(int[][] envelopes) {
        // Sort by 1st row, if it is same, sort by 2nd row
        Arrays.sort(envelopes, (a, b) -> {
            if (a[0] == b[0])
                return a[1] - b[1];
            else
                return a[0] - b[0];
        });

        int[] dp = new int[envelopes.length];   // stores only length
        int ans = 0;
        for (int i = 0; i < envelopes.length; i++) {
            dp[i] = 1;
            // iterate previous values of current index & check for
            // envelop we can Russian-doll into current one
            for (int j = 0; j < i; j++) {
                if (envelopes[i][0] > envelopes[j][0] && envelopes[i][1] > envelopes[j][1])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            // keep track of maximum Russian-doll envelop
            ans = Math.max(ans, dp[i]);
        }

        return ans;
    }

}
