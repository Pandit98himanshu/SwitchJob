import java.util.PriorityQueue;

/**
 * 703. Kth Largest Element in a Stream
 * https://leetcode.com/problems/kth-largest-element-in-a-stream
 */

class KthLargest {
	private static PriorityQueue<Integer> minHeap;
	private static int k;

	/*
	 * TC: O(n log k)	-> processing only top "k" elements
	 * SC: O(k)			-> heap of size k
	 */
	public KthLargest(int k, int[] nums) {
		KthLargest.k = k;
		minHeap = new PriorityQueue<>();
		for (int num : nums) {
			minHeap.offer(num);
			if (minHeap.size() > KthLargest.k)
				minHeap.poll();
		}
	}

	/*
	 * TC: O(log k)
	 * SC: O(1)
	 */
	public int add(int val) {
		minHeap.offer(val);
		if (minHeap.size() > KthLargest.k)
			minHeap.poll();
		return minHeap.peek();
	}
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */