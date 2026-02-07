/**
 * 35. Search Insert Position
 * https://leetcode.com/problems/search-insert-position
 */

class Solution {
	// find upper bound
	public int searchInsert(int[] nums, int target) {
		int l = 0, r = nums.length;
		while (l < r) {
			int mid = (l + r)/2;
			if (nums[mid] >= target)
				r = mid;
			else
				l = mid + 1;
		}
		return r;
	}
}

public class SearchInsertPosition {
	public static void main(String[] args) {
		int[] nums = {1, 3, 5, 6};
		int target = 5;	// output: 5
		System.out.println(new Solution().searchInsert(nums, target));
	}
}