/**
 * 235. Lowest Common Ancestor of a Binary Search Tree
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree
 */


// Definition for a binary tree node.
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x) { val = x; }
}


class Solution {
	/*
	 * TC: O(n)		-> search whole BST for 
	 * SC: O(1)
	 */
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		if (p.val > q.val)
			return lowestCommonAncestor(root, q, p);
		TreeNode lca = root;
		while (lca != null) {
			if (p.val == lca.val || q.val == lca.val)		// one of the node is LCA
				break;
			else if (p.val < lca.val && q.val > lca.val)	// elements are on either side
				break;
			else if (p.val < lca.val && q.val < lca.val)	// LCA on left subtree
				lca = lca.left;
			else if (p.val > lca.val && q.val > lca.val)	// LCA is on right subtree
				lca = lca.right;
		}
		return lca;
	}
}