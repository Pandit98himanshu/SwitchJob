/**
 * 213. House Robber II
 * https://leetcode.com/problems/house-robber-ii/
 */

class Solution {
	/*
	 * TC: O(2 * n)
	 * SC: O(n)
	 */
	public int rob(int[] nums) {
		int n = nums.length;
		int[] dp = new int[n + 1];
		// 1 -> (n - 1) : excluding the last house & including the 1st one
		dp[0] = 0;
		dp[1] = nums[0];
		for (int i = 2; i <= n - 1; i++) {
			dp[i] = Math.max(dp[i - 1], nums[i - 1] + dp[i - 2]);
		}
		int excludeLast = (n > 1) ? dp[n - 1] : dp[1];
		// 2 -> n : excluding the 1st house & including the last one
		dp[0] = 0;
		dp[1] = (n > 1) ? nums[1] : 0;
		for (int i = 2; i < n; i++) {
			dp[i] = Math.max(dp[i - 1], nums[i] + dp[i - 2]);
		}
		int excludeFirst = dp[n - 1];

		return Math.max(excludeFirst, excludeLast);
	}
}