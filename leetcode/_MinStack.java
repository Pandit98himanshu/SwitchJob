/*
 * 155. Min Stack
 * https://leetcode.com/problems/min-stack/
 */

package leetcode;

import java.util.*;

/**
 * <strong>NOT WORKING - </strong>
 * <strong>Don't work for large values</strong>
 * <pre>
 * Time Complexity:
 *     push() : O(1)
 *     pop() : O(1)
 *
 * Space Complexity: O(n)
 * </pre>
 */
class _MinStack {
    private final Stack<Integer> stk;
    private int minVal;

    public _MinStack() {
        stk = new Stack<>();
        this.minVal = Integer.MAX_VALUE;
    }

    public void push(int x) {
        if (minVal < x) {
            stk.push(x);
            return;
        }
        int tos = -2 * x - minVal;
        stk.push(tos);
        minVal = x;
    }

    public void pop() {
        int tos = stk.pop();
        if (tos < minVal)
            minVal = -(tos + 2 * minVal);
    }

    public int top() {
        int tos = stk.peek();
        if (tos > minVal)
            return tos;
        return minVal;
    }

    public int getMin() {
        return minVal;
    }
}

/**
 * <pre>
 * Time Complexity: Runtime 4 ms
 *     push() : O(1)
 *     pop() : O(1)
 * Space Complexity: O(2 * n)
 * </pre>
 */
class MinStack2 {
    static class stkNode {
        int val, min;
        stkNode prev;

        public stkNode(int val, int min, stkNode prev) {
            this.val = val;
            this.min = min;
            this.prev = prev;
        }
    }

    stkNode topOfStack;

    public MinStack2() {
        new stkNode(-1, Integer.MAX_VALUE, null);
    }

    public void push(int val) {
        stkNode newNode;
        int min;
        try {
            min = topOfStack.min;
        } catch (Exception ex) {
            min = Integer.MAX_VALUE;
        }
        if (min > val)
            newNode = new stkNode(val, val, topOfStack);
        else
            newNode = new stkNode(val, min, topOfStack);
        topOfStack = newNode;
    }

    public void pop() {
        topOfStack = topOfStack.prev;
    }

    public int top() {
        return topOfStack.val;
    }

    public int getMin() {
        return topOfStack.min;
    }
}

/**
 * <pre>
 * Time Complexity: Runtime 7 ms
 *     push() : O(1)
 *     pop() : O(1)
 * Space Complexity: O(2 * n)
 * </pre>
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
