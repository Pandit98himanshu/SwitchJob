/**
 * Number of occurrence
 * https://www.geeksforgeeks.org/problems/number-of-occurrence2259/1
 */

class Solution {
	int countFreq(int[] arr, int target) {
		// code here
		return upperBound(arr, target) - lowerBound(arr, target);
	}
	private int lowerBound(int[] arr, int target) {
		int l = 0, r = arr.length;
		while (l < r) {
			int mid = (l + r)/2;
			if (arr[mid] >= target)
				r = mid;
			else
				l = mid + 1;
		}
		return r;
	}
	private int upperBound(int[] arr, int target) {
		int l = 0, r = arr.length;
		while (l < r) {
			int mid = (l + r)/2;
			if (arr[mid] > target)
				r = mid;
			else
				l = mid + 1;
		}
		return r;
	}
}

public class NumberOfOccurrence {
	public static void main(String[] args) {
		int[] arr = {1, 1, 2, 2, 2, 2, 3};
		int target = 2;	// output: 4
		// int target = 4;	// output: 0
		System.out.println(new Solution().countFreq(arr, target));
	}
}