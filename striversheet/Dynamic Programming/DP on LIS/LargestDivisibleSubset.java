import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 368. Largest Divisible Subset
 * https://leetcode.com/problems/largest-divisible-subset/
 */

class Solution {
	/*
	 * Bottom-Up (LIS pattern)
	 * TC: O(n²)
	 * SC: O(n)
	 */
	public List<Integer> largestDivisibleSubset(int[] nums) {
		Arrays.sort(nums);							// need to find subset, hence sorting is allowed
		int[] dp = new int[nums.length];			// stores max length of divisible subset
		int[] prev = new int[nums.length];			// stores index of previous element
		int maxLen = 1, startingIndex = -1;
		for (int i = nums.length - 1; i >= 0; i--) {
			dp[i] = 1;								// each element is divisible by itself
			prev[i] = -1;							// no divisors
			for (int j = i + 1; j < nums.length; j++) {
				if (nums[j] % nums[i] == 0) {		// since array is sorted, nums[j] > nums[i]
					if (dp[i] < dp[j] + 1) {		// max(dp[i], 1 + dp[j])
						dp[i] = dp[j] + 1;
						prev[i] = j;				// update index of previous divisor
					}
				}
			}
			if (maxLen < dp[i]) {
				maxLen = dp[i];						// to update "startingIndex" we need "maxLen"
				startingIndex = i;					// stores the starting index for longest divisor subset
			}
		}

		List<Integer> finalAns = new ArrayList<>();
		while (startingIndex != -1) {				// iteratively add all divisible elements until we hit 1st element
			finalAns.add(nums[startingIndex]);
			startingIndex = prev[startingIndex];
		}
		return finalAns;
	}
	/*
	 * Top-Down (Memoization - LIS pattern)
	 * TC: O(n²)
	 * SC: O(n²)
	 */
	private static List<Integer>[] memo;
	public List<Integer> largestDivisibleSubset1(int[] nums) {
		Arrays.sort(nums);                  // sorting is important
		memo = new ArrayList[nums.length];

		List<Integer> finalAns = new ArrayList<>();
		for (int i = 0; i < nums.length; i++) {
			List<Integer> curr = LIS(nums, i);
			if (curr.size() > finalAns.size())
				finalAns = curr;
		}
		return finalAns;
	}
	private List<Integer> LIS(int[] nums, int i) {
		if (memo[i] != null)
			return memo[i];

		List<Integer> longest = new ArrayList<>();
		for (int j = i + 1; j < nums.length; j++) {
			if (nums[j] % nums[i] == 0) {       // nums[i] % nums[j] is not required, since array is sorted
				List<Integer> temp = LIS(nums, j);
				if (temp.size() > longest.size())
					longest = temp;
			}
		}

		List<Integer> retv = new ArrayList<>();
		retv.add(nums[i]);
		retv.addAll(longest);

		return memo[i] = retv;
	}

	/*
	 * Brute Force
	 * TC: O(n * 2ⁿ)
	 * SC: O(n) => Recursive stack
	 */
	List<Integer> ans;
	public List<Integer> largestDivisibleSubset2(int[] nums) {
		ans = new ArrayList<>();
		dfs(nums, 0, new ArrayList<>());
		return ans;
	}
	private void dfs(int[] nums, int i, List<Integer> tempAns) {
		if (i == nums.length) {
			if (ans.size() < tempAns.size())
				ans = new ArrayList<>(tempAns);
			return;
		}
		// check whether nums[i] satisfy the rule
		boolean isDivisible = true;
		for (int val : tempAns) {
			if (nums[i] % val != 0 && val % nums[i] != 0) {
				isDivisible = false;
				break;
			}
		}
		// inculsion
		if (isDivisible) {
			tempAns.add(nums[i]);
			dfs(nums, i + 1, tempAns);
			tempAns.remove(tempAns.size() - 1);
		}
		// exclusion
		dfs(nums, i + 1, tempAns);
	}
}