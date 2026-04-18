/**
 * 222. Count Complete Tree Nodes
 * https://leetcode.com/problems/count-complete-tree-nodes/
 */

// Definition for a binary tree node.
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode() {}
	TreeNode(int val) { this.val = val; }
	TreeNode(int val, TreeNode left, TreeNode right) {
		this.val = val;
		this.left = left;
		this.right = right;
	}
}

class Solution {
	/*
	 * TC: O(n)	-> traverse all nodes
	 * SC: O(h)	-> recursive stack
	 */
	public int countNodes(TreeNode root) {
		if (root == null)
			return 0;
		return countNodes(root.left) + countNodes(root.right) + 1;
	}
}