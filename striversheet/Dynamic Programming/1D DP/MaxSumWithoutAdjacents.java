import java.util.Arrays;

/**
 * Max Sum without Adjacents
 * https://www.geeksforgeeks.org/problems/max-sum-without-adjacents2430/1
 * 
 * Similar to 198. House Robber [https://leetcode.com/problems/house-robber]
 */

class Solution {
	/*
	 * Top-Down Approach
	 * TC: O(n)
	 * SC: O(n)		-> memoization
	 */
	private int[] memo;
	int findMaxSum(int arr[]) {
		memo = new int[arr.length];
		Arrays.fill(memo, -1);
		return maxSumHelper(arr, arr.length - 1);
	}
	private int maxSumHelper(int[] arr, int i) {
		// max-sum will be ZERO if arr.length == 0
		if (i < 0)
			return 0;
		// use memoization
		if (memo[i] != -1) return memo[i];
		// if we exclude the current element, the sum will not increase
		// if we include the sum will be added with current element & we can't pick adjacent previous element
		return memo[i] = Math.max(maxSumHelper(arr, i - 1), arr[i] + maxSumHelper(arr, i - 2));
	}
}