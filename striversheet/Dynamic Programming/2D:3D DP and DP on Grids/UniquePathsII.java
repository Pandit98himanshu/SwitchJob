/**
 * 63. Unique Paths II
 * https://leetcode.com/problems/unique-paths-ii/
 */

class Solution {
	/*
	 * Bottom-Up / Tabulation Approach
	 * TC: O(n²)
	 * SC: O(n²)
	 */
	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
		int m = obstacleGrid.length, n = obstacleGrid[0].length;
		int[][] dp = new int[m][n];
		// if we hit obstacle in first-row, we can't go any further
		for (int i = 0; i < m && obstacleGrid[i][0] != 1; i++)
			dp[i][0] = 1;
		// if we hit obstacle in first-column, we can't go any further
		for (int j = 0; j < n && obstacleGrid[0][j] != 1; j++)
			dp[0][j] = 1;

		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				if (obstacleGrid[i][j] == 1)
					dp[i][j] = 0; // there's no option to go to the cell having obstacle
				else {
					dp[i][j] = dp[i - 1][j] + dp[i][j - 1]; // we can reach current cell wither from top or right cell
				}
			}
		}

		return dp[m - 1][n - 1];
	}
}