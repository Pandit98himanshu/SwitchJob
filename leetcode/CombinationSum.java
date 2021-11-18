package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * <a href=https://leetcode.com/problems/combination-sum/>39. Combination Sum</a>
 *
 * @author Himanshu Shekhar
 */
// todo: solve using dynamic programming approach
public class CombinationSum {
    private final List<List<Integer>> ans = new ArrayList<>();

    /**
     * <strong>Backtracking</strong>
     * <p>Can also be solved by <strong>dynamic programming</strong> approach
     * similar to <strong>coin change</strong>, <strong>knapsack</strong>, etc.
     * Have a look at <a href=https://leetcode.com/problems/combination-sum/discuss/16509/Iterative-Java-DP-solution/219411>dynamic programming approach</a>
     * <p>Time Complexity: O(n<sup>n</sup>)
     * <br>Space Complexity: O(n<sup>n</sup>)
     *
     * @see <a href=https://leetcode.com/problems/combination-sum/discuss/16502/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partitioning)>leetcode discussion</a>
     */
    private void combinationSum(int[] candidates, int target, LinkedList<Integer> currList, int start) {
        // (edge + base) case
        if (target < 0)
            return;
        // base case
        if (target == 0) {
            ans.add(new ArrayList<>(currList));
            return;
        }
        // iterate over all array & look for solution
        for (int i = start; i < candidates.length; i++) {       // O(n)
            // add current element to our current hypothetical solution
            currList.add(candidates[i]);
            // we can use same element infinitely, therefore we are passing "i", not "i+1"
            combinationSum(candidates, target - candidates[i], currList, i);
            // we need to remove last element so that we can try diff element
            currList.removeLast();
        }
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);        // helps us to avoid duplicates
        combinationSum(candidates, target, new LinkedList<>(), 0);
        return ans;
    }
}
