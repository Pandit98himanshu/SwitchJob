import java.util.Arrays;

/**
 * Frog Jump
 * https://www.geeksforgeeks.org/problems/geek-jump/1
 */

class Solution {
	/*
	 * Bottom-Up Approach
	 * TC: O(n)
	 * SC: O(n)
	 */
	int minCost(int[] height) {
		int n = height.length;
		int[] dp = new int[n];
		// cost for jumping 1 step
		if (n > 1)
			dp[1] = Math.abs(height[1] - height[0]);

		for (int i = 2; i < n; i++) {
			int oneStep = Math.abs(height[i] - height[i - 1]) + dp[i - 1];	// cost for jumping 1 step
			int twoSteps = Math.abs(height[i] - height[i - 2]) + dp[i - 2];	// cost for jumping 2 steps
			dp[i] = Math.min(oneStep, twoSteps);							// capture minumum among them
		}
		return dp[n - 1];		// returns min cost for jumping "n" steps
	}
	/*
	 * Top-Down Approach
	 * TC: O(n)
	 * SC: O(n)
	 */
	private int[] memo;
	int minCost1(int[] height) {
		memo = new int[height.length];
		Arrays.fill(memo, -1);

		return findMinCost(height, height.length - 1);
	}

	private int findMinCost(int[] height, int n) {
		// no steps to jump
		if (n <= 0)
			return 0;
		// only 1 step available to jump
		if (n == 1)
			return Math.abs(height[1] - height[0]);
		// use memoization
		if (memo[n] != -1)
			return memo[n];
		// calculate cost for jumping 1 step
		int oneStep = Math.abs(height[n] - height[n - 1]) + findMinCost(height, n - 1);
		// calculate cost for jumping 2 steps
		int twoStep = Integer.MAX_VALUE;
		if (n > 1)
			twoStep = Math.abs(height[n] - height[n - 2]) + findMinCost(height, n - 2);
		// keep track of minimum & memoization
		return memo[n] = Math.min(oneStep, twoStep);
	}
}