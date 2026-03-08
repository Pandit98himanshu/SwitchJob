import java.util.Stack;

/**
 * 496. Next Greater Element I
 * https://leetcode.com/problems/next-greater-element-i
 */

class Solution {
	public int[] nextGreaterElement(int[] q, int[] nums2) {
		int[] ans = new int[q.length];
		int n = nums2.length;
		Stack<Integer> stk = new Stack<>(); 			// monotonic increasing stack (top contains lesser value element)
		int[] nextGreaterMap = new int[(int) 1e4 + 1]; 	// can also use HashMap

		nextGreaterMap[nums2[n - 1]] = -1;				// no greater element after last element
		stk.push(nums2[n - 1]);
		for (int i = n - 2; i >= 0; i--) {
			while (!stk.isEmpty() && nums2[i] > stk.peek()) // remove smaller element from stack until greater element
				stk.pop();
			nextGreaterMap[nums2[i]] = stk.isEmpty() ? -1 : stk.peek();
			stk.push(nums2[i]);
		}
		// answer all queries
		for (int i = 0; i < q.length; i++)
			ans[i] = nextGreaterMap[q[i]];
		return ans;
	}
}