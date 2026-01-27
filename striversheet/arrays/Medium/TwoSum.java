package striversheet.Arrays.Medium;

/*
 * https://leetcode.com/problems/two-sum/
 */
import java.util.*;

class Solution {
	public int[] twoSum(int[] nums, int target) {
			Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < nums.length; i++){
			int complement = target - nums[i];
			if (map.containsKey(complement)) {
				return new int[]{map.get(complement), i};
			}
			map.put(nums[i], i);
		}
		return new int[]{-1, -1};
	}
}

public class TwoSum {
	public static void main(String[] args) {
		int[] nums = {2,7,11,15};
		int target = 7;
		System.out.println(Arrays.toString(new Solution().twoSum(nums, target)));
	}
}