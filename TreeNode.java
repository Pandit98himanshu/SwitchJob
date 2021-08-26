public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public TreeNode TreeNode(int[] arr) {
        TreeNode root = new TreeNode();
        buildTree(arr, root, 0);
        return root;
    }

    private TreeNode buildTree(int[] arr, TreeNode root, int i) {
        if (i < arr.length) {
            root = new TreeNode(arr[i]);
            root.left = buildTree(arr, root.left, 2 * i + 1);
            root.right = buildTree(arr, root.right, 2 * i + 2);
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
