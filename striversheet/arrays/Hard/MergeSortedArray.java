package striversheet.Arrays.Hard;

/**
 * 88. Merge Sorted Array
 * https://leetcode.com/problems/merge-sorted-array/
 */
class Solution {
    /*
     * TC: O(m + n)
     * SC: O(1)
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // handle edge-cases
        if (n == 0) return;
        if (m == 0) {
            System.arraycopy(nums2, 0, nums1, 0, n);
            return;
        }
        // start putting elements in decreasing order at empty place (i.e., end of nums1)
        int k = m + n - 1;
        int i = m - 1, j = n - 1;
        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j])
                nums1[k--] = nums1[i--];
            else
                nums1[k--] = nums2[j--];
        }
        // copy rest of the elements form nums2
        while (j >= 0) {
            nums1[k--] = nums2[j--];
        }
    }
}

public class MergeSortedArray {
	public static void main(String[] args) {
		int[] nums1 = {1,2,3,0,0,0};
		int m = 3;
		int[] nums2 = {2,5,6};
		int n = 3;
		new Solution().merge(nums1, m, nums2, n);
		System.out.println(java.util.Arrays.toString(nums1));
	}
}
