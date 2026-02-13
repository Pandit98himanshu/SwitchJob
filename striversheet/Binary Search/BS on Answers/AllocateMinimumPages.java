import java.util.Arrays;

/**
 * Allocate Minimum Pages
 * https://www.geeksforgeeks.org/problems/allocate-minimum-number-of-pages0937/1
 */

class Solution {
	/*
	 * TC: O(n log n)
	 * SC: O(1)
	 */
	public int findPages(int[] arr, int k) {
		// we can't sort the arr, since we need to maintain order of the books
		if (k > arr.length)
			return -1;

		long l = 0;
		long r = Arrays.stream(arr).sum();

		while (l < r) {
			long mid = l + (r - l) / 2;
			if (canDistribute(arr, mid, k)) {
				r = mid;
			} else
				l = mid + 1;
		}

		return (int) l;
	}

	private boolean canDistribute(int[] arr, long maxPages, int k) {
		long currPages = 0;
		int studentsUsed = 1;

		for (int pages : arr) {
			if (currPages + pages > maxPages) {
				studentsUsed++;
				currPages = 0;
			}
			currPages += pages;

			if (studentsUsed > k || pages > maxPages)
				return false;
		}
		return true;
	}
}

public class AllocateMinimumPages {
	
}
