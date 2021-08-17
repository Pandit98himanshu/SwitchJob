/*
 * 1955. Count Number of Special Subsequences
 * https://leetcode.com/problems/count-number-of-special-subsequences/
 */

package leetcode;

public class _CountNumberOfSpecialSubsequences_ {
    /**
     * Copied from <a href=https://leetcode.com/problems/count-number-of-special-subsequences/discuss/1375485/JavaC++Python-DP-Solution>leetcode discuss</a>
     * <p>A sequence is <strong>special</strong> if it consists of a positive number of 0s,
     * followed by a positive number of 1s, then a positive number of 2s
     * <p>Time Complexity: O(n)
     * <br>Space Complexity: O(1)
     *
     * @return the number of different subsequences that are <strong>special</strong>
     */
    public int countSpecialSubsequences(int[] nums) {
        int mod = (int) 1e9 + 7;
        int[] dp = new int[3];

        // dp[0] means the number of special sequence of 0.
        // dp[1] means the number of special sequence of 0,1.
        // dp[2] means the number of special sequence 0,1,2.

        for (int i : nums) {
            if (i == 0)
                dp[0] = ((2 * dp[0]) % mod + 1) % mod;
            if (i == 1)
                dp[1] = ((2 * dp[1]) % mod + dp[0]) % mod;
            if (i == 2)
                dp[2] = ((2 * dp[2]) % mod + dp[1]) % mod;
        }

/*
        // same as above in 1 liner
        for (int i : nums) {
            dp[i] = ((2 * dp[i]) % mod + (i > 0 ? dp[i - 1] : 1)) % mod;
        }
*/

        return dp[2];
    }
}
