import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Power Set
 * https://www.geeksforgeeks.org/problems/power-set4302/1
 */

class Solution {
	/*
	 * TC: O(n² * 2ⁿ)	// generating power-set (n * 2ⁿ) + sorting 2ⁿ elements (n * 2ⁿ [comparisions] * n [comparing each strings] = n² * 2ⁿ)
	 * SC: O(n * 2ⁿ)	// recursively generating all power sets (n * 2ⁿ) + sorting 2ⁿ elements (2ⁿ)
	 * Recursive DFS approach
	 */
	public List<String> AllPossibleStrings1(String s) {
		List<String> ans = new ArrayList<>();
		generatePowerSet(s, 0, new StringBuilder(), ans);
		Collections.sort(ans);		// return the answer in lexicographical order
		return ans;
	}

	private List<String> generatePowerSet(String s, int idx, StringBuilder curr, List<String> ans) {
		for (int i = idx; i < s.length(); i++) {
			curr.append(s.charAt(i));
			ans.add(curr.toString());				// all generated strings are power-set
			generatePowerSet(s, i + 1, curr, ans);	// next recursion loop should start after the current index
			curr.deleteCharAt(curr.length() - 1);
		}
		return ans;
	}
	
	/*
	 * TC: O(n * 2ⁿ)
	 * SC: O(n * 2ⁿ)
	 * Iterative Bitmasking approach
	 */
	public List<String> AllPossibleStrings(String s) {
		int n = s.length();
		TreeMap<String, Integer> powerSetFreq = new TreeMap<>(); // keys will be in sorted order and map will handle duplicate char
		// generate all power set
		for (int mask = 1; mask < (1 << n); mask++) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < n; i++) {
				if ((mask & (1 << i)) != 0) {
					sb.append(s.charAt(i));
				}
			}
			String curr = sb.toString();
			powerSetFreq.put(curr, powerSetFreq.getOrDefault(curr, 0) + 1);
		}
		// store in return value format
		List<String> ans = new ArrayList<>();
		for (Map.Entry<String, Integer> e : powerSetFreq.entrySet()) {
			int freq = e.getValue();
			while (freq-- > 0) {
				ans.add(e.getKey());
			}
		}
		return ans;
	}
}

public class PowerSet {
	public static void main(String[] args) {
		String s = "tk"; // output: {k, t, tk}
		System.out.println(new Solution().AllPossibleStrings(s));
	}
}
