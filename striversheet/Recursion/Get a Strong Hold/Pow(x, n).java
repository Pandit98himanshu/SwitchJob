/**
 * 50. Pow(x, n)
 * https://leetcode.com/problems/powx-n
 */

class Solution {
	public double myPow(double x, int n) {
		if (x == 0d || x == 1d)
			return x;
		if (x == -1d)
			return n > 0 ? x : -1 * x;

		if (n < 0) {		// ⎤
			x = 1 / x;		// |- not required for powHelper1()
			n = -1 * n;		// ⎦
		}
		return powHelper(x, n, 1);
	}
	/*
	 * TC: O(log n)
	 * SC: O(log n)	-> recursive stack
	 */
	private double powHelper(double x, int n, double pow) {
		if (n == 0)
			return pow;
		if (n < -100000000 || n > 100000000) 		// handling cases like x = Integer.MAX_VALUE
			return 0d;
		if ((n & 1) == 1)
			return powHelper(x, n - 1, pow * x); 	// when power is odd, multiply normally
		return powHelper(x * x, n / 2, pow); 		// then power is even, multiply the powers
	}

	/*
	 * TC: O(xⁿ)
	 * SC: O(xⁿ)	-> recursive stack
	 */
	private double powHelper1(double x, int n, double pow) {
		if (n == 0)
			return pow;
		if (n < -100000000 || n > 100000000)
			return 0d;
		if (n > 0)
			return powHelper(x, n - 1, pow * x);
		else
			return powHelper(x, n + 1, pow * (1 / x));
	}
}