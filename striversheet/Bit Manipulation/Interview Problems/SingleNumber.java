/**
 * 136. Single Number
 * https://leetcode.com/problems/single-number/
 */

class Solution {
	/*
	 * TC: O(n)
	 * SC: O(1)
	 */
	public int singleNumber(int[] nums) {
		int singleOccur = 0;
		for (int i : nums)
			singleOccur ^= i;
		return singleOccur;
	}
}