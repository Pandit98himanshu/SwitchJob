package leetcode;

import datastructure.TreeNode;

import java.util.*;

/**
 * Created at : 02/09/21
 * <p>
 * <a href=https://leetcode.com/problems/unique-binary-search-trees-ii/>95. Unique Binary Search Trees II</a>
 *
 * @author Himanshu Shekhar
 */

public class UniqueBinarySearchTreesII {
    /**
     * <p>It was difficult to come up with this approach, but I figured the basic idea, which is {@link UniqueBinarySearchTreesII#generateTrees1(int)}.
     *
     * <p>Time Complexity: O(2<sup>n</sup>)
     * <br>Space Complexity: O(2<sup>n</sup>)
     *
     * @see <a href=https://leetcode.com/problems/unique-binary-search-trees-ii/discuss/31494/A-simple-recursive-solution/30203>leetcode discuss comment</a>
     */
    private List<TreeNode> buildTree(int l, int r) {
        List<TreeNode> res = new ArrayList<>();     // store results

        if (l > r) {        // this violates the principle of BST
            res.add(null);  // whence add "null"
            return res;
        }

        // value of current node ranges from l to r, inclusive
        for (int i = l; i <= r; i++) {
            List<TreeNode> left = buildTree(l, i - 1);  // stores left subtree
            List<TreeNode> right = buildTree(i + 1, r); // stores right subtree
            for (TreeNode leftNode : left) {
                for (TreeNode rightNode : right) {
                    // create a node ranges from [l, r] and add left and right subtree
                    TreeNode root = new TreeNode(i, leftNode, rightNode);
                    // after adding left and right subtree, add it to final list
                    res.add(root);
                }
            }
        }

        return res;
    }

    public List<TreeNode> generateTrees(int n) {
        return buildTree(1, n);
    }

    /**
     * <strong>Wrong Answer</strong>
     * <p>Time Complexity: O(2<sup>n * n</sup>)
     */
    List<TreeNode> res = new ArrayList<>();

    private TreeNode buildTree(int n, int l, int r) {
        TreeNode root = null;
        for (int i = l; i <= r; i++) {
            root = new TreeNode(i);     // create a current node
            root.left = buildTree(n, l, i - 1);    // build left subtree
            root.right = buildTree(n, i + 1, r);   // build right subtree

            res.add(root);          // when we create all nodes i.e., from (1 - n) we'll add it to our result
        }
        return root;
    }

    public List<TreeNode> generateTrees1(int n) {
        buildTree(n, 1, n);
        return res;
    }

    public static void main(String[] args) {
        int n = 10;
        List<TreeNode> res = new UniqueBinarySearchTreesII().generateTrees(n);
    }
}
