import java.util.Arrays;

/**
 * 516. Longest Palindromic Subsequence
 * https://leetcode.com/problems/longest-palindromic-subsequence/
 */

class Solution {
	/*
	 * Top-Down Approach
	 * TC(average): O(n²/2)	-> LCS only till half of the string
	 * SC: O(n²)
	 * Runtime: 26ms
	 */
	private static int[][] memo;

	public int longestPalindromeSubseq(String s) {
		// initialization with -1
		memo = new int[s.length()][s.length()];
		for (int[] row : memo)
			Arrays.fill(row, -1);
		// finding LCS in string s & it's reversed version
		return LCS(s, s, 0, s.length() - 1);
	}

	private int LCS(String a, String b, int i, int j) {
		// base cases
		if (i > j)			// going till half of the string
			return 0;
		if (i == j)
			return 1;		// s[i] == s[j]; i == j

		if (memo[i][j] != -1)
			return memo[i][j];

		if (a.charAt(i) == b.charAt(j))
			return memo[i][j] = 2 + LCS(a, b, i + 1, j - 1);		// added "2" for both sides
		else
			return memo[i][j] = Math.max(LCS(a, b, i, j - 1), LCS(a, b, i + 1, j));
	}

	/*
	 * Bottom-Up Approach
	 * TC: O(n + n²)	-> reversing the string + LCS
	 * SC: O(n²)
	 * Runtime: 56ms
	 */
	public int longestPalindromeSubseq1(String s) {
		StringBuilder sb = new StringBuilder(s);
		String s_reverse = sb.reverse().toString();
		return findLCSLength(s, s_reverse, s.length(), s.length());
	}
	private int findLCSLength(String a, String b, int m, int n) {
		int[][] dp = new int[m + 1][n + 1];
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (a.charAt(i - 1) == b.charAt(j - 1))
					dp[i][j] = 1 + dp[i - 1][j - 1];
				else
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
			}
		}
		return dp[m][n];
	}
}