package leetcode;

import datastructure.TreeNode;

/**
 * Created at : 10/09/21
 * <p>
 * 297. Serialize and Deserialize Binary Tree
 * <br>
 * https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
 * @author Himanshu Shekhar
 */
public class SerializeAndDeserializeBinaryTree {
    static class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            return "";
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            return null;
        }
    }

    public static void main(String[] args) {
        Codec ser = new Codec();
        Codec deser = new Codec();

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        TreeNode ans = deser.deserialize(ser.serialize(root));
        ans.inorder(ans);
    }
}
