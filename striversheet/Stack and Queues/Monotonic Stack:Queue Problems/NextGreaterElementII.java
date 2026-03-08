import java.util.Arrays;
import java.util.Stack;

/**
 * 503. Next Greater Element II
 * https://leetcode.com/problems/next-greater-element-ii/
 */

class Solution {
	/*
	 * TC: O(n)
	 * SC: O(n)
	 * Runtime: 10ms
	 */
	public int[] nextGreaterElements(int[] nums) {
		int n = nums.length;
		int[] ans = new int[n];
		Stack<Integer> stk = new Stack<>();

		Arrays.fill(ans, -1);											// O(n)

		for (int i = 0; i < 2 * n; i++) {								// O(2n)
			while (!stk.isEmpty() && nums[stk.peek()] < nums[i % n])	// O(n)
				ans[stk.pop()] = nums[i % n];
			if (i < n)
				stk.push(i);
		}

		return ans;
	}
	/*
	 * TC: O(n)		-> improvisation over "nextGreaterElements1"
	 * SC: O(n)
	 * Runtime: 17ms
	 */
	public int[] nextGreaterElements2(int[] nums) {
		int[] ans = new int[nums.length];
		Stack<Integer> stk = new Stack<>();
		stk.push(nums[nums.length - 1]);
		ans[nums.length - 1] = -1;

		helper(nums, ans, stk, nums.length - 2);			// O(2n)
		helper(nums, ans, stk, nums.length - 1);			// O(2n)

		return ans;
	}
	private void helper(int[] nums, int[] ans, Stack<Integer> stk, int start) {
		for (int i = start; i >= 0; i--) {					// O(n)
			while (!stk.isEmpty() && stk.peek() <= nums[i])	// O(n)
				stk.pop();
			ans[i] = stk.isEmpty() ? -1 : stk.peek();
			stk.push(nums[i]);
		}
	}

	/*
	 * TC: O(n)
	 * SC: O(n)
	 * Runtime: 22ms
	 */
	public int[] nextGreaterElements1(int[] nums) {
		int[][] nextGreater = new int[2 * nums.length][2];
		for (int i = 0; i < nums.length; i++) {							// O(n)
			nextGreater[i][0] = nextGreater[i + nums.length][0] = nums[i];
		}
		Stack<Integer> stk = new Stack<>();
		stk.push(nextGreater[2 * nums.length - 1][0]);
		nextGreater[2 * nums.length - 1][1] = -1;
		for (int i = 2 * nums.length - 2; i >= 0; i--) {				// O(2n)
			while (!stk.isEmpty() && stk.peek() <= nextGreater[i][0])	// O(n)
				stk.pop();
			nextGreater[i][1] = stk.isEmpty() ? -1 : stk.peek();
			stk.push(nextGreater[i][0]);
		}
		int[] ans = new int[nums.length];
		for (int i = 0; i < nums.length; i++)
			ans[i] = nextGreater[i][1];
		return ans;
	}
}