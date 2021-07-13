/*
 * 5809. Unique Length-3 Palindromic Subsequences
 * 
 */
package leetcode;

import java.util.*;

class _UniqueLength3PalindromicSubsequences {
	static class Solution {
		public int countPalindromicSubsequence(String s) {
			int totalPalindromicSubsequences = 0;
			int n = s.length();

			// add positions of elements in map
			HashMap<Character, ArrayList<Integer>> map = new HashMap<Character, ArrayList<Integer>>();
			for (int i = 0; i < n; i++) {
				char c = s.charAt(i);
				ArrayList<Integer> list = map.get(c);

				if (list == null)
					list = new ArrayList<Integer>();

				list.add(i);
				map.put(c, list);
			}
			System.out.println(map);


			for (Map.Entry<Character, ArrayList<Integer>> e : map.entrySet()) {
				Character currentChar = e.getKey();
				ArrayList<Integer> positions = e.getValue();

				Integer start = positions.get(0);					// first element
				Integer end = positions.get(positions.size()-1);	// last element

				int palindromes = end-start-1;

				int countDuplicate = 0;
				for (Map.Entry<Character, ArrayList<Integer>> f : map.entrySet()) {
					// if element is same as current element then continue
					if (f.getKey() != currentChar) {
						ArrayList<Integer> list = f.getValue();
						if (list.size() > 1) {
							for (Integer k : list) {
								if (start < k && k < end) {
									countDuplicate++;
								}
							}
						}
					}
				}
				palindromes = palindromes - countDuplicate + 1;
				totalPalindromicSubsequences += palindromes;
			}
    	/*
        ababcdfc
        aba
        bab
        cdc
        cfc
        */
			return totalPalindromicSubsequences;
		}
	}
	public static void main(String[] args) {
		String s = "ababcdfca";

		System.out.println(new Solution().countPalindromicSubsequence(s));
	}
}