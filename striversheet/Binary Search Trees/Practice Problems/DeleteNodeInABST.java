/**
 * 450. Delete Node in a BST
 * https://leetcode.com/problems/delete-node-in-a-bst/
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
	 * TC: O(log n)
	 * SC: O(1)
	 */
	public TreeNode deleteNode(TreeNode root, int key) {
		TreeNode prevDelNode = null, delNode = root;
		// move to node to be deleted and it's parent node
		while (delNode != null) {
			if (delNode.val == key)
				break;
			prevDelNode = delNode;
			if (key < delNode.val)
				delNode = delNode.left;
			else if (key > delNode.val)
				delNode = delNode.right;
		}
		// deleting node not found in given BST
		if (delNode == null)
			return root;
		
		// root node to be deleted
		if (delNode == root) {
			TreeNode leftSubtree = (root != null) ? root.left : null;
			root = delNode.right;
			// place left-subtree of deleting node at the leftmost position of right-subtree
			TreeNode leftMost = root;
			if (leftMost == null)
				return leftSubtree;
			while (leftMost.left != null)
				leftMost = leftMost.left;
			leftMost.left = leftSubtree;
			return root;
		}
		// only left-subtree present of deleting node
		if (delNode.right == null) {
			if (delNode == prevDelNode.left)
				prevDelNode.left = delNode.left;
			else if (delNode == prevDelNode.right)
				prevDelNode.right = delNode.left;
		}
		// only right-subtree present of deleting node
		else if (delNode.left == null) {
			if (delNode == prevDelNode.left)
				prevDelNode.left = delNode.right;
			else if (delNode == prevDelNode.right)
				prevDelNode.right = delNode.right;
		}
		// place right subtree at the place of deleting node & place left-subtree at the leftmost position of right-subtree
		else {
			TreeNode leftSubtree = (delNode != null) ? delNode.left : null;
			if (delNode == prevDelNode.left) {
				prevDelNode.left = delNode.right;
				prevDelNode = prevDelNode.left;
			}
			else if (delNode == prevDelNode.right)  {
				prevDelNode.right = delNode.right;
				prevDelNode = prevDelNode.right;
			}
			TreeNode leftMost = prevDelNode;
			if (leftMost == null)
				return root;
			// place left subtree at leftmost node
			while (leftMost.left != null)
				leftMost = leftMost.left;
			leftMost.left = leftSubtree;
		}
		return root;
	}
}