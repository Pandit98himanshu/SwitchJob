import java.util.ArrayList;
import java.util.Collections;

/**
 * Rat in a Maze
 * https://www.geeksforgeeks.org/problems/rat-in-a-maze-problem/1
 */

class Solution {
	private final int[] coords = new int[] { -1, 0, 1, 0, -1 };
	private final char[] dirs = new char[] { 'U', 'R', 'D', 'L' };

	/*
	 * TC: O(4^(n²))	-> 4 options at each cell (n²)
	 * SC: O(n²)	-> total no. of cells in the maze
	 */
	public ArrayList<String> ratInMaze(int[][] maze) {
		ArrayList<String> ans = new ArrayList<>();
		solveMazeDFS(maze, 0, 0, new StringBuilder(), ans);
		Collections.sort(ans);		// return order in lexicographical order
		return ans;
	}

	private void solveMazeDFS(int[][] maze, int x, int y, StringBuilder temp, ArrayList<String> ans) {

		int n = maze.length;
		// condition satisfied
		if (x == n - 1 && y == n - 1) {
			ans.add(temp.toString());
			return;
		}
		// check boundary conditions
		if (x < 0 || x >= n || y < 0 || y >= n)
			return;
		// check walls or visited
		if (maze[x][y] == 0 || maze[x][y] == -1)
			return;

		// mark current cell as visited
		maze[x][y] = -1;
		// visit all 4 directions
		for (int i = 0; i < 4; i++) {
			temp.append(dirs[i]);
			solveMazeDFS(maze, x + coords[i], y + coords[i + 1], temp, ans);
			temp.deleteCharAt(temp.length() - 1);
		}
		// mark current cell as visited
		maze[x][y] = 1;
	}
}

public class RatInAMaze {
	public static void main(String[] args) {
		int n = 5;
		int[][] maze = {{ 1, 1, 1, 0, 1 },
						{ 1, 0, 1, 1, 1 },
						{ 0, 0, 1, 1, 1 },
						{ 1, 0, 0, 1, 1 },
						{ 1, 0, 0, 0, 1 }};
		System.out.println(new Solution().ratInMaze(maze));	// [RRDDRDRD, RRDDRRDD, RRDDRURDDD, RRDRDDRD, RRDRDRDD, RRDRRDDD, RRDRRDLDRD]
	}
}
