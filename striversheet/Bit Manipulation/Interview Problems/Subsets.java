import java.util.ArrayList;
import java.util.List;

/**
 * 78. Subsets
 * https://leetcode.com/problems/subsets/
 */

class Solution {
	/*
	 * TC: O(n * 2ⁿ)	-> total subsets = 2ⁿ + iterate complete nums + copying to ans list.
	 * SC: O(2ⁿ)
	 */
	public List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> ans = new ArrayList<>();
		for (int mask = 0; mask < (1 << nums.length); mask++) {
			List<Integer> curr = new ArrayList<>();
			for (int i = 0; i < nums.length; i++)
				if ((mask & (1 << i)) != 0)
					curr.add(nums[i]);
			ans.add(curr);
		}
		return ans;
	}
}