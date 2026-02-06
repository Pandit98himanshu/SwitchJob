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
		if (l >= r) return 0;

		int mid = (l + r) / 2;
		int count = mergeSort(arr, l, mid) + mergeSort(arr, mid + 1, r);
		count += mergeAndCountInversion(arr, l, mid, r);
		return count;
	}
	private int mergeAndCountInversion(int[] arr, int l, int mid, int r) {
		int count = 0;
		int i = l, j = mid + 1, k = 0;
		int[] temp = new int[r - l + 1];
		// merge both sorted arrays
		while (i <= mid && j <= r) {
			if (arr[i] <= arr[j]) {
				temp[k++] = arr[i++];
			} else {					// there's an inversion
				temp[k++] = arr[j++];
				count += mid - i + 1;	// since arr1 is sorted, all ele ∈ (i, n1] is also greater than arr2[j]
			}
		}

		// copy remaining elements
		while (i <= mid)
			temp[k++] = arr[i++];
		while (j <= r)
			temp[k++] = arr[j++];
		System.arraycopy(temp, 0, arr, l, temp.length);
		return count;
	}
}

public class CountInversions {
	public static void main(String[] args) {
		int[] arr = {2, 4, 1, 3, 5};
		System.out.println(new Solution().inversionCount(arr));
	}
}