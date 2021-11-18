package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created at : 21/08/21
 * <p>
 * <a href=https://leetcode.com/problems/subsets/>78. Subsets</a>
 *
 * @author Himanshu Shekhar
 */

public class Subsets {
    private final List<List<Integer>> ans = new ArrayList<>();

    /**
     * <strong>Backtracking</strong>
     * <p>Can also be solved by <strong>dynamic programming</strong> approach
     * similar to <strong>coin change</strong>, <strong>knapsack</strong>, etc.
     *
     * <p>Time Complexity: O(2<sup>n</sup>)
     * <br>Space Complexity: O(2<sup>n</sup>)
     *
     * @see <a href=https://leetcode.com/problems/combination-sum/discuss/16502/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partitioning)>leetcode discussion</a>
     */
    private void subsets(int[] nums, LinkedList<Integer> currList, int start) {
        ans.add(new ArrayList<>(currList));
        // iterate over all array
        for (int i = start; i < nums.length; i++) {
            // add current element to our current hypothetical solution
            currList.add(nums[i]);
            // we can use same element infinitely, therefore we are passing "i", not "i+1"
            subsets(nums, currList, i + 1);
            // we need to remove last element so that we can try diff element
            currList.removeLast();
        }
    }

    public List<List<Integer>> subsets(int[] nums) {
//        Arrays.sort(nums);        // helps us to avoid duplicates
        subsets(nums, new LinkedList<>(), 0);
        System.out.println(ans.size());
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};
        // total number of subsets = 2^n
        System.out.println(new Subsets().subsets(nums));
    }
}
