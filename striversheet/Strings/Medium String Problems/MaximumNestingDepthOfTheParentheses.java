/**
 * 1614. Maximum Nesting Depth of the Parentheses
 * https://leetcode.com/problems/maximum-nesting-depth-of-the-parentheses/
 */

class Solution {
	/*
	 * TC: O(n)
	 * SC: O(1)
	 */
	public int maxDepth(String s) {
		int maxDep = 0, depth = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(')		// increase depth when opening brace encountered
				depth++;
			if (s.charAt(i) == ')')		// decrease depth when closing brace encountered
				depth--;
			maxDep = Math.max(maxDep, depth);	// calculate the maxDepth
		}
		return maxDep;
	}
}
