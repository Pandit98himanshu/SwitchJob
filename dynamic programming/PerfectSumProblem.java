import java.util.Arrays;

/**
 * Perfect Sum Problem (Count Subsets with sum K)
 * https://www.geeksforgeeks.org/problems/perfect-sum-problem5633/1
 */

class Solution {
	/*
	 * Top-Down Approach
	 * TC: O(n²)
	 * SC: O(n²)
	 */
	public int perfectSum(int[] nums, int target) {
		int[][] dp = new int[nums.length + 1][target + 1];
		// initialization
		dp[0][0] = 1;					// empty set
		for (int i = 1; i <= nums.length; i++)
			dp[i][0] = nums[i - 1] == 0 ? dp[i - 1][0] << 1 : dp[i - 1][0];		// adding 1 zero multiples the count subsets by 2
		for (int j = 1; j <= target; j++)
			dp[0][j] = 0;				// no elements are given but requires target-sum > 0

		// usual Subset-Sum problem
		for (int i = 1; i <= nums.length; i++) {
			for (int j = 1; j <= target; j++) {
				if (nums[i - 1] <= j)
					dp[i][j] = dp[i - 1][j - nums[i - 1]] + dp[i - 1][j];
				else
					dp[i][j] = dp[i - 1][j];
			}
		}
		return dp[nums.length][target];
	}

	/*
	 * Bottom-Up Approach
	 * TC: O(n²)
	 * SC: O(n²)
	 * Function to calculate the number of subsets with a given sum
	 */
	private static int[][] memo;
	public int perfectSum1(int[] nums, int target) {
		memo = new int[nums.length][target + 1];
		for (int[] row : memo)
			Arrays.fill(row, -1);

		return countSubsetSum(nums, target, nums.length - 1);
	}

	private int countSubsetSum(int[] nums, int target, int n) {
		if (target == 0 && n < 0)		// (n < 0) is necessary due to inclusion of ZERO in "nums"
			return 1;

		if (n < 0 || target < 0)		// base case (either elements not left or added more elements into subset)
			return 0;

		if (memo[n][target] != -1)
			return memo[n][target];		// return already calculated value

		// usual Subset-sum
		if (nums[n] <= target)
			return memo[n][target] = countSubsetSum(nums, target - nums[n], n - 1) + 
					countSubsetSum(nums, target, n - 1);
		else
			return memo[n][target] = countSubsetSum(nums, target, n - 1);
	}
}