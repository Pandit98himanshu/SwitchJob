/*
 * 1970. Last Day Where You Can Still Cross
 * https://leetcode.com/problems/last-day-where-you-can-still-cross/
 */

package leetcode;

import java.util.*;

public class LastDayWhereYouCanStillCross {
    public int latestDayToCross(int row, int col, int[][] cells) {
        int l = 1, r = cells.length - 1, ans = 0;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (canWalk(cells, mid, row, col)) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }

    public boolean canWalk(int[][] cells, int dayAt, int row, int col) {
        int[][] matrix = new int[row][col];

        for (int i = 0; i < dayAt; i++) {
            matrix[cells[i][0] - 1][cells[i][1] - 1] = 1;
        }

        Queue<int[]> bfs = new ArrayDeque<>();
        for (int j = 0; j < col; j++) {
            if (matrix[0][j] == 0) {
                bfs.offer(new int[]{0, j});
                matrix[0][j] = 1;       // mark as visited
            }
        }

        int[][] moves = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        while (!bfs.isEmpty()) {
            int[] curr = bfs.poll();
            if (curr[0] == row - 1)
                return true;
            for (int i = 0; i < 4; i++) {
                int next_row = curr[0] + moves[i][0];
                int next_col = curr[1] + moves[i][1];
                if (next_row < 0 || next_row >= row ||
                        next_col < 0 || next_col >= col ||
                        matrix[next_row][next_col] == 1)
                    continue;
                bfs.offer(new int[]{next_row, next_col});
                matrix[next_row][next_col] = 1;     // mark as visited
            }
        }
        return false;
    }
}
