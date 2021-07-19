/*
 * 1918. Kth Smallest Subarray Sum
 * https://leetcode.com/problems/kth-smallest-subarray-sum/
 */

package leetcode;

public class KthSmallestSubarraySum {
    static public class Solution {
        /**
         * Copied from <a href=https://leetcode.com/problems/kth-smallest-subarray-sum/discuss/1333116/Binary-Search-with-sliding-window-O(N-log-(max-min))-and-O(1)-space>leetcode discuss</a>
         * <p>{@code Time Complexity: O(n*log(n^2)) = O(nlogn)}</p>
         * <p>{@code Space Complexity: O(1)}</p>
         *
         * @param nums an integer array
         * @param k
         * @return kth smallest subarray sum
         */
        public int kthSmallestSubarraySum(int[] nums, int k) {
            int l = 1, r = 20000 * 50000;   // lowest value is 1 and largest value is 10^9, look at the question
            while (l < r) {
                int m = (l + r) / 2;
                // if number of subarrays (with sum less than or equal to mid) are less than "k", we need to go right
                if (predicate(nums, m) < k)
                    l = m + 1;
                else
                    r = m;
            }
            return l;
        }

        /**
         *
         * @param nums a numbers array in which operation is to be performed
         * @param sum mid element
         * @return number of subarrays whose sum is less than or equal to given sum
         */
        private int predicate(int[] nums, int sum) {
            int subarraySum = 0, count = 0;
            for (int i = 0, j = 0; i < nums.length; ) {
                if (subarraySum + nums[i] <= sum) {
                    subarraySum += nums[i++];
                    count += i - j;
                } else {
                    subarraySum -= nums[j++];
                }
            }
            return count;
        }
    }

    public static void main(String[] args) {
        int[] nums = {2, 1, 3, 5, 4};
        int k = 12;
        System.out.println(new Solution().kthSmallestSubarraySum(nums, k));
    }
}
