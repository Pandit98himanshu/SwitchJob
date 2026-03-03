/**
 * 2220. Minimum Bit Flips to Convert Number
 * https://leetcode.com/problems/minimum-bit-flips-to-convert-number/
 */

class Solution {
	/*
	 * TC: O(log(max(start, goal)))
	 * SC: O(1)
	 */
	public int minBitFlips(int start, int goal) {
		int count = 0;
		while (start > 0 || goal > 0) {
			count += (start & 1) ^ (goal & 1);	// take xor of least-significant bit
			start >>= 1;
			goal >>= 1;
		}
		return count;
	}
}