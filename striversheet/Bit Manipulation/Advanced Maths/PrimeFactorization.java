/**
 * Prime Factorization
 * https://www.geeksforgeeks.org/problems/prime-factorization/1
 */

class Solution {
	/*
	 * TC:O(√n)
	 * SC: O(1)
	 */
    public static void printPrimeFactorization(int n) {
		for (int i = 2; i <= Math.sqrt(n); i++) {
			while (n % i == 0) {
				System.out.print(i + " ");
				n = n/i;
			}
		}
		if (n != 1) System.out.print(n);
	}
}