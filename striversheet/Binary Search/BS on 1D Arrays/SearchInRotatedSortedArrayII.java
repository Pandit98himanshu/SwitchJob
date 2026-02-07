/**
 * 81. Search in Rotated Sorted Array II
 * https://leetcode.com/problems/search-in-rotated-sorted-array-ii
 */

class Solution {
	/*
	 * TC: O(n + log n)
	 * SC: O(1)
	 */
	public boolean search(int[] nums, int target) {
		// find starting index in rotated sorted array
		// we have to use linear search here, since there are duplicates
		int startIndex = 1;
		while (startIndex < nums.length - 1) {
			if (nums[startIndex] < nums[startIndex - 1])
				break;
			startIndex++;
		}
		// find which subarray contains "target" value
		int l = 0, r = nums.length - 1;
		if (target >= nums[0] && target <= nums[startIndex - 1]) {
			l = 0;
			r = startIndex - 1;
		} else {
			l = startIndex;
			r = nums.length - 1;
		}
		return binarySearch(nums, target, l, r);
	}
	private boolean binarySearch(int[] nums, int target, int l, int r) {
		while(l < r) {
			int mid = (l + r)/2;
			if (nums[mid] >= target)
				r = mid;
			else
				l = mid + 1;
		}
		return nums[r] == target;
	}
}