package striversheet.Arrays.Medium;

/*
 * https://leetcode.com/problems/maximum-subarray/
 */
class Solution {
	/*
	 * Divide & Conquer
	 * TC: O(n logn)
	 */
	public int maxSubArray(int[] nums) {
		return findMaxSubArray(nums, 0, nums.length - 1);
	}
	private int findMaxSubArray(int[] nums, int l, int r) {
		if (l == r)
			return nums[l];
		int mid = (l + r)/2;
		int maxSumLeft = findMaxSubArray(nums, l, mid);
		int maxSumRight = findMaxSubArray(nums, mid + 1, r);
		int maxSumCrossing = findMaxCrossingSubArray(nums, l, mid, r);

		return Math.max(Math.max(maxSumLeft, maxSumRight), maxSumCrossing);
	}

	private int findMaxCrossingSubArray(int[] nums, int l, int mid, int r) {
		int maxLeft = Integer.MIN_VALUE, maxRight = Integer.MIN_VALUE;
		int leftSum = 0, rightSum = 0;
		for (int i = mid; i >= l; i--) {
			leftSum += nums[i];
			maxLeft = Math.max(maxLeft, leftSum);
		}
		for (int i = mid + 1; i <= r; i++) {
			rightSum += nums[i];
			maxRight = Math.max(maxRight, rightSum);
		}
		return maxLeft + maxRight;
	}
	/*
	 * Kadane's algo.
	 * TC: O(n)
	 */
	public int maxSubArray(int[] nums) {
		int sumSoFar = Integer.MIN_VALUE, maxSum = Integer.MIN_VALUE;
		for (int i = 0; i < nums.length; i++) {
			if (sumSoFar < 0 && sumSoFar < nums[i]) {
				sumSoFar = nums[i];
			} else {
				sumSoFar += nums[i];
			}
			maxSum = Math.max(maxSum, sumSoFar);
		}
		return maxSum;
	}
}

public class MaximumSubarray {
	public static void main(String[] args) {
		int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
		System.out.println("Maximum Subarray Sum: " + new Solution().maxSubArray(nums));
	}
}
