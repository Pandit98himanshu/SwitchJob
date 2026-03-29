/**
 * Print Longest Common Subsequence
 * https://www.naukri.com/code360/problems/print-longest-common-subsequence_8416383
 */

public class PrintLongestCommonSubsequence {
	public static String findLCS(int n, int m, String s1, String s2) {
		int[][] dp = LCS(s1, s2, n, m);
		return getLCSString(s1, s2, n, m, dp);
	}

	/*
	 * Bottom-Up Approach
	 * TC: O(n²)
	 * SC: O(n²)
	 */
	private static int[][] LCS(String a, String b, int n, int m) {
		int[][] dp = new int[n + 1][m + 1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				if (a.charAt(i - 1) == b.charAt(j - 1))
					dp[i][j] = 1 + dp[i - 1][j - 1];
				else
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
			}
		}
		return dp;
	}
	/*
	 * Reverse-Enginering
	 * TC: O(n²)
	 * SC: O(n²)
	 */
	private static String getLCSString(String a, String b, int n, int m, int[][] dp) {
		StringBuilder sb = new StringBuilder();
		while (n > 0 && m > 0) {
			if (a.charAt(n - 1) == b.charAt(m - 1)) {
				sb.append(a.charAt(n - 1));
				n--;
				m--;
			} else if (dp[n - 1][m] > dp[n][m - 1])
				n--;
			else
				m--;
		}
		return sb.reverse().toString();
	}
}