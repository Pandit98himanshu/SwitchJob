import java.util.Arrays;

/**
 * 70. Climbing Stairs
 * https://leetcode.com/problems/climbing-stairs/
 */

class Solution {
	/*
	 * Bottom-Up Approach
	 * TC: O(n)
	 * SC: O(n)
	 */
	public int climbStairs(int n) {
		int[] dp = new int[n + 1];
		dp[0] = dp[1] = 1;
		for (int i = 2; i <= n; i++)
			dp[i] = dp[i - 1] + dp[i - 2];
		return dp[n];
	}
	/*
	 * Top-Down Approach
	 * TC: O(n)
	 * SC: O(n)
	 */
	public int climbStairs1(int n) {
		int[] memo = new int[n + 1];
		Arrays.fill(memo, -1);
		return findWays(n, memo);
	}

	private int findWays(int n, int[] memo) {
		// no stairs to climb
		if (n < 0)
			return 0;
		// only 1 option to reach step 1
		if (n <= 1)
			return 1;
		// use memoization
		if (memo[n] != -1)
			return memo[n];
		// we can reach step "n" from either step "n - 1" or "n - 2", hence take the sum of both
		return (memo[n - 1] = findWays(n - 1, memo)) + (memo[n - 2] = findWays(n - 2, memo));
	}
}