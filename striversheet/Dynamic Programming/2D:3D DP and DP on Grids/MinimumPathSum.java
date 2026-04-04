/**
 * 64. Minimum Path Sum
 * https://leetcode.com/problems/minimum-path-sum/
 */

class Solution {
	/*
	 * Bottom-Up / Tabulation Approach
	 * TC: O(n²)
	 * SC: O(n²)
	 */
	public int minPathSum(int[][] grid) {
		int m = grid.length, n = grid[0].length;
		int[][] dp = new int[m][n];
		// initialization
		dp[0][0] = grid[0][0];
		for (int i = 1; i < m; i++)
			dp[i][0] = grid[i][0] + dp[i - 1][0];
		for (int j = 1; j < n; j++)
			dp[0][j] = grid[0][j] + dp[0][j - 1];
		// calculate min sum required to reach (i, j)
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				// we can reach current cell either from adjacent top or left cell
				dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
			}
		}

		return dp[m - 1][n - 1];
	}
}