import java.util.ArrayList;
import java.util.List;

/**
 * 94. Binary Tree Inorder Traversal
 * https://leetcode.com/problems/binary-tree-inorder-traversal
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
	 * Morris Traversal
	 *
	 * TC(amortized): O(n)
	 * SC: O(1)
	 */
	public List<Integer> inorderTraversal(TreeNode root) {
		List<Integer> ans = new ArrayList<>();
		TreeNode curr = root;
		while (curr != null) {
			// 1. if left-subtree exists, create a thread from right-most node of left-subtree to curr
			if (curr.left != null) {
				TreeNode prev = curr.left;
				while (prev.right != null && prev.right != curr) {
					prev = prev.right;
				}
				if (prev.right == null) {
					prev.right = curr;
					// 2. move curr to left child & repeat step 1
					curr = curr.left;
				}
				// 3. if prev node points to curr, delete the thread and move to right subtree
				else {
					prev.right = null;
					ans.add(curr.val);
					curr = curr.right;
				}
			}
			// 4. if no left-subtree, store the node
			else {
				ans.add(curr.val);
				curr = curr.right;
			}
			// 5. repeat steps 1 - 4 until curr points to null
		}
		return ans;
	}
}