/*
 * 611. Valid Triangle Number
 * https://leetcode.com/problems/valid-triangle-number/
 */

package leetcode;

import java.util.*;

public class _ValidTriangleNumber {
    static class Solution {
        /**
         * <strong>Brute Force</strong>
         * <p>{@code Time Complexity: O(n^3); where n = nums.length}</p>
         * <p>{@code Space Complexity: O(1)}</p>
         */
        public boolean isTriangle(int a, int b, int c) {
            return a + b > c && b + c > a && a + c > b;
        }

        public int triangleNumber1(int[] nums) {
            int n = nums.length, countTriplets = 0;
            if (n > 2)
                for (int i = 0; i < n - 2; i++)
                    for (int j = i + 1; j < n - 1; j++)
                        for (int k = j + 1; k < n; k++)
                            if (isTriangle(nums[i], nums[j], nums[k]))
                                countTriplets++;
            return countTriplets;
        }

        /**
         * <strong>More Efficient Approach</strong>
         * <p>{@code Time Complexity: O(n^2logn); where n = nums.length}</p>
         * <p>{@code Space Complexity: O(1)}</p>
         */
        public int lower_bound(int[] nums, int start, int end, int key) {
            int l = start, r = end - 1, result = -1, mid;
            while (l <= r) {
                mid = l + (r - l) / 2;
                if (mid < end && nums[mid] <= key) {
                    result = mid;
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
            return result;
        }

        public int triangleNumber(int[] nums) {
            int n = nums.length, countTriplets = 0;
            Arrays.sort(nums);
            if (n > 2) {
                for (int i = 0; i < n - 2; i++) {
                    for (int j = i + 1; j < n - 1; j++) {
                        int index = lower_bound(nums, j + 1, n, nums[i] + nums[j]);
                        if (nums[index] < nums[i] + nums[j])
                            countTriplets += index - j + 1;
                    }
                }
            }
            return countTriplets;
        }
    }

    public static void main(String[] args) {
        int[] nums = {4, 2, 3, 4};
        System.out.println(new Solution().triangleNumber(nums));
    }
}
