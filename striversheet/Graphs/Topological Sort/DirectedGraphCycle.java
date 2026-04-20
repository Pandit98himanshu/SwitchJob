import java.util.*;

/**
 * Directed Graph Cycle
 * https://www.geeksforgeeks.org/problems/detect-cycle-in-a-directed-graph/1
 */

class Solution {
	/*
	 * Kahn's Algorithm
	 * TC: O(V + E)
	 * SC: O(V)
	 */
	public boolean isCyclic(int V, int[][] edges) {
		List<List<Integer>> adjList = new ArrayList<>();
		int[] indegree = new int[V];
		Queue<Integer> bfsKahn = new ArrayDeque<>();
		List<Integer> topoSort = new ArrayList<>();
		
		for (int i = 0; i < V; i++)
			adjList.add(new ArrayList<>());			// inititalize adjacency list

		for (int[] edge : edges) {
			int u = edge[0];
			int v = edge[1];

			indegree[v]++;							// indegree if vertex 'v' will increase by 1
			adjList.get(u).add(v);					// u -> v
		}

		
		for (int i = 0; i < V; i++) {
			if (indegree[i] == 0)
				bfsKahn.offer(i);					// store all vertices having indegree 0, will come 1st in the topological sorting
		}

		while (!bfsKahn.isEmpty()) {
			int currV = bfsKahn.poll();
			topoSort.add(currV);					// create topological sorting
			for (int next : adjList.get(currV)) {
				indegree[next]--;					// decrease the indegree of connected next vertices by 1
				if (indegree[next] == 0)
					bfsKahn.offer(next);			// if no vertex previous to "next" node, add it for traversal
			}
		}
		return topoSort.size() != V;				// if all nodes not appeared in topological sorting, then there must be a cycle
	}
}