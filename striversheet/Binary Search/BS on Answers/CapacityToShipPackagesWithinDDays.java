import java.util.Arrays;

/**
 * 1011. Capacity To Ship Packages Within D Days
 * https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/
 */
class Solution {
	/*
	 * TC: O(n log n)
	 * SC: O(1)
	 */
	public int shipWithinDays(int[] weights, int days) {
		long l = 0, r = Arrays.stream(weights).sum();
		while (l < r) {
			long mid = l + (r - l)/2;
			if (canShip(weights, mid, days))
				r = mid;
			else
				l = mid + 1;
		}
		return (int) r;
	}
	private boolean canShip(int[] weights, long capacity, int days) {
		int currDays = 0;
		long weightSum = 0;
		for (int wt : weights) {
			// below check is important
			if (currDays > days || wt > capacity)
				return false;
			weightSum += wt;
			if (weightSum > capacity) {
				currDays++;
				weightSum = wt;
			}
		}
		if (weightSum >= weights[weights.length - 1])
			currDays++;
		return currDays <= days;
	}
}

public class CapacityToShipPackagesWithinDDays {

}
