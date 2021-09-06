package leetcode;


import java.util.*;

class MinStack {

    static class stkNode {
        int val, min;
        stkNode prev;

        public stkNode(int val, int min, stkNode prev) {
            this.val = val;
            this.min = min;
            this.prev = prev;
        }
    }
    public MinStack() {

    }

    public void push(int val) {

    }

    public void pop() {

    }

    public int top() {

    }

    public int getMin() {

    }
}

/**
 * Time Complexity:
 *     push() : O(1)
 *     pop() : O(1)
 * Space Complexity: O(2 * n)
 */
class MinStack1 {
    Stack<Integer> stk;
    Stack<Integer> minStk;

    public MinStack1() {
        stk = new Stack<>();
        minStk = new Stack<>();
    }

    public void push(int val) {
        stk.push(val);
        if (minStk.isEmpty()) {
            minStk.push(val);
        } else {
            int min = minStk.peek();
            minStk.push(Math.min(min, val));
        }
    }

    public void pop() {
        stk.pop();
        minStk.pop();
    }

    public int top() {
        return stk.peek();
    }

    public int getMin() {
        return minStk.peek();
    }
}
