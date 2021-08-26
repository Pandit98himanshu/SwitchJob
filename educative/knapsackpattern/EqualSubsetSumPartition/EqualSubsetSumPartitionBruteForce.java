/*
 * Equal Subset Sum Partition - Brute Force
 */

package educative.knapsackpattern.EqualSubsetSumPartition;

class PartitionSet {

 	static boolean canPartition(int[] num) {
 		// finding sum of given set
 		int sum = 0;
 		for (int i : num)
 			sum += i;
 		// if sum of given set is odd, we can't make partition
 		if (sum % 2 != 0 || num.length == 0)
 			return false;

    	return canPartitionUtil(num, sum/2, 0);
 	}

 	// construct binary including-excluding tree, to understand properly
 	private static boolean canPartitionUtil(int[] num, int sum, int currentIndex) {
 		// base case
 		if (sum == 0)
 			return true;
 		// edge case
 		else if (sum < 0 || currentIndex >= num.length)
 			return false;
 		// subtracting every value form num array in all possible combinations
 		// (i.e., making all possible subsets) and find whether sum of subset 
 		// is equal to (sum of given set)/2
 		return canPartitionUtil(num, sum - num[currentIndex], currentIndex + 1)		// including current element
 		 || canPartitionUtil(num, sum, currentIndex + 1);		// excluding current element
 	}
}

class EqualSubsetSumPartitionBruteForce {
	public static void main(String[] args) {
		int[] set = {1, 2, 3, 4};

		System.out.println(new PartitionSet().canPartition(set));
	}
}