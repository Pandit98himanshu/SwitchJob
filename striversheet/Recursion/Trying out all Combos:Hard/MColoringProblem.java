import java.util.Arrays;

/**
 * M-Coloring Problem
 * https://www.geeksforgeeks.org/problems/m-coloring-problem-1587115620/1
 */

class Solution {
	/*
	 * TC: O(e * mᵛ)	-> for each backtracking call, checking the validation for all edges (e)
	 * SC: O(v)
	 */
	boolean graphColoring(int v, int[][] edges, int m) {
		int[] colorMap = new int[v];
		Arrays.fill(colorMap, -1);		// initialize with '-1 : no_color'
		return colorBacktracking(v - 1, edges, m, 0, colorMap);
	}

	private boolean colorBacktracking(int v, int[][] edges, int m, int currV, int[] colorMap) {
		// filled all vertices
		if (currV > v)
			return true;
		// start assigning color from 0 – (m - 1)
		for (int i = 0; i < m; i++) {
			colorMap[currV] = i;	// assign color 'i'
			if (valid(edges, colorMap) && colorBacktracking(v, edges, m, currV + 1, colorMap)) {
				return true;		// if the color is valid, move to next vertex
			}
			colorMap[currV] = -1;	// if color is not valid, remove the color for backtracking
		}
		return false;
	}
	// verifies whether 2 adjacent vertices assigned same color
	private boolean valid(int[][] edges, int[] colorMap) {
		for (int[] edge : edges) {
			if (colorMap[edge[0]] != -1 && colorMap[edge[1]] != -1)
				if (edge[0] != edge[1] && colorMap[edge[0]] == colorMap[edge[1]])
					return false;
		}
		return true;
	}
}

public class MColoringProblem {
	public static void main(String[] args) {
		int v = 2;
		int[][] edges = new int[][] { { 0, 0 }, { 0, 1 } };
		int m = 2;
		System.out.println(new Solution().graphColoring(v, edges, m));	// true
	}
}
