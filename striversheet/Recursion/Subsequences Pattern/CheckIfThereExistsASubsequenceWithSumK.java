/**
 * Check if there exists a subsequence with sum K
 * https://www.geeksforgeeks.org/problems/check-if-there-exists-a-subsequence-with-sum-k/1
 */

class Solution {
	/*
	 * TC: O(2ⁿ)
	 * SC: O(log n)	// recursive stack
	 * TLE
	 */
	public static boolean checkSubsequenceSum(int N, int[] arr, int K) {
		return checkRecur(arr, K, 0, 0);
	}
	private static boolean checkRecur(int[] arr, int K, int i, int currSum) {
		if (i >= arr.length || currSum > K)
			return false;

		if (currSum == K)
			return true;
		
		return checkRecur(arr, K, i + 1, currSum + arr[i]) || checkRecur(arr, K, i + 1, currSum);
	}
}

public class CheckIfThereExistsASubsequenceWithSumK {
	public static void main(String[] args) {
		int N = 28;
		int K = 500;
		int[] arr = new int[] { 999, 559, 389, 238, 322, 300, 596, 999, 753, 783, 865, 747, 892, 246, 991, 438, 201,
				129, 711, 112, 683, 331, 578, 51, 423, 427, 833, 7 };
		System.out.println(checkSubsequenceSum(N, arr, K));
	}
}
