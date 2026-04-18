/**
 * 547. Number of Provinces
 * https://leetcode.com/problems/number-of-provinces
 */

class Solution {
	/*
	 * TC: O(n)	-> visit 1 city only once
	 * SC: O(n)	-> visited city + recursive stack (when all cities are connected)
	 */
	public int findCircleNum(int[][] isConnected) {
		int n = isConnected.length, provinces = 0;
		boolean[] visited = new boolean[n];

		for (int i = 0; i < n; i++) {			// for each "n" cities
			if (!visited[i]) {					// visit all connected cities if not visited
				dfs(isConnected, visited, i);
				provinces++;					// visited 1 province
			}
		}
		return provinces;
	}
	private void dfs(int[][] isConnected, boolean[] visited, int i) {
		visited[i] = true;						// mark current city visited
		for (int j = 0; j < isConnected[i].length; j++) {	// traverse all connected cities if not visited
			if (isConnected[i][j] == 1 && !visited[j]) {
				dfs(isConnected, visited, j);
			}
		}
	}
}