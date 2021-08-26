/*
 * 543. Diameter of Binary Tree
 * https://leetcode.com/problems/diameter-of-binary-tree/
 */

package leetcode;

import datastructure.TreeNode;

public class DiameterOfBinaryTree {
    private int diameter;

    /**
     * <p>Time Complexity: O(n)
     * <br>Space Complexity: O(n)
     *
     * @return length of the longest path between any two nodes in a tree
     */
    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = dfs(root.left);      // longest path in left subtree
        int right = dfs(root.right);    // longest path in right subtree
        // diameter = sum of the longest path in left subtree and in right subtree
        diameter = Math.max(diameter, left + right);
        // longest path from curr node = the longest path in left or right subtree + 1 (curr node)
        return Math.max(left, right) + 1;
    }

    public int diameterOfBinaryTree(TreeNode root) {
        this.diameter = 0;
        dfs(root);
        return this.diameter;
    }

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode();
        root1 = root1.buildTree(new int[]{1,2,3,4,5}, root1);
//        root1.inorder(root1);

        TreeNode root2 = new TreeNode(new int[]{1,2,3,4,5});
        root2.inorder(root2);
    }
}
