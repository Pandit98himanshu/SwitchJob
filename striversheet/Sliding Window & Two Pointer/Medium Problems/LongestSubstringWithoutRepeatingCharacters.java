import java.util.Arrays;

/**
 * 3. Longest Substring Without Repeating Characters
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 */

class Solution {
	/*
	 * TC: O(n)
	 * SC: O(1)		-> storing the last occurance of all chars in the string
	 */
	public int lengthOfLongestSubstring(String s) {
		int l = 0, maxLen = 0;
		int[] lastOccurIndex = new int[256];
		Arrays.fill(lastOccurIndex, -1);				// mark all characters as not-visited
		for (int r = 0; r < s.length(); r++) {
			if (lastOccurIndex[s.charAt(r)] >= 0) {
				l = Math.max(l, lastOccurIndex[s.charAt(r)] + 1);	// move the window if it contains repeating character
			}
			maxLen = Math.max(maxLen, r - l + 1);		// capture max length of substring w/o repeating chars
			lastOccurIndex[s.charAt(r)] = r;			// store most recent occurance index
		}
		return maxLen;
	}
}