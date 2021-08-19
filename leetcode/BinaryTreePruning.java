/*
 * 814. Binary Tree Pruning
 * https://leetcode.com/problems/binary-tree-pruning/
 */

package leetcode;

public class BinaryTreePruning {
    /**
     * Definition for a binary tree node.
     */
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }

    static class Solution {
        /**
         * <p>Time Complexity: O(n)
         * <br>Space Complexity: O(n);
         * where n = total number of nodes in binary tree</p>
         *
         * @param root root node of binary tree
         * @return the same tree where every subtree
         * (of the given tree) not containing a 1 has been removed
         */
        public TreeNode pruneTree(TreeNode root) {
            return prune(root);
        }

        /**
         * Helper function
         *
         * @return pruned binary tree
         */
        public TreeNode prune(TreeNode node) {
            if (node == null)
                return null;
            if (node.left != null)
                node.left = prune(node.left);
            if (node.right != null)
                node.right = prune(node.right);
            // delete leaf node if its value is zero
            if (node.left == null && node.right == null && node.val == 0) {
                node = null;
            }
            return node;
        }

        /**
         * <strong>NOT WORKING</strong>
         */
        public TreeNode pruneTree1(TreeNode root) {
            if (root == null) return null;

            root.left = pruneTree(root.left);
            root.right = pruneTree(root.right);
            if (!contains1(root)) {
                root = null;
            }
            return root;
        }

        public boolean contains1(TreeNode root) {
            if (root == null) return false;
            if (root.val == 1) return true;
            boolean left = contains1(root.left);
            boolean right = contains1(root.right);

            return left && right && root.val == 1;
        }
    }

    /**
     * Print binary tree in inorder fashion
     */
    public static void printBinaryTree(TreeNode root) {
        if (root == null) {
            return;
        }
        printBinaryTree(root.left);
        System.out.print(root.val + ", ");
        printBinaryTree(root.right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(0);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(1);

        printBinaryTree(root);
        System.out.println("\nPruned: ");
        printBinaryTree(new Solution().pruneTree(root));
    }
}
