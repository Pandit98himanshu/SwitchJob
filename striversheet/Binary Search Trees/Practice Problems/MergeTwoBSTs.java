import java.util.ArrayList;
import java.util.Collections;

/**
 * Merge two BST 's
 * https://www.geeksforgeeks.org/problems/merge-two-bst-s/1
 */


class Node {
	int data;
	Node left, right;

	public Node(int val)
	{
		data = val;
		left = right = null;
	}
}


class Solution {
	/*
	 * TC: O(n + m)		-> iterate over both BSTs
	 * SC: O(n + m)
	 */
	public ArrayList<Integer> merge(Node root1, Node root2) {
		ArrayList<Integer> merged = new ArrayList<>();

		inorder(root1, merged);		// get inorder of BST1 and store in a list
		inorder(root2, merged);		// get inorder of BST2 appended to same list

		Collections.sort(merged);	// sort complete list
		return merged;
	}
	private void inorder(Node root, ArrayList<Integer> list) {
		if (root == null)
			return;
		inorder(root.left, list);
		list.add(root.data);
		inorder(root.right, list);
	}
}