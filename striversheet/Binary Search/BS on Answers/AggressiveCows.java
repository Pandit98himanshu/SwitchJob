import java.util.Arrays;

/**
 * Aggressive Cows
 * https://www.geeksforgeeks.org/problems/aggressive-cows/1
 */
class Solution {
	/*
	 * TC: O(2 *(n log n))
	 * SC: O(1)
	 */
	public int aggressiveCows(int[] stalls, int k) {
		int n = stalls.length;
		Arrays.sort(stalls);				// order of stalls doesn't matter here.
		int l = 1, r = stalls[n - 1] - stalls[0];
		// we check for minDistances b/w stalls
		while (l <= r) {
			int mid = l + (r - l)/2;
			// if we are able to place all 'k' cows with min distance as 'mid'
			if (checkCowsPlacedAt(stalls, mid, k)) {
				l = mid + 1;				// try to maximize the stall dist. b/w the cows
			} else
				r = mid - 1;				// can't place cows when dist. is 'mid', hence reducing the 'mid' value
		}
		return r;
	}
	private boolean checkCowsPlacedAt(int[] stalls, int minDist, int k) {
		int prev = stalls[0];		// placing 1 cow at starting stall
		k--;
		for (int i = 1; i < stalls.length && k > 0; i++) {
			int curr = stalls[i];
			if (curr - prev >= minDist) {
				prev = curr;
				k--;
			}
		}
		return k == 0;
	}
}

public class AggressiveCows {
	public static void main(String[] args) {
		int[] stalls = {2, 12, 11, 3, 26, 7};
		int k = 3;
		System.out.println(new Solution().aggressiveCows(stalls, k));
	}
}
