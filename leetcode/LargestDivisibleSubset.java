package leetcode;

import java.util.*;

/**
 * Created at : 04/01/22
 * <p>
 * <a href=https://leetcode.com/problems/largest-divisible-subset/>368. Largest Divisible Subset</a>
 * <br><br>
 * Variant of {@link LongestIncreasingSubsequence}
 * @author Himanshu Shekhar
 */

public class LargestDivisibleSubset {
    /**
     * <strong>Dynamic Programming</strong>
     * <p>Time Complexity: O(n log n + n<sup>2</sup> + n)
     * <br>Space Complexity: O(2 * n)
     */
    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);  // sort the given array so that all divisors are previous to current element
        int[] count = new int[nums.length];
        int[] pre = new int[nums.length];
        int maxCount = 0, prev = 0;

        for (int i = 0; i < nums.length; i++) {
            count[i] = 1;   // each element is divisible by itself
            pre[i] = -1;    // initially there is no previous divisor of current element
            for (int j = 0; j < i; j++) {
                // if current element is divisible by any previous element
                // then store maximum length of divisible subset in "count" array,
                // and store its index in "pre" array for current element
                if (nums[i] % nums[j] == 0) {
                    if (count[i] < count[j] + 1) {
                        count[i] = count[j] + 1;    // store maximum length of sub-array
                        pre[i] = j;     // store index of element from which current element is divisible
                    }
                }
            }
            // keep track of max length and it's index
            if (maxCount < count[i]) {
                maxCount = count[i];    // stores maximum-length-of-divisible-subset
                prev = i;               // stores it's index, such that we can track back and find the sequence
            }
        }
        // track-back and store result in "ans" list
        List<Integer> ans = new ArrayList<>();
        while (prev != -1) {
            ans.add(0, nums[prev]);
            prev = pre[prev];
        }
        return ans;
    }


    /**
     * <strong>Brute-force</strong>
     * <p>Time Complexity: O(n * 2<sup>n</sup>)
     */
    List<Integer> finalAns;
    int[] nums;

    public List<Integer> largestDivisibleSubset1(int[] nums) {
        this.nums = nums;
        finalAns = new ArrayList<>();

        recur(0, new LinkedList<>());
        return finalAns;
    }

    private void recur(int curr, LinkedList<Integer> ans) {
        // base-case
        if (curr == nums.length) {
            if (finalAns.size() <= ans.size()) {
                finalAns = new ArrayList<>(ans);
            }
            return;
        }

        // include current element
        boolean divisible = true;
        for (int i : ans) {
            if (nums[curr] % i != 0 && i % nums[curr] != 0) {
                divisible = false;
                break;
            }
        }
        if (divisible) {
            ans.add(nums[curr]);
            recur(curr + 1, ans);
            ans.removeLast();
        }

        // exclude current element
        recur(curr + 1, ans);
    }
}
