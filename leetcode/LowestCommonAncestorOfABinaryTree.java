package leetcode;

public class LowestCommonAncestorOfABinaryTree {
    /**
     * Definition for a binary tree node.
     */
     private static class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode(int x) { val = x; }
     }

    private TreeNode lca;

    /**
     * <p>Time Complexity: O(n); traverse each node once
     * <br>Space Complexity: O(n); storing states of r,left,right for each node
     * @return lowest common ancestor of p and q
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null)
            return null;
        lowestCommonAncestorUtil(root, p, q);
        return this.lca;
    }
    private int lowestCommonAncestorUtil(TreeNode root, TreeNode p, TreeNode q) {
        // either leaf lode or we already found answer
        if (root == null || lca != null)
            return 0;
        // root node is either p or q
        int r = (root == p || root == q) ? 1 : 0;
        // p/q in left subtree
        int left = lowestCommonAncestorUtil(root.left, p, q);
        // p/q in right subtree
        int right = lowestCommonAncestorUtil(root.right, p, q);
        /*
        Possible scenarios:
        case 1: root == (p/q) +
                 (left_subtree.contains(p/q), right_subtree.contains(p/q)) = 2
        case 2: left_subtree.contains(p/q) + right_subtree.contains(p/q) = 2
        case 3: root == (p/q) = 1;
        case 4: left_subtree.contains(p/q) = 1;
        case 5: right_subtree.contains(p/q) = 1;
         */
        if (r + left + right == 2 && lca == null)
            lca = root;

        return r + left + right;
    }
}
