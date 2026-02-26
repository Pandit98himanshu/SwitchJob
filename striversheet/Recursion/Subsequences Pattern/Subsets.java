import java.util.ArrayList;
import java.util.List;

/**
 * 78. Subsets
 * https://leetcode.com/problems/subsets
 */

class Solution {
	/*
	 * TC: O(n * 2ⁿ)	-> total power set of 'n' nos.
	 * SC: O(2ⁿ)		-> storing all power set in 'ans'
	 * Using Bit-Masking
	 */
	public List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> ans = new ArrayList<>();

		int n = nums.length;
		int total = 1 << n;
		for (int mask = 0; mask < total; mask++) {
			List<Integer> curr = new ArrayList<>();		// allocating new memory addresses
			for (int i = 0; i < n; i++) {			// iterating 'nums' for each 2ⁿ masks
				if ((mask & (1 << i)) != 0) {
					curr.add(nums[i]);
				}
			}
			ans.add(curr);			// simply adding the reference, instead of deep-copying
		}
		return ans;
	}
	/*
	 * TC: O(n * 2ⁿ)	-> total power set of 'n' nos. * copying all arrays to 'ans' list
	 * SC: O(2ⁿ)		-> storing all power set in 'ans'
	 * Using Recursion
	 */
	public List<List<Integer>> subsets1(int[] nums) {
		return generatePowerSet(nums, 0, new ArrayList<>(), new ArrayList<>());
	}
	private List<List<Integer>> generatePowerSet(int[] nums, int ind, List<Integer> curr, List<List<Integer>> ans) {
		ans.add(new ArrayList<>(curr));		// copying into 'ans' list, since 'curr' list will get modified in recursive return call
		for (int i = ind; i < nums.length; i++) {
			curr.add(nums[i]);
			generatePowerSet(nums, i + 1, curr, ans);
			curr.remove(curr.size() - 1);
		}
		return ans;
	}
}