import java.util.Arrays;

/**
 * Minimum number of deletions and insertions
 * https://www.geeksforgeeks.org/problems/minimum-number-of-deletions-and-insertions0209/1
 * OR,
 * 583. Delete Operation for Two Strings
 * https://leetcode.com/problems/delete-operation-for-two-strings/
 */

class Solution {
	private static int[][] memo;

	public int minOperations(String s1, String s2) {
		int n = s1.length(), m = s2.length();

		memo = new int[n][m];
		for (int[] row : memo)
			Arrays.fill(row, -1);

		int lcsLen = findLCSLen(s1, s2, n - 1, m - 1);				// elements common in both s1 & s2
		return n + m - 2 * lcsLen;			// we need to delete/insert characters which are not common in s1 & s2
	}

	private int findLCSLen(String a, String b, int n, int m) {
		if (n < 0 || m < 0)
			return 0;

		if (memo[n][m] != -1)
			return memo[n][m];
		
		if (a.charAt(n) == b.charAt(m))
			return memo[n][m] = 1 + findLCSLen(a, b, n - 1, m - 1);
		else
			return memo[n][m] = Math.max(findLCSLen(a, b, n - 1, m), findLCSLen(a, b, n, m - 1));
	}
}