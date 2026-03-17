import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 239. Sliding Window Maximum
 * https://leetcode.com/problems/sliding-window-maximum/
 */

class Solution {
	/*
	 * TC: O(n)
	 * SC: O(k)
	 */
	public int[] maxSlidingWindow1(int[] nums, int k) {
		int ansLen = nums.length - k + 1;
		Deque<Integer> monoDq = new ArrayDeque<>(); 	// create a monotonic deque
		for (int i = 0; i < k; i++) {
			while (monoDq.size() > 0 && monoDq.peekFirst() < nums[i])
				monoDq.pollFirst();
			monoDq.offerFirst(nums[i]);
		}
		int[] ans = new int[ansLen];
		ans[0] = monoDq.peekLast(); 					// last element will always be maximum no. in the curr subarray
		for (int i = 1; i < ansLen; i++) {
			while (monoDq.size() > 0 && monoDq.peekFirst() < nums[i + k - 1])
				monoDq.pollFirst();
			monoDq.offerFirst(nums[i + k - 1]);
			if (monoDq.peekLast() == nums[i - 1]) 		// remove the element if it's out of current subarray window
				monoDq.pollLast();
			ans[i] = monoDq.peekLast();
		}
		return ans;
	}
	
	/*
	 * TC: O(n)
	 * SC: O(k)
	 * Small improvisation over "maxSlidingWindow1", makes it easy to read
	 */
	public int[] maxSlidingWindow(int[] nums, int k) {
		int[] ans = new int[nums.length - k + 1];
		Deque<Integer> monoDq = new ArrayDeque<>();
		for (int i = 0; i < nums.length; i++) {
			while (monoDq.size() > 0 && monoDq.peekFirst() < nums[i])
				monoDq.pollFirst();
			monoDq.offerFirst(nums[i]);
			if (i >= k && monoDq.peekLast() == nums[i - k])
				monoDq.pollLast();
			ans[i < k ? 0 : i - k + 1] = monoDq.peekLast();	// overwrite the answer for 1st subaray of length k
		}
		return ans;
	}
}