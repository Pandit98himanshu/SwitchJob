import java.util.Stack;

/**
 * 55. Min Stack
 * https://leetcode.com/problems/min-stack/
 */

class MinStack {
	Stack<Integer> stk;
	int minVal;

	/*
	 * TC: O(n)
	 * SC: O(2*n)	-> when input nos. are pushed in decreasing order
	 */
	public MinStack() {
		stk = new Stack<>();
		minVal = Integer.MAX_VALUE;
	}
	
	public void push(int val) {
		if (val <= minVal) {
			stk.push(minVal);
			minVal = val;
		}
		stk.push(val);
	}
	
	public void pop() {
		if (minVal == stk.peek()) {
			stk.pop();
			minVal = stk.peek();
		}
		stk.pop();
	}
	
	public int top() {
		return stk.peek();
	}
	
	public int getMin() {
		return minVal;
	}
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */