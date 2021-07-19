/*
 * Minimum Subset Sum Difference - Top Down Approach
 */

package educative.KnapsackPattern.MinimumSubsetSumDifference;

class MinimumSubsetSumDifferenceTopDown {
	// method to find sum of an array
	public static int sumArray (int[] arr) {
		int sum = 0;
		for (int i : arr)
			sum += i;

		return sum;
	}

	public static int findMinimumSubsetSum (int[] num) {
		int sum = sumArray(num);
		Integer[][] dp = new Integer[num.length][sum + 1];
		return findMinimumSubsetSumRecursive(num, dp, 0, 0, 0);
	}

	private static int findMinimumSubsetSumRecursive (int[] num, Integer[][] dp, int sum1, int sum2, int currentIndex) {
		// base case
		if (currentIndex >= num.length)
			return Math.abs(sum1 - sum2);
		// if we haven't already processed a similar problem
		// 'sum2' is the sum of remaining numbers, therefore, we use 'sum1'
		if (dp[currentIndex][sum1] == null) {
			// recursive call after including the number at the currentIndex in 1st set
			int diff1 = findMinimumSubsetSumRecursive(num, dp, sum1 + num[currentIndex], sum2, currentIndex + 1);
			// recursive call after including the number at the currentIndex in 1st set
			int diff2 = findMinimumSubsetSumRecursive(num, dp, sum1, sum2 + num[currentIndex], currentIndex + 1);
			// store min difference
			dp[currentIndex][sum1] = Math.min(diff1, diff2);
		}
		// return already processed result
		return dp[currentIndex][sum1];
	}

	public static void main(String[] args) {
		int[] num = {1, 2, 3, 9};

		System.out.println(findMinimumSubsetSum(num));
	}
}