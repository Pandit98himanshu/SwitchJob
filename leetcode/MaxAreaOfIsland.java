package leetcode;

/**
 * Created at : 07/10/21
 * <p>
 * <a href=https://leetcode.com/problems/max-area-of-island/>695. Max Area of Island</a>
 *
 * @author Himanshu Shekhar
 */

public class MaxAreaOfIsland {
    /**
     * <strong>DFS algorithm</strong>
     */
    public int maxAreaOfIsland(int[][] grid) {
        int maxArea = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    maxArea = Math.max(maxArea, dfs(grid, i, j, 1));
                }
            }
        }
        return maxArea;
    }

    private int dfs(int[][] grid, int x, int y, int area) {
        grid[x][y] = 0;

        int[] d = {0, 1, 0, -1, 0};
        for (int i = 0; i < 4; i++) {
            int nextX = x + d[i], nextY = y + d[i + 1];
            boolean inBound = (nextX >= 0 && nextX < grid.length) && (nextY >= 0 && nextY < grid[x].length);
            if (inBound && grid[nextX][nextY] == 1)
                area = dfs(grid, nextX, nextY, area + 1);
        }
        return area;
    }
}
