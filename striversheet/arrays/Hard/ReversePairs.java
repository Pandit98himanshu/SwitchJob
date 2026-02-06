/**
 * 493. Reverse Pairs
 * https://leetcode.com/problems/reverse-pairs/
 */

class Solution {
	/**
	 * TC: O((n + n)logn) = (n logn)
	 * SC: (n)
	 */
	public int reversePairs(int[] nums) {
		return mergeSort(nums, 0, nums.length - 1);
	}
	
	private int mergeSort(int[] nums, int l, int r) {
		if (l >= r) return 0;
		
		int mid = l + (r - l) / 2;
		int count = mergeSort(nums, l, mid) + mergeSort(nums, mid + 1, r);
		count += mergeAndCountReversePairs(nums, l, mid, r);
		return count;
	}
	
	private int mergeAndCountReversePairs(int[] nums, int l, int mid, int r) {
		// Count reverse pairs between left and right subarrays
		int count = 0;
		int i = l;
		int j = mid + 1;
		// for each "i" in left subarray find "j", which satisfies given cond.
		for (i = l; i <= mid; i++) {											//	⎤
			// Move j to the first position where nums[i] <= 2 * nums[j]		//	|
			while (j <= r && (long) nums[i] > 2L * (long) nums[j])				//	|	We can also use Binary Search since
				j++;															//	|	both left & right subarrays are sorted,
			// All ele from mid+1 to j-1 satisfy the cond with nums[i]			//	|	but it will increase the time-complexity
			count += j - (mid + 1);												//	|
		}																		//	⎦

		// usual merge-sort technique
		int[] temp = new int[r - l + 1];
		i = l;
		j = mid + 1;
		int k = 0;
		// put elements in sorted fashion.
		while (i <= mid && j <= r) {
			if (nums[i] <= nums[j]) {
				temp[k++] = nums[i++];
			} else {
				temp[k++] = nums[j++];
			}
		}
		// copy rest of the elements
		while (i <= mid)
			temp[k++] = nums[i++];
		while (j <= r)
			temp[k++] = nums[j++];
		
		// Copy merged array back to original array
		System.arraycopy(temp, 0, nums, l, temp.length);
		
		return count;
	}
}


public class ReversePairs {
	public static void main(String[] args) {
		int[] nums = {2147483647,2147483647,-2147483647,-2147483647,-2147483647,2147483647};
		// int[] nums = {2147483647,2147483647,2147483647,2147483647,2147483647,2147483647};

		System.out.println(new Solution().reversePairs(nums));
	}
}