package striversheet.Arrays.Medium;
/*
 * https://leetcode.com/problems/majority-element
 */
class Solution {
	// Boyer-Moore voting algorithm.
	public int majorityElement(int[] nums) {
		int candidate = nums[0], count = 1;
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] == candidate) {
				count++;
			} else {
				count--;
			}
			if (count == 0) {
				candidate = nums[i];
				count = 1;
			}
		}
		return candidate;
	}
}

public class MajorityElement {
	public static void main(String[] args) {
		int[] arr = {2,2,1,1,1,2,2};
		System.out.println(new Solution().majorityElement(arr));
	}
}
