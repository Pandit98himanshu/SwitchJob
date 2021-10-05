package leetcode;

import com.sun.source.tree.Tree;
import datastructure.TreeNode;
import datastructure.Trie;

import java.util.*;

/**
 * Created at : 10/09/21
 * <p>
 * <a href=https://leetcode.com/problems/serialize-and-deserialize-binary-tree/>297. Serialize and Deserialize Binary Tree</a>
 *
 * @author Himanshu Shekhar
 */


class Codec {
    /**
     * Used BFS to serialize the binary tree
     * <p>Time Complexity: O(N)
     * <br>Space Complexity: O(N)
     *
     * @return encoded data of given binary tree in string
     */
    public String serialize(TreeNode root) {
        // tree does not exist, return "null"
        if (root == null)
            return "null";

        StringBuilder ser = new StringBuilder();    // stores encoded form of binary tree
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode curr = q.poll();
            if (curr == null) {
                ser.append("null,");                // if there are no nodes, append "null"
            } else {
                q.add(curr.left);                   // traverse left subtree
                q.add(curr.right);                  // traverse right subtree
                ser.append(curr.val + ",");         // append current node to encoding string
            }
        }
        return ser.toString();
    }

    /**
     * Reverse method of serialization
     * <p>Time Complexity: O(N)
     * <br>Space Complexity: O(N)
     *
     * @return decoded binary tree from encoded string
     */
    public TreeNode deserialize(String data) {
        String[] nodes = data.split(",");   // split into array
        TreeNode root;                          // root of binary tree
        // if root node of binary tree is "null"
        // (1st node in given string is root node)
        // tree does not exist
        if (nodes[0].equals("null"))
            return null;

        root = new TreeNode(Integer.parseInt(nodes[0]));
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int i = 1;
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            // add left node if it's not "null"
            if (i < nodes.length && !nodes[i].equals("null")) {
                TreeNode left = new TreeNode(Integer.parseInt(nodes[i]));   // create left node
                node.left = left;           // connect left node to it's parent node
                q.add(left);                // create left subtree
            }
            i++;
            // add right node if it's not "null"
            if (i < nodes.length && !nodes[i].equals("null")) {
                TreeNode right = new TreeNode(Integer.parseInt(nodes[i]));  // create right node
                node.right = right;         // connect right node to it's parent node
                q.add(right);               // create right subtree
            }
            i++;
        }
        return root;
    }
}

public class SerializeAndDeserializeBinaryTree {
    public static void main(String[] args) {
        Codec ser = new Codec();
        Codec deser = new Codec();

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);
        root.right.left.left = new TreeNode(0);
        root.right.left.right = new TreeNode(7);
        root.right.left.left.left = new TreeNode(6);

        TreeNode ans = deser.deserialize(ser.serialize(root));
        ans.inorder(ans);
    }
}
