package datastructure;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode root;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public TreeNode(int[] arr) {
        this.root = new TreeNode();
        buildTree(arr, this.root);
    }

    /**
     * Build binary tree from given array {@code arr}
     *
     * @param arr  given array in integer format
     * @param root root of the binary tree
     */
    public TreeNode buildTree(int[] arr, TreeNode root) {
        return buildTreeUtil(arr, root, 0);
    }

    /**
     * Build binary tree from given array {@code arr}
     *
     * @param arr  given array in String format
     * @param root root of the binary tree
     */
    public TreeNode buildTree(String[] arr, TreeNode root) {
        return buildTreeUtil(arr, root, 0);
    }

    private TreeNode buildTreeUtil(int[] arr, TreeNode root, int i) {
        if (i < arr.length) {
            root = new TreeNode(arr[i]);
            root.left = buildTreeUtil(arr, root.left, 2 * i + 1);
            root.right = buildTreeUtil(arr, root.right, 2 * i + 2);
        }
        return root;
    }

    private TreeNode buildTreeUtil(String[] arr, TreeNode root, int i) {
        if (i < arr.length) {
            if (arr[i].equals("null") || arr[i].equals("N"))
                return root;
            root = new TreeNode(Integer.parseInt(arr[i]));
            root.left = buildTreeUtil(arr, root.left, 2 * i + 1);
            root.right = buildTreeUtil(arr, root.right, 2 * i + 2);
        }
        return root;
    }

    /**
     * Inorder traversal
     * @param root root node of binary tree
     */
    public void inorder(TreeNode root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.val + " ");
            inorder(root.right);
        }
    }

    /**
     * Preorder traversal
     * @param root root node of binary tree
     */
    public void preorder(TreeNode root) {
        if (root != null) {
            System.out.print(root.val + " ");
            preorder(root.left);
            preorder(root.right);
        }
    }
}
