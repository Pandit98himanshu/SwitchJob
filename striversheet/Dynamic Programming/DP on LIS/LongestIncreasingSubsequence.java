import java.util.Arrays;

/**
 * 300. Longest Increasing Subsequence
 * https://leetcode.com/problems/longest-increasing-subsequence/
 */

class Solution {
	/*
	 * Patience Sorting (with Binary Search)
	 * TC: O(n logn)
	 * SC: O(n)
	 */
	public int lengthOfLIS(int[] nums) {
		int[] tails = new int[nums.length];
		int size = 0;

		for (int num : nums) {
			int left = 0, right = size;
			while (left < right) {
				int mid = (left + right) / 2;
				if (tails[mid] < num) {
					left = mid + 1;
				} else {
					right = mid;
				}
			}
			tails[left] = num;
			if (left == size) size++;
		}

		return size;
	}

	/*
	 * Bottom-Up
	 * TC: O(n²)
	 * SC: O(n)
	 */
	public int lengthOfLIS1(int[] nums) {
		int[] dp = new int[nums.length];
		dp[nums.length - 1] = 1;		// starting from last element, 1 element is longest in it's own
		for (int i = nums.length - 2; i >= 0; i--) {
			dp[i] = 1;					// the current element is a longes LIS in it's own
			for (int j = i + 1; j < nums.length; j++) {
				if (nums[i] < nums[j])
					dp[i] = Math.max(dp[i], 1 + dp[j]);		// find max LIS, if exists afterwards
			}
		}
		return Arrays.stream(dp).max().getAsInt();
	}

	/*
	 * Top-Down / Memoization
	 * TC: O(n²)
	 * SC: O(n)
	 */
	private int[] memo;
	public int lengthOfLIS2(int[] nums) {
		memo = new int[nums.length];
		Arrays.fill(memo, -1);
		int ans = 0;
		for (int i = 0; i < nums.length; i++) {
			ans = Math.max(ans, LISHelper(nums, i));	// subsequence starting from each index
		}
		return ans;
	}
	private int LISHelper(int[] nums, int i) {
		if (memo[i] != -1)
			return memo[i];
		int maxLen = 1;
		for (int j = i + 1; j < nums.length; j++) {
			if (nums[j] > nums[i])
				maxLen = Math.max(maxLen, 1 + LISHelper(nums, j));		// find next element of the subsequence if current element is greater than previous one
		}
		return memo[i] = maxLen;		// memoize
	}
}