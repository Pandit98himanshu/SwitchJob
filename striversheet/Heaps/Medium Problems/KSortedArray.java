import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * k sorted array
 * https://www.geeksforgeeks.org/problems/k-sorted-array1610/1
 */

class Solution {
	/*
	 * Using Min-Heap
	 * TC: O(n log n)
	 * SC: O(n)
	 */
	static String isKSortedArray(int arr[], int n, int k) {
		PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> (a[0] == b[0]) ? a[1] - b[1] : a[0] - b[0]);
		for (int i = 0; i < n; i++) {
			minHeap.offer(new int[]{arr[i], i});
		}
		int index = 0;
		while (!minHeap.isEmpty()) {
			int[] curr = minHeap.poll();
			if (Math.abs(curr[1] - index) > k)
				return "No";
			index++;
		}
		return "Yes";
	}
	/*
	 * Using Array-Sorting
	 * TC: O(n log n)
	 * SC: O(n)
	 */
	static String isKSortedArray1(int arr[], int n, int k) {
		int[][] sortedArr = new int[n][2];
		for (int i = 0; i < n; i++) {
			sortedArr[i][0] = arr[i];
			sortedArr[i][1] = i;
		}
		Arrays.sort(sortedArr, (int[] a, int[] b) -> {
					if (a[0] == b[0])
						return a[1] - b[1];
					else
						return a[0] - b[0];
				});
		for (int i = 0; i < n; i++) {
			if (Math.abs(sortedArr[i][1] - i) > k)
				return "No";
		}
		return "Yes";
	}
}