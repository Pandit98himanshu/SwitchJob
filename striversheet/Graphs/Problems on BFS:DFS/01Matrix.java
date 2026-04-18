import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 542. 01 Matrix
 * https://leetcode.com/problems/01-matrix
 */

class Solution {
	/*
	 * Multi-Source BFS (Iterative)
	 *
	 * TC: O(m * n)	-> each cell is processed once
	 * SC: O(m * n)	-> store cells in BFS queue for preocessing
	 */
	private static final int[] dir = { -1, 0, 1, 0, -1 };
	public int[][] updateMatrix(int[][] mat) {
		int m = mat.length, n = mat[0].length;
		int[][] retv = new int[m][n];					// stores updated matrix (answer)

		Queue<int[]> bfs = new ArrayDeque<>();
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				retv[i][j] = Integer.MAX_VALUE;
				if (mat[i][j] == 0) {
					bfs.offer(new int[]{ i, j });		// starting point for multi-source BFS
					retv[i][j] = 0;						// cell having ZERO, will require ZERO distance
				}
			}
		}
		int dist = 0;									// distance keep increasing at each level of BFS
		while (!bfs.isEmpty()) {
			int size = bfs.size();
			while (size-- > 0) {						// for all cells at current distance from ZERO
				int[] curr = bfs.poll();
				for (int i = 0; i < 4; i++) {			// check all 4 adjacent cells
					int nextX = curr[0] + dir[i];
					int nextY = curr[1] + dir[i + 1];
					retv[curr[0]][curr[1]] = Math.min(dist, retv[curr[0]][curr[1]]);	// stores min dist from ZERO
					if ((nextX >= 0 && nextX < m) && (nextY >= 0 && nextY < n) && retv[nextX][nextY] == Integer.MAX_VALUE) {
						bfs.offer(new int[]{ nextX, nextY });	// add current cell for further processing
					}
				}
			}
			dist++;										// next level of cells will be 1 distance away from ZERO
		}
		return retv;
	}
}