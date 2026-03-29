import java.util.Arrays;

/**
 * 1143. Longest Common Subsequence
 * https://leetcode.com/problems/longest-common-subsequence/
 */

class Solution {
	/*
	 * Top-Down Approach
	 * TC: O(n²)
	 * SC: O(n²)
	 */
	public int longestCommonSubsequence(String text1, String text2) {
		int m = text1.length(), n = text2.length();
		int[][] dp = new int[m + 1][n + 1];
		// initialization
		for (int i = 0; i <= m; i++)
			dp[i][0] = 0;				// LCS when text1 is empty & len(text2) >= 0
		for (int j = 0; j <= n; j++)
			dp[0][j] = 0;				// LCS when text2 is empty & len(text1) >= 0

		for (int i = 1; i <= m; i++)
			for (int j = 1; j <= n; j++)
				if (text1.charAt(i - 1) == text2.charAt(j - 1))
					dp[i][j] = 1 + dp[i - 1][j - 1];		// LCS till previous index of both text1 & text2
				else
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
		return dp[m][n];
	}

	/*
	 * Bottom-Up Approach
	 * TC: O(n²)
	 * SC: O(n²)
	 */
	private static int[][] memo;
	public int longestCommonSubsequence1(String text1, String text2) {
		memo = new int[text1.length()][text2.length()];
		for(int[] row : memo)
			Arrays.fill(row, -1);
		return findLCS(text1, text2, text1.length() - 1, text2.length() - 1);
	}
	private int findLCS(String a, String b, int m, int n) {
		if (m < 0 || n < 0)
			return 0;

		if (memo[m][n] != -1)
			return memo[m][n];

		if (a.charAt(m) == b.charAt(n))
			return memo[m][n] = 1 + findLCS(a, b, m - 1, n - 1);
		else
			return memo[m][n] = Math.max(findLCS(a, b, m - 1, n), findLCS(a, b, m, n - 1));
	}
}