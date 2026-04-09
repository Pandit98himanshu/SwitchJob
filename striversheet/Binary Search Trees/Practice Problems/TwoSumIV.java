/**
 * 653. Two Sum IV - Input is a BST
 * https://leetcode.com/problems/two-sum-iv-input-is-a-bst
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
	TreeNode root;			// global root reference for accessibility

	/*
	 * TC: O(n log n)
	 * SC: O(1)
	 */
	public boolean findTarget(TreeNode root, int k) {
		this.root = root;
		return inorder(root, k);
	}
	private boolean inorder(TreeNode curr, int k) {
		boolean retv = false;
		if (curr == null)
			return retv;
		if (!retv)			// stop left subtree calls if both elements are found
			retv = inorder(curr.left, k);
		// either both elements is found earlier or 2nd element it's present in the tree
		if (retv || isPresent(root, curr, k - curr.val)) {
			return true;
		}
		if (!retv)			// stop right subtree calls if both elements are found
			retv = inorder(curr.right, k);
		return retv;
	}
	// search for "key" element in whole tree
	private boolean isPresent(TreeNode curr, TreeNode first, int key) {
		boolean retv = false;
		while (curr != null) {
			if (curr.val == key) {		// 2nd element is present
				retv = (curr != first);	// we need to find 2 different elements
				break;
			}
			if (curr.val < key)			// search in right subtree
				curr = curr.right;
			else if (curr.val > key)	// search in left subtree
				curr = curr.left;
		}
		return retv;
	}
}