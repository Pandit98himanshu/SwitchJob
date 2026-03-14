import java.util.Stack;

/**
 * 735. Asteroid Collision
 * https://leetcode.com/problems/asteroid-collision
 */

class Solution {
	public int[] asteroidCollision(int[] asteroids) {
		/*
		 * TC: O(2 * n)
		 * SC: O(n)
		 */
		Stack<Integer> stk = new Stack<>();		// List implementation of stack will perform better at runtime
		for (int as : asteroids) {
			// destroy right moving asteroids which have lesser size than incoming one
			while (!stk.isEmpty() && stk.peek() > 0 && as < 0 && stk.peek() < -1 * as) {
				stk.pop();
			}
			// if the current asteroid is coming in left direction
			if (!stk.isEmpty() && as < 0) {
				if (stk.peek() > -1 * as)
					continue; // destroy current asteroid if it's size lesser than top one
				if (stk.peek() == -1 * as) { // destroy both the top & current asteroid
					stk.pop();
					continue;
				}
			}
			stk.push(as); // keep the asteroid
		}
		// format in return format
		int[] ans = new int[stk.size()];
		int k = 0;
		for (int as : stk)
			ans[k++] = as;
		return ans;
	}
}