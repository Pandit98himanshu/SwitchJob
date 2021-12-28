package gfg;

/**
 * Node structure for a binary tree
 */
public class Node {
    int data;
    Node left, right;

    Node() {
        data = 0;
        left = right = null;
    }

    Node(int data) {
        this.data = data;
        left = right = null;
    }

    Node(int data, Node left, Node right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
}
