import java.util.Arrays;

/**
 * 416. Partition Equal Subset Sum
 * https://leetcode.com/problems/partition-equal-subset-sum/
 */

class Solution {
	/*
	 * Top-Down Approach
	 * TC: O(n²)
	 * SC: O(n²)
	 * Runtime: 49 ms
	 */
	public boolean canPartition1(int[] nums) {
		int sum = (int) Arrays.stream(nums).sum();
		if ((sum & 1) == 1)
			return false; 		// given array having odd sum can't be divided into 2 subsets
		sum >>= 1;				// we need to find only 1 subset, remaining elements will fall in other subset having same sum

		// same approach as Subset Sum Problem
		boolean[][] dp = new boolean[nums.length + 1][sum + 1];
		for (int j = 0; j <= sum; j++)
			dp[0][j] = false;
		for (int i = 0; i <= nums.length; i++)
			dp[i][0] = true;
		
		for (int i = 1; i <= nums.length; i++) {
			for (int j = 1; j <= sum; j++) {
				if (nums[i - 1] <= j)
					dp[i][j] = dp[i - 1][j - nums[i - 1]] || dp[i - 1][j];
				else
					dp[i][j] = dp[i - 1][j];
			}
		}
		return dp[nums.length][sum];
	}

	/*
	 * Bottom-Up Approach
	 * TC: O(n²)
	 * SC: O(n²)
	 * Runtime: 12 ms
	 */
	private static Boolean[][] memo; 	// using primitive boolean will give TLE at max value of constraint

	public boolean canPartition(int[] nums) {
		int sum = (int) Arrays.stream(nums).sum();
		if ((sum & 1) == 1)
			return false; 				// if total sum of the array is odd, we can't divide it in 2 equal parts

		memo = new Boolean[nums.length][sum / 2 + 1];
		return checkPartitionRecur(nums, sum / 2, nums.length - 1);
	}

	// same as Subset Sum Problem
	private boolean checkPartitionRecur(int[] nums, int sum, int n) {
		if (sum == 0)			// we can find subset having it's sum as "sum"
			return true;

		if (n < 0 || sum < 0)
			return false;		// we are out-of-elements or added more elemets into the current subset

		if (memo[n][sum] != null)
			return memo[n][sum];	// return value if we already calculated it

		if (nums[n] <= sum)
			return memo[n][sum] = checkPartitionRecur(nums, sum - nums[n], n - 1) || 
					checkPartitionRecur(nums, sum, n - 1);
		else
			return memo[n][sum] = checkPartitionRecur(nums, sum, n - 1);
	}
}