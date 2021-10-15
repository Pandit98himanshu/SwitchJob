package leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created at : 10/10/21
 * <p>
 * <a href=https://leetcode.com/problems/rotting-oranges/>994. Rotting Oranges</a>
 *
 * @author Himanshu Shekhar
 */

public class RottingOranges {
    private int[] d = {0, 1, 0, -1, 0};

    /**
     * <strong>Multi-source BFS</strong> : 2 ms
     *
     * @see ZeroOneMatrix.Solution#updateMatrix1(int[][])
     */
    public int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        Queue<int[]> q = new LinkedList<>();    // stores all rotten oranges
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2)
                    q.offer(new int[]{i, j});   // store all rotten oranges into the queue
            }
        }
        int minMin = 0;                         // stores minimum minutes required for rotting all oranges
        while (!q.isEmpty()) {
            int rottenOranges = q.size();
            minMin++;                           // every minute adjacent fresh oranges are rotting
            while (rottenOranges-- > 0) {
                int[] curr = q.poll();          // current rotten orange
                // this will rot it's all 4 adjacent oranges
                for (int i = 0; i < 4; i++) {
                    int nextX = curr[0] + d[i];
                    int nextY = curr[1] + d[i + 1];
                    boolean inBound = (nextX >= 0 && nextX < m) && (nextY >= 0 && nextY < n);
                    // if adjacent orange is fresh, next minute it will rot
                    // and become a source for their adjacent fresh oranges
                    if (inBound && grid[nextX][nextY] == 1) {
                        grid[nextX][nextY] = 2;             // rot adjacent oranges
                        q.offer(new int[]{nextX, nextY});   // now it will become a source
                    }
                }
            }
        }
        // return -1 if any orange remain fresh
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1)
                    return -1;
            }
        }
        return minMin > 0 ? minMin - 1 : 0;
    }
}
