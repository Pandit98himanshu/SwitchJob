/*
 * 5793. Nearest Exit from Entrance in Maze
 * https://leetcode.com/problems/nearest-exit-from-entrance-in-maze/
 */

package leetcode;

class _NearestExitfromEntranceinMaze {
    // https://leetcode.com/problems/nearest-exit-from-entrance-in-maze/discuss/1329077/JAVA-DFS-beats-100
    static class Solution {

        public int dfs (char[][] maze, int x, int y, int m, int n) {
            // base case
            if (x < 0 || y < 0 || x > m || y > n) {
                System.out.println("Exit");
                return Integer.MAX_VALUE;
            }
            if (maze[x][y] == '+') {
                System.out.println("Wall");
                return Integer.MAX_VALUE;
            }
            if (x == 0 || y == 0 || x == m-1 || y == n-1) {
                System.out.println("Boundary");
                return 1;
            }

            int up = dfs(maze, x-1, y, m, n) + 1;
            int down = dfs(maze, x+1, y, m, n) + 1;
            int left = dfs(maze, x, y-1, m, n) + 1;
            int right = dfs(maze, x, y+1, m, n) + 1;

            // mark current position as un-visited, so that we can again
            // reach this position from another route (which may be shorter)
            maze[x][y] = '.';

            int min = Math.min(Math.min(up, down), Math.min(left, right));
            if (min != Integer.MAX_VALUE || min < 0) {
                min += 1;
            }
            return min;
        }

        public int nearestExit(char[][] maze, int[] entrance) {
            int m = maze.length, n = maze[0].length;
            int x = entrance[0], y = entrance[1];
            // mark starting position as visited
            maze[x][y] = '+';
            int ans = new Solution().dfs(maze, x, y, m, n);

            return (ans==Integer.MAX_VALUE) ? -1 : ans;
        }
    }

	public static void main(String[] args) {
		char[][] maze = {{'+','+','.','+'},
						{'.','.','.','+'},
						{'+','+','+','.'}};
		int[] entrance = {1,2};

		System.out.println(new Solution().nearestExit(maze, entrance));
	}
}