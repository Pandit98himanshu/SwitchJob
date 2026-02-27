import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 131. Palindrome Partitioning
 * https://leetcode.com/problems/palindrome-partitioning/
 */

// Runtime: 7ms
class Solution {
	public List<List<String>> partition(String s) {
		return palindromePartitionRecur(s, 0, new ArrayList<>(), new ArrayList<>());
	}

	private List<List<String>> palindromePartitionRecur(String s, int start, List<String> tempList,
			List<List<String>> finalList) {
		if (start >= s.length()) {
			finalList.add(new ArrayList<>(tempList));
			return finalList;
		}
		for (int i = start; i < s.length(); i++) {
			if (isPalindrome(s, start, i)) {
				tempList.add(s.substring(start, i + 1));
				palindromePartitionRecur(s, i + 1, tempList, finalList);
				tempList.removeLast();
			}
		}
		return finalList;
	}

	private boolean isPalindrome(String s, int l, int r) {
		while (l < r) {
			if (s.charAt(l++) != s.charAt(r--))
				return false;
		}
		return true;
	}
}

// Runtime: 15ms
class Solution1 {
	public List<List<String>> partition(String s) {
		return palindromePartitionRecur(s, 0, new ArrayList<>(), new ArrayList<>());
	}

	private List<List<String>> palindromePartitionRecur(String s, int start, List<String> tempList,
			List<List<String>> finalList) {
		if (start >= s.length()) {
			finalList.add(new ArrayList<>(tempList));
			return finalList;
		}
		String curr = "";
		for (int i = start; i < s.length(); i++) {
			curr = curr + s.charAt(i); // takes more runtime due to string object creation in each recursion loop
			if (isPalindrome(curr)) {
				tempList.add(curr);
				palindromePartitionRecur(s, i + 1, tempList, finalList);
				tempList.remove(tempList.size() - 1);
			}
		}
		return finalList;
	}

	private boolean isPalindrome(String s) {
		int l = 0, r = s.length() - 1;
		while (l < r) {
			if (s.charAt(l++) != s.charAt(r--))
				return false;
		}
		return true;
	}
}