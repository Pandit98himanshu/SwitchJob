import java.util.Arrays;

/**
 * 322. Coin Change
 * https://leetcode.com/problems/coin-change/
 */

class Solution {
	/*
	 * Top-Down Approach
	 * TC: O(n²)
	 * SC: O(n²)
	 */
	public int coinChange(int[] coins, int amount) {
		long[][] dp = new long[coins.length + 1][amount + 1];
		
		// initialization
		for (int i = 0; i <= coins.length; i++)
			dp[i][0] = 0;							// need 0 coins for getting sum = 0, no matter how many coins provided
		Arrays.fill(dp[0], Integer.MAX_VALUE);
		for (int j = 1; j <= amount; j++)
			dp[1][j] = (j % coins[0] == 0) ? j / coins[0] : Integer.MAX_VALUE;

		// usual unbounded-knapsack
		for (int i = 2; i <= coins.length; i++) {
			for (int j = 1; j <= amount; j++) {
				if (coins[i - 1] <= j)
					dp[i][j] = Math.min(1 + dp[i][j - coins[i - 1]], dp[i - 1][j]);
				else
					dp[i][j] = dp[i - 1][j];
			}
		}
		return (int)(dp[coins.length][amount] >= Integer.MAX_VALUE ? -1 : dp[coins.length][amount]);
	}

	/*
	 * Bottom-Up Approach
	 * TC: O(n²)
	 * SC: O(n²)
	 */
	private static long[][] memo;
	public int coinChange1(int[] coins, int amount) {
		memo = new long[coins.length][amount + 1];
		for (long[] row : memo)
			Arrays.fill(row, -1);
		long minDenominations = unboundedKnapsack(coins, amount, coins.length - 1);
		return (int)(minDenominations >= Integer.MAX_VALUE ? -1 : minDenominations);
	}

	private long unboundedKnapsack(int[] wt, int W, int n) {
		// base case
		if (n < 0 || W < 0)				// need infinite coins if no coins but sum > 0
			return Integer.MAX_VALUE;
		if (W == 0)						// need ZERO coins to get ZERO sum
			return 0;
		// memoization
		if (memo[n][W] != -1)
			return memo[n][W];
		// usual unbounded-knapsack
		if (wt[n] <= W)
			return memo[n][W] = Math.min(1 + unboundedKnapsack(wt, W - wt[n], n),
				unboundedKnapsack(wt, W, n - 1));
		else
			return memo[n][W] = unboundedKnapsack(wt, W, n - 1);
	}
}