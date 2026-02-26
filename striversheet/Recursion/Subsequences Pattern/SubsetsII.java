import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 90. Subsets II
 *  https://leetcode.com/problems/subsets-ii/
 */

class Solution {
	/*
	 * TC: O(n * 2ⁿ)	-> iterating complete 'nums' array for all possible power-set
	 * SC: O(2ⁿ)		-> storing all power-set
	 * Iterative Bit-masking
	 */
	public List<List<Integer>> subsetsWithDup(int[] nums) {
		Arrays.sort(nums);
		List<List<Integer>> ans = new ArrayList<>();
		int n = nums.length;
		for (int mask = 0; mask < (1 << n); mask++) {
			List<Integer> curr = new ArrayList<>();
			boolean valid = true;
			for (int i = 0; i < n; i++) {
				if ((mask & (1 << i)) != 0) {
					if (i > 0 && nums[i] == nums[i - 1] && (mask & (1 << (i - 1))) == 0) {	// remove duplicates
						valid = false;
						break;
					}
					curr.add(nums[i]);
				}
			}
			if (valid)
				ans.add(curr);
		}
		return ans;
	}

	/*
	 * TC: O(n * 2ⁿ)	-> generating all power-set + copying the power set into 'ans' at each call
	 * SC: O(2ⁿ)		-> storing all power-set
	 * Recursive Approach
	 */
	public List<List<Integer>> subsetsWithDup1(int[] nums) {
		Arrays.sort(nums);      // will help in removing duplicate subsets
		return generatePowerSetRecur(nums, 0, new ArrayList<>(), new ArrayList<>());
	}
	private List<List<Integer>> generatePowerSetRecur(int[] nums, int ind, List<Integer> curr, List<List<Integer>> ans) {
		ans.add(new ArrayList<>(curr));
		for (int i = ind; i < nums.length; i++) {
			if (i > ind && nums[i] == nums[i - 1])
				continue;
			curr.add(nums[i]);
			generatePowerSetRecur(nums, i + 1, curr, ans);
			curr.remove(curr.size() - 1);
		}
		return ans;
	}
}