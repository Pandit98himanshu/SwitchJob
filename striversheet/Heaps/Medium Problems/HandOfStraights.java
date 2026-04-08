import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 846. Hand of Straights
 * https://leetcode.com/problems/hand-of-straights
 */

class Solution {
	/*
	 * TC: O(n log n)
	 * SC: O(n)
	 */
	public boolean isNStraightHand(int[] hand, int groupSize) {
		if (hand.length % groupSize != 0)			// each group consists of "groupSize" cards
			return false;

		Map<Integer, Integer> freq = new HashMap<>();
		for (int num : hand)
			freq.put(num, freq.getOrDefault(num, 0) + 1);		// store frequency of all elements

		PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
		for (Map.Entry<Integer, Integer> e : freq.entrySet())
			minHeap.offer(new int[]{e.getKey(), e.getValue()});				// put {num, freq} into min-heap, sorted on num values

		while (!minHeap.isEmpty()) {
			int prevVal = -1;						// stores value of previous element
			int size = groupSize;					// all groups have size of "groupSize"
			List<int[]> tempList = new ArrayList<>();
			while (size-- > 0) {
				if (minHeap.isEmpty()) return false;	// not sufficient elements to form the group
				int[] curr = minHeap.poll();
				// if current element is starting element of not consecutive to previous element
				if (prevVal != -1 && curr[0] != prevVal + 1)
					return false;
				curr[1]--;							// reduce the frequency
				if (curr[1] != 0)
					tempList.add(curr);				// keep it away from "minHeap", to not disturb the consecutiveness
				prevVal = curr[0];					// current value will be previous of next element
			}
			for (int[] i : tempList)
				minHeap.offer(i);					// store all elements back into "minHeap" which was processed in previous group
		}
		return true;
	}
}