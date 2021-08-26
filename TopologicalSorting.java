import java.util.*;

public class TopologicalSorting {
    /**
     * <p>Time Complexity: O(E + V)
     * <br>Space Complexity: O(V)
     *
     * @param adjList adjacency list of graph
     * @return a linear ordering of vertices such that
     * for every directed edge u -> v, vertex u comes before v in the ordering
     * @see <a href=https://www.geeksforgeeks.org/topological-sorting/>topological sorting article GfG</a>
     */
    private List<Integer> topologicalSort(List<Integer>[] adjList) {
        int n = adjList.length;
        Stack<Integer> sorted = new Stack<>();
        boolean[] visited = new boolean[n];
        // starts from vertex 0 to n
        for (int i = 0; i < n; i++) {
            if (!visited[i])
                topologicalSortUtil(adjList, sorted, visited, i);
        }
        // storing result
        List<Integer> result = new ArrayList<>();
        while (!sorted.isEmpty())
            result.add(sorted.pop());

        return result;
    }

    private void topologicalSortUtil(List<Integer>[] adjList, Stack<Integer> stk, boolean[] visited, int i) {
        // visit all nodes which are adjacent to the current node
        if (adjList[i] != null) {
            for (int x : adjList[i])
                if (!visited[x])
                    topologicalSortUtil(adjList, stk, visited, x);
        }
        stk.push(i);        // push current vertex into the stack
        visited[i] = true;  // make current vertex visited, so that we'll not visit it again
    }

    public static void main(String[] args) throws Exception {
        int n = 6;
        int[][] arr = {{5, 2}, {5, 0}, {4, 0}, {4, 1}, {2, 3}, {3, 1}};
        // create adjacency list from given array "arr"
        List<Integer>[] adjList = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            List<Integer> list = adjList[arr[i][0]];
            if (list == null)
                list = new ArrayList<>();
            list.add(arr[i][1]);
            adjList[arr[i][0]] = list;
        }
        System.out.println(new TopologicalSorting().topologicalSort(adjList));
    }
}
