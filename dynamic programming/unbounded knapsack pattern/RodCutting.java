import java.util.Arrays;

/**
 * Rod Cutting
 * https://www.geeksforgeeks.org/problems/rod-cutting0840/1
 */

class Solution {
	/*
	 * Bottom-Up Approach
	 * TC: O(n²)
	 * SC: O(n²)
	 */
	private static int[][] memo;
	public int cutRod(int[] price) {
		memo = new int[price.length + 1][price.length + 1];
		for (int[] row : memo)
			Arrays.fill(row, -1);
		return unboundedKnapsack(price, price.length, price.length - 1);
	}
	private int unboundedKnapsack(int[] price, int W, int n) {
		if (n < 0 || W < 0)		// can get 0 profit if either we explored all cutting options or, no rod left to cut
			return 0;

		if (memo[n][W] != -1)
			return memo[n][W];

		if (n + 1 <= W) {		// since 1-indexing, length of rod at index n = n + 1
			return memo[n][W] = Math.max(price[n] + unboundedKnapsack(price, W - (n + 1), n),
													unboundedKnapsack(price, W, n - 1));
		} else
			return memo[n][W] = unboundedKnapsack(price, W, n - 1);
	}
}