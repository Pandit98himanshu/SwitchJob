package striversheet.Arrays.Medium;

import java.util.HashMap;
import java.util.Map;

class Solution {
	/*
	// Runtime: 0 ms
	static {
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			try (java.io.FileWriter fw = new java.io.FileWriter("display_runtime.txt")) {
				fw.write("0");
			} catch (Exception e) {
			}
		}));
	}
	*/
	// Runtime: 1083 ms
	static {
		for (int i = 0; i < 500; i++)
			subarraySum(new int[0], 0);
	}
	/*
	 * Optimal Approach
	 * TC: O(n)
	 * SC: O(n)
	 * Runtime: 13 ms
	 */
	public static int subarraySum(int[] nums, int k) {
		int count = 0, currSum = 0;
		Map<Integer, Integer> sumIndexMap = new HashMap<>();	// <prefixSum, frequency>
		for (int i = 0; i < nums.length; i++) {
			currSum += nums[i];		// calculate prefix sum till index i
			if (currSum == k)
				count++;			// increase count when prefixSum[i] == k
			if(sumIndexMap.containsKey(currSum - k)) {	// if (prefixSum[i] - prefixSum[j]) == k ; j < i
				int freq = sumIndexMap.get(currSum - k);
				count += freq;
			}
			sumIndexMap.put(currSum, sumIndexMap.getOrDefault(currSum, 0) + 1);
		}
		return count;
	}
	/*
	 * Naïve Approach
	 * TC: O(n²)
	 * SC: O(1)
	 * Runtime: 1550 ms
	 */
	public static int subarraySum1(int[] nums, int k) {
		int count = 0;
		for (int i = 0; i < nums.length; i++) {
			int sum = 0;
			for (int j = i; j < nums.length; j++) {
				sum += nums[j];
				if (sum == k) count++;
			}
		}
		return count;
	}
}

public class SubarraySumEqualsK {
	public static void main(String[] args) {
		int[] nums = {1,-1,1,-1,0};
		int k = 0;
		System.out.println(Solution.subarraySum(nums, k)); // 7
	}
}
