import java.util.Arrays;

/**
 * 455. Assign Cookies
 * https://leetcode.com/problems/assign-cookies/
 */

class Solution {
	/*
	 * TC: O(m logm + n logn)
	 * SC: O(1)
	 */
	public int findContentChildren(int[] g, int[] s) {
		Arrays.sort(g);
		Arrays.sort(s);
		int count = 0, i = 0, j = 0;
		while (i < g.length && j < s.length) {
			if (g[i] <= s[j]) {
				count++;
				i++;
				j++;
			} else if (g[i] > s[j]) {
				j++;		// search for larger size cookie since greed of current childern is higher than cookie size
			}
		}
		return count;
	}
}

public class AssignCookies {
	public static void main(String[] args) {
		int[] g = new int[] { 4, 5, 7 };
		int[] s = new int[] { 8, 2, 5, 8 };
		System.out.println(new Solution().findContentChildren(g, s));	// output: 2
	}
}
