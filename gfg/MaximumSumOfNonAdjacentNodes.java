package gfg;

import java.util.HashMap;

/**
 * Created at : 10/12/21
 * <p>
 * <a href=https://www.geeksforgeeks.org/maximum-sum-nodes-binary-tree-no-two-adjacent/>Maximum sum of Non-adjacent nodes</a>
 *
 * @author Himanshu Shekhar
 */

public class MaximumSumOfNonAdjacentNodes {
    private static HashMap<Node, Integer> memo = new HashMap<>();

    /**
     * <strong>Dynamic Programming : top-down</strong>
     *
     * <p>Time Complexity: O(n); we need to traverse each node once
     * <br>Space Complexity: O(n); we have to memoize values for each node
     *
     * @return maximum sum of non-adjacent nodes.
     */
    private static int getMaxSum(Node root) {
        if (root == null)
            return 0;

        // return already calculated value
        if (memo.containsKey(root))
            return memo.get(root);

        // include current node
        int inc = root.data;
        // since we include current node
        // we cannot add its children
        if (root.left != null)
            inc += getMaxSum(root.left.left) + getMaxSum(root.left.right);
        if (root.right != null)
            inc += getMaxSum(root.right.left) + getMaxSum(root.right.right);

        // exclude current node, then consider its children
        int exc = getMaxSum(root.left) + getMaxSum(root.right);

        // store in memo, so that we don't need to calculate values again
        memo.put(root, Math.max(inc, exc));

        // return max sum we can get in left-subtree or right-subtree
        return Math.max(inc, exc);
    }
}
