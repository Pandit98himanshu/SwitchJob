import java.util.Stack;

/**
 * 20. Valid Parentheses
 * https://leetcode.com/problems/valid-parentheses/
 */

class Solution {
	/*
	 * TC: O(n)
	 * SC: O(n)
	 */
	public boolean isValid(String s) {
		Stack<Character> stk = new Stack<>();
		for (char c : s.toCharArray()) {
			if (c == '(' || c == '{' || c == '[') {
				stk.push(c);
				continue;
			}
			if (stk.isEmpty())
				return false;
			char top = stk.pop();
			if (c == ')' && top != '(')
				return false;
			if (c == '}' && top != '{')
				return false;
			if (c == ']' && top != '[')
				return false;
		}
		return stk.isEmpty();
	}
}