/**
 * 151. Reverse Words in a String
 * https://leetcode.com/problems/reverse-words-in-a-string
 */

class Solution {
	public String reverseWords(String s) {
		StringBuilder ans = new StringBuilder();
		StringBuilder temp = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == ' ') {
				if (i > 0 && s.charAt(i - 1) != ' ') {
					temp.insert(0, s.charAt(i));
					temp.append(ans);		// append current word at the beginning
					ans = temp;
					temp = new StringBuilder();
				}
			} else {
				temp.append(s.charAt(i));	// keep building the word
			}
		}
		if (temp.length() > 0) {
			temp.append(ans);				// append at the beginning last word if left
			ans = temp;
		}
		return ans.toString().trim();		// remove 'leading' & 'trailing' spaces
	}
}