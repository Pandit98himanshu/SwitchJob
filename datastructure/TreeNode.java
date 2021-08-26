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

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public TreeNode(int[] arr) {
        this.root = new TreeNode();
        buildTree(arr, this.root);
    }

    public TreeNode buildTree(int[] arr, TreeNode root) {
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

    public void inorder(TreeNode root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.val + " ");
            inorder(root.right);
        }
    }
}
