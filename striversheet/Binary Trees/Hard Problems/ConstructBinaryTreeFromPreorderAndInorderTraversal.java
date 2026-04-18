import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 105. Construct Binary Tree from Preorder and Inorder Traversal
 * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
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
	 * TC: O(n)	-> create each node & place at respective position
	 * SC: O(h)	-> store root nodes at all depths in Stack
	 * 
	 * Iterative version
	 */
	public TreeNode buildTree(int[] preorder, int[] inorder) {
		Map<Integer, Integer> valIndexMap = new HashMap<>();		// stores value & it's index in inorder map (can use int[] for hashing)
		TreeNode root = null, curr = null;

		for (int i = 0; i < inorder.length; i++)
			valIndexMap.put(inorder[i], i);							// tree contains unique values, hence values can be the key

		int i = 0;
		Stack<TreeNode> nodeStk = new Stack<>();					// store nodes
		Stack<int[]> rangeStk = new Stack<>();						// store { left-/right-subtree, start-column, end-column }
		rangeStk.push(new int[]{ 0, 0, preorder.length - 1 });

		while (i < preorder.length) {								// traverse preorder
			int[] range = rangeStk.pop();
			int dir = range[0], l = range[1], r = range[2];
			if (l > r) {											// null nodes after leaf node
				nodeStk.pop();
				continue;
			}
			int ind = valIndexMap.get(preorder[i]);					// get the column where curr node exist in inorder-traversal

			TreeNode newNode = new TreeNode(preorder[i]);			// create tree node
			if (curr == null) {										// create root node
				root = newNode;
				curr = root;
			} else {
				if (dir == -1)										// left node
					nodeStk.pop().left = newNode;
				else if (dir == 1)									// right node
					nodeStk.pop().right = newNode;
				curr = newNode;
			}
			rangeStk.push(new int[]{ 1, ind + 1, r });				// left subtree & column range in inorder
			rangeStk.push(new int[]{ -1, l, ind - 1 });				// right subtree & column range in inorder
			nodeStk.push(newNode);									// push root node to build left-subtree
			nodeStk.push(newNode);									// again push root node to build right-subtree
			i++;													// build next node from preorder
		}
		return root;
	}
}