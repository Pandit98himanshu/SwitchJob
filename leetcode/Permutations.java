package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created at : 12/10/21
 * <p>
 * <a href=https://leetcode.com/problems/permutations/>46. Permutations</a>
 *
 * @author Himanshu Shekhar
 */

public class Permutations {
    private final List<List<Integer>> ans = new ArrayList<>();

    /**
     * <strong>Backtracking</strong>
     * <p>Can also be solved by <strong>dynamic programming</strong> approach
     * similar to <strong>coin change</strong>, <strong>knapsack</strong>, etc.
     *
     * <p>Time Complexity: O(n<sup>n</sup>)
     * <br>Space Complexity: O(n<sup>n</sup>)
     *
     * @see <a href=https://leetcode.com/problems/combination-sum/discuss/16502/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partitioning)>leetcode discussion</a>
     */
    private void permute(int[] nums, LinkedList<Integer> currList, boolean[] added) {
        if (currList.size() == nums.length) {
            ans.add(new ArrayList<>(currList));
            return;
        }
        // iterate over all array
        for (int i = 0; i < nums.length; i++) {
            if (added[i])
                continue;
            added[i] = true;
            // add current element to our current hypothetical solution
            currList.add(nums[i]);
            // we can use same element infinitely, therefore we are passing "i", not "i+1"
            permute(nums, currList, added);
            // we need to remove last element so that we can try diff element
            currList.removeLast();
            added[i] = false;
        }
    }

    public List<List<Integer>> permute(int[] nums) {
//        Arrays.sort(nums);        // helps us to avoid duplicates
        permute(nums, new LinkedList<>(), new boolean[nums.length]);
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        // total number of permutations = n!
        System.out.println(new Permutations().permute(nums));
    }
}
