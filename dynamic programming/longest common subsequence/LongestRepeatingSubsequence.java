import java.util.Arrays;

/**
 * Longest Repeating Subsequence
 * https://www.geeksforgeeks.org/problems/longest-repeating-subsequence2004/1
 */

class Solution {
	private static int[][] memo;

	/*
	 * Top-Down Approach (Memoization)
	 * TC: O(n²)
	 * SC: O(n²)
	 */
	public int LongestRepeatingSubsequence(String s) {
		// initialization
		memo = new int[s.length()][s.length()];
		for (int[] row : memo)
			Arrays.fill(row, -1);

		return LCS(s, s.length() - 1, s.length() - 1);
	}

	private int LCS(String s, int i, int j) {
		// base case
		if (i < 0 || j < 0)
			return 0;
		
		if (memo[i][j] != -1)
			return memo[i][j];

		if (s.charAt(i) == s.charAt(j) && i != j)		// character in same string but at different index
			return memo[i][j] = 1 + LCS(s, i - 1, j - 1);
		else
			return memo[i][j] = Math.max(LCS(s, i - 1, j), LCS(s, i, j - 1));
	}
}