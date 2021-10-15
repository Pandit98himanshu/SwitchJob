package leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created at : 08/10/21
 * <p>
 * <a href=https://leetcode.com/problems/populating-next-right-pointers-in-each-node/>116. Populating Next Right Pointers in Each Node</a>
 *
 * @author Himanshu Shekhar
 */

public class PopulatingNextRightPointersInEachNode {
    /**
     * <strong>DFS</strong> : 0 ms
     */
    public Node connect(Node root) {
        dfs(root);
        return root;
    }

    private void dfs(Node root) {
        // base case
        if (root == null) return;
        // if root is not a leaf node
        if (root.left != null) {
            // add reference of immediate right node in current depth level
            root.left.next = root.right;
            if (root.next != null) {
                root.right.next = root.next.left;
            }
        }
        // traverse left and right subtree
        dfs(root.left);
        dfs(root.right);
    }

    /**
     * <strong>BFS</strong> : 1 ms
     */
    public Node connect1(Node root) {
        if (root == null) return root;

        Queue<Node> q = new LinkedList<>();
        int depth = 0;  // current depth level in binary tree
        q.add(root);    // adding root of the binary tree
        while (!q.isEmpty()) {
            Node prev = q.poll();   // take out the left most element of current depth
            // adding their children into the queue
            if (prev.left != null && prev.right != null) {
                q.add(prev.left);
                q.add(prev.right);
            }
            // depth "d" has 2^d number of nodes
            for (int i = 1; i < (1 << depth); i++) {
                Node curr = q.poll();
                prev.next = curr;   // adding next to previous node in current depth level
                prev = curr;        // making current as previous
                // adding children into the queue
                if (prev.left != null && prev.right != null) {
                    q.add(prev.left);
                    q.add(prev.right);
                }
            }
            // going one level deep
            depth++;
        }
        // returning root of the binary tree
        return root;
    }

    /**
     * Structure of a node of binary tree;
     */
    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
}
