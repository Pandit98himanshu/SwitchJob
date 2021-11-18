package leetcode;

import datastructure.TreeNode;

import java.util.*;

/**
 * Created at : 17/09/21
 * <p>
 * <a href=https://leetcode.com/problems/path-sum-ii/>113. Path Sum II</a>
 *
 * @author Himanshu Shekhar
 */

public class PathSumII {
    List<List<Integer>> res = new ArrayList<>();

    public void dfs(TreeNode root, int targetSum, int currSum, int[] list, int index) {
        if (root == null) return;

        currSum += root.val;

        // this should not be there because there might be some negative values
        // if (Math.abs(currSum) > Math.abs(targetSum)) return;

        list[index++] = root.val;
        if ((root.left == null && root.right == null) && currSum == targetSum) {
            ArrayList<Integer> al = new ArrayList<>();
            for (int i = 0; i < index; i++) {
                al.add(list[i]);
            }
            res.add(al);
            return;
        }

        if (root.left != null)
            dfs(root.left, targetSum, currSum, list, index);
        if (root.right != null)
            dfs(root.right, targetSum, currSum, list, index);
    }

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        dfs(root, targetSum, 0, new int[50000], 0);
        return res;
    }

    public void print(Object... O) {
        System.out.println(Arrays.deepToString(O));
    }

    public static void main(String[] args) {
        int[] list = {1, 3, 5, 2, 6, 5, 2, 8, 6, 0, 1};
        Arrays.stream(list).filter(i -> i > 2).forEach(i -> System.out.print(i + " "));
    }
}
