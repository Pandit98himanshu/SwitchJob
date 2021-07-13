/*
 * 1220. Count Vowels Permutation
 * https://leetcode.com/problems/count-vowels-permutation/
 */
package leetcode;

class CountVowelsPermutation {
	static class Solution {

		public int countVowelPermutation(int n) {
			int mod = 1000000007;
			int[][] dp = new int[n+1][5];

			for (int j = 0; j < 5; ++j) {
				dp[1][j] = 1;
			}
			for (int i = 2; i <= n; i++) {
				for (int j = 0; j < 5; j++) {
					if (j == 0)
						dp[i][j] = ((dp[i-1][1] + dp[i-1][2])%mod + dp[i-1][4])%mod;
					if (j == 1)
						dp[i][j] = (dp[i-1][0] + dp[i-1][2])%mod;
					if (j == 2)
						dp[i][j] = (dp[i-1][1] + dp[i-1][3])%mod;
					if (j == 3)
						dp[i][j] = dp[i-1][2]%mod;
					if (j == 4)
						dp[i][j] = (dp[i-1][2] + dp[i-1][3])%mod;
				}
			}

			int res = 0;
			for (int j = 0; j < 5; ++j) {
				res = (res + dp[n][j])%mod;
			}
			return res;
		}
	}

	public static void main(String[] args) {
		int n = 5;
		System.out.println(new Solution().countVowelPermutation(n));
	}
}