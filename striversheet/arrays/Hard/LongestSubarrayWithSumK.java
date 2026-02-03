import java.util.HashMap;
import java.util.Map;

/*
 * Longest Subarray with Sum K
 * https://www.geeksforgeeks.org/problems/longest-sub-array-with-sum-k0809/1
 * This is a similar problem to 560. Subarray Sum Equals K - Leetcode
 * You need to store index of first occurrence of prefix sum instead of count
 */
class Solution {
	/*
	 * TC: O(n)
	 * SC: O(n)
	 */
	public int longestSubarray(int[] arr, int k) {
		int maxLen = 0, prefixSum = 0;
		Map<Integer, Integer> sumIndexMap = new HashMap<>();
		for (int i = 0; i < arr.length; i++) {
			prefixSum += arr[i];
			if (prefixSum == k) {
				maxLen = Math.max(maxLen, i + 1);
			}
			if (sumIndexMap.containsKey(prefixSum - k)) {
				int len = i - sumIndexMap.get(prefixSum - k);
				maxLen = Math.max(maxLen, len);
			}
			if (!sumIndexMap.containsKey(prefixSum))
				sumIndexMap.put(prefixSum, i);
		}
		return maxLen;
	}
}

public class LongestSubarrayWithSumK {
	public static void main(String[] args) {
		int[] arr = {10, 5, 2, 7, 1, -10};
		int k = 15;
		System.out.println(new Solution().longestSubarray(arr, k));
	}
}
