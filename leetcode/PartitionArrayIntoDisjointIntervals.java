/*
 * 915. Partition Array into Disjoint Intervals
 * https://leetcode.com/problems/partition-array-into-disjoint-intervals/
 */

package leetcode;

import java.util.Arrays;

public class PartitionArrayIntoDisjointIntervals {
    static class Solution {

        /**
         * Copied from <a href=https://leetcode.com/problems/partition-array-into-disjoint-intervals/discuss/1355919/Java-2-easy-solutions-explained>leetcode discuss</a>
         */
        public int partitionDisjoint(int[] nums) {
            int max1 = nums[0], max2 = nums[0], ans = 0;
            for (int i = 1; i < nums.length; i++) {
                if (max1 > nums[i]) {
                    max1 = max2;
                    ans = i;
                } else {
                    max2 = Math.max(max2, nums[i]);
                }
            }
            return ans + 1;
        }

        /**
         * Similar to {@link leetcode.TrappingRainWater}
         *
         * @param nums integer array needs to be divided into 2 parts i.e., {@code left} and {@code right},
         *             where every element in {@code left} <= {@code right}, while keeping {@code left.length} minimum.
         * @return length of left after partitioning
         */
        public int partitionDisjoint1(int[] nums) {
            int n = nums.length;
            int[] maxLeftSwipe = new int[n];
            int[] minRightSwipe = new int[n];
            maxLeftSwipe[0] = nums[0];
            for (int i = 1; i < n; i++) {
                maxLeftSwipe[i] = Math.max(maxLeftSwipe[i - 1], nums[i]);
            }
            minRightSwipe[n - 1] = nums[n - 1];
            for (int i = n - 2; i >= 0; i--) {
                minRightSwipe[i] = Math.min(minRightSwipe[i + 1], nums[i]);
            }

/*
            // My Solution //
            /////////////////
            // NOT WORKING //
            /////////////////
            int l = 0, r = n - 1;
            while (l <= r && maxLeftSwipe[l] <= minRightSwipe[r]) {
                l++;
                r--;
            }
            r++;
*/
            // I WAS 98% CLOSER TO THE SOLUTION
            // copied from https://leetcode.com/problems/partition-array-into-disjoint-intervals/discuss/1355919/Java-2-easy-solutions-explained
            int i = 0;
            while (i < n - 1 && maxLeftSwipe[i] > minRightSwipe[i + 1]) {
                i++;
            }
            return i + 1;     // answer is in 1-indexing
        }

        /**
         * <strong>NOT WORKING</strong>
         */
        public int partitionDisjoint2(int[] nums) {
            int n = nums.length;
            int p = n - 1;
            while (p >= 0 && nums[p] >= nums[0]) {
                p--;
            }
            return p < 1 ? 1 : p + 1;
        }

        /**
         * <strong>NOT WORKING</strong>
         */
        public int partitionDisjoint3(int[] nums) {
            int n = nums.length;

            int[] maxLeftSwipe = new int[n];
            int[] minRightSwipe = new int[n];
            maxLeftSwipe[0] = nums[0];
            for (int i = 1; i < n; i++) {
                maxLeftSwipe[i] = Math.max(maxLeftSwipe[i - 1], nums[i]);
            }
            int min = nums[n - 1], minIndex = n - 1;
            minRightSwipe[n - 1] = nums[n - 1];
            for (int i = n - 2; i >= 0; i--) {
                minRightSwipe[i] = Math.min(minRightSwipe[i + 1], nums[i]);
                if (min >= nums[i]) {
                    min = nums[i];
                    minIndex = i;
                }
            }
            print(maxLeftSwipe);
            print(minRightSwipe);
            int ans = minIndex;
            for (int i = minIndex; i < n; i++) {
                if (nums[i] < maxLeftSwipe[i]) {
                    ans = i;
                }
            }
            return ans + 1;
        }
    }

    private static void print(Object... O) {
        System.out.println(Arrays.deepToString(O));
    }

    public static void main(String[] args) {
        int[] nums = {90, 47, 69, 10, 43, 92, 31, 73, 61, 97}; /*{32,57,24,19,0,24,49,67,87,87}; /*{26,51,40,58,42,76,30,48,79,91}; /* */

        System.out.println(new Solution().partitionDisjoint1(nums));
    }
}
