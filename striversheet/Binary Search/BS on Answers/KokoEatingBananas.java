import java.util.Arrays;

/**
 * 875. Koko Eating Bananas
 * https://leetcode.com/problems/koko-eating-bananas
 */
class Solution {
	/*
	 * TC: O(n log n)
	 * SC: O(1)
	 */
	public int minEatingSpeed(int[] piles, int h) {
		long l = 0, r = (long) Arrays.stream(piles).max().getAsInt();
		while (l < r) {
			long mid = l + (r - l)/2;
			if (canKokoEatAll(piles, (double) mid, h)) {
				r = mid;
			} else {
				l = mid + 1;
			}
		}
		return (int) r;
	}
	// find whether Koko can eat all bananas if her eating-speed/hr = rate
	private boolean canKokoEatAll(int[] piles, double rate, int h) {
		long totalTime = 0;
		for (int i : piles) {
			totalTime += (int) (i - 1)/rate + 1;
		}
		return totalTime <= h;
	}
}

public class KokoEatingBananas {
	public static void main(String[] args) {
		int[] piles = {805306368,805306368,805306368};
		int h = 1000000000;
		System.out.print(new Solution().minEatingSpeed(piles, h));	// 3
	}
}
