/*
 * 239. Sliding Window Maximum
 * https://leetcode.com/problems/sliding-window-maximum/
 */
package leetcode;

import java.util.*;

class _SlidingWindowMaximum {
    static class Solution {
        /**
         * <strong>Using Sliding Window Technique</strong>
         * <p>Time Complexity: O(n)
         *
         * @param nums
         * @param k
         * @return
         */
        public int[] maxSlidingWindow1(int[] nums, int k) {
            int n = nums.length;
            int[] maxNums = new int[n - k + 1];

            int max = Integer.MIN_VALUE, maxIndex = 0;
            for (int i = 0; i < k; ++i) {
                if (max < nums[i]) {
                    max = nums[i];
                    maxIndex = i;
                }
            }
            maxNums[0] = max;

            for (int i = k; i < n; i++) {

            }
            return maxNums;
        }

        /**
         * <strong>Using Heap (Priority Queue)</strong>
         * <p>Time Complexity: O(nklogk)
         *
         * @param
         * @return
         */
        public int[] maxSlidingWindow(int[] nums, int k) {
            int n = nums.length;
            PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder());
            int[] maxNums = new int[n - k + 1];

            for (int i = 0; i <= n; i++) {
                if (i >= k) {
                    maxNums[i - k] = pq.peek();
                    pq.remove(nums[i - k]);
                }
                if (i < n) {
                    pq.add(nums[i]);
                }
            }

            return maxNums;
        }
    }

    public static void main(String[] args) {
        int[] nums = {4, -2};
        int k = 2;

        int[] res = new Solution().maxSlidingWindow(nums, k);
        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i] + " ");
        }
    }
}