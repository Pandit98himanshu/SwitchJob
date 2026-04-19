import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Topological Sort
 * https://www.geeksforgeeks.org/problems/topological-sort/1
 */

class Solution {
	/*
	 * TC: O(V²)	-> pop all nodes if a node comes before 1st node in the stack
	 * SC: O(V)		-> use Stack for storing all nodes + HashMap for tracking visited
	 */
	public ArrayList<Integer> topoSort(int V, int[][] edges) {
		Stack<Integer> stk = new Stack<>();					// stores vertices in topologically sorted order
		Map<Integer, Integer> visited = new HashMap<>();	// stores { val, position in the stack }

		// put all vertices in topologically sorted order based on directed edge
		for (int[] edge : edges) {
			// direction = u -> v
			int u = edge[0];
			int v = edge[1];
			// if both the nodes are not visited
			if (!visited.containsKey(u) && !visited.containsKey(v)) {
				stk.push(u);							// 1st put 'u'
				visited.put(u, stk.size());

				stk.push(v);							// then comes 'v'
				visited.put(v, stk.size());
			}
			// later vertix is already present somewhere
			else if (!visited.containsKey(u)) {
				Stack<Integer> temp = new Stack<>();

				while (stk.peek() != v)					// pop till node 'v'
					temp.push(stk.pop());
				stk.pop();								// pop 'v'

				stk.push(u);							// first comes 'u'
				visited.put(u, stk.size());

				stk.push(v);							// then comes 'v'
				visited.put(v, stk.size());

				while (!temp.isEmpty()) {
					int val = temp.pop();
					stk.push(val);						// put later nodes
					visited.put(val, stk.size());
				}
			} else if (!visited.containsKey(v)) {
				stk.push(v);							// 'u' is already present, hence adding 'v' after that
				visited.put(v, stk.size());
			} else {
				// check whether ordering is correct in the stack
				int uPos = visited.get(u);				// getting 'u' position in stack
				int vPos = visited.get(v);				// getting 'v' position in stack
				if (uPos < vPos)						// do nothing, if 'u' & 'v' are already in sorted order
					continue;
				
				// 'v' comes before 'u' in the topological sorting
				Stack<Integer> temp = new Stack<>();

				while (stk.peek() != u)
					temp.push(stk.pop());				// take out all node until 'u'
				stk.pop();								// pop 'u'

				while (stk.peek() != v)
					temp.push(stk.pop());				// then take all nodes until 'v'
				stk.pop();								// pop 'v'

				stk.push(u);							// 1st put 'u'
				visited.put(u, stk.size());

				stk.push(v);							// then comes 'v'
				visited.put(v, stk.size());

				while (!temp.isEmpty()) {
					int val = temp.pop();
					stk.push(val);						// then put rest of the nodes
					visited.put(val, stk.size());
				}
			}
		}
		// isolated nodes can come anywhere, hence putting at the very end
		for (int i = 0; i < V; i++) {
			if (!visited.containsKey(i))
				stk.push(i);
		}
		return new ArrayList<>(stk);
	}
}