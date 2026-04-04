/**
 * 62. Unique Paths
 * https://leetcode.com/problems/unique-paths/
 */

class Solution {
	public int uniquePaths(int m, int n) {
		int h = n - 1, v = m - 1;
		// unique combinations of arranging h & v
		// (h + v)!/(h! * v!)
		return nCr(h, v);
	}
	private int nCr(int h, int v) {
		int n = h + v;
		int r = Math.min(h, v); // symmetry: C(n, r) == C(n, n-r)

		long res = 1;

		for (int i = 1; i <= r; i++) {
			res = res * (n - r + i) / i;
		}

		return (int) res;
	}
}