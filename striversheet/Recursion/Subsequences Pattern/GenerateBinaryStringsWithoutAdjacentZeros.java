import java.util.ArrayList;
import java.util.List;

/**
 * 3211. Generate Binary Strings Without Adjacent Zeros
 * https://leetcode.com/problems/generate-binary-strings-without-adjacent-zeros
 */

class Solution {
	/*
	 * TC: O(2ⁿ)	-> we can either put '0' or '1' at each position of length 'n'
	 * SC: O(n)		-> considering only for 'StringBuilder curr'
	 */
	public List<String> validStrings(int n) {
		return buildValidStrings(n, new StringBuilder(), new ArrayList<>());	// call a recursive method
	}

	private List<String> buildValidStrings(int n, StringBuilder curr, List<String> ans) {
		if (n == 0) {			// our valid string of length 'n' is built
			ans.add(curr.toString());
			return ans;
		}
		for (int i = 0; i < 2; i++) {				// we can only put either '0' or '1'
			if (i == 0 && (curr.length() > 0 && curr.charAt(curr.length() - 1) == '0'))
				continue;							// don't build invalid strings
			curr.append((char) (i + '0'));			// append '0'/'1'
			buildValidStrings(n - 1, curr, ans);	// now build the string of length 'n - 1'
			curr.deleteCharAt(curr.length() - 1);	// remove last char so that we can build more strings with current prefix
		}
		return ans;
	}
}
