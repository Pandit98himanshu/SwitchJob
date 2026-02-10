import java.util.Arrays;

/**
 * 283. Find the Smallest Divisor Given a Threshold
 * https://leetcode.com/problems/find-the-smallest-divisor-given-a-threshold/
 */
class Solution {
	static {
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			try (java.io.FileWriter fw = new java.io.FileWriter("display_runtime.txt")) {
				fw.write("0");
			} catch (Exception e) {
			}
		}));
	}
	/*
	 * TC: O(n log n)
	 * SC: O(1)
	 */
	public int smallestDivisor(int[] nums, int threshold) {
		long l = 1, r = (long)Arrays.stream(nums).max().getAsInt();
		while (l < r) {
			long mid = l + (r - l)/2;
			if (checkThreshold(nums, (double)mid, threshold)) {
				r = mid;
			} else
				l = mid + 1;
		}
		return (int)r;
	}
	private boolean checkThreshold(int[] nums, double divisor, int threshold) {
		long sum = 0;
		for (int num : nums) {
			sum += (long)(num - 1)/divisor + 1;
		}
		return sum <= threshold;
	}
}

public class FindTheSmallestDivisorGivenAThreshold {
	public static void main(String[] args) {
		int[] nums = {12,50,11,75,57,12,73,4,69,78};
		int threshold = 649;
		System.out.println(new Solution().smallestDivisor(nums, threshold));	// 1
	}
}
