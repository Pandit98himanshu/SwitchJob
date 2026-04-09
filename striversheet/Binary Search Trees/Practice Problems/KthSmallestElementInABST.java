/**
 * 230. Kth Smallest Element in a BST
 * https://leetcode.com/problems/kth-smallest-element-in-a-bst
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
	 * TC: O(k)
	 * SC: O(k)		-> recursive stack
	 */
	private int kth, times;
	public int kthSmallest(TreeNode root, int k) {
		kth = 0;
		times = 0;
		kthSmallestInorder(root, k);
		return kth;
	}
	private void kthSmallestInorder(TreeNode root, int k) {
		if (root == null)
			return;
		kthSmallestInorder(root.left, k);
		times++;
		if (times <= k)
			kth = root.val;
		kthSmallestInorder(root.right, k);
	}
}
