package gfg;

/**
 * Created at : 22/12/21
 * <p>
 * <a href=https://leetcode.com/discuss/interview-question/1321204/efficient-harvest-faang-oa-question-2021>Efficient Harvest</a>
 *
 * @author Himanshu Shekhar
 */

public class EfficientHarvest {
    /**
     * <strong>Sliding Window</strong>
     * <p>Time Complexity: O(2 * n)
     * <br>Space Complexity: O(1)
     */
    public int maxProfit(int[] profits, int n, int k) {
        int max = 0, currMax = 0;
        // find sub-array of length k with max
        // profit in a circular array "profits"
        for (int i = 0; i < n/2; i++) {
            profits[i] = profits[i + n/2];
            profits[i + n/2] = profits[i];
        }
        int l = 0, r = 0;

        while (r < n - 1) {
            // increase window size till "k"
            if (r < k) {
                currMax += profits[r++];
                max = Math.max(max, currMax);
            }
            // slide window
            else {
                currMax += profits[r++];
                currMax -= profits[l++];
                max = Math.max(max, currMax);
            }
        }

        return max;
    }
}
