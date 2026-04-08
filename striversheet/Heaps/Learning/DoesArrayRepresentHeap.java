/**
 * Does array represent Heap
 * https://www.geeksforgeeks.org/problems/does-array-represent-heap4345/1
 */

class Solution {
	/*
	 * TC: O(n)
	 * SC: O(1)
	 */
	public boolean countSub(long arr[], long n) {
		for (int i = 0; i < n; i++) {
			int leftChild = 2 * i + 1;
			int rightChild = 2 * i + 2;
			if ((leftChild < n && arr[leftChild] > arr[i]) || (rightChild < n && arr[rightChild] > arr[i]))
				return false;
		}
		return true;
	}
}