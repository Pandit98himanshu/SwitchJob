import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 139. Word Break
 * https://leetcode.com/problems/word-break/
 */

class Solution {
	/*
	 * TC: O(n²)
	 * SC: O(n)
	 * Use memoization on previous backtrack approach
	 */
	public boolean wordBreak(String s, List<String> wordDict) {
		return backtrack(s, new HashSet<>(wordDict), 0, new Boolean[s.length()]);
	}

	private boolean backtrack(String s, Set<String> dict, int start, Boolean[] memo) {
		if (start >= s.length())
			return true;
		if (memo[start] != null)
			return memo[start];
		for (int i = start; i < s.length(); i++) {
			// if current substring is present in wordDict, search for next word
			if (dict.contains(s.substring(start, i + 1)) && backtrack(s, dict, i + 1, memo))
				return memo[start] = true;
		}
		return memo[start] = false;
	}

	/*
	 * TC: O(n * mⁿ)
	 * SC: O(n)
	 * Break string "s" index by index and search in "wordDict"
	 */
	public boolean wordBreak2(String s, List<String> wordDict) {
		return backtrack(s, new HashSet<>(wordDict), 0);
	}

	private boolean backtrack(String s, Set<String> dict, int start) {
		if (start >= s.length())
			return true;
		for (int i = start; i < s.length(); i++) {
			// if current substring is present in wordDict, search for next word
			if (dict.contains(s.substring(start, i + 1)) && backtrack(s, dict, i + 1))
				return true;
		}
		return false;
	}

	/*
	 * TC: O(n * mⁿ)    ; m = size(wordDict)
	 * SC: O(n)         ; n = len(s)
	 * Build string "s" by contatenating words in "wordDict"
	 */
	public boolean wordBreak1(String s, List<String> wordDict) {
		return backtrack(s, wordDict, new StringBuilder());
	}
	private boolean backtrack(String s, List<String> dict, StringBuilder curr) {
		if (curr.length() > s.length())
			return false;
		if (curr.toString().equals(s))
			return true;

		for (String word : dict) {
			curr.append(word);
			if (backtrack(s, dict, curr))
				return true;
			curr.delete(curr.length() - word.length(), curr.length());  // O(n)
		}
		return false;
	}
}

public class WordBreak {
	
}
