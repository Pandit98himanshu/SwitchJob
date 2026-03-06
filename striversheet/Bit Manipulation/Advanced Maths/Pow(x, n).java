/**
 * 50. Pow(x, n)
 *  https://leetcode.com/problems/powx-n/
 */

class Solution {
	/*
	 * TC: O(log n)
	 * SC: O(1)
	 */
	public double myPow(double x, int n) {
		// handle edge-cases
		if (x == 1d)
			return 1d;
		if (x == -1d)
			return (n & 1) == 1 ? x : 1d;
		if (n < (int)-1e5)
			return 0;

		// makes "n" always +ve
		if (n < 0) {
			x = 1 / x;
			n = -1 * n;
		}
		double ans = 1d;
		while (n > 0) {
			if ((n & 1) == 1) {
				ans = ans * x; // multiply with given no. when power is odd
				n--; // power reduced by 1
			} else {
				x = x * x; // multiply number itself when power is even
				n >>= 1; // power will be reduced by half
			}
		}
		// we don't need to multiply "ans" with "x" in the end
		// because "n" will always become 1 before 0 and,
		// "ans = ans * x" will always run before exiting the while loop
		return ans;
	}
}