/*
 * 90. Subsets II
 * https://leetcode.com/problems/subsets-ii/
 */
package leetcode;

import java.util.*;

public class SubsetsII {
    /**
     * <strong>Backtracking</strong>
     * <p>Copied from <a href=https://leetcode.com/problems/subsets-ii/solution/>leetcode solution</a>
     */
    protected void subsetsWithDupUtil(int[] nums, List<List<Integer>> result, LinkedList<Integer> currSubset, int index) {
        result.add(new ArrayList<>(currSubset));

        for (int i = index; i < nums.length; i++) {
            // ignore duplicates
            if (i != index && nums[i] == nums[i - 1])
                continue;
            currSubset.add(nums[i]);
            subsetsWithDupUtil(nums, result, currSubset, i + 1);
            currSubset.removeLast();
        }
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);      // sort to avoid duplicates
        List<List<Integer>> result = new ArrayList<>();
        LinkedList<Integer> currSubset = new LinkedList<>();

        subsetsWithDupUtil(nums, result, currSubset, 0);
        return result;
    }

    /**
     * <strong>Bit-masking</strong>
     * <p>Time Complexity: O(n*2<sup>n</sup>)
     * <br>Space Complexity: O(n*2<sup>n</sup>)
     *
     * @param nums an integer array
     * @return Power set of {@code nums}
     * @see <a href=https://www.geeksforgeeks.org/finding-all-subsets-of-a-given-set-in-java/>GfG article</a>,
     * <a href=https://leetcode.com/problems/subsets-ii/solution/>leetcode solution</a>
     */
    public List<List<Integer>> subsetsWithDup1(int[] nums) {
        List<List<Integer>> powerSet = new ArrayList<>();
        int n = nums.length;
        Arrays.sort(nums);                      // sort array to remove duplicates
        Set<String> seen = new HashSet<>();     // store calculated subsets

        for (int i = 0; i < (1 << n); i++) {
            List<Integer> currentSet = new ArrayList<>();
            StringBuilder subset = new StringBuilder();     // store string version of currentSet
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) > 0) {       // use bit-masking to generate subsets
                    currentSet.add(nums[j]);
                    subset.append(nums[j]).append(",");
                }
            }
            if (!seen.contains(subset.toString())) {
                seen.add(subset.toString());
                powerSet.add(currentSet);
            }
        }
        return powerSet;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2};
        System.out.println(new SubsetsII().subsetsWithDup(nums));
    }
}
