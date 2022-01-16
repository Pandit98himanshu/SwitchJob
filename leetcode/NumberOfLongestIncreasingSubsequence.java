package leetcode;

/**
 * Created at : 07/01/22
 * <p>
 * <a href=https://leetcode.com/problems/number-of-longest-increasing-subsequence/>673. Number of Longest Increasing Subsequence</a>
 *
 * @author Himanshu Shekhar
 */

public class NumberOfLongestIncreasingSubsequence {
    /**
     * <strong>Dynamic Programming</strong>
     * <p>Time Complexity: O(n<sup>2</sup>) : 43 ms
     * <br>Space Complexity: O(2 * n)
     *
     * <p>Copied from <a href=https://leetcode.com/problems/number-of-longest-increasing-subsequence/discuss/107293/JavaC++-Simple-dp-solution-with-explanation>leetcode discuss</a>
     * <p>Variation of {@link LongestIncreasingSubsequence#lengthOfLIS4}, {@link RussianDollEnvelopes#maxEnvelopes}, {@link MaximumLengthOfPairChain}
     */
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        int maxLen = 0, res = 0;
        int[] len = new int[n], count = new int[n];
        for (int i = 0; i < n; i++) {
            len[i] = count[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {

                    if (len[i] < len[j] + 1) {
                        len[i] = len[j] + 1;
                        count[i] = count[j];
                    } else if (len[i] == len[j] + 1) {
                        count[i] += count[j];
                    }
                }
            }
            if (maxLen < len[i]) {
                maxLen = len[i];
                res = count[i]; // reset count if max length changes(increases)
            } else if (maxLen == len[i]) {
                res += count[i];
            }
        }
        return res;
    }
}
