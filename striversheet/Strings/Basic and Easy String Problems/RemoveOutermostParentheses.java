/**
 * 1021. Remove Outermost Parentheses
 * https://leetcode.com/problems/remove-outermost-parentheses
 */

class Solution {
	/*
	 * TC: O(n)
	 * SC: O(n)
	 */
	public String removeOuterParentheses1(String s) {
		char[] sArr = s.toCharArray();
		int level = 0;
		sArr[0] = 0; 				// remove outer 'opening' parenthesis
		for (int i = 1; i < s.length(); i++) {
			if (sArr[i] == '(') 	// increase the level of paren
				level++;
			else if (sArr[i] == ')') // decrease the level of paren
				level--;
			if (level < 0) { 		// then there's extra 'closing' paren
				sArr[i] = 0; 		// remove current outer
				if (i < s.length() - 1)
					sArr[i + 1] = 0; // remove outer 'opening' paren for next set
				level = 0;
			}
		}
		StringBuilder sb = new StringBuilder();
		for (char c : sArr) {
			if (c != 0)
				sb.append((char) c);
		}
		return sb.toString();
	}
	/*
	 * TC: O(n)
	 * SC: O(1)
	 */
	public String removeOuterParentheses(String s) {
		int level = 0;
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < s.length(); i++) {
			char curr = s.charAt(i);
			if (curr == '(') {
				if (level > 0)			// we need to consider paren which has level > 0
					sb.append(curr);
				level++;
			}
			else if (curr == ')') {
				level--;
				if (level > 0)
					sb.append(curr);
			}
		}
		return sb.toString();
	}
}

public class RemoveOutermostParentheses {
	
}
