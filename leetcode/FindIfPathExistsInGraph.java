package leetcode;

import java.util.*;

public class FindIfPathExistsInGraph {
    /**
     * <strong>Using DFS</strong>
     * <p>We can also solve this problem using <strong>Disjoint Set</strong>.
     * If root node of both {@code start} and {@code end} node are same then
     * they must be connected (directly or indirectly).
     */
    public Map<Integer, ArrayList<Integer>> createGraph(int[][] edges) {
        Map<Integer, ArrayList<Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            ArrayList<Integer> to1 = graph.getOrDefault(edge[0], new ArrayList<>());
            ArrayList<Integer> to2 = graph.getOrDefault(edge[1], new ArrayList<>());

            to1.add(edge[1]);
            to2.add(edge[0]);
            graph.put(edge[0], to1);
            graph.put(edge[1], to2);
        }
        return graph;
    }

    boolean sol = false;

    public void dfs(Map<Integer, ArrayList<Integer>> graph, HashSet<Integer> visited, int start, int end) {
        // if start is only the end node (edge case)
        if (start == end)
            sol = true;

        ArrayList<Integer> list = graph.get(start);
        if (list != null) {
            for (int i : list) {
                // if a node is already visited, we'll not visit again
                if (visited.contains(i))
                    continue;
                if (i == end)       // there's an edge from start to end
                    sol = true;

                visited.add(i);     // mark current node as visited
                // make current node as start node and search for the end node
                dfs(graph, visited, i, end);
            }
        }
    }

    public boolean validPath(int n, int[][] edges, int start, int end) {
        Map<Integer, ArrayList<Integer>> graph = createGraph(edges);
//        System.out.println(graph);
        dfs(graph, new HashSet<>(), start, end);
        return sol;
    }

    public static void main(String[] args) {
        int n = 6, start = 0, end = 5;
        int[][] edges = {{0, 1}, {0, 2}, {3, 5}, {5, 4}, {4, 3}};

        System.out.println(new FindIfPathExistsInGraph().validPath(n, edges, start, end));
    }
}
