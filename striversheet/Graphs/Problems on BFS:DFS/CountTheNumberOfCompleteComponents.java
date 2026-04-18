import java.util.ArrayList;
import java.util.List;

/**
 * 2685. Count the Number of Complete Components
 * https://leetcode.com/problems/count-the-number-of-complete-components
 */

class Solution {
	private static final int[] edgesRequired = new int[51];		// stores edges required for complete connected component when # of vertex is "i"
	static {
		for (int i = 2; i < edgesRequired.length; i++) {
			edgesRequired[i] = (i - 1) + edgesRequired[i - 1];
		}
	}

	public int countCompleteComponents(int n, int[][] edges) {
		int completeComponent = 0;
		List<Integer>[] adjList = new ArrayList[n];
		boolean[] visited = new boolean[n];
		// initialization
		for (int i = 0; i < n; i++)
			adjList[i] = new ArrayList<>();
		// build adjacency list
		for (int[] edge : edges) {
			int a = edge[0], b = edge[1];
			adjList[a].add(b);
			adjList[b].add(a);
		}

		for (int i = 0; i < n; i++) {
			// count edges & vertices in a sub-graph
			if (!visited[i]) {
				int[] verticesEdges = dfs(adjList, i, visited);
				// count edges associated with the vertices in current sub-graph
				int numVertices = verticesEdges[0];
				int numEdges = verticesEdges[1];
				numEdges /= 2;								// an edge will be counted twice from adjacency list
				if (numEdges == edgesRequired[numVertices])
					completeComponent++;					// # of edges in current graph satisfies for complete connected graph
			}
		}
		return completeComponent;
	}
	private int[] dfs(List<Integer>[] adjList, int i, boolean[] visited) {
		visited[i] = true;
		int vertices = 1;							// current vertex is in current graph
		int edges = adjList[i].size();				// edges connect with current vertex

		for (int neighbor : adjList[i]) {			// visit all neighbor nodes if not visited
			if (!visited[neighbor]) {
				int[] verticesEdges = dfs(adjList, neighbor, visited);
				vertices += verticesEdges[0];		// store # of vertices in current connected graph
				edges += verticesEdges[1];			// store # of edges in current connected graph
			}
		}
		return new int[]{vertices, edges};			// return total # of vertices & edges in current connected graph including current vertex
	}
}