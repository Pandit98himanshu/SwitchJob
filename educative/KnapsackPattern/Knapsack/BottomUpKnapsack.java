/*
 * Bottom-Up approach for Knapsack DP problem
 */

package educative.KnapsackPattern.Knapsack;

class BottomUpKnapsack {
	public int solveKnapsack(int[] profits, int[] weights, int capacity) {
		int[][] dp = new int[profits.length][capacity + 1];
		return knapsack(dp, profits, weights, capacity);
	}

	private Integer knapsack(int[][] dp, int[] profits, int[] weights, int capacity) {
		// we can't put anything because capacity of knapsack is zero
		for (int i = 0; i < profits.length; i++)
			dp[i][0] = 0;
		// we have only 1st item available to us
		for (int c = 1; c <= capacity; c++)
			if (weights[0] <= c)
				dp[0][c] = 0;
			else
				dp[0][c] = profits[0];

		// iterate for all elements
		for (int i = 1; i < profits.length; i++) {
			// increasing capacity of knapsack from 1 to full
			for (int c = 1; c <= capacity; c++) {
				// add the particular item to knapsack and calculate the profit
				int profit1 = 0;
				if (weights[i] <= c)
					profit1 = profits[i] + dp[i-1][c - weights[i]];
				// don't add the particular item and calculate the profit
				int profit2 = dp[i-1][c];
				// store the calculated maximum profit
				dp[i][c] = Math.max(profit1, profit2);
			}
		}
		
		return dp[profits.length - 1][capacity];
	}

	public static void main(String[] args) {
		int[] profits = {1, 6, 10, 16};
		int[] weights = {1, 2, 3, 5};
		int capacity = 7;

		System.out.println(new BottomUpKnapsack().solveKnapsack(profits, weights, capacity));
	}
}