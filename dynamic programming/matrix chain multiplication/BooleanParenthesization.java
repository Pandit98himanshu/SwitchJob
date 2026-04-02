import java.util.Arrays;

/**
 * Boolean Parenthesization
 * https://www.geeksforgeeks.org/problems/boolean-parenthesization5610/1
 */

class Solution {
	/*
	 * TC: O(n⁴)
	 * SC: O(n²)	-> 3rd dimension is fixed = 2
	 */
	static int countWays(String s) {
		int[][][] dp = new int[s.length()][s.length()][2];
		for (int[][] matrix : dp) {
			for (int[] row : matrix) {
				Arrays.fill(row, -1);
			}
		}
		return countRecur(s, dp, 0, s.length() - 1, true);
	}
	static int countRecur(String s, int[][][] dp, int i, int j, boolean isTrue) {
		if (i > j)
			return 0;					// ZERO ways if there's no string
		if (i == j) {
			if (isTrue)
				return s.charAt(i) == 'T' ? 1 : 0;		// found a way to get TRUE
			else
				return s.charAt(i) == 'F' ? 1 : 0;		// found a way to get FALSE
		}
		// utilize memoization
		if (dp[i][j][isTrue ? 0 : 1] != -1)
			return dp[i][j][isTrue ? 0 : 1];

		int totalWays = 0;				// stores total # ways to get TRUE for expression for substring [i, j]
		for (int k = i + 1; k < j; k += 2) {
			int trueL = countRecur(s, dp, i, k - 1, true);		// ways to get TRUE in left-part
			int trueR = countRecur(s, dp, k + 1, j, true);		// ways to get TRUE in right-part
			int falseL = countRecur(s, dp, i, k - 1, false);	// ways to get FALSE in left-part
			int falseR = countRecur(s, dp, k + 1, j, false);	// ways to get FALSE in right-part
			
			// memoization
			dp[i][k - 1][0] = trueL;
			dp[k + 1][j][0] = trueR;
			dp[i][k - 1][1] = falseL;
			dp[k + 1][j][1] = falseR;
			
			switch(s.charAt(k)) {
				case '&' -> {
					if (isTrue)
						totalWays += trueL * trueR;
					else {
						totalWays += trueL * falseR + falseL * trueR + falseL * falseR;
					}
				}
				case '|' -> {
					if (isTrue)
						totalWays += trueL * falseR + falseL * trueR + trueL * trueR;
					else {
						totalWays += falseL * falseR;
					}
				}
				case '^' -> {
					if (isTrue)
						totalWays += trueL * falseR + falseL * trueR;
					else {
						totalWays += trueL * trueR + falseL * falseR;
					}
				}
			}
		}
		return dp[i][j][isTrue ? 0 : 1] = totalWays;
	}
}