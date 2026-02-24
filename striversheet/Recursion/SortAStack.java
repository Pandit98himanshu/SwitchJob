import java.util.Stack;

/**
 * Sort a stack
 * https://www.geeksforgeeks.org/problems/sort-a-stack/1
 */

class Solution {
	/*
	 * TC: O(n²)
	 * SC: O(n)
	 * Iterative approach
	 */
	public void sortStack1(Stack<Integer> stk) {
		Stack<Integer> tempStk = new Stack<>();

		while (!stk.isEmpty()) {
			do {
				tempStk.push(stk.pop());
			} while (!stk.isEmpty() && stk.peek() <= tempStk.peek());

			if (stk.isEmpty())
				break;

			int temp = stk.pop();

			while (!tempStk.isEmpty() && temp > tempStk.peek()) {
				stk.push(tempStk.pop());
			}
			tempStk.push(temp);
		}
		while (!tempStk.isEmpty())
			stk.push(tempStk.pop());
	}
	/*
	 * TC: O(n²)
	 * SC: O(n)
	 * Recursive approach
	 */
	public static void sortStack(Stack<Integer> st) {
		if (st.isEmpty()) return;

		int top = st.pop();		// take out top element of the stack
		sortStack(st);			// do above step recursively until the stack is empty
		insertSorted(st, top);	// insert the top element in sorted manner into the same stack
	}

	private static void insertSorted(Stack<Integer> st, int val) {
		if (st.isEmpty() || st.peek() <= val) {
			st.push(val);
			return;
		}

		int top = st.pop();		// take out top element of the stack 
		insertSorted(st, val);	// until the 'val' find it's correct place into the stack
		st.push(top);			// place current element
	}
}

public class SortAStack {
	
}
