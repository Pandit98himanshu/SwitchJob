package leetcode;

/**
 * Created at : 15/01/22
 * <p>
 * <a href=https://leetcode.com/problems/minimum-moves-to-reach-target-score/>5194. Minimum Moves to Reach Target Score</a>
 *
 * @author Himanshu Shekhar
 */

public class MinimumMovesToReachTargetScore {
    /**
     * <strong>DP</strong>
     * <p>Time Complexity: O(target * maxDoubles)
     */
    public int minMoves(int target, int maxDoubles) {
        int[][] dp = new int[target + 1][maxDoubles + 1];

        for (int i = 0; i <= target; i++) {
            for (int j = 0; j <= maxDoubles; j++) {
                if (i == 0 || i == 1) {
                    dp[i][j] = 0;
                    continue;
                }
                if (j == 0) {
                    dp[i][j] = dp[i - 1][j] + 1;
                    continue;
                }
                if (i % 2 != 0) {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1] + 1);
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1] + 1, dp[i - 1][i / 2] + 1));
                }
            }
        }
        return dp[target][maxDoubles];
    }

    /**
     * <strong>Recursion</strong>
     * <p>Time Complexity: O(2<sup>n</sup>)
     */
    public int minMoves1(int target, int maxDoubles) {

        return recur(0, target, maxDoubles);
    }

    private int recur(int steps, int target, int maxDoubles) {
        if (target < 0) {
            return Integer.MAX_VALUE;
        }
        if (target == 1) {
            return steps;
        }

        int inc = Integer.MAX_VALUE, doubles = Integer.MAX_VALUE;

        inc = recur(steps + 1, target - 1, maxDoubles);

        if (maxDoubles > 0 && target % 2 == 0) {
            doubles = recur(steps + 1, target / 2, maxDoubles - 1);
        }
        return Math.min(inc, doubles);
    }
}
