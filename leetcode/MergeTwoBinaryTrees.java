package leetcode;

import datastructure.TreeNode;

/**
 * Created at : 08/10/21
 * <p>
 * <a href=https://leetcode.com/problems/merge-two-binary-trees/>617. Merge Two Binary Trees</a>
 *
 * @author Himanshu Shekhar
 */

public class MergeTwoBinaryTrees {
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null)
            return root2;
        if (root2 == null)
            return root1;
        helper(root1, root2);
        return root1;
    }

    private void helper(TreeNode root1, TreeNode root2) {
        root1.val = root1.val + root2.val;
        if (root1.left != null && root2.left != null)
            helper(root1.left, root2.left);
        if (root1.right != null && root2.right != null)
            helper(root1.right, root2.right);
        if (root1.left == null && root2.left != null)
            root1.left = root2.left;
        if (root1.right == null && root2.right != null)
            root1.right = root2.right;
    }
}
