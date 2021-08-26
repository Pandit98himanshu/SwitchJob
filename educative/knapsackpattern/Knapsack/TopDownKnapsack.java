/*
 * Top-Down approach for Knapsack DP problem
 */
package educative.knapsackpattern.Knapsack;

class TopDownKnapsack {
	public int solveKnapsack(int[] profits, int[] weights, int capacity) {
		Integer[][] dp = new Integer[profits.length][capacity + 1];
		return knapsackRecursive(dp, profits, weights, capacity, 0);
	}

	private Integer knapsackRecursive(Integer[][] dp, int[] profits, int[] weights, int capacity, int currentIndex) {
		// base cases
		if (capacity <= 0 || currentIndex >= profits.length) {
			return 0;
		}
		// simply return what we have already calculated
		if (dp[currentIndex][capacity] != null)
			return dp[currentIndex][capacity];
		// add the particular item to knapsack and calculate the profit
		Integer profit1 = 0;
		if (weights[currentIndex] <= capacity) 
			profit1 = profits[currentIndex] + knapsackRecursive(dp, profits, weights, capacity - weights[currentIndex], currentIndex + 1);
		// don't add the particular item and calculate the profit
		Integer profit2 = knapsackRecursive(dp, profits, weights, capacity, currentIndex + 1);
		// store the calculated maximum profit
		dp[currentIndex][capacity] = Math.max(profit1, profit2);

		return dp[currentIndex][capacity];
	}

	public static void main(String[] args) {
		int[] profits = {1, 6, 10, 16};
		int[] weights = {1, 2, 3, 5};
		int capacity = 7;

		System.out.println(new TopDownKnapsack().solveKnapsack(profits, weights, capacity));
	}
}