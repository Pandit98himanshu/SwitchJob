import java.util.ArrayList;
import java.util.List;

/**
 * 22. Generate Parentheses
 * https://leetcode.com/problems/generate-parentheses/
 */

class Solution {
	/*
	 * TC: O(2ⁿ)	-> each position has 2 options '(' or ')'
	 * SC: O(n)		-> only considering 'curr'
	 */
	public List<String> generateParenthesis(int n) {
		return generateParenthesisRecur(n, 0, 0, new StringBuilder(), new ArrayList<>());
	}
	private List<String> generateParenthesisRecur(int n, int open, int close, StringBuilder curr, List<String> ans) {
		if (curr.length() == 2 * n) {		// each parenthesis comes in pair of 2
			ans.add(curr.toString());
			return ans;
		}
		// append '('
		if (open < n) {
			curr.append('(');
			generateParenthesisRecur(n, open + 1, close, curr, ans);
			curr.deleteCharAt(curr.length() - 1);
		}
		// now append ')' if we have enough '('
		if (close < open) {
			curr.append(')');
			generateParenthesisRecur(n, open, close + 1, curr, ans);
			curr.deleteCharAt(curr.length() - 1);
		}
		return ans;
	}
}