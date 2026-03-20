import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 1004. Max Consecutive Ones III
 * https://leetcode.com/problems/max-consecutive-ones-iii/
 */

class Solution {
	/*
	 * TC: O(n)
	 * SC: O(n)
	 */
	public int longestOnes(int[] nums, int k) {
		Queue<Integer> q = new ArrayDeque<>(); 				// store zeroes position, helps in moving window starting point
		int l = 0, zeroes = 0, ans = 0;
		for (int r = 0; r < nums.length; r++) {
			if (nums[r] == 0) {
				if (zeroes == k) { 							// can't add more zeroes to current window
					l = (q.isEmpty() ? r : q.poll()) + 1; 	// skip 1st zero since starting of current window
					zeroes--;
				}
				// handle edge-case
				if (k == 0)
					l = r + 1;
				zeroes++;
				q.offer(r); 								// update the queue with zero
			}
			ans = Math.max(ans, r - l + 1); 				// keep track of longest length of consecutive 1s
		}
		return ans;
	}
}