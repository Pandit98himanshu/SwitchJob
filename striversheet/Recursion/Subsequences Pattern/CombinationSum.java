import java.util.ArrayList;
import java.util.List;

/**
 * 39. Combination Sum
 * https://leetcode.com/problems/combination-sum/
 */

class Solution {
	/*
	 * TC: O(target * n^target)		-> generating combination sum (n^target) * copying to 'ans' (target)
	 * SC: O(target) 	-> max depth of recursive callstack = target
	 */
	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		return combiSumHelper(candidates, target, 0, 0, new ArrayList<>(), new ArrayList<>());
	}

	private List<List<Integer>> combiSumHelper(int[] candidates, int target, int currSum, int startingIndex,
			ArrayList<Integer> subList, List<List<Integer>> ans) {
		// we need to add below base case, since the next recursion 
		// again start from same index & will trap into infinite loop
		if (currSum > target)
			return ans;
		// Base case when condition is satisfied
		if (currSum == target) {
			ans.add(new ArrayList<>(subList));
			return ans;
		}
		// we need to start from 'startingIndex' because it will generate same sequence multiple time
		for (int i = startingIndex; i < candidates.length; i++) {
			subList.add(candidates[i]);				// add current element
			combiSumHelper(candidates, target, currSum + candidates[i], i, subList, ans);
			subList.remove(subList.size() - 1);		// remove current element
		}
		return ans;
	}
}