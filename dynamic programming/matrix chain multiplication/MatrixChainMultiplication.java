import java.util.Arrays;

/**
 * Matrix Chain Multiplication
 * https://www.geeksforgeeks.org/problems/matrix-chain-multiplication0303/1
 */

class Solution {
	private static int[][] memo;
	static int matrixMultiplication(int arr[]) {
		memo = new int[arr.length][arr.length];
		for (int [] row : memo)
			Arrays.fill(row, -1);
		return MCM(arr, 1, arr.length - 1);
	}
	private static int MCM(int[] arr, int i, int j) {
		// base case
		if (i >= j)
			return 0;
		if (memo[i][j] != -1)
			return memo[i][j];
		int minCost = Integer.MAX_VALUE;
		for (int k = i; k < j; k++) {
			// left subtree
			int left = (memo[i][k] != -1) ? memo[i][k] : MCM(arr, i, k);
			// right subtree
			int right = (memo[k + 1][j] != -1) ? memo[k + 1][j] : MCM(arr, k + 1, j);
			// calculate cost when matrices are separated at 'k'
			int cost = left + right + arr[i - 1] * arr[k] * arr[j];
			// keep track of minimum cost of matrices multiplication
			minCost = Math.min(minCost, cost);
		}
		return memo[i][j] = minCost;
	}
}