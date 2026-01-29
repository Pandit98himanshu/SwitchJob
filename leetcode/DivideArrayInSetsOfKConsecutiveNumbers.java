package leetcode;

import java.util.TreeMap;

/*
 * 1296. Divide Array in Sets of K Consecutive Numbers
 * https://leetcode.com/problems/divide-array-in-sets-of-k-consecutive-numbers
 */

class Solution {
	static {
		for(int i = 0; i < 500; i++) isPossibleDivide(new int[0], 0);
	}

	/*
	 * TC: O(n log n)
	 * SC: O(n)
	 */
	public static boolean isPossibleDivide(int[] nums, int k) {
		TreeMap<Integer, Integer> map = new TreeMap<>();
		// build frequency map
		for (int num : nums) {
			map.put(num, map.getOrDefault(num, 0) + 1);
		}

		while (!map.isEmpty()) {
			// get smallest number
			int first = (int)map.firstKey();
			// build a consecutive sequence from 1st no. -> 1st no. + k
			for (int i = 0; i < k; i++) {
				int currConseq = first + i;
				if (!map.containsKey(currConseq) || map.get(currConseq) == 0)
					return false;
				int freq = map.get(currConseq) - 1;
				if (freq == 0)
					map.remove(currConseq);
				else
					map.put(currConseq, freq);
			}
		}
		return true;
	}
}

public class DivideArrayInSetsOfKConsecutiveNumbers {
	public static void main(String[] args) {
		int[] nums = {3,2,1,2,3,4,3,4,5,9,10,11};
		int k = 3;
		System.out.println(Solution.isPossibleDivide(nums, k));	// true
	}
}
