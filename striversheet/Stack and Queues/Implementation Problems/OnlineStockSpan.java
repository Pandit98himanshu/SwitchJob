import java.util.Stack;

/**
 * 901. Online Stock Span
 * https://leetcode.com/problems/online-stock-span/
 */

/*
 * TC: O(n); n = # of next-calls
 * SC: O(n)
 * Using monotonic-stack
 */
class StockSpanner {
	Stack<int[]> stk;		// ArrayDeque will give better runtime performance
	int day;

	public StockSpanner() {
		this.stk = new Stack<>();
		this.day = 0;
	}
	
	public int next(int price) {
		int span = -1;
		day = day + 1;
		if (stk.isEmpty() || stk.peek()[0] > price) {			// when stock price is decreasing
			span = 1;
		} else {
			while (!stk.isEmpty() && stk.peek()[0] <= price)	// implementing monotonic-stack
				stk.pop();
			span = day - (stk.isEmpty() ? 0 : stk.peek()[1]);
		}
		stk.push(new int[]{price, day});
		return span;
	}
}