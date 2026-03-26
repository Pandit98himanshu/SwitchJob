import java.util.Arrays;

/**
 * 0 - 1 Knapsack Problem
 * https://www.geeksforgeeks.org/problems/0-1-knapsack-problem0945/1
 */

class Solution {
	/*
	 * Top-Down Approach
	 * TC: O(n²)
	 * SC: O(n²)
	 */
	public int knapsack(int W, int val[], int wt[]) {
		int[][] dp = new int[wt.length + 1][W + 1];
		// initialize
		for (int i = 0; i <= wt.length; i++)
			dp[i][0] = 0; // knapsack capacity is zero, hence can't add any item

		for (int j = 0; j <= W; j++)
			dp[0][j] = 0; // no items are given, hence can't add any item into knapsack

		for (int i = 1; i <= wt.length; i++) {
			for (int j = 1; j <= W; j++) {
				// check if including will give max output or excluding the item
				if (wt[i - 1] <= j) // we used 1-indexing
					dp[i][j] = Math.max(val[i - 1] + dp[i - 1][j - wt[i - 1]], dp[i - 1][j]);
				else
					dp[i][j] = dp[i - 1][j]; // exclude the item since it can't fit into the knapsack
			}
		}
		return dp[wt.length][W];
	}

	/*
	 * Bottom-Up Approach
	 * TC: O(n²)
	 * SC: O(n²)	-> recursive stack + memoization
	 */
	private static int[][] memo = new int[(int)1e3 + 1][(int)1e3 + 1];
	public int knapsack1(int W, int val[], int wt[]) {
		for(int[] row : memo)
			Arrays.fill(row, -1);
		return knapsackRecur(W, val, wt, wt.length - 1);
	}
	private int knapsackRecur(int W, int[] val, int[] wt, int n) {
		// either we are out of items or knapsack is filled
		if (n < 0 || W <= 0)
			return 0;
		
		// return the value if already calculated previously
		if (memo[n][W] != -1)
			return memo[n][W];

		// check if including the item will give max output or excluding it
		if (wt[n] <= W)
			return memo[n][W] = Math.max(val[n] + knapsackRecur(W - wt[n], val, wt, n - 1), 
					knapsackRecur(W, val, wt, n - 1));

		// we can't add the element hence excluding it and moving forward
		return memo[n][W] = knapsackRecur(W, val, wt, n - 1);
	}
}
