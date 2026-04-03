import java.util.Arrays;

/**
 * Frog jump with K distances
 * https://takeuforward.org/plus/dsa/problems/frog-jump-with-k-distances
 */

class Solution {
	/*
	 * Top-Down Approach
	 * TC: O(n * k)	-> check previous k steps for each step
	 * SC: O(n)		-> memoization
	 */
	private int[] memo;
	public int frogJump(int[] heights, int k) {
		memo = new int[heights.length];
		// initialization
		Arrays.fill(memo, -1);
		return calculateMinEnergy(heights, k, heights.length - 1);
	}
	private int calculateMinEnergy(int[] heights, int k, int n) {
		// needs ZERO energy when there are no stairs to jump
		if (n <= 0)
			return 0;
		// for only 2 steps, the energy is defined
		if (n == 1)
			return Math.abs(heights[n] - heights[n - 1]);
		// use memoization
		if (memo[n] != -1) return memo[n];

		int minEnergy = Integer.MAX_VALUE;
		for (int i = 1; i <= k; i++) {
			if (n - i < 0) break;
			// calculate energy required for jumping to step "n" from step "n - i"; i ∈ [1, k]
			int energy = memo[n - i] != -1 ? memo[n - i] : calculateMinEnergy(heights, k, n - i);
			// keep track of minimum energy required
			minEnergy = Math.min(minEnergy, Math.abs(heights[n] - heights[n - i]) + energy);
		}
		// return min energy calculated for current step "n" and memoize
		return memo[n] = minEnergy;
	}
}