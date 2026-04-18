import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 994. Rotting Oranges
 * https://leetcode.com/problems/rotting-oranges
 */

class Solution {
	/*
	 * Multi-Source BFS
	 * TC: O(m * n)		-> visites every cell exactly once
	 * SC: O(m * n)		-> stores nodes into bfs queue
	 */
	private static final int[] dir = { -1, 0, 1, 0, -1 };
	public int orangesRotting(int[][] grid) {
		int rottenOranges = 0, freshOranges = 0;
		int totalTime = -1;
		Queue<int[]> bfs = new ArrayDeque<>();
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] == 1)
					freshOranges++;									// count # of fresh oranges
				else if (grid[i][j] == 2) {
					rottenOranges++;								// count # of rotten oranges
					bfs.offer(new int[]{i, j});						// store all rotten oranges into a queue for processing
				}
			}
		}

		while (!bfs.isEmpty()) {
			int rottenAtCurrT = bfs.size();
			// all rotten oranges will rot adjacent oranges simultaneously
			while (rottenAtCurrT-- > 0) {
				int[] rottenPos = bfs.poll();
				// search for fresh oranges in all 4 co-ordinal direction of current rotten oranges
				for (int i = 0; i < 4; i++) {
					int nextX = rottenPos[0] + dir[i];
					int nextY = rottenPos[1] + dir[i + 1];
					if ((nextX >= 0 && nextX < grid.length) && (nextY >= 0 && nextY < grid[0].length) && grid[nextX][nextY] == 1) {
						grid[nextX][nextY] = 2;						// if fresh orange present, make it rotten
						bfs.offer(new int[]{ nextX, nextY });		// add it into the queue to make next adjacent oranges rotten
						rottenOranges++;							// increase no. of rotten oranges
						freshOranges--;								// and decrease no. of fresh oranges
					}
				}
			}
			totalTime++;
		}
		// our goal was to rot all fresh oranges
		if (freshOranges > 0)
			return -1;
		else
			return rottenOranges == 0 ? 0 : totalTime;
	}
}