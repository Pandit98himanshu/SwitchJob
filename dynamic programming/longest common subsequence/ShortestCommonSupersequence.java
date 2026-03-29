/**
 * 1092. Shortest Common Supersequence 
 * https://leetcode.com/problems/shortest-common-supersequence
 */

class Solution {
	public String shortestCommonSupersequence(String str1, String str2) {
		int[][] dp = LCSMatrix(str1, str2, str1.length(), str2.length());
		return getCommonSupersequence(str1, str2, str1.length(), str2.length(), dp);
	}

	/*
	 * Bottom-Up Approach
	 * TC: O(n²)
	 * SC: O(n²)
	 * Finding longest common subsequence
	 */
	private int[][] LCSMatrix(String a, String b, int n, int m) {
		int[][] dp = new int[n + 1][m + 1];
		// initialization not required, since java array initialized with zero
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
	 * Recursive Backtracking
	 * TC: O(n²)
	 * SC: O(n²)
	 * Reverse Engineering
	 */
	private String getCommonSupersequence(String a, String b, int i, int j, int[][] dp) {
		StringBuilder sb = new StringBuilder();
		while (i > 0 && j > 0) {
			if (a.charAt(i - 1) == b.charAt(j - 1)) {
				sb.append(a.charAt(i - 1)); 			// present in both "a" & "b", hence adding only once
				i--;
				j--;
			} else if (dp[i - 1][j] >= dp[i][j - 1]) {
				sb.append(a.charAt(i - 1)); 			// present in "a", but not in "b"
				i--;
			} else {
				sb.append(b.charAt(j - 1)); 			// present in "b", but not in "a"
				j--;
			}
		}
		// add rest of the elments of "a" or "b"
		while (i > 0) {
			sb.append(a.charAt(i - 1));
			i--;
		}
		while (j > 0) {
			sb.append(b.charAt(j - 1));
			j--;
		}
		return sb.reverse().toString();
	}
}