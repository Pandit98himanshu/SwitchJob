import java.util.Arrays;

/**
 * 494. Target Sum
 * https://leetcode.com/problems/target-sum/
 */

class Solution {
	// 2 subsets: 1 having (+) sign = s1 & other having (-) sign = s2
	// s1 - s2 = target
	// s1 + s2 = S = ∑nums[i]
	// s1 = (S + target) / 2

	/*
	 * Bottom-Up Approach
	 * TC: O(n²)
	 * SC: O(n²)
	 * Same as "Partition with Given Difference"
	 */
	private static int[][] memo;
	public int findTargetSumWays(int[] nums, int target) {
		int S = (int) Arrays.stream(nums).sum();
		if (((S + target) & 1) != 0)
			return 0;
		if (Math.abs(target) > S)				// handle edge-cases
			return 0;
		
		int s1 = (S + target) >> 1;
		
		memo = new int[nums.length][s1 + 1];
		for (int[] row : memo)
			Arrays.fill(row, -1);				// initialize every cell with -1
		// usual count Subset-Sum problem
		return countSubsetSum(nums, s1, nums.length - 1);
	}
	private int countSubsetSum(int[] nums, int sum, int n) {
		if (sum == 0 && n < 0)		// (n < 0) is added to handle cases like nums[] = [0,2], target-sum = 2
			return 1;

		if (n < 0 || sum < 0)
			return 0;

		if (memo[n][sum] != -1)
			return memo[n][sum];

		if (nums[n] <= sum)
			return memo[n][sum] = countSubsetSum(nums, sum - nums[n], n - 1) + countSubsetSum(nums, sum, n - 1);
		else
			return memo[n][sum] = countSubsetSum(nums, sum, n - 1);
	}
}