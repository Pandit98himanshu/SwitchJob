package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
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
	public static boolean isPossibleDivide1(int[] nums, int k) {
		TreeMap<Integer, Integer> map = new TreeMap<>();
		// build frequency map
		for (int num : nums) {
			map.put(num, map.getOrDefault(num, 0) + 1);
		}

		while (!map.isEmpty()) {
			// get starting numaber of the sequence
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

	/*
	 * TC: O(2n)
	 * SC: O(n)
	 */
	public static boolean isPossibleDivide(int[] nums, int k) {
		if (nums.length == 0 || nums.length % k != 0)
			return false;
		Map<Integer, Integer> freqMap = new HashMap<>();
		// store frequency of each number
		for (int num : nums) {
			freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
		}
		// sort the array such that we can get the starting number of each sequence
		Arrays.sort(nums);
		for (int num : nums) {
			if (!freqMap.containsKey(num)) continue;
			int first = num;	// starting number of the sequence
			// build a consecutive sequence from 1st no. -> 1st no. + k
			for (int i = 0; i < k; i++) {
				int currConseq = first + i;
				// if we can't build the sequence
				if (!freqMap.containsKey(currConseq)) return false;
				// reduce the frequency of the current consecutive number by 1
				int freq = freqMap.get(currConseq) - 1;

				if (freq == 0)
					freqMap.remove(currConseq);	// remove the key from the map if frequency == 0
				else
					freqMap.put(currConseq, freq);
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
