/*
 * Equal Subset Sum Partition : (0/1 Knapsack [Bottom-up approach])
 */
package educative.KnapsackPattern.EqualSubsetSumPartition;

class EqualSubsetSumPartitionBottomUp {
	public static int sumArray (int[] arr) {
		int sum = 0;
		for (int i : arr)
			sum += i;

		return sum;
	}
	public static boolean canPartition (int[] set) {
		int sum = sumArray(set);
		// we can't divide the given set into 2 subsets if sum of elements is odd
		if (sum % 2 != 0)
			return false;
		else {
			int n = sum/2;
			boolean[][] dp = new boolean[set.length][n + 1];
			// we can always have '0' sum without including any element
			for (int i = 0; i < set.length; i++)
				dp[i][0] = true;
			// with only one number, we can form a subset only when
			// the required sum is equal to its value i.e., j == set[0]
			for (int j = 1; j <= n; j++)
				dp[0][j] = (j == set[0]) ? true : false;

			for (int i = 1; i < set.length; i++)
				for (int j = 1; j <= n; j++)
					if (dp[i - 1][j])
						dp[i][j] = dp[i - 1][j];			// excluding current element
					else if (set[i] <= j)
						dp[i][j] = dp[i - 1][j - set[i]];	// including current element

			return dp[set.length - 1][n];
		}
	}
	public static void main(String[] args) {
		int[] set = {3, 3, 4, 6};

		System.out.println(canPartition(set));
	}
}