import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Swap two numbers
 * https://www.geeksforgeeks.org/problems/swap-two-numbers3844/1
 */

class Solution {
	/*
	 * TC: O(1)
	 * SC: O(1)
	 */
	static List<Integer> get(int a, int b) {
		a = a ^ b;
		b = a ^ b;
		a = a ^ b;
		return new ArrayList<>(Arrays.asList(a, b));
	}
}