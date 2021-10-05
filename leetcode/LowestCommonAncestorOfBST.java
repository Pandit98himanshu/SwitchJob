/*
 * 235. Lowest Common Ancestor of a Binary Search Tree
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
 */

package leetcode;

import datastructure.TreeNode;

public class LowestCommonAncestorOfBST {
    static class Solution {
        /**
         * <strong>The lowest common ancestor is defined between two nodes {@code p} and {@code q}
         * as the lowest node in BST that has both {@code p} and {@code q} as descendants
         * (where we allow a node to be a descendant of itself)</strong>
         *
         * @param root root node of BST
         * @return lowest common ancestor of nodes {@code p} ans {@code q}
         */
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            // go to left if root value is greater than both p and q
            if (root.val > p.val && root.val > q.val)
                return lowestCommonAncestor(root.left, p, q);
                // go to right if root value is less than both p and q
            else if (root.val < p.val && root.val < q.val)
                return lowestCommonAncestor(root.right, p, q);
                // the root value is the LCA of nodes p and q
            else
                return root;
        }
    }

    public static void main(String[] args) {
        // building BST
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(4);
        root.left.right.left = new TreeNode(3);
        root.left.right.right = new TreeNode(5);
        root.right = new TreeNode(8);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);

        TreeNode p = new TreeNode(2);
        TreeNode q = new TreeNode(0);

        System.out.println(new Solution().lowestCommonAncestor(root, p, q).val);
    }
}
