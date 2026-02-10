import java.util.Arrays;

/**
 * 1482. Minimum Number of Days to Make m Bouquets
 * https://leetcode.com/problems/minimum-number-of-days-to-make-m-bouquets/
 */
class Solution {
	/*
	 * TC: O(n log n)
	 * SC: O(1)
	 */
	public int minDays(int[] bloomDay, int m, int k) {
		if (bloomDay.length < (long)m * k)
			return -1;
		long l = 0, r = (long) Arrays.stream(bloomDay).max().getAsInt();
		while (l < r) {
			long mid = l + (r - l)/2;
			if (canMakeBouquets(bloomDay, m, k, mid)) {
				r = mid;
			} else {
				l = mid + 1;
			}
		}
		return (int) r;
	}
	// check whether we can make "m" bouquets of "k" adj. flowers if day is "currDay"
	private boolean canMakeBouquets(int[] bloomDay, int m, int k, long currDay) {
		int bouquets = 0, conseqFlowers = 0;
		for (int day : bloomDay) {
			if (day <= currDay) {
				conseqFlowers++;	// if current flower has bloomed, we can use it in bouquet
				// we have 'k' consecutive flowers, hence we make a bouquet
				if (conseqFlowers == k) {
					bouquets++;
					conseqFlowers = 0;
				}
			} else
				conseqFlowers = 0;
		}
		return bouquets >= m;
	}
}

public class MinimumNumberOfDaysToMakeMBouquets {
	
}
