import java.util.ArrayList;
import java.util.List;

/**
 * 257. Binary Tree Paths
 * https://leetcode.com/problems/binary-tree-paths
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
	private final String arrow = "->";
	private List<String> ans;

	/*
	 * TC: O(n)		-> traverse all nodes
	 * SC: O(2ʰ)	-> h = height of the tree;		# of paths = # of leaf nodes
	 */
	public List<String> binaryTreePaths(TreeNode root) {
		ans = new ArrayList<>();
		inorder(root, new StringBuilder());
		return ans;
	}
	private void inorder(TreeNode root, StringBuilder intermediatePath) {
		if (root == null) {
			return;
		}

		intermediatePath.append(Integer.toString(root.val));	// store current node
		if (root.left == null && root.right == null) {
			ans.add(intermediatePath.toString());				// store path from root -> leaf
		}
		intermediatePath.append(arrow);							// add "->"

		int length = intermediatePath.length();					// get nodes till current level

		inorder(root.left, intermediatePath);					// add nodes from left subtree
		intermediatePath.setLength(length);						// delete nodes from left sub-tree
		inorder(root.right, intermediatePath);					// add nodes from right subtree
	}
}