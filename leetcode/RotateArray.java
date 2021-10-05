package leetcode;

/**
 * Created at : 30/09/21
 * <p>
 * <a href=https://leetcode.com/problems/rotate-array/>189. Rotate Array</a>
 *
 * @author Himanshu Shekhar
 */

public class RotateArray {
    /**
     * <p>Time Complexity: O(N) : 1 ms
     * <br>Space Complexity: O(1)
     */
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;

        reverse(nums, 0, n - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
    }

    private void reverse(int[] arr, int start, int end) {
        while (start < end) {
            int temp = arr[end];
            arr[end] = arr[start];
            arr[start] = temp;
            start++;
            end--;
        }
    }

    /**
     * <p>Time Complexity: O(N) : 1 ms
     * <br>Space Complexity: O(N)
     */
    public void rotate1(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        if (n < 2 || k == 0)
            return;
        // create another array of same length
        int[] res = new int[n];
        // place all elements from n-k to n-1 at the front of the array
        for (int i = n - k; i < n; i++)
            res[i - (n - k)] = nums[i];
        // place elements from 0 to n-k-1 at the end of the array
        for (int i = 0; i < n - k; i++)
            res[i + k] = nums[i];

        System.arraycopy(res, 0, nums, 0, nums.length);
    }
}
