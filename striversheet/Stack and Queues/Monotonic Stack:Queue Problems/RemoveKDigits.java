import java.util.Stack;

/**
 * 402. Remove K Digits
 * https://leetcode.com/problems/remove-k-digits/
 */

class Solution {
	public String removeKdigits(String num, int k) {
		Stack<Character> stk = new Stack<>();
		for (char c : num.toCharArray()) {
			while (!stk.isEmpty() && stk.peek() > c & k > 0) {	// remove larger digits from start or, middle
				stk.pop();
				k--;
			}
			stk.push(c);
		}
		while (k-- > 0 && !stk.isEmpty()) {						// remove elements from end of the number
			stk.pop();
		}
		StringBuilder ans = new StringBuilder();
		for (char c : stk) {
			if (ans.length() == 0 && c == '0') continue;		// remove leading zeroes
			ans.append(c);
		}
		if (ans.length() == 0)  ans.append('0');
		return ans.toString();
	}
}