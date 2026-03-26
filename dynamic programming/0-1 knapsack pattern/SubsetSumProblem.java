import java.util.Arrays;

/**
 * Subset Sum Problem
 * https://www.geeksforgeeks.org/problems/subset-sum-problem-1611555638/1
 */

class Solution {
	/*
	 * Top-Down Approach
	 * TC: O(n²)
	 * SC: O(n²)
	 */
	static Boolean isSubsetSum(int arr[], int sum) {
		boolean[][] dp = new boolean[arr.length + 1][sum + 1];
		// initialization
		for (int j = 0; j <= sum; j++)
			dp[0][j] = false;			// no element(s) is provided but need sum > 0
		for (int i = 0; i <= arr.length; i++)
			dp[i][0] = true;			// element(s) are provided while we need sum equals ZERO

		for (int i = 1; i <= arr.length; i++) {
			for (int j = 1; j <= sum; j++) {
				if (arr[i - 1] <= j)	// check if we can get a subset with sum (j) if we include or exclude current element
					dp[i][j] = dp[i - 1][j - arr[i - 1]] || dp[i - 1][j];
				else					// since current element > required sum, hence only 1 option is to exclude current element
					dp[i][j] = dp[i - 1][j];
			}
		}
		return dp[arr.length][sum];
	}
	
	/*
	 * Bottom-Up Approach
	 * TC: O(n²)
	 * SC: O(n²)
	 */
	private static boolean[][] memo = new boolean[201][(int) 1e4 + 1];
	static Boolean isSubsetSum1(int arr[], int sum) {
		for (boolean[] row : memo)
			Arrays.fill(row, false);
		return isSubsetSumRecur(arr, sum, arr.length - 1);
	}
	private static boolean isSubsetSumRecur(int[] arr, int sum, int n) {
		if (sum == 0)				// put this check on top, otherwise get wrong result for cases when ∑arr[i] = sum
			return true;

		if (n < 0 || sum < 0)		// edge-cases
			return false;

		if (memo[n][sum])			// return if we already calculated, it will re-calculate if the result was "false"
			return memo[n][sum];

		if (arr[n] <= sum)			// check if we can get a subset of sum "sum" by including or by excluding the current element
			return memo[n][sum] = isSubsetSumRecur(arr, sum - arr[n], n - 1) || isSubsetSumRecur(arr, sum, n - 1);
		else						// since current element value > sum required, there's no chance but to exclude current element
			return memo[n][sum] = isSubsetSumRecur(arr, sum, n - 1);
	}
}