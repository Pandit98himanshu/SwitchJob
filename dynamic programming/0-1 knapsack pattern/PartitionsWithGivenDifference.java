import java.util.Arrays;

/**
 * Partitions with Given Difference
 * https://www.geeksforgeeks.org/problems/partitions-with-given-difference/1
 */

class Solution {
	// ∑arr[i] = S = s1 + s2
	// |s1 - s2| = diff
	// s1 = (S + diff) / 2
	// if we find "s1", remaining elements will automatically sums to "s2"
	
	/*
	 * Top-Down Approach
	 * TC: O(n²)
	 * SC: O(n²)
	 */
	public int countPartitions(int[] arr, int diff) {
		int S = (int) Arrays.stream(arr).sum();
		if (((S + diff) & 1) != 0)		// sum of integers will an integer,
			return 0;					// even / 2 = integer, odd / 2 = floating-point

		int s1 = (S + diff) / 2;

		// refer Count Subset-Sum solution for better explanation
		int[][] dp = new int[arr.length + 1][s1 + 1];
		dp[0][0] = 1;
		for (int i = 1; i <= arr.length; i++)
			dp[i][0] = arr[i - 1] == 0 ? dp[i - 1][0] << 1 : dp[i - 1][0];
		for (int j = 1; j <= s1; j++)
			dp[0][j] = 0;

		for (int i = 1; i <= arr.length; i++)
			for (int j = 1; j <= s1; j++)
				if (arr[i - 1] <= j)
					dp[i][j] = dp[i - 1][j - arr[i - 1]] + dp[i - 1][j];
				else
					dp[i][j] = dp[i - 1][j];

		return dp[arr.length][s1];
	}
	
	/*
	 * Bottom-Up Approach
	 * TC: O(n²)
	 * SC: O(n²)
	 */
	private static int[][] memo;
	public int countPartitions1(int[] arr, int diff) {
		int S = (int) Arrays.stream(arr).sum();
		if (((S + diff) & 1) != 0)
			return 0;
		
		int s1 = (S + diff) / 2;
		
		memo = new int[arr.length][s1 + 1];
		for (int[] row : memo)
			Arrays.fill(row, -1);				// initialize every cell with -1
		// usual count Subset-Sum problem
		return countSubsetSum(arr, s1, arr.length - 1);
	}
	private int countSubsetSum(int[] arr, int sum, int n) {
		if (sum == 0 && n < 0)		// (n < 0) is added to handle cases like arr[] = [0,2], target-sum = 2
			return 1;

		if (n < 0 || sum < 0)
			return 0;

		if (memo[n][sum] != -1)
			return memo[n][sum];

		if (arr[n] <= sum)
			return memo[n][sum] = countSubsetSum(arr, sum - arr[n], n - 1) + countSubsetSum(arr, sum, n - 1);
		else
			return memo[n][sum] = countSubsetSum(arr, sum, n - 1);
	}
}
