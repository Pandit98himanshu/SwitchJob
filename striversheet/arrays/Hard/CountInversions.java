/**
 * Count Inversions
 * https://www.geeksforgeeks.org/problems/inversion-of-array-1587115620/1
 */

class Solution {
	/*
	 * TC: O(n logn)
	 * SC: O(n)
	 * Using Merge-Sort
	 */
	int inversionCount(int arr[]) {
		return mergeSort(arr, 0, arr.length - 1);
	}
	private int mergeSort(int[] arr, int l, int r) {
		int count = 0;
		if (l < r) {
			int mid = (l + r) / 2;
			count += mergeSort(arr, l, mid);
			count += mergeSort(arr, mid + 1, r);
			count += mergeAndCountInversion(arr, l, mid, r);
		}
		return count;
	}
	private int mergeAndCountInversion(int[] arr, int l, int mid, int r) {
		int n1 = mid - l + 1;
		int n2 = r - mid;

		// copy array elements into another array
		int[] arr1 = new int[n1];
		int[] arr2 = new int[n2];
		for (int i = 0; i < n1; i++)
			arr1[i] = arr[i + l];
		for (int i = 0; i < n2; i++)
			arr2[i] = arr[i + mid + 1];

		int count = 0;
		// merge both sorted arrays
		int i = 0, j = 0, k = l;
		while (i < n1 && j < n2) {
			if (arr1[i] <= arr2[j]) {
				arr[k++] = arr1[i++];
			} else {					// there's an inversion
				arr[k++] = arr2[j++];
				count += n1 - i;		// since arr1 is sorted, all ele ∈ (i, n1] is also greater than arr2[j]
			}
		}

		// copy remaining elements
		while (i < n1)
			arr[k++] = arr1[i++];
		while (j < n2)
			arr[k++] = arr2[j++];

		return count;
	}
}

public class CountInversions {
	public static void main(String[] args) {
		int[] arr = {2, 4, 1, 3, 5};
		System.out.println(new Solution().inversionCount(arr));
	}
}