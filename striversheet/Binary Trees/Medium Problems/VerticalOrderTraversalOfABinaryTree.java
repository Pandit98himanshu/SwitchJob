import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

/**
 * 987. Vertical Order Traversal of a Binary Tree
 * https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree
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
	 * TC: O(n + h log h)	-> traverse all nodes and sort if comes at same level
	 * SC: O(n)				-> store all nodes
	 */
	TreeMap<Integer, List<int[]>> columnNodesMap;
	public List<List<Integer>> verticalTraversal(TreeNode root) {
		columnNodesMap = new TreeMap<>();
		inorder(root, 0, 0);
		List<List<Integer>> ans = new ArrayList<>();
		for (List<int[]> list : columnNodesMap.values()) {
			Collections.sort(list, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
			List<Integer> subList = new ArrayList<>();
			for (int[] depthVal : list)
				subList.add(depthVal[1]);
			ans.add(subList);
		}
		return ans;
	}
	private void inorder(TreeNode root, int x, int y) {
		if (root == null)
			return;
		
		// do some business (start)
		List<int[]> nodes = columnNodesMap.get(y);
		if (nodes == null)
			nodes = new ArrayList<>();
		nodes.add(new int[] {x, root.val});
		columnNodesMap.put(y, nodes);
		// do some business (end)
		inorder(root.left, x + 1, y - 1);
		inorder(root.right, x + 1, y + 1);
	}
}