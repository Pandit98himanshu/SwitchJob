/*
 * 1. Two Sum
 * https://leetcode.com/problems/two-sum/
 */
package leetcode;

import java.util.*;

class TwoSum {
    static class Solution {
        /**
         * <strong>Two pointer method</strong>
         * <p>{@code Time Complexity: O(nlogn)}</p>
         * <p>{@code Space Complexity: O(n)}</p>
         * @param nums integer array
         * @param target needs to be achieved by adding 2 integers from {@code nums}
         * @return indices of the two numbers such that they add up to {@code target}
         */
        public int[] twoSum1(int[] nums, int target) {
            int n = nums.length;
            // store nums in 1st column and its indices in 2nd column
            int[][] duplicateNums = new int[n][2];
            for (int i = 0; i < n; i++) {
                duplicateNums[i][0] = nums[i];
                duplicateNums[i][1] = i;
            }
            // sort "duplicateNums" array based on 1st column
            Arrays.sort(duplicateNums, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return Integer.compare(o1[0], o2[0]);
                }
            });

            int l = 0, r = n-1;
            int[] result = new int[2];         // store result
            while (l < r) {
                int sum = duplicateNums[l][0] + duplicateNums[r][0];
                if (sum < target)
                    l++;
                else if (sum > target)
                    r--;
                else {
                    result[0] = duplicateNums[l][1];
                    result[1] = duplicateNums[r][1];
                    break;
                }
            }
            return result;
        }
        /**
         * <strong>Using HashMap</strong>
         * <p>{@code Time Complexity: O(n)}</p>
         * <p>{@code Space Complexity: O(n)}</p>
         * @param nums integer array
         * @param target needs to be achieved by adding 2 integers from {@code nums}
         * @return indices of the two numbers such that they add up to {@code target}
         */
        public int[] twoSum(int[] nums, int target) {
            int n = nums.length;
            // store nums in 1st column and its indices in 2nd column
            HashMap<Integer, Integer> map = new HashMap<>();
            int[] result = new int[2];
            for (int i = 0; i < n; i++) {
                int complement = target - nums[i];
                if (map.containsKey(complement)) {
                    result[0] = i;
                    result[1] = map.get(complement);
                    break;
                }
                map.put(nums[i], i);
            }
            System.out.println(map);
            return result;
        }
    }
    private static void print(Object...O) {
        System.out.println(Arrays.deepToString(O));
    }
    public static void main(String[] args) {
        int[] nums = {3,2,4};
        int target = 6;

        print(new Solution().twoSum(nums, target));
    }
}