import java.util.PriorityQueue;

/**
 * 451. Sort Characters By Frequency
 * https://leetcode.com/problems/sort-characters-by-frequency/
 */

class Solution {
	/*
	 * TC: O(n + n log n + n)
	 * SC: O(n)
	 */
	public String frequencySort(String s) {
		// 1. store the frequency of all characters
		int[] freq = new int[128];
		for (char c : s.toCharArray()) {
			freq[(int) c]++;
		}
		// 2. sort it based on freq
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] == b[1] ? a[0] - b[0] : b[1] - a[1]);
		for (int i = 0; i < freq.length; i++) {
			if (freq[i] > 0)
				pq.offer(new int[] { i, freq[i] });				// [a, 0] -> [char_ascii, freq]
		}
		// 3. rebuild the string based on frequency
		StringBuilder sb = new StringBuilder();
		while (!pq.isEmpty()) {
			int[] curr = pq.poll();
			while (curr[1]-- > 0)
				sb.append((char) curr[0]);
		}
		return sb.toString();
	}
}