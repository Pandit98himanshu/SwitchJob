/*
 * 90. Subsets II
 * https://leetcode.com/problems/subsets-ii/
 */
package leetcode;

import java.util.*;

public class SubsetsII {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        int n = nums.length;
        List<List<Integer>> powerSet = new ArrayList<>();

        for (int i = 0; i < (1 << n); i++) {
            List<Integer> set = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if (j > 0 && nums[j] == nums[j-1])
                    j++;
                else if ((i & (1 << j)) > 0)
                    set.add(nums[j]);
            }
            powerSet.add(set);
        }
        return powerSet;
    }
}
