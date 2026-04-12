/**
 * 124. Binary Tree Maximum Path Sum
 * https://leetcode.com/problems/binary-tree-maximum-path-sum
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
	private int maxSum;
	public int maxPathSum(TreeNode root) {
		maxSum = Integer.MIN_VALUE;
		maxSumInorder(root);
		return maxSum;
	}
	private int maxSumInorder(TreeNode root) {
		if (root == null)
			return 0;

		// max-sum from left & right
		int left = Math.max(0, maxSumInorder(root.left));
		int right = Math.max(0, maxSumInorder(root.right));

		// case where path passes through root
		maxSum = Math.max(maxSum, root.val + left + right);

		// return single path to parent
		return root.val + Math.max(left, right);
	}
}