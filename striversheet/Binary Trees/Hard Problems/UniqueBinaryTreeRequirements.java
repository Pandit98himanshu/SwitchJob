/**
 * Unique Binary Tree Requirements
 * https://www.geeksforgeeks.org/problems/unique-binary-tree-requirements/1
 */

class Solution {
	/*
	 * TC: O(1)
	 * SC: O(1)
	 * Return true, if 2 types of traversal are provided and one of them are Inorder
	 */
	public static boolean isPossible(int a, int b) {
		if (a == b)
			return false;
		if (a == 2 || b == 2)
			return true;
		return false;
	}
}