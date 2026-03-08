import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Infix to Postfix
 * https://www.geeksforgeeks.org/problems/infix-to-postfix-1587115620/1
 */

class Solution {
	private static final Map<Character, Integer> priorityMap;
	static {
		priorityMap = new HashMap<>();	// store the priority of operators
		priorityMap.put('^', 3);
		priorityMap.put('*', 2);
		priorityMap.put('/', 2);
		priorityMap.put('+', 1);
		priorityMap.put('-', 1);
	}

	public static String infixToPostfix(String s) {
		Stack<Character> stk = new Stack<>();
		StringBuilder ans = new StringBuilder();

		for (char c : s.toCharArray()) {
			if ((c >= '0' && c <= '9') || (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
				ans.append(c);
				continue;
			}
			if (c == '(') {
				stk.push(c);
				continue;
			}
			if (c == ')') {
				while (stk.peek() != '(')	// can add isEmpty() safe-check
					ans.append(stk.pop());
				stk.pop(); // remove '('
				continue;
			}
			while (!stk.isEmpty() && stk.peek() != '(') {
				char top = stk.peek();
				if (priorityMap.get(top) > priorityMap.get(c)
						|| (priorityMap.get(top).equals(priorityMap.get(c)) && c != '^'))
					ans.append(stk.pop());
				else
					break;
			}
			stk.push(c);
		}
		while (!stk.isEmpty())
			ans.append(stk.pop());
		return ans.toString();
	}
}

public class InfixToPostfix {
	public static void main(String[] args) {
		String infix = "k/(l-j*u/a)";
	}
}
