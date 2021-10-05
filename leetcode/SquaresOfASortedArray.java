package leetcode;

import java.util.Arrays;

/**
 * Created at : 30/09/21
 * <p>
 * <a href=https://leetcode.com/problems/squares-of-a-sorted-array/>977. Squares of a Sorted Array</a>
 *
 * @author Himanshu Shekhar
 */

public class SquaresOfASortedArray {
    /**
     * Since given array "nums" is sorted, hence squared array
     * will be first non-increasing and then non-decreasing
     * <p>Time Complexity: O(N) : 1 ms
     * <br>Space Complexity: O(N)
     */
    public int[] sortedSquares(int[] nums) {
        for(int i = 0; i < nums.length; i++)
            nums[i] = nums[i] * nums[i];
        // start putting larger values from end of resultant array
        int[] res = new int[nums.length];
        int l = 0, r = nums.length-1, i = nums.length-1;
        while (l <= r) {
            res[i--] = (nums[l] <= nums[r]) ? nums[r--] : nums[l++];
        }
        return res;
    }
    /**
     * <p>Time Complexity: O(N logN) : 3 ms
     * <br>Space Complexity: O(1)
     */
    public int[] sortedSquares1(int[] nums) {
        for(int i = 0; i < nums.length; i++)
            nums[i] = nums[i] * nums[i];
        Arrays.sort(nums);
        return nums;
    }
}
