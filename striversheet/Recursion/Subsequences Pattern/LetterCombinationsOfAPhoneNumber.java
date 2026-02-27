import java.util.ArrayList;
import java.util.List;

/**
 * 17. Letter Combinations of a Phone Number
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 */

class Solution {
	/*
	 * TC: O(n * 4ⁿ)		; total 4ⁿ possibilities if digits are '7' or '9'
	 * SC (auxiliary): O(n)	; n = len(digits)
	 * Recursive approach
	 */
	private final char[][] letterMap = new char[][]{{}, {}, {'a','b','c'}, {'d','e','f'}, {'g','h','i'}, {'j','k','l'}, {'m','n','o'}, {'p','q','r','s'}, {'t','u','v'}, {'w','x','y','z'}};
	public List<String> letterCombinations(String digits) {
		return generateLetterCombiRecur(digits, 0, new StringBuilder(), new ArrayList<>());
	}
	private List<String> generateLetterCombiRecur(String digits, int i, StringBuilder temp, List<String> finalList) {
		if (i >= digits.length()) {
			finalList.add(temp.toString());		// copying whole string; O(n)
			return finalList;
		}
		for (char c : letterMap[digits.charAt(i) - '0']) {
			temp.append(c);
			generateLetterCombiRecur(digits, i + 1, temp, finalList);
			temp.deleteCharAt(temp.length() - 1);
		}
		return finalList;
	}
}