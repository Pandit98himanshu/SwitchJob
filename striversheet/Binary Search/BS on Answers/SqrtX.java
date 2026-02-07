/**
 * 69. Sqrt(x)
 * https://leetcode.com/problems/sqrtx
 */

class Solution {
	public int mySqrt(int x) {
		if (x == 1) return x;
		int l = 0, r = x/2;
		while (l < r) {
			long mid = (long)r - (r - l)/2L;
			if (mid * mid > x)
				r = (int) mid - 1;
			else
				l = (int) mid;
		}
		return l;
	}
}

public class SqrtX {
	public static void main(String[] args) {
		System.out.println(new Solution().mySqrt(1));
		System.out.println(new Solution().mySqrt(6));
		System.out.println(new Solution().mySqrt(2147483647));
	}
}