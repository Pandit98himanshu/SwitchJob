import java.util.Arrays;

/**
 * Knapsack with Duplicate Items
 * https://www.geeksforgeeks.org/problems/knapsack-with-duplicate-items4201/1
 */

class Solution {
	/*
	 * Top-Down Approach
	 * TC: O(n²)
	 * SC: O(n²)
	 */
	public int knapSack(int val[], int wt[], int capacity) {
		int[][] dp = new int[wt.length + 1][capacity + 1];
		Arrays.fill(dp[0], 0);
		for (int i = 0; i <= wt.length; i++)
			dp[i][0] = 0;

		for (int i = 1; i <= wt.length; i++) {
			for (int j = 1; j <= capacity; j++) {
				if (wt[i - 1] <= j)
					dp[i][j] = Math.max(val[i - 1] + dp[i][j - wt[i - 1]], dp[i - 1][j]);
				else
					dp[i][j] = dp[i - 1][j];
			}
		}
		return dp[wt.length][capacity];
	}
	
	/*
	 * Bottom-Up Approach
	 * TC: O(n²)
	 * SC: O(n²)
	 */
	private static int[][] memo;
	public int knapSack1(int val[], int wt[], int capacity) {
		memo = new int[wt.length][capacity + 1];
		for (int[] row : memo)
			Arrays.fill(row, -1);
		return unboundedKnapSack(val, wt, capacity, wt.length - 1);
	}
	private int unboundedKnapSack(int[] val, int[] wt, int W, int n) {
		if (n < 0 || W <= 0)		// can't get any value when either out of items or knapsack is full
			return 0;

		if (memo[n][W] != -1)		// use already calculated value
			return memo[n][W];

		if (wt[n] <= W) {			// knapsack can accomodate current value
			return memo[n][W] = Math.max(val[n] + unboundedKnapSack(val, wt, W - wt[n], n),	// include current item of any instances
					unboundedKnapSack(val, wt, W, n - 1));									// exclude current item and move to next item
		} else
			return memo[n][W] = unboundedKnapSack(val, wt, W, n - 1);						// only 1 option to exclude since knapsack can't accomodate current item
	}
}