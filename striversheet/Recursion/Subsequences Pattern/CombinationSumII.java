import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 40. Combination Sum II
 * https://leetcode.com/problems/combination-sum-ii/
 */

class Solution {
	/*
	 * TC & SC: Same as Combination Sum I
	 */
	public List<List<Integer>> combinationSum2(int[] candidates, int target) {
		Arrays.sort(candidates); // sorting array will help to remove duplicate subarrays
		return combiSumHelper(candidates, target, 0, 0, new ArrayList<>(), new ArrayList<>());
	}

	private List<List<Integer>> combiSumHelper(int[] candidates, int target, int currSum, int startingIndex,  ArrayList<Integer> subList, List<List<Integer>> ans) {
		if (currSum > target)
			return ans;
		if (currSum == target) {
			ans.add(new ArrayList<>(subList));
			return ans;
		}
		for (int i = startingIndex; i < candidates.length; i++) {
			// skipping 
			if (i > startingIndex && candidates[i] == candidates[i - 1])	// "i > 0" will not work
				continue;
			subList.add(candidates[i]);
			combiSumHelper(candidates, target, currSum + candidates[i], i + 1, subList, ans);
			subList.remove(subList.size() - 1);
		}
		return ans;
	}
}