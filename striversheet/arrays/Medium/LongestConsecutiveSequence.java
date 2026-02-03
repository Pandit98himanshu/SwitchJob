import java.util.*;

/*
 * 128. Longest Consecutive Sequence
 * https://leetcode.com/problems/longest-consecutive-sequence
 */

class Solution {
	static {
		for(int i = 0; i < 500; i++) longestConsecutive(new int[0]);
	}
	/*
	 * TC: O(2n)
	 * SC: O(n)
	 */
	public static int longestConsecutive(int[] nums) {
		if (nums == null || nums.length == 0)
			return 0;
		int longestConseq = 1 ;
		Set<Integer> set = new HashSet<>();
		// add all elements to the hash-set
		for (int num : nums) {
			set.add(num);
		}
		for (int num : set) {
			// skip, if we encounter middle element of any consecutive sequence
			if (set.contains(num - 1))
				continue;
			// we found the start of a consecutive sequence
			else {
				int currConseq = 1;
				// count the length of consecutive sequence starts with "num"
				while (set.contains(num + currConseq)) {
					currConseq++;
				}
				longestConseq = Math.max(longestConseq, currConseq);
			}
		}
		return longestConseq;
	}
	/*
	 * TC: O(n logn + n)
	 * SC: O(1)
	 */
	public static int longestConsecutive1(int[] nums) {
		if (nums == null || nums.length == 0)
			return 0;
		// sort all elements
		Arrays.sort(nums);
		int longestConseq = 1, currConseq = 1;
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] == nums[i - 1])
				continue;
			if (nums[i] == nums[i - 1] + 1) {
				currConseq++;
			} else {
				currConseq = 1;
			}
			longestConseq = Math.max(longestConseq, currConseq);
		}
		return longestConseq;
	}
}

public class LongestConsecutiveSequence {
	public static void main(String[] args) {
		int[] nums = {100, 4, 200, 1, 3, 2};
		System.out.println(Solution.longestConsecutive(nums));
	}
}
