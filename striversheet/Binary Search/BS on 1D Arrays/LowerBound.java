/**
 * Implement Lower Bound
 * https://www.geeksforgeeks.org/problems/implement-lower-bound/1
 */

class Solution {
	int lowerBound(int[] arr, int target) {
		int l = 0, r = arr.length;
		while (l < r) {
			int mid = (l + r)/2;
			if (arr[mid] >= target) {
				r = mid;
			} else {
				l = mid + 1;
			}
		}
		return r;
	}
}

public class LowerBound {
	public static void main(String[] args) {
		int[] arr = {2, 3, 7, 10, 11, 11, 25};
		// int target = 9;	// output: 3
		// int target = 11;	// output: 4
		int target = 25;	// output: 6
		// int target = 100;	// output: 7
		System.out.println(new Solution().lowerBound(arr, target));
	}
}