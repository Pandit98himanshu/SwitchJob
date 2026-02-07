/**
 * 162. Find Peak Element
 * https://leetcode.com/problems/find-peak-element
 */

class Solution {
	/*
	 * TC: O(log n)
	 * SC: O(1)
	 * find any peak element using binary search
	 */
	public int findPeakElement(int[] nums) {
		int l = 0, r = nums.length - 1;
		while (l < r) {
			int mid = (l + r)/2;
			if (nums[mid + 1] < nums[mid])
				r = mid;
			else
				l = mid + 1;
		}
		return r;
	}
}