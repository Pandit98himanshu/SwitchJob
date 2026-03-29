import java.util.Arrays;

/**
 * Longest Common Substring
 * https://www.geeksforgeeks.org/problems/longest-common-substring1452/1
 */

class Solution {
	/*
	 * Bottom-Up Approach
	 * TC: O(n²)
	 * SC: O(n²)
	 */
	public int longCommSubstr1(String s1, String s2) {
		int m = s1.length(), n = s2.length(), ans = 0;
		int[][] dp = new int[m + 1][n + 1];
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				} else {
					dp[i][j] = 0;
				}
				ans = Math.max(ans, dp[i][j]);
			}
		}
		return ans;
	}

	/*
	 * Top-Down Approach
	 * TC: O(n²)
	 * SC: O(n²)
	 */
	private static int[][] memo;
	private int maxLen;					// need a global variable for keeping track of longest length

	public int longCommSubstr(String s1, String s2) {
		maxLen = 0;
		memo = new int[s1.length()][s2.length()];
		for (int[] row : memo)
			Arrays.fill(row, -1);

		findLongestCommonSubstring(s1, s2, s1.length() - 1, s2.length() - 1);
		return maxLen;
	}

	private int findLongestCommonSubstring(String a, String b, int m, int n) {
		if (m < 0 || n < 0)				// no common substring if one of the string empty
			return 0;

		if (memo[m][n] != -1)
			return memo[m][n];

		int currLen = 0;
		if (a.charAt(m) == b.charAt(n)) {
			currLen = 1 + findLongestCommonSubstring(a, b, m - 1, n - 1);
			maxLen = Math.max(maxLen, currLen);
		}
		findLongestCommonSubstring(a, b, m, n - 1);
		findLongestCommonSubstring(a, b, m - 1, n);
		return memo[m][n] = currLen;
	}
}

public class LongestCommonSubstring {
	public static void main(String[] args) {
		String s1 = "KXCGMTMVVGFQQWSPD";
		String s2 = "JXZADDUKVLQPKUZJZHWSUTPCAFSYAIBJHAMMEGWBTPQELRNKBLDXGUZGCSEC";
		System.out.println(new Solution().longCommSubstr(s1, s2)); // Output: 2
		
		s1 = "LRBBMQBHCDARZOWKKYHIDDQSCDXRJMOWFRXSJYBLDBEFSARCBYNECDYGGXXPKLORELLNMPAPQFWKHOPKMCO";
		s2 = "QHNWNKUEWHSQMGBBUQCLJJIVSWMDKQTBXIXMVTRRBLJPTNSNFWZQFJMAFADRRWSOFSBCNUVQHFFBSAQXWPQCAC";
		System.out.println(new Solution().longCommSubstr(s1, s2));	// Output: 2
	}
}