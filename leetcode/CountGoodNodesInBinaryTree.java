package leetcode;

import datastructure.TreeNode;

/**
 * <a href=https://leetcode.com/problems/count-good-nodes-in-binary-tree/>1448. Count Good Nodes in Binary Tree</a>
 *
 * @author Himanshu Shekhar
 */

public class CountGoodNodesInBinaryTree {
    int count = 0;

    public void countGoodNodes(TreeNode root, int max) {
        if (root == null)
            return;
        if (root.val >= max) {
            count++;
            max = root.val;
        }
        countGoodNodes(root.left, max);     // count good nodes in left sub-tree
        countGoodNodes(root.right, max);    // count good nodes in right sub-tree
    }

    /**
     * Keep track of the maximum value in the path.
     * If current node's value is less than max value in the path,
     * then that node is not a {@code good node}.
     * <p>Time Complexity: O(n); where n = number of nodes
     * <br>Space Complexity: O(n)
     */
    public int goodNodes(TreeNode root) {
        countGoodNodes(root, Integer.MIN_VALUE);
        return count;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.left.left = new TreeNode(3);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(1);
        root.right.right = new TreeNode(5);

        System.out.println(new CountGoodNodesInBinaryTree().goodNodes(root));
    }
}
