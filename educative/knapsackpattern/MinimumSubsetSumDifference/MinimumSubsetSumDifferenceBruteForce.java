/*
 * Minimum Subset Sum Difference - Brute Force
 */

package educative.knapsackpattern.MinimumSubsetSumDifference;

class PartitionSet {

 	static int findMinimumSubsetSum(int[] num) {
    	return findMinimumSubsetSumRecur(num, 0, 0, 0);
 	}

 	// construct binary including-excluding tree, to understand properly
 	private static int findMinimumSubsetSumRecur(int[] num, int currentIndex, int sum1, int sum2) {
 		// base case
 		if (currentIndex == num.length)
 			return Math.abs(sum1 - sum2);
 		// recursive call after including current element in 1st subset
 		int diff1 = findMinimumSubsetSumRecur(num, currentIndex + 1, sum1 + num[currentIndex], sum2);
 		// recursive call after including current element in 2nd subset
 		int diff2 = findMinimumSubsetSumRecur(num, currentIndex + 1, sum1, sum2 + num[currentIndex]);
 		
 		return Math.min(diff1, diff2);
 	}
}

class MinimumSubsetSumDifferenceBruteForce {
	public static void main(String[] args) {
		int[] set = {1, 2, 3, 40};

		System.out.println(new PartitionSet().findMinimumSubsetSum(set));
	}
}