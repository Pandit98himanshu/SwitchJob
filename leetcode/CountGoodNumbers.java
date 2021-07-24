/*
 * 1922. Count Good Numbers
 * https://leetcode.com/problems/count-good-numbers/
 */
package leetcode;

import java.util.*;

class CountGoodNumbers {
	static class Solution {
		/**
		 * Iterative Function to calculate (x^y) % p in O(log y)
		 */
		public long power(long x, long y, long p) {
			long res = 1;

			x = x % p;
			if (x == 0)
				return 0;

			while (y > 0) {
				if ((y & 1) != 0)
					res = (res * x) % p;
				y >>= 1; // y = y/2
				x = (x * x) % p;
			}
			return res;
		}
		/**
		 *  @returns (a * b) % mod
		 */
		public int moduloMultiplication(long a, long b, long mod) {
			long res = 1;

			a %= mod;
			while (b > 0) {
				if ((b & 1) > 0)
					res = (res + a) % mod;
				b >>= 1; // b = b / 2
				a = (a + a) % mod;
			}
			return (int)res;
		}
		public int countGoodNumbers(long n) {
			Solution sol = new Solution();
			long evens, odds;
			if ((n % 2) == 0)
				evens = n/2;
			else
				evens = n/2 + 1;

			odds = n/2;

			long mod = 1000000007;
			long evensRes = 1, oddsRes = 1;

			evensRes = sol.power(5, evens, mod);
			if (odds > 0)
				oddsRes = sol.power(4, odds, mod);

			int res = 1;
			if (oddsRes > 0)
				res = sol.moduloMultiplication(evensRes, oddsRes, mod)-1;

			return res;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		long n = sc.nextLong();
		
		System.out.println(new Solution().countGoodNumbers(n));
	}
}