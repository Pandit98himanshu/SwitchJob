import java.util.Arrays;
/**
 * Next Permutation
 * https://leetcode.com/problems/next-permutation/
 */
class Solution {
    /*
     * Runtime: 2 ms
     * TC: O(n + n logn)
     * SC: O(1)
     */
    public void nextPermutation(int[] nums) {
        if (nums.length < 2)
            return;
        // finding pivot from end of the number
        // pivot = crater (whose both adjacent nos. are greater)
        int pivot = nums.length - 1;
        do {
            pivot--;
        } while (pivot >= 0 && nums[pivot] >= nums[pivot + 1]);

        if (pivot < 0) {
            // largest number, hence next permutation will be smallest number
            Arrays.sort(nums);
        } else {
            // find next greater digit than pivot
            int nextGreater = upper_bound(nums, pivot + 1, nums.length - 1, nums[pivot]);
            // replace pivot element with next greater digit
            if (nextGreater < 0) return;
            swap(nums, pivot, nextGreater);
            // sort all previously traversed digits in increasing order
            Arrays.sort(nums, pivot + 1, nums.length);
        }
    }
    private int upper_bound(int[] nums, int l, int r, int target) {
        int retv = -1;
        while (l <= r) {
            int mid = (l + r)/2;
            if (nums[mid] > target) {
                retv = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return retv;
    }
    private void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}

public class NextPermutation {
	public static void main(String[] args) {
		int[] nums = {5,1,1};
		// int[]nums = {5,4,7,5,3,2};
		new Solution().nextPermutation(nums);
		System.out.println(Arrays.toString(nums));
	}
}
