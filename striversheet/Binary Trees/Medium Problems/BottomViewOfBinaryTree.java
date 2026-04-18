import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Bottom View of Binary Tree
 * https://www.geeksforgeeks.org/problems/bottom-view-of-binary-tree/1
 */

class Node {
	int data;
	Node left;
	Node right;

	Node(int data) {
		this.data = data;
		left = null;
		right = null;
	}
}

class Solution {
	/*
	 * TC: O(n)	-> traverse all nodes
	 * SC: O(w)	; w = width of the tree, w ∈ [1, n]
	 */
	TreeMap<Integer, int[]> columnNodeMap;
	public ArrayList<Integer> bottomView(Node root) {
		columnNodeMap = new TreeMap<>();

		inorder(root, 0, 0);

		ArrayList<Integer> ans = new ArrayList<>();
		for (int[] val : columnNodeMap.values()) {
			ans.add(val[1]);									// convert to required return format
		}
		return ans;
	}
	private void inorder(Node root, int x, int y) {
		if (root == null)
			return;

		if (!columnNodeMap.containsKey(y))
			columnNodeMap.put(y, new int[] {x, root.data});		// store if no node present for column "y"
		else {
			int[] val = columnNodeMap.get(y);
			if (val[0] <= x) {									// store node which is present at lower depth for column "y"
				val[0] = x;
				val[1] = root.data;
				columnNodeMap.put(y, val);
			}
		}

		inorder(root.left, x + 1, y - 1);						// traverse left subtree
		inorder(root.right, x + 1, y + 1);						// traverse right subtree
	}
}