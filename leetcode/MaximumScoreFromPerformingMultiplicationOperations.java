package leetcode;

/**
 * Created at : 28/11/21
 * <p>
 * <a href=https://leetcode.com/problems/maximum-score-from-performing-multiplication-operations/>1770. Maximum Score from Performing Multiplication Operations</a>
 * <p>This problem is to understand, how to convert top-down dp into bottom-up approach
 * Compare with <a href=https://leetcode.com/problems/house-robber/>198. House Robber</a> to understand how to start iteration i.e., (n-1 to 0) or (0 to n-1).
 * Here current values depends on next values while in <a href=https://leetcode.com/problems/house-robber/>198. House Robber</a>, it depends on previous values.
 *
 * @author Himanshu Shekhar
 * @see <a href=https://leetcode.com/explore/learn/card/dynamic-programming/631/strategy-for-solving-dp-problems/4100/>Leetcode DP explore card</a>
 */

public class MaximumScoreFromPerformingMultiplicationOperations {

    private int[][] memo;

    /**
     * <strong>top-down dp (memoization)</strong>
     */
    public int maximumScore1(int[] nums, int[] multipliers) {
        int m = multipliers.length;
        memo = new int[m][m];
        // final result will be returned when (i, l) == (0, 0)
        return maximumScore(nums, multipliers, 0, 0, nums.length - 1);
    }

    /**
     * {@code r = (nums.length - 1) - (i - l)}
     */
    public int maximumScore(int[] nums, int[] multipliers, int i, int l, int r) {
        // base case
        if (i == multipliers.length)
            return 0;
        // base case
        if (l > r)
            return 0;
        // return which is already calculated
        if (memo[i][l] != 0)
            return memo[i][l];
        // multiply current picked element with multiplier
        // and find max-score for rest of the elements
        int takeLeft = multipliers[i] * nums[l] + maximumScore(nums, multipliers, i + 1, l + 1, r);
        int takeRight = multipliers[i] * nums[r] + maximumScore(nums, multipliers, i + 1, l, r - 1);

        return memo[i][l] = Math.max(takeLeft, takeRight);
    }

    /**
     * <strong>bottom-up dp (tabulation)</strong>
     */
    public int maximumScore(int[] nums, int[] multipliers) {
        int m = multipliers.length;
        int[][] dp = new int[m + 1][m + 1];
        // since we need next values i.e., dp[i+1] to calculate current one,
        // therefore, we have to start from (m-1) & iterate till zero(0)
        for (int i = m - 1; i >= 0; i--) {
            // (i == m) is our base case from top-down approach
            for (int l = i; l >= 0; l--) {
                // we can also calculate "r" in top-down approach,
                // but we passed "r" as parameter to increase readability
                int r = (nums.length - 1) - (i - l);
                int takeLeft = multipliers[i] * nums[l] + dp[i + 1][l + 1];
                int takeRight = multipliers[i] * nums[r] + dp[i + 1][l];

                dp[i][l] = Math.max(takeLeft, takeRight);
            }
        }
        // final result will be stored in (i, l) == (0, 0)
        return dp[0][0];
    }
}
