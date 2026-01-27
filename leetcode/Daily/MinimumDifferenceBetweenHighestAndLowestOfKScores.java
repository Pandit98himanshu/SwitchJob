package leetcode.Daily;

import java.util.Arrays;

/**
 * 1984. Minimum Difference Between Highest and Lowest of K Scores
 * https://leetcode.com/problems/minimum-difference-between-highest-and-lowest-of-k-scores/ 
 */

class Solution {
    public int minimumDifference(int[] nums, int k) {
        int n = nums.length;
        if (k <= 1 || n <= 1) return 0;
        Arrays.sort(nums);
        int minDiff = Integer.MAX_VALUE;
        for (int i = 0;  i < n; i++) {
            for (int j = i + k - 1; j < n; j++) {
                minDiff = Math.min(minDiff, Math.abs(nums[i] - nums[j]));
            }
        }
        return minDiff;
    }
}

public class MinimumDifferenceBetweenHighestAndLowestOfKScores {
	public static void main(String[] args) {
		int[] nums = {87063,61094,44530,21297,95857,93551,9918};
		int k = 6;
		int ans = 74560;
		System.out.println(new Solution().minimumDifference(nums, k));
	}
}
