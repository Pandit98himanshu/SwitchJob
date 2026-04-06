import java.util.PriorityQueue;

/**
 * 215. Kth Largest Element in an Array
 * https://leetcode.com/problems/kth-largest-element-in-an-array/
 */

class Solution {
	/*
	 * TC: O(n log k)
	 * SC: O(k)
	 */
	public int findKthLargest(int[] nums, int k) {
		PriorityQueue<Integer> minHeap = new PriorityQueue<>();		// stores largest k elements from "nums" array
		for (int num : nums) {
			minHeap.offer(num);
			if (minHeap.size() > k)			// remove min elements
				minHeap.poll();
		}
		return minHeap.peek();
	}
}