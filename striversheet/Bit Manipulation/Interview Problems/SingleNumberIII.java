/**
 * 260. Single Number III
 * https://leetcode.com/problems/single-number-iii
 */

// Abbreviations:
// lSB = left significant bits
// rSB = right significant bits

class Solution {
	private static final long minusInf = Long.MIN_VALUE;

	public int[] singleNumber(int[] nums) {
		// 1. get a ^ b
		int aXORb = nums[0];
		for (int i = 1; i < nums.length; i++) {
			aXORb ^= nums[i];
		}

		// 2. get lowest set bit
		/*
			The reason of getting lowest set bit is, we can distinguish b/w 2 group of nos.,
			[since both distinct nos. will differ by atleast 1 bit, i.e., 1 no. will have '0' & other have '1']
			one containing 1st distinct no. while the other group will contain 2nd distinct no.
			(a - 1)				-> makes all rSB "1" after first set bit, and leaves lSB as-it-is
			(a & (a - 1)) ^ a	-> gives lowest set bit
		*/
		int firstSetBit = (aXORb & (aXORb - 1)) ^ aXORb;	// can also use "x & ~(x-1)"
		
		// 3. use "concept of buckets"
		/* 
			All numbers having set-bit as firstSetBit falls in bucket 'a'
			while others will fall in bucket 'b'.
			Since there are only 2 distinct unique nos. & rest are in pair,
			the XOR of type 'a' = 1st dictinct no. & XOR of type 'b' = 2nd dictinct no.
		*/
		long a = minusInf, b = minusInf;
		for (int num : nums) {
			if ((num & firstSetBit) == 0) {
				a = (a == minusInf) ? num : a ^ num;
			} else {
				b = (b == minusInf) ? num : b ^ num;
			}
		}
		return new int[]{(int) a, (int) b};
	}
}