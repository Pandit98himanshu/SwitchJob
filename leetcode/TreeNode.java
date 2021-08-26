package leetcode;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode root;

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

    TreeNode(int[] arr) {
        this.root = new TreeNode();
        this.buildTree(arr, this.root);
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

    public TreeNode buildTreeUtil(String[] arr, TreeNode root, int i) {
        if (i < arr.length && !arr[i].equals("null")) {
            root = new TreeNode(Integer.parseInt(arr[i]));
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
