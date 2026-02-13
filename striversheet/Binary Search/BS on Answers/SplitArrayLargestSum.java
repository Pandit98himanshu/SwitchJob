import java.util.Arrays;

/**
 * 410. Split Array Largest Sum
 * https://leetcode.com/problems/split-array-largest-sum/
 * It's same as Book allocation problem
 */

class Solution {
	public int splitArray(int[] nums, int k) {
		int l = 0, r = Arrays.stream(nums).sum();
		while (l < r) {
			int mid = l + (r - l) / 2;
			if (canSplit(nums, mid, k)) {
				r = mid;
			} else
				l = mid + 1;
		}
		return l;
	}

	private boolean canSplit(int[] nums, int maxSum, int k) {
		long currSum = 0;
		for (int num : nums) {
			currSum += num;
			if (currSum > maxSum) {
				k--;
				currSum = num;
			}
			if (k <= 0 || num > maxSum)
				return false;
		}
		return true;
	}
}

public class SplitArrayLargestSum {
	
}
