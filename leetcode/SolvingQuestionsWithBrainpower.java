package leetcode;

import java.util.Arrays;

/**
 * Created at : 16/01/22
 * <p>
 * <a href=https://leetcode.com/contest/weekly-contest-276/problems/solving-questions-with-brainpower/>2140. Solving Questions With Brainpower</a>
 *
 * @author Himanshu Shekhar
 */

public class SolvingQuestionsWithBrainpower {
    private int n;
    private int[][] questions;
    private long[] memo;

    /**
     * <strong>Recursion with Memoization</strong>
     * <p>Time Complexity: O(n)
     * <br>Space Complexity: O(n)
     */
    public long mostPoints1(int[][] questions) {
        n = questions.length;
        this.questions = questions;
        memo = new long[n];
        Arrays.fill(memo, -1);

        recur(0, 0);

        return Arrays.stream(memo).max().getAsLong();
    }

    private long recur(int i, int points) {
        if (i >= n)
            return points;

        if (memo[i] != -1)
            return memo[i];

        long inc = recur(i + 1 + questions[i][1], points + questions[i][0]);
        long exc = recur(i + 1, points);

        return memo[i] = Math.max(inc, exc);
    }

    /**
     * <strong>Dynamic Programming</strong>
     * <p>Time Complexity: O(n)
     * <br>Space Complexity: O(n)
     */
    public long mostPoints(int[][] questions) {
        int n = questions.length;
        long[] dp = new long[(int)1e6];
        for (int i = n-1; i >= 0; i--) {
            long inc = questions[i][0] + dp[i + 1 + questions[i][1]];
            long exc = dp[i + 1];
            dp[i] = Math.max(inc, exc);
        }
        return dp[0];
    }
}
