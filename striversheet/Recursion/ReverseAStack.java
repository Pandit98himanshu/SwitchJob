import java.util.Stack;

/**
 * Reverse a Stack
 * https://www.geeksforgeeks.org/problems/reverse-a-stack/1
 */


class Solution {
	/*
	 * TC: O(n^2)
	 * SC: O(n)     // Recursive stack
	 */
	public static void reverseStack(Stack<Integer> stk) {
		if (stk.isEmpty()) return;
		int top = stk.pop();		// take out top element from the stack
		reverseStack(stk);			// do it recursively
		insertReversed(stk, top);	// insert the element into the stack in reversed manner
	}
	private static void insertReversed(Stack<Integer> stk, int top) {
		if (stk.isEmpty())
			stk.push(top);			// put the element into the stack after taking out previous elements
		else {
			int temp = stk.pop();
			insertReversed(stk, top);
			stk.push(temp);			// put previously added element into the stack
		}
	}

	/*
	 * TC: O(n^2)
	 * SC: O(n)
	 * Iterative Approach
	 */
	public static void reverseStack1(Stack<Integer> stk) {
		Stack<Integer> tempStk = new Stack<>();
		for (int i = stk.size() - 1; i > 0; i--) {
			int top = stk.pop();
			for (int j = 0; j < i; j++)
				tempStk.push(stk.pop());
			stk.push(top);
			while (!tempStk.isEmpty())
				stk.push(tempStk.pop());
		}
	}
}
