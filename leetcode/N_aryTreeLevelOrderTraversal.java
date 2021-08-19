/*
 * 429. N-ary Tree Level Order Traversal
 * https://leetcode.com/problems/n-ary-tree-level-order-traversal/
 */
package leetcode;

import java.util.*;

public class N_aryTreeLevelOrderTraversal {
    static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, List<Node> children) {
            this.val = val;
            this.children = children;
        }
    }

    private static final List<List<Integer>> ans = new ArrayList<>();

    static {
        for (int i = 0; i < 1005; i++) {
            ans.add(i, new ArrayList<>());
        }
    }

    public void dfs(Node root, int level) {
        if (root == null) return;
        List<Integer> children;
        print("level:" + level + " val:" + root.val);

        children = ans.get(level);
        children.add(root.val);
        ans.set(level, children);

        print(ans);

        if (root.children == null) return;
        for (Node n : root.children) {
//            print("parent:" + root.val + " children:" + n.val);
            dfs(n, level + 1);
        }
    }

    public List<List<Integer>> levelOrder(Node root) {
        dfs(root, 0);

        List<List<Integer>> finalAns = new ArrayList<>();
        for (List<Integer> list : ans) {
            if (list.isEmpty()) {
                continue;
            }
            finalAns.add(list);
        }
        return finalAns;
    }

    public static void print(Object... O) {
        System.out.println(Arrays.deepToString(O));
    }

    public static void main(String[] args) {
        Node root = new Node(1,
                Arrays.asList(new Node(2), new Node(3,
                        Arrays.asList(new Node(6), new Node(7,
                                Arrays.asList(new Node(11, Arrays.asList(new Node(14))))))), new Node(4,
                        Arrays.asList(new Node(8, Arrays.asList(new Node(12))))), new Node(5,
                        Arrays.asList(new Node(9, Arrays.asList(new Node(13))), new Node(10)))));
        print(new N_aryTreeLevelOrderTraversal().levelOrder(root));
    }
}
