package striversheet.Arrays.Medium;

import java.util.Arrays;

/*
 * https://leetcode.com/problems/rearrange-array-elements-by-sign/
 */

/**
 * TLE
 * Time Complexity: O(n^2)
 * Space Complexity: O(1)
*/
class Solution1 {
	private static int sign = 1;

	public int[] rearrangeArray(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			if (getSign(nums[i]) != sign) {
				int j = getOppSignIndex(nums, i, getSign(nums[i]));
				rotateAntiClockwise(nums, i, j);
			}
			sign = sign * -1;
		}
		return nums;
	}
	private int getOppSignIndex(int[] nums, int i, int sign) {
		for (int j = i + 1; j < nums.length; j++) {
			if (getSign(nums[j]) != sign)
				return j;
		}
		return -1;
	}
	private int getSign(int x) {
		return x > 0 ? 1 : -1;
	}
	private void rotateAntiClockwise(int[] nums, int start, int end) {
        int temp = nums[end];
        for (int i = end; i > start; i--) {
            nums[i] = nums[i - 1];
        }
        nums[start] = temp;
	}
}

/**
 * Runtime: 6 ms
 * TC: O(2*n)
 * SC: O(n)
 */
class Solution2 {
	public int[] rearrangeArray(int[] nums) {
		int[] posEle = new int[nums.length/2];	// stores all +ve elements
		int[] negEle = new int[nums.length/2];	// stores all -ve elements

		int posIdx = 0, negIdx = 0;
		// stores +ve & -ve elements in their respective arrrays
		for (int num : nums) {
			if (num > 0) posEle[posIdx++] = num;
			else if (num < 0) negEle[negIdx++] = num;
			else System.out.println("Invalid Input");
		}
		posIdx = 0; negIdx = 0;
		// fill original array with appropriate elements
		for (int i = 0; i < nums.length; i++) {
			if (i % 2 == 0) nums[i] = posEle[posIdx++];
			else nums[i] = negEle[negIdx++];
		}
		return nums;
	}
}

/**
 * Runtine: 4 ms
 * TC: O(n)
 * SC: O(n)
 */
class Solution {
	public int[] rearrangeArray(int[] nums) {
		int[] result = new int[nums.length];
		int posIdx = 0, negIdx = 1;
		// even places are for +ve elements & 
		// odd places are for -ve elements
		for (int num : nums) {
			if (num > 0) {
				result[posIdx] = num;
				posIdx += 2;
			} else {
				result[negIdx] = num;
				negIdx += 2;
			}
		}
		return result;
	}
}

public class RearrangeArrayElementsBySign {
	public static void main(String[] args) {
		// TC 1
		int[] input = {28,-41,22,-8,-37,46,35,-9,18,-6,19,-26,-37,-10,-9,15,14,31};
		int[] output = {28,-41,22,-8,46,-37,35,-9,18,-6,19,-26,15,-37,14,-10,31,-9};
		System.out.println(java.util.Arrays.toString(new Solution().rearrangeArray(input)));
		if (!Arrays.equals(input, output))
			System.out.println("Test case failed");
	}
}
