package leetcode;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created at : 02/01/22
 * <p>
 * <a href=https://leetcode.com/problems/longest-increasing-subsequence/>300. Longest Increasing Subsequence</a>
 * <br><br>
 * Other examples: {@link LargestDivisibleSubset}, {@link RussianDollEnvelopes}
 * @author Himanshu Shekhar
 */

public class LongestIncreasingSubsequence {
    /**
     * <strong>Dynamic Programming</strong>
     * <p>Copied from <a href=https://leetcode.com/problems/longest-increasing-subsequence/discuss/74824/JavaPython-Binary-search-O(nlogn)-time-with-explanation>leetcode discuss</a>
     *
     * <p>Time Complexity: O(n log n) : 9 ms
     * <br>Space Complexity: O(n)
     */
    public int lengthOfLIS(int[] nums) {
        int[] lis = new int[nums.length];
        int size = 0;

        // iterate each element in nums array
        for (int x : nums) {
            int i = 0, j = size;

            // use binary search to find the correct place for
            // current number "x" in increasing sequence found till now
            while (i != j) {
                int mid = (i + j) / 2;
                if (lis[mid] < x)
                    i = mid + 1;
                else
                    j = mid;
            }

            // put current number at its correct position
            lis[i] = x;
            if (i == size)
                size++; // increase size if we reached end if current sequence
        }
        return size;
    }

    /**
     * <strong>Dynamic Programming</strong>
     * <p>Time Complexity: O(n<sup>2</sup>) : 109 ms
     * <br>Space Complexity: O(n)
     */
    public int lengthOfLIS4(int[] nums) {
        int ans = 0;
        int dp[] = new int[nums.length];

        // add elements 1-by-1
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;  // each element is increasing sequence in itself

            // iterate all previous elements and check if current element
            // fits as increasing sequence...if it is, store max
            // of previous sequence's length + 1 & current value
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
                }
            }
            // keep track of maximum value
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }


    int[] nums;

    /**
     * <strong>Recursion with memoization</strong>
     * <p>Copied from <a href=https://leetcode.com/problems/longest-increasing-subsequence/discuss/988578/c++recursionmemoizationdynamic-programming>leetcode discuss</a>
     *
     * <p>Time Complexity: O(n<sup>3</sup>) : 502 ms
     * <br>Space Complexity: O(n<sup>2</sup>)
     */
    public int lengthOfLIS3(int[] nums) {
        this.nums = nums;

        int[][] memo = new int[nums.length + 1][nums.length + 1];
        // initialize memoized space with "-1"
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        // start with 1st element and end at last
        return memo(-1, 0, memo);
    }

    public int memo(int prev, int curr, int[][] memo) {
        // no element left to add
        if (curr == nums.length) {
            return 0;
        }
        // return memoized result
        if (prev >= 0 && memo[prev][curr] >= 0)
            return memo[prev][curr];

        int inc = 0, exc = 0;

        // include current element, only when last added
        // element is lesser than the current element
        if (prev == -1 || nums[prev] < nums[curr]) {
            inc = 1 + memo(curr, curr + 1, memo);
        }

        // exclude current element
        exc = memo(prev, curr + 1, memo);

        // return max length either by including or excluding current element
        if (prev != -1)
            return memo[prev][curr] = Math.max(inc, exc);
        else
            return Math.max(inc, exc);
    }

    /**
     * <strong>Recursion</strong>
     * <p>Time Complexity: O(2<sup>n</sup>)
     * <br>Space Complexity: O(n)
     */
    public int lengthOfLIS2(int[] nums) {
        this.nums = nums;
        // start with 1st element and end at last
        int[][] memo = new int[nums.length + 1][nums.length + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return recur(-1, 0);
    }

    public int recur(int prev, int curr) {
        // no element left to add
        if (curr == nums.length) {
            return 0;
        }

        int inc = 0, exc = 0;
        // include current element, only when last added
        // element is lesser than the current element
        if (prev == -1 || nums[prev] < nums[curr]) {
            inc = 1 + recur(curr, curr + 1);
        }
        // exclude current element
        exc = recur(prev, curr + 1);
        // return max length either by including or excluding current element
        return Math.max(inc, exc);
    }

    /**
     * <strong>Recursion</strong>
     * <p>Time Complexity: O(2<sup>n</sup>)
     * <br>Space Complexity: O(n)
     */
    public int lengthOfLIS1(int[] nums) {
        this.nums = nums;
        // start with 1st element and end at last
        return recur(0, new LinkedList<>());
    }

    public int recur(int i, LinkedList<Integer> ans) {
        // no element left to add
        if (i == nums.length) {
            return 0;
        }

        int inc = 0, exc = 0;
        // include current element, only when last added
        // element is lesser than the current element
        if (ans.size() == 0 || ans.getLast() < nums[i]) {
            ans.add(nums[i]);
            inc = 1 + recur(i + 1, ans);
            ans.removeLast();
        }
        // exclude current element
        exc = recur(i + 1, ans);
        // return max length either by including or excluding current element
        return Math.max(inc, exc);
    }
}
