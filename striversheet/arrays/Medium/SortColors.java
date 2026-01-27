package striversheet.Arrays.Medium;

/*
 * https://leetcode.com/problems/sort-colors
 */
class Solution {
    // in-place sorting
    // quick-sort
    public void sortColors(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }
    private void quickSort(int[] nums, int l, int r) {
        if (l < r) {
            int pi = partition(nums, l, r);
            quickSort(nums, l, pi - 1);
            quickSort(nums, pi + 1, r);
        }
    }
    private int partition(int[] nums, int l, int r) {
        int pivot = nums[r];
        // move all elements smaller to pivot to left & greater to the right
        int i = l - 1;
        for (int j = l; j < r; j++) {
            if (nums[j] < pivot) {
                i++;
                swap(nums, i, j);
            }
        }
        swap(nums, i + 1, r);
        return i + 1;
    }
    private void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}

public class SortColors {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {2, 0, 2, 1, 1, 0};
        solution.sortColors(nums);
        // Output the sorted array
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }
}
