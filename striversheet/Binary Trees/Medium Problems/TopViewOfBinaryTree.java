import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Top View of Binary Tree
 * https://www.geeksforgeeks.org/problems/top-view-of-binary-tree/1
 */

class Node {
	int data;
	Node left, right;

	Node(int val) {
		this.data = val;
		this.left = null;
		this.right = null;
	}
}

class Solution {
	/*
	 * TC: O(n)	-> traverse all nodes using inorder traversal
	 * SC: O(w)	; w = width of the tree, w ∈ [1, n]
	 */
	TreeMap<Integer, int[]> columnNodeMap;
	public ArrayList<Integer> topView(Node root) {
		columnNodeMap = new TreeMap<>();

		inorder(root, 0, 0);

		ArrayList<Integer> ans = new ArrayList<>();
		for (int[] val : columnNodeMap.values()) {
			ans.add(val[1]);									// store node values
		}
		return ans;
	}
	private void inorder(Node root, int x, int y) {
		if (root == null)
			return;

		if (!columnNodeMap.containsKey(y))
			columnNodeMap.put(y, new int[] {x, root.data});		// add node if no node added for column "y"
		else {
			int[] val = columnNodeMap.get(y);
			if (val[0] > x) {									// replace node which comes at top for column "y"
				val[0] = x;
				val[1] = root.data;
				columnNodeMap.put(y, val);
			}
		}

		inorder(root.left, x + 1, y - 1);						// move to left subtree
		inorder(root.right, x + 1, y + 1);						// move to right subtree
	}
}