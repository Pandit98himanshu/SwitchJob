/**
 * Print all Divisors of a number
 * https://www.naukri.com/code360/problems/print-all-divisors-of-a-number_1164188
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Solution {
	/*
	 * TC: O(√n)
	 * SC: O(log n) -> storing all factors
	 */
	public static List< Integer > printDivisors(int n) {
		ArrayList<Integer> divisors = new ArrayList<>();

		for (int i = 1; i <= Math.sqrt(n); i++) {
			if (n % i == 0) {
				divisors.add(i);
				if (i != n/i)
					divisors.add(n/i);
			}
		}
		Collections.sort(divisors);
		return divisors;
	}

	/*
	 * TC: O(n * √n) -> right-shifting all elements each time while adding elements
	 * SC: O(log n)
	 */
	public static List<Integer> printDivisors1(int n) {
		List<Integer> divisors = new ArrayList<>();
		int k = 0;
		for (int i = 1; i <= Math.sqrt(n); i++) {
			if (n % i == 0) {
				divisors.add(k, i);
				if (i != n / i)
					divisors.add(divisors.size() - k, n / i);
				k++;
			}
		}
		return divisors;
	}

	/*
	 * TC: O(n)
	 * SC: O(log n)
	 * Brute-force traditional approach
	 */
	public static List< Integer > printDivisors2(int n) {
		List<Integer> divisors = new ArrayList<>();
		for (int i = 1; i <= n; i++) {
			if (n % i == 0) {
				divisors.add(i);
			}
		}
		return divisors;
	}
}