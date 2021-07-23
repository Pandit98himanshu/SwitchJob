/*
 * 15. 3Sum
 * https://leetcode.com/problems/3sum/
 */

package leetcode;

import java.util.*;

public class ThreeSum {
    static class Solution {
        /**
         * Copied from <a href=https://leetcode.com/problems/3sum/discuss/1182773/Java-Solution-with-HashSet>leetcode discuss</a>
         * @param nums an integer array
         * @return list of distinct triplets which adds up to zero
         */
        public List<List<Integer>> threeSum(int[] nums) {
            int n = nums.length;
            Set<List<Integer>> ans = new HashSet<>();
            Arrays.sort(nums);

            for (int i = 0; i < n - 2; i++) {
                // now converted to Two Sum problem(https://leetcode.com/problems/two-sum/)
                int l = i + 1, r = n - 1;
                while (l < r) {
                    int sum = nums[i] + nums[l] + nums[r];
                    if (sum == 0) {
                        List<Integer> list = Arrays.asList(nums[i], nums[l], nums[r]);
                        ans.add(list);
                        l++; r--;
                    }
                    else if (sum < 0)
                        l++;
                    else
                        r--;
                }
            }
            return new ArrayList<>(ans);
        }

        /**
         * 2nd own genuine attempt, <strong>but failed</strong>
         */
        public List<List<Integer>> threeSum2(int[] nums) {
            int n = nums.length;
            List<List<Integer>> ans = new ArrayList<>();
            HashSet<Integer> set = new HashSet<>();

            if (n < 3) {
                return ans;
            }
            for (int i : nums) {
                set.add(i);
            }

            for (int i : set) {
                for (int j : set) {
                    if (i == j) continue;
                    int complement = -1 * (i + j);
                    if (set.contains(complement) && (complement != i || complement != j)) {
                        List<Integer> triplet = Arrays.asList(i, j, complement);
                        ans.add(triplet);
                    }
                }
            }

            return ans;
        }

        /**
         * 1st own genuine attempt, <strong>but failed</strong>
         */
        public List<List<Integer>> threeSum1(int[] nums) {
            int n = nums.length;
            List<List<Integer>> ans = new ArrayList<>();
            HashMap<Integer, ArrayList<Integer>> visited = new HashMap<>();

            if (n <= 2) {
                return ans;
            }
            for (int i = 0; i < n; i++) {
                ArrayList<Integer> list = visited.get(nums[i]);
                if (list == null) {
                    list = new ArrayList<>();
                }
                list.add(i);
                visited.put(nums[i], list);
            }
            for (int i = 0; i < n - 1; i++) {
                for (int j = i + 1; j < n; j++) {
                    int complement = -1 * (nums[i] + nums[j]);
                    ArrayList<Integer> k = visited.get(complement);
                    if (k != null) {
                        for (int index : k) {
                            if (index > i && index > j) {
                                ArrayList<Integer> triplet = new ArrayList<>();
                                triplet.add(nums[i]);
                                triplet.add(nums[j]);
                                triplet.add(complement);

                                ans.add(triplet);
                            }
                        }
                    }
                }
            }

            return ans;
        }
    }

    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        System.out.println(new Solution().threeSum(nums));
    }
}
