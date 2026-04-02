import java.util.Arrays;

/**
 * 132. Palindrome Partitioning II
 *  https://leetcode.com/problems/palindrome-partitioning-ii
 */

class Solution {
	private static boolean[][] isPalindrome;

	// computes whether substring i -> j is a palindrome or not; i, j ∈ [0, n]
	private static void fillIsPalindrome(String s) {
		isPalindrome = new boolean[s.length()][s.length()];
		for (int i = s.length() - 1; i >= 0; i--) {
			for (int j = s.length() - 1; j >= i; j--) {
				if (s.charAt(i) == s.charAt(j)) {
					isPalindrome[i][j] = (j - i) <= 1 ? true : isPalindrome[i + 1][j - 1];
				}
			}
		}
	}

	/*
	 * Bottom-Up Approach
	 * TC: O(n²)
	 * SC: O(n)
	 */
	public int minCut(String s) {
		fillIsPalindrome(s);
		int[] dp = new int[s.length() + 1];

		for (int i = s.length() - 1; i >= 0; i--) {
			dp[i] = Integer.MAX_VALUE;
			for (int j = i; j < s.length(); j++) {
				if (isPalindrome[i][j]) {
					dp[i] = Math.min(dp[i], 1 + dp[j + 1]);
				}
			}
		}
		return dp[0] - 1;
	}

	/*
	 * Top-Down Approach (Brute-Force)
	 * TC: O(2ⁿ)
	 * SC: O(2ⁿ)	-> recursive stack
	 */
	public int minCut0(String s) {
		fillIsPalindrome(s);

		return minCutHelper(s, 0);
	}

	private int minCutHelper(String s, int i) {
		if (i == s.length())
			return 0;

		int minCost = Integer.MAX_VALUE;
		for (int j = i; j < s.length(); j++) {
			if (isPalindrome[i][j]) {
				int cost = 1 + minCutHelper(s, j + 1);		// make a cut at each index for substring (i, j)
				minCost = Math.min(minCost, cost);			// keep track of minimum-cut to satisfy the condition
			}
		}
		return minCost;
	}

	/*
	 * Bottom-Up Approach (MCM pattern)
	 * TC: O(n³)	-> TLE for larger strings
	 * SC: O(n²)
	 */
	public int minCut1(String s) {
		fillIsPalindrome(s);
		int[][] dp = new int[s.length()][s.length()];

		for (int len = 2; len <= s.length(); len++) {
			for (int i = 0, j = i + len - 1; j < s.length(); i++, j++) {
				if (isPalindrome[i][j]) {
					dp[i][j] = 0;							// needs zero cuts if substring (i, j) is a palindrome
				} else {
					dp[i][j] = Integer.MAX_VALUE;
					for (int k = i; k < j; k++) {
						dp[i][j] = Math.min(dp[i][j], 1 + dp[i][k] + dp[k + 1][j]);		// find min cuts required
					}
				}
			}
		}
		
		return dp[0][s.length() - 1];
	}

	/*
	 * Top-Down Approach (MCM pattern)
	 * TC: O(n³)	-> TLE for larger strings
	 * SC: O(n²)
	 */
	public int minCut2(String s) {
		fillIsPalindrome(s);
		int[][] memo = new int[s.length()][s.length()];
		for (int[] row : memo)
			Arrays.fill(row, -1);

		return MCM(s, 0, s.length() - 1, memo);
	}
	private int MCM(String s, int i, int j, int[][] memo) {
		// base case
		if (i >= j)
			return 0;
		if (isPalindrome[i][j])
			return 0;
		// use memoization
		if (memo[i][j] != -1)
			return memo[i][j];
		// recursive calls
		int min = Integer.MAX_VALUE;
		for (int k = i; k < j; k++) {
			int left = memo[i][k] != -1 ? memo[i][k] : MCM(s, i, k, memo);
			int right = memo[k + 1][j] != -1 ? memo[k + 1][j] : MCM(s, k + 1, j, memo);
			int cuts = left + right + 1;
			min = Math.min(min, cuts);
		}
		return memo[i][j] = min;
	}
}