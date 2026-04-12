/**
 * 110. Balanced Binary Tree
 * https://leetcode.com/problems/balanced-binary-tree/
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
	private boolean ans;
	public boolean isBalanced(TreeNode root) {
		ans = true;
		checkBalancedInorder(root, 0);
		return ans;
	}
	private int checkBalancedInorder(TreeNode root, int depth) {
		if (root == null)
			return depth;
		int leftDepth = checkBalancedInorder(root.left, depth + 1);
		int rightDepth = checkBalancedInorder(root.right, depth + 1);
		if (Math.abs(leftDepth - rightDepth) > 1)
			ans = false;
		return Math.max(leftDepth, rightDepth);
	}
}