import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

/**
 * Shortest Path in Undirected Graph
 * https://www.geeksforgeeks.org/problems/shortest-path-in-undirected-graph-having-unit-distance/1
 */

class Solution {
	/*
	 * TC: O(V + E)
	 * SC: O(V + E)	-> store all vertices & it's edges in adj. list
	 */
	public int[] shortestPath(int V, int[][] edges, int src) {
		int[] ans = new int[V];
		List<List<Integer>> adjList = new ArrayList<>();
		Queue<int[]> bfs = new ArrayDeque<>();
		
		Arrays.fill(ans, -1);					// initialize all node dist with 'src' as -1 (not reachable)
		
		for (int i = 0; i < V; i++)
			adjList.add(new ArrayList<>());		// initialize adjacency list
		
		for (int[] edge : edges) {
			int u = edge[0];
			int v = edge[1];
			
			adjList.get(u).add(v);				// u -> v
			adjList.get(v).add(u);				// v -> u (non-directional)
		}
		
		bfs.offer(new int[]{src, 0});			// reaching source vertex will have ZERO distance
		
		while (!bfs.isEmpty()) {
			int size = bfs.size();				// for all vertices at same distance
			while (size-- > 0) {
				int[] curr = bfs.poll();
				int node = curr[0];
				int dist = curr[1];

				ans[node] = ans[node] == -1 ? dist : Math.min(ans[node], dist); // store min. dist from 'src' vertex

				for (int next : adjList.get(node)) {
					if (ans[next] == -1) {
						bfs.offer(new int[]{next, dist + 1});		// put next node in queue for processing in next batch with increased distance by 1 from 'src'
					}
				}
			}
		}
		return ans;
	}
}