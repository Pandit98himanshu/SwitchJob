package leetcode;

import java.util.*;
import datastructure.TreeNode;

/**
 * Created at : 27/01/22
 * <p>
 * <a href=https://leetcode.com/problems/all-elements-in-two-binary-search-trees/>1305. All Elements in Two Binary Search Trees</a>
 *
 * @author Himanshu Shekhar
 */

public class AllElementsInTwoBinarySearchTrees {
    /**
     * <strong>Min Heap</strong> - WRONG ANSWER
     */
    public List<Integer> getAllElements1(TreeNode root1, TreeNode root2) {

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root1);
        q.add(root2);
        while(!q.isEmpty()) {
            TreeNode curr = q.poll();
            if (curr == null)
                continue;
            pq.add(curr.val);
            System.out.println(pq);
            if (curr.left != null) q.add(curr.left);
            if (curr.right != null) q.add(curr.right);
        }

        return new ArrayList<Integer>(pq);
    }

    /**
     * <strong>Hashing</strong>
     * <p>Time Complexity: O(n1 + n2) + O(2 * 10<sup>5</sup>) : 58 ms
     * <br>Space Complexity: O(n1 + n2) + O(2 * 10<sup>5</sup>)
     */
    public List<Integer> getAllElements2(TreeNode root1, TreeNode root2) {
        int[] neg = new int[(int)1e5+1];
        int[] pos = new int[(int)1e5+1];

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root1);
        q.add(root2);
        while(!q.isEmpty()) {
            TreeNode curr = q.poll();
            if (curr == null)
                continue;

            if (curr.val < 0)
                neg[-1 * curr.val]++;
            else
                pos[curr.val]++;

            if (curr.left != null) q.add(curr.left);
            if (curr.right != null) q.add(curr.right);
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = neg.length-1; i >= 0; i--)
            if (neg[i] > 0)
                while(neg[i]-- > 0)
                    ans.add(-1 * i);

        for (int i = 0; i < pos.length; i++)
            if (pos[i] > 0)
                while(pos[i]-- > 0)
                    ans.add(i);

        return ans;
    }

    /**
     * <strong>Inorder + Merge 2 sorted array</strong>
     * <p>Time Complexity: O(2 * (n1 + n2)) : 38 ms
     * <br>Space Complexity: O(2 * (n1 + n2))
     */
    public List<Integer> getAllElements3(TreeNode root1, TreeNode root2) {
        List<Integer> inorder1 = new ArrayList<>();
        List<Integer> inorder2 = new ArrayList<>();

        getInorder(root1, inorder1);
        getInorder(root2, inorder2);

        List<Integer> res = new ArrayList<>();
        int i = 0, j = 0;
        while(i < inorder1.size() && j < inorder2.size()) {
            if (inorder1.get(i) <= inorder2.get(j))
                res.add(inorder1.get(i++));
            else
                res.add(inorder2.get(j++));
        }

        while(i < inorder1.size())
            res.add(inorder1.get(i++));
        while(j < inorder2.size())
            res.add(inorder2.get(j++));

        return res;
    }
    private void getInorder(TreeNode root, List<Integer> inorder) {
        if (root == null)
            return;
        getInorder(root.left, inorder);
        inorder.add(root.val);
        getInorder(root.right, inorder);
    }

    /**
     * <strong>Inorder</strong>
     * <p>Time Complexity: O(n1 + n2) : 18 ms
     * <br>Space Complexity: O(n1 + n2)
     */
    private int i = 0;
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> in = new ArrayList<>();
        List<Integer> res = new ArrayList<>();

        inorder(root1, in);
        inorder(root2, in, res);

        while (i < in.size())
            res.add(in.get(i++));

        return res;
    }
    private void inorder(TreeNode root, List<Integer> in) {
        if (root == null)
            return;
        inorder(root.left, in);
        in.add(root.val);
        inorder(root.right, in);
    }
    private void inorder(TreeNode root, List<Integer> in, List<Integer> res) {
        if (root == null)
            return;

        inorder(root.left, in, res);

        while(i < in.size() && in.get(i) <= root.val)
            res.add(in.get(i++));

        res.add(root.val);

        inorder(root.right, in, res);
    }
}
