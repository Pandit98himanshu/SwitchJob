/*
 * 1. Two Sum
 * https://leetcode.com/problems/two-sum/
 */
package leetcode;

import java.util.*;

class TwoSum {
    static class Solution {
        public int[] twoSum(int[] nums, int target) {
            int n = nums.length;

            int[][] dup = new int[n][2];
            for (int i = 0; i < n; i++) {
                dup[i][0] = nums[i];
                dup[i][1] = i;
            }

            Arrays.sort(dup, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return Integer.compare(o1[0], o2[0]);
                }
            });

            int i = 0, j = n-1;
            int[] ans = new int[2];
            while (i < j && i < n && j >= 0) {
                if ((dup[i][0] + dup[j][0]) == target) {
                    ans[0] = dup[i][1];
                    ans[1] = dup[j][1];
                    break;
                }
                else if ((dup[i][0] + dup[j][0]) < target) {
                    i++;
                }
                else {
                    j--;
                }
            }
            return ans;
        }
    }
    public static void main(String[] args) {
        int[] nums = {3,2,4};
        int target = 6;

        int[] ans = new Solution().twoSum(nums, target);
        System.out.println("[" + ans[0] + ", " + ans[1] + "]");

    }
}