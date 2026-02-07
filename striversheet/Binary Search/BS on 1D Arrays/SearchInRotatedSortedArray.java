/**
 * 33. Search in Rotated Sorted Array
 * https://leetcode.com/problems/search-in-rotated-sorted-array/
 */

class Solution {
	/*
	 * TC: O(2*log n)
	 * SC: O(1)
	 */
	public static int search(int[] nums, int target) {
		int n = nums.length;
		if (nums[0] <= nums[n - 1])
			return binarySearch(nums, target, 0, n - 1);
		// find starting index using binary search      -> O(logn)
		int l = 0, r = n - 1;
		while (l < r) {
			int mid = (l + r)/2;
			if (nums[mid] < nums[mid + 1] && nums[mid] < nums[n - 1])
				r = mid;
			else
				l = mid + 1;
		}

		// System.out.println("starting index: " + r);
		// locate which subarray possibly contains the target   -> O(1)
		if (target >= nums[0] && target <= nums[r - 1]) {
			l = 0;
			r = r - 1;
		} else {
			l = r;
			r = n - 1;
		}
		// find the target using binary-search          -> O(logn)
		return binarySearch(nums, target, l, r);
	}
	private static int binarySearch(int[] nums, int target, int l, int r) {
		while (l < r) {
			int mid = (l + r)/2;
			if (nums[mid] >= target)
				r = mid;
			else
				l = mid + 1;
		}
		return nums[r] == target ? r : -1;
	}
}

public class SearchInRotatedSortedArray {
	public static void main(String[] args) {
		int[] nums = {1};
		int target = 1;
		System.out.println(new Solution().search(nums, target));
	}
}