/**
 * 1781. Sum of Beauty of All Substrings
 * https://leetcode.com/problems/sum-of-beauty-of-all-substrings/
 */

class Solution {
	/*
	 * TC: O(26 * n³) 
	 * SC: O(n)
	 */
	public int beautySum(String s) {
		int ans = 0;
		for (int i = 0; i < s.length(); i++) {
			int[] freq = new int[26];
			for (int j = i; j < s.length(); j++) {
				freq[(int) (s.charAt(j) - 'a')]++;		// store the freq of chars in current subarray
				int minFreq = Integer.MAX_VALUE, maxFreq  = Integer.MIN_VALUE;
				for (int f : freq) {
					if (f > 0 && minFreq > f)	// calculate minimum freq of characters if persent
						minFreq = f;
					if (f > 0 && maxFreq < f)	// calculate maximum freq of characters if persent in the subarray
						maxFreq = f;
				}
				// System.out.println(s.substring(i, j + 1) + " -> min : " + minFreq + ", max : " + maxFreq);
				ans += maxFreq - minFreq;		// calculate beauty of current substring
			}
		}
		return ans;
	}
}
