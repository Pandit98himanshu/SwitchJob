/**
 * Implement stack using array
 * https://www.geeksforgeeks.org/problems/implement-stack-using-array/1
 */

class myStack {
	private int[] stk;
	int size;
	public myStack(int n) {
		stk = new int[n];
		size = -1;
	}

	public boolean isEmpty() {
		return size < 0;
	}

	public boolean isFull() {
		return size == stk.length - 1;
	}

	public void push(int x) {
		if (!isFull())
			stk[++size] = x;
	}

	public void pop() {
		if (!isEmpty())
			size--;
	}

	public int peek() {
		if (!isEmpty())
			return stk[size];
		return -1;
	}
}