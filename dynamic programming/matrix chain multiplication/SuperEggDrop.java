import java.util.Arrays;

/**
 * 887. Super Egg Drop
 * https://leetcode.com/problems/super-egg-drop/
 */

class Solution {
	/*
	 * Top-Down Approach
	 * TC: O(n³)	-> TLE
	 * SC: O(n²)
	 */
	public int superEggDrop1(int k, int n) {
		int[][] memo = new int[k + 1][n + 1];
		for (int[] row : memo)
			Arrays.fill(row, -1);
		return MCM(k, n, memo);
	}
	private int MCM(int e, int f, int[][] memo) {
		// there's only 1 floor
		if (f <= 1)
			return f;
		// continuously check floors starting from the 1st floor, since only 1 egg is provided
		if (e == 1)
			return f;
		// using memoization
		if (memo[e][f] != -1)
			return memo[e][f];

		int minTrials = Integer.MAX_VALUE;
		for (int k = 1; k <= f; k++) {
			int eggBreaks = memo[e - 1][k - 1] != -1 ? memo[e - 1][k - 1] : MCM(e - 1, k - 1, memo);
			int notBreak = memo[e][f - k] != -1 ? memo[e][f - k] : MCM(e, f - k, memo);
			minTrials = Math.min(minTrials, 1 + Math.max(eggBreaks, notBreak));
		}
		return memo[e][f] = minTrials;
	}
}