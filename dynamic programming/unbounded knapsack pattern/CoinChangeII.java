import java.util.Arrays;

/**
 * 518. Coin Change II
 * https://leetcode.com/problems/coin-change-ii/
 */

class Solution {
	/*
	 * Top-Down Approach
	 * TC: O(n²)
	 * SC: O(n²)
	 */
	public int change(int amount, int[] coins) {
		int[][] dp = new int[coins.length + 1][amount + 1];
		// initialization
		for (int i = 0; i <= coins.length; i++)
			dp[i][0] = 1;						// null set is 1 option to get sum = 0
		for (int j = 1; j <= amount; j++)
			dp[0][j] = 0;						// no option to get sum > 0 when no coins are provided
		// usual unbounded-knapsack
		for (int i = 1; i <= coins.length; i++) {
			for (int j = 1; j <= amount; j++) {
				if (coins[i - 1] <= j)
					dp[i][j] = dp[i][j - coins[i - 1]] + dp[i - 1][j];
				else
					dp[i][j] = dp[i - 1][j];
			}
		}
		return dp[coins.length][amount];
	}

	/*
	 * Bottom-Up Approach
	 * TC: O(n²)
	 * SC: O(n²)
	 */
	private static int[][] memo;
	public int change1(int amount, int[] coins) {
		memo = new int[coins.length][amount + 1];
		for (int[] row : memo)
			Arrays.fill(row, -1);
		return unboundedKnapsack(coins, amount, coins.length - 1);
	}
	private int unboundedKnapsack(int[] coins, int amt, int n) {
		// success case
		if (amt == 0)
			return 1;				// there is atleast 1 way which made remaining amt = 0
		// base case
		if (n < 0 || amt < 0)
			return 0;
		// memoization
		if (memo[n][amt] != -1)
			return memo[n][amt];
		// usual unbounded-knapsack
		if (coins[n] <= amt)
			return memo[n][amt] = unboundedKnapsack(coins, amt - coins[n], n) + 
					unboundedKnapsack(coins, amt, n - 1);
		else
			return memo[n][amt] = unboundedKnapsack(coins, amt, n - 1);
	}
}