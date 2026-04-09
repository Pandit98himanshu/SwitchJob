import java.util.ArrayList;
import java.util.List;

/**
 * 173. Binary Search Tree Iterator
 * https://leetcode.com/problems/binary-search-tree-iterator
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

class BSTIterator {
	private static List<TreeNode> inorder;	// can use Stack too

	/*
	 * TC: O(log n)
	 * SC: O(log n)
	 */
	public BSTIterator(TreeNode root) {
		inorder = new ArrayList<>();
		TreeNode temp = root;
		while (temp != null) {
			inorder.add(temp);				// put all left elements
			temp = temp.left;
		}
	}
	
	/*
	 * TC: θ(1)
	 * SC: O(log n)
	 */
	public int next() {
		TreeNode retv = null;
		if (hasNext()) {
			retv = inorder.get(inorder.size() - 1);
			inorder.remove(inorder.size() - 1);		// remove used element

			TreeNode temp = retv.right;				// add all left elements of right subtree
			while (temp != null) {
				inorder.add(temp);
				temp = temp.left;
			}
		}
		return retv.val;
	}
	
	/*
	 * TC: O(1)
	 * SC: O(1)
	 */
	public boolean hasNext() {
		return !inorder.isEmpty();
	}
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */