/**
 * Find Kth Rotation
 * https://www.geeksforgeeks.org/problems/rotation4723/1
 */

class Solution {
	public int findKRotation(int arr[]) {
		int l = 0, r = arr.length - 1;
		// check whether array is rotated
		if (arr[l] < arr[r])
			return 0;
		// find the starting index
		while (l < r) {
			int mid = (l + r)/2;
			if (arr[mid] < arr[arr.length - 1])
				r = mid;
			else
				l = mid + 1;
		}
		return r;
	}
}