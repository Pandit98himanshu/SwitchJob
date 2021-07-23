/*
 * 18. 4Sum
 * https://leetcode.com/problems/4sum/
 */

package leetcode;

import java.util.*;

public class FourSum {
    static class Solution {
        /**
         * Copied from <a href=https://leetcode.com/problems/4sum/solution/>leetcode solution</a>
         *
         * @return an array of all the unique quadruplets such that
         * {@code nums[a] + nums[b] + nums[c] + nums[d] == target}; where a, b, c, and d are distinct
         */
        public List<List<Integer>> fourSum(int[] nums, int target) {
            Arrays.sort(nums);
            return kSum(nums, 4, target, 0, nums.length);
        }

        /**
         * <p>{@code Time Complexity: O(n^(k-1)}</p>
         * <p>{@code Space Complexity: O(n)}</p>
         *
         * @param nums   integer array
         * @param k      numbers to be selected from {@code nums}
         * @param target needs to be achieved by adding k distinct numbers from {@code nums}
         * @param start  starting index
         * @param end    ending index
         * @return k distinct numbers which adds upto {@code target}
         */
        public List<List<Integer>> kSum(int[] nums, int k, int target, int start, int end) {
            List<List<Integer>> result = new ArrayList<>();
//            // edge cases
//            if (start == end || nums[start] * k > target || target > nums[end - 1] * k)
//                return result;
            if (k == 2)
                return twoSum(nums, target, start, end);

            for (int i = start; i < end; i++) {
                if (i == start || nums[i - 1] != nums[i]) {
                    for (List<Integer> subset : kSum(nums, k - 1, target - nums[i], i + 1, end)) {
                        result.add(new ArrayList<>(Arrays.asList(nums[i])));
                        result.get(result.size() - 1).addAll(subset);
                    }
                }
            }
            return result;
        }

        /**
         * Similar to {@link TwoSum.Solution#twoSum1}
         *
         * @param start lower bound of search range
         * @param end   upper bound of search range (here it is {@code nums.length}
         * @return unique pairs such that they add up to {@code target}
         */
        public List<List<Integer>> twoSum(int[] nums, int target, int start, int end) {
            List<List<Integer>> result = new ArrayList<>();
            int l = start, r = end - 1;
            while (l < r) {
                int sum = nums[l] + nums[r];
                // either current sum is less than target or duplicate pairs
                if (sum < target || (l > start && nums[l] == nums[l - 1]))
                    l++;
                    // either current sum is grater than target or duplicate pairs
                else if (sum > target || (r < end - 1 && nums[r] == nums[r + 1]))
                    r--;
                else {
                    result.add(Arrays.asList(nums[l], nums[r]));
                    l++;
                    r--;
                }
            }
            return result;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 0, -1, 0, -2, 2};
        int target = 0;
        System.out.println(new Solution().fourSum(nums, target));
    }
}
