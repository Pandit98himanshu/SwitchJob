import java.util.ArrayList;
import java.util.List;

/**
 * 216. Combination Sum III
 * https://leetcode.com/problems/combination-sum-iii/
 */

class Solution {
	/*
	 * TC: O(9!/(9−k)!k!) = O(9Ck)	-> choosing k elements out of 9
	 * SC: O(9)		-> max depth for each sublist [not considering 'ans']
	 */
	public List<List<Integer>> combinationSum3(int k, int n) {
		return combiSumRecur(k, n, 1, 0, 0, new ArrayList<>(), new ArrayList<>());
	}
	private List<List<Integer>> combiSumRecur(int k, int target, int currVal, int currSum, int nos, List<Integer> curr, List<List<Integer>> ans) {
		if (currSum > target || nos > k)
			return ans;
		if (currSum == target && nos == k) {
			ans.add(new ArrayList<>(curr));
			return ans;
		}
		for (int i = currVal; i < 10; i++) {	// generate nos. instead of iterating any given array
			curr.add(i);
			combiSumRecur(k, target, i + 1, currSum + i, nos + 1, curr, ans);
			curr.remove(curr.size() - 1);
		}
		return ans;
	}
}