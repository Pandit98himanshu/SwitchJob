/*
 * 797. All Paths From Source to Target
 * https://leetcode.com/problems/all-paths-from-source-to-target/
 */

package leetcode;

import java.util.*;

public class AllPathsFromSourceToTarget {
    private final List<List<Integer>> allPaths = new ArrayList<>();

    /**
     * <p><strong>NOT WORKING</strong></p>
     * <strong>Backtracking with memoization</strong>
     * <p>Time Complexity: O(n*2<sup>n</sup>)
     * <br>Space Complexity: O(n*2<sup>n</sup>)
     *
     * @param graph    directed acyclic graph in a jagged array
     * @param memo     hashmap for memoization
     * @param currNode zero initially
     * @param end      target node (same always)
     */
    protected List<List<Integer>> allPathsToTarget(int[][] graph, HashMap<Integer, List<List<Integer>>> memo, int currNode, int end) {
        if (memo.containsKey(currNode))
            return memo.get(currNode);  // we already know all paths from current node to target node

        List<List<Integer>> results = new ArrayList<>();

        // we reach our target node
        if (currNode == end) {
            List<Integer> currPath = new ArrayList<>();
            currPath.add(currNode);     // add current node into current path
            results.add(currPath);      // add current path to all paths
            return results;
        }
        // traverse all connecting nodes from current node
        for (int nextNode : graph[currNode]) {
            // find all paths from next connected node to target node
            for (List<Integer> path : allPathsToTarget(graph, memo, nextNode, end)) {
                List<Integer> newPath = new ArrayList<>();
                // (all paths from current node to target node) =
                // (currNode + (all paths from next node to target node))
                newPath.add(currNode);
                newPath.addAll(path);

                results.add(newPath);   // store all paths from currNode to target node
            }
        }
        // store in memo
        memo.put(currNode, results);
        return results;
    }

    /**
     * <strong>Backtracking</strong>
     * <p>Time Complexity: O(n*2<sup>n</sup>)
     * <br>Space Complexity: O(n*2<sup>n</sup>)
     *
     * @param graph    directed acyclic graph in a jagged array
     * @param currPath the path we are at
     * @param start    zero initially
     * @param end      target node (same always)
     */
    protected void dfs(int[][] graph, List<Integer> currPath, int start, int end) {
        // add current node into current path
        currPath.add(start);
        // we reach our target node
        if (start == end) {
            allPaths.add(new ArrayList<>(currPath));       // add current path to all paths
        }
        // traverse all connecting nodes from start node
        for (int to : graph[start])
            this.dfs(graph, currPath, to, end);
        // remove current node 'cause we need to traverse & find other paths too
        currPath.remove((Integer) start);
    }

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
//        this.dfs(graph, new ArrayList<>(), 0, graph.length - 1);
        allPathsToTarget(graph, new HashMap<>(), 0, graph.length-1);
        return this.allPaths;
    }

    public static void main(String[] args) {
        int[][] graph = new int[][]{{4, 3, 1}, {3, 2, 4}, {3}, {4}, {}};
        System.out.println(new AllPathsFromSourceToTarget().allPathsSourceTarget(graph));
    }
}
