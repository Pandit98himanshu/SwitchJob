import java.util.Arrays;

/**
 * 1312. Minimum Insertion Steps to Make a String Palindrome
 * https://leetcode.com/problems/minimum-insertion-steps-to-make-a-string-palindrome/
 */

class Solution {
	private static int[][] memo;

	public int minInsertions(String s) {
		// initialization with -1
		memo = new int[s.length()][s.length()];
		for (int[] row : memo)
			Arrays.fill(row, -1);
		// finding LCS in string s & it's reversed version
		int lcsLen = LCS(s, s, 0, s.length() - 1);
		// total deletions/insertions will be single elements which doesn't participate in palindromic subsequence
		return s.length() - lcsLen;
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
}