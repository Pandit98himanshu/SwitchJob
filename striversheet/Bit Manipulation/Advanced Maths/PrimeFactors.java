import java.util.ArrayList;

/**
 * Prime Factors
 * https://www.geeksforgeeks.org/problems/prime-factors5052/1
 */

class Solution {
	/*
	 * TC: O(√n)	-> loop goes till end
	 * SC: O(1)
	 * Use traditional method of factorization
	 */
	public static ArrayList<Integer> primeFac(int n) {
		ArrayList<Integer> factors = new ArrayList<>();
		for (int i = 2; i <= Math.sqrt(n); i++) {
			if (n % i == 0) {
				factors.add(i);
				while (n % i == 0)
					n = n/i;
			}
		}
		if (n != 1) factors.add(n);		// required when the large no. itself is a prime number
		return factors;
	}
}