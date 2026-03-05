import java.util.Arrays;

/**
 * 204. Count Primes
 * https://leetcode.com/problems/count-primes
 */

class Solution {
	/*
	 * TC: O(MAX log(log(MAX)))
	 * SC: O(MAX)
	 */
	static final int size = 5 * (int)1e6 + 1;
	static final int[] totalPrimes = new int[size];
	
	// Sieve of Eratosthenes
	static {
		boolean[] isPrime = new boolean[size];
		// initially consider all elements as prime
		Arrays.fill(isPrime, true);

		for (int i = 2; i * i < size; i++) {
			if (!isPrime[i]) continue;
			// mark all multiples of i as non-prime
			for (int j = 2 * i; j < size; j += i)
				isPrime[j] = false;
		}
		// count prime nos. till index "i"
		for (int i = 2; i < size; i++) {
			totalPrimes[i] = isPrime[i] ? totalPrimes[i-1] + 1 : totalPrimes[i-1];
		}
	}

	public int countPrimes(int n) {
		if (n == 0) return 0;
		return totalPrimes[n - 1];
	}
}