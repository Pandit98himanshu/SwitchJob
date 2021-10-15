package leetcode;

import java.util.*;

/**
 * Created at : 12/10/21
 * <p>
 * <a href=https://leetcode.com/problems/permutations-ii/>47. Permutations II</a>
 *
 * @see Permutations#permute(int[])
 * @author Himanshu Shekhar
 */

public class PermutationsII {
    private List<List<Integer>> ans;

    /**
     * <p>Time Complexity: O(N * N!)
     * <br>Space Complexity: O(N!)
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        // store freq of all elements into a hashmap
        HashMap<Integer, Integer> freq = new HashMap<Integer, Integer>();
        for(int i : nums)
            freq.put(i, freq.getOrDefault(i, 0) + 1);

        ans = new ArrayList<>();
        this.permute(freq, nums.length, new LinkedList<>());
        return ans;
    }
    private void permute(
            HashMap<Integer, Integer> freq,
            int n,
            LinkedList<Integer> curr) {
        // base case
        if (curr.size() == n) {
            ans.add(new LinkedList<>(curr));
            return;
        }
        // iterate over each unique value
        freq.forEach((num, count) -> {
            if (count != 0) {
                curr.addLast(num);      // add current value into hypothetical result
                freq.put(num, count-1); // remove the element

                permute(freq, n, curr); // add other elements

                freq.put(num, count);   // again add the element
                // and remove the element, so that
                // we'll add another element at this place
                curr.removeLast();
            }
        });
    }
}
