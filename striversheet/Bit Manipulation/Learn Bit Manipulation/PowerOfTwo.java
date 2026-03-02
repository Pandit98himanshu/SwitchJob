/**
 * 231. Power of Two
 * https://leetcode.com/problems/power-of-two
 */

class Solution {
	/*
	 * TC: O(log n)
	 * SC: O(1)
	 */
	public boolean isPowerOfTwo(int n) {
		int countSetBit = 0;
		while (n > 0) {
			if ((n & 1) == 1) {		// check if least-significant bit is set of not
				countSetBit++;
			}
			n >>= 1;				// right-shift by 1 bit
		}
		return countSetBit == 1;	// if the no. is power of 2, only 1 bit should be set
	}
}