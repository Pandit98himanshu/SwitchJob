/*
 * Transform to Sum Tree
 * https://practice.geeksforgeeks.org/problems/transform-to-sum-tree/1
 */

package leetcode;

import datastructure.TreeNode;

public class TransformToSumTree {
    public void toSumTree(TreeNode root) {
        if (root == null)               // edge case
            return;
        if (root.left == null && root.right == null) {
            root.val = 0;              // leaf node modifies to zero
            return;
        }
        int left = root.left != null ? root.left.val : 0;
        int right = root.right != null ? root.right.val : 0;
        toSumTree(root.left);           // move to left subtree
        toSumTree(root.right);          // move to right subtree

        root.val = (left + right);     // previous values
        left = root.left != null ? root.left.val : 0;
        right = root.right != null ? root.right.val : 0;
        root.val += (left + right);    // values after modification of tree
    }
}
