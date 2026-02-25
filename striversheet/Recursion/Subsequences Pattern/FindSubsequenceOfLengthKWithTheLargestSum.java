import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 2099. Find Subsequence of Length K With the Largest Sum
 * https://leetcode.com/problems/find-subsequence-of-length-k-with-the-largest-sum/
 */

class Solution {
	/*
	 * TC: O(n log k + k) ≈ n log k
	 * SC: O(2*k)
	 * Runtime: 9ms | Beats 52.82%
	 * Intuition: Only maximum k values will have maximum sum
	 */
	public int[] maxSubsequence(int[] nums, int k) {
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < nums.length; i++) {
			pq.offer(nums[i]);				// put the current element into priority queue
			list.add(nums[i]);				// also put in the list for maitaining the order
			if (pq.size() > k) {
				list.remove(pq.poll());		// remove least value element from both pq & list
			}
		}
		int[] ans = new int[k];
		for (int i = 0; i < k; i++)
			ans[i] = list.get(i);			// format the output in appropriate return value
		return ans;
	}

	/*
	 * TC: O(n + 2n log n + k) ≈ n log n
	 * SC: O(n)
	 * Runtime: 7ms | Beats 90.49%
	 * Intuition: Only maximum k values will have maximum sum
	 */
	public int[] maxSubsequence2(int[] nums, int k) {
		int n = nums.length;
		int[][] numsIndex = new int[n][2];
		for (int i = 0; i < n; i++) {
			numsIndex[i][0] = nums[i];
			numsIndex[i][1] = i;
		}
		Arrays.sort(numsIndex, (a, b) -> a[0] - b[0]);		// sort all nums based on it's value
		Arrays.parallelSort(numsIndex, n - k, n, (a, b) -> a[1] - b[1]);	// sort only last k elements based on original index

		int[] ans = new int[k];
		for (int i = 0; i < k; i++) {
			ans[i] = numsIndex[n - k + i][0];				// format the output in appropriate return value
		}
		return ans;
	}

	/*
	 * TC: O(nCk)   // choosing 'k' elements out of 'n' elements
	 * Verdict: TLE
	 */
	private int maxSum = Integer.MIN_VALUE;

	public int[] maxSubsequence1(int[] nums, int k) {
		int[] ans = new int[k];
		maxSumSubseq(nums, k, 0, new LinkedList<>(), 0, ans);

		return ans;
	}

	private void maxSumSubseq(int[] nums, int k, int ind, LinkedList<Integer> curr, int currSum, int[] ans) {
		if (curr.size() > k)
			return;
		if (curr.size() == k && currSum > maxSum) {
			for (int i = 0; i < k; i++) {
				ans[i] = curr.get(i);		// store in appropriate return value format
			}
			maxSum = currSum;		// keep track of maximum sum value
			return;
		}
		for (int i = ind; i < nums.length; i++) {
			curr.addLast(nums[i]);
			maxSumSubseq(nums, k, i + 1, curr, currSum + nums[i], ans);
			curr.removeLast();
		}
	}
}