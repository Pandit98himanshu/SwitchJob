/**
 * 701. Insert into a Binary Search Tree
 * https://leetcode.com/problems/insert-into-a-binary-search-tree/
 */

class Solution {
	/*
	 * TC: O(n)		-> max length of BST
	 * SC: O(1)
	 */
	public TreeNode insertIntoBST(TreeNode root, int val) {
		TreeNode newNode = new TreeNode(val);

		TreeNode prev = null, curr = root;
		// move to appropriate node whose child is "val"
		while (curr != null) {
			prev = curr;
			if (val < curr.val)
				curr = curr.left;
			else if (val > curr.val)
				curr = curr.right;
		}
		// place new node having value as "val"
		if (prev != null) {
			if (val < prev.val)
				prev.left = newNode;
			if (val > prev.val)
				prev.right = newNode;
		} else {
			root = newNode;
		}
		return root;
	}
}

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
