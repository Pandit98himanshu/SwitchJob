package leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created at : 09/10/21
 * <p>
 * <a href=https://leetcode.com/problems/01-matrix/>542. 01 Matrix</a>
 *
 * @author Himanshu Shekhar
 */
public class ZeroOneMatrix {
    public static void print(Object... O) {
        System.out.println(Arrays.deepToString(O));
    }

    public static void main(String[] args) {
        int[][] mat = {{0, 0, 0}, {0, 1, 0}, {1, 1, 1}};
        print(new Solution().updateMatrix(mat));
    }

    static class Solution {
        private int m, n;
        private int[] d = {0, 1, 0, -1, 0};
        private int[][] minDist;

        /**
         * <strong>DP</strong> : 3 ms
         */
        public int[][] updateMatrix(int[][] mat) {
            m = mat.length;
            n = mat[0].length;

            // Use mat[][] as a dynamic programming array. We need two passes
            // through the dp array. One pass scanning cells moving down and right,
            // the other up and left. We make processing more efficient for larger
            // arrays by referencing the rows of the array through one-dimensional
            // arrays row[] and prevRow[], to save the time of repeatedly using two
            // indexes. Also use separate processing for the
            // 1) starting corner cell,
            // 2) starting row,
            // 3) all other cells.
            // This saves us from doing separate checks in a single generalized loop
            // that would need to repeatedly check to see if we are at the starting
            // row or the starting column.

            // ------- Pass #1: Scan down and right.  Look up and left from each cell.
            int[] row = mat[0];
            int[] prevRow;
            if (row[0] == 1)                        // Top left corner
                row[0] = m + n;
            for (int c = 1; c < n; c++)             // Top row (except top left corner)
                if (row[c] == 1)
                    row[c] = row[c - 1] + 1;
            for (int r = 1; r < m; r++) {           // All other rows (not first row)
                prevRow = row;
                row = mat[r];
                if (row[0] == 1)                    // First column in the current row
                    row[0] = prevRow[0] + 1;
                for (int c = 1; c < n; c++)         // Other columns in the current row
                    if (row[c] == 1)
                        row[c] = Math.min(row[c - 1], prevRow[c]) + 1;
            }

            // ------- Pass #2: Scan left and up.  Look right and below from each cell.
            row = mat[m - 1];
            for (int c = n - 2; c >= 0; c--)        // Bottom row (except bottom right corner)
                if (row[c] > 1)
                    row[c] = Math.min(row[c], row[c + 1] + 1);
            for (int r = m - 2; r >= 0; r--) {      // All other rows (not bottom row)
                prevRow = row;
                row = mat[r];
                if (row[n - 1] > 1)                 // Rightmost column in current row
                    row[n - 1] = Math.min(row[n - 1], prevRow[n - 1] + 1);
                for (int c = n - 2; c >= 0; c--)    // Other columns in current row
                    if (row[c] > 1)
                        row[c] = Math.min(row[c], Math.min(row[c + 1], prevRow[c]) + 1);
            }
            return mat;
        }

        /**
         * <strong>Left & right swipe</strong> : 5 ms
         */
        public int[][] updateMatrix3(int[][] mat) {
            m = mat.length;
            n = mat[0].length;
            final int inf = Integer.MAX_VALUE - 10000;      // represents infinity
            // we subtracted 10^4, because maximum order of "mat" is 10^4

            minDist = new int[m][n];                // stores result
            // traverse whole array from top-left to bottom-right
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (mat[i][j] != 0) {
                        // find minimum from top and left adjacent cell
                        minDist[i][j] = Math.min(
                                        (i - 1 < 0) ? inf : minDist[i - 1][j] + 1,
                                        (j - 1 < 0) ? inf : minDist[i][j - 1] + 1
                        );
                    }
                }
            }
            // traverse whole array from bottom-right to top-left
            for (int i = m - 1; i >= 0; i--) {
                for (int j = n - 1; j >= 0; j--) {
                    if (mat[i][j] != 0) {
                        // find minimum from bottom and right adjacent cell
                        minDist[i][j] = Math.min(minDist[i][j],
                                Math.min(
                                        (i + 1 >= m) ? minDist[i][j] : minDist[i + 1][j] + 1,
                                        (j + 1 >= n) ? minDist[i][j] : minDist[i][j + 1] + 1
                                )
                        );
                    }
                }
            }
            return minDist;
        }

        /**
         * <strong>DFS</strong> : 1724 ms
         * <p>Copied from <a href=https://leetcode.com/problems/01-matrix/discuss/1371441/Java-DFS>letcode discuss</a>
         */
        public int[][] updateMatrix2(int[][] mat) {
            m = mat.length;
            n = mat[0].length;
            minDist = new int[m][n];

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    // considering zeroes as source,
                    // start filling resultant array
                    if (mat[i][j] == 0) {
                        startFill(mat, i, j, 0);
                    }
                }
            }
            return minDist;
        }

        private void startFill(int[][] mat, int x, int y, int currVal) {
            boolean inBound = (x >= 0 && x < m && y >= 0 && y < n);
            // change value of current cell and visit adjacent cells, if
            // 1) it is not visited (current cell has "1" & it's min distance is "0")
            // 2) current distance value is lesser than previous
            if (inBound && ((currVal == 0 || mat[x][y] == 1) && (minDist[x][y] == 0 || minDist[x][y] > currVal))) {
                minDist[x][y] = currVal;
                // visit all 4 adjacent cells and change their values
                for (int i = 0; i < 4; i++) {
                    int nextX = x + d[i];
                    int nextY = y + d[i + 1];
                    startFill(mat, nextX, nextY, currVal + 1);
                }
            }
        }

        /**
         * <strong>Multi-source BFS</strong> : 14 ms
         *
         * <p>The idea is, all 4 adjacent cells around cell containing 0 is 1,
         * and all 4 adjacent cells around cell containing 1 is 2, and-so-on...
         */
        public int[][] updateMatrix1(int[][] mat) {
            m = mat.length;
            n = mat[0].length;
            minDist = new int[m][n];

            Queue<int[]> q = new LinkedList<>();        // store sources
            // traverse whole array, mark all zeroes & store it as source in queue
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (mat[i][j] == 0) {
                        q.add(new int[]{i, j});         // add all zero positions in the queue
                    } else {
                        minDist[i][j] = Integer.MAX_VALUE;
                    }
                }
            }

            while (!q.isEmpty()) {
                int[] curr = q.poll();                  // polling each source
                for (int i = 0; i < 4; i++) {           // start traversing its neighbours
                    int nextX = curr[0] + d[i];
                    int nextY = curr[1] + d[i + 1];
                    boolean inBound = (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n);
                    // store minimum distance from zero and also traverse its neighbors
                    if (inBound && minDist[nextX][nextY] > minDist[curr[0]][curr[1]] + 1) {
                        minDist[nextX][nextY] = minDist[curr[0]][curr[1]] + 1;
                        q.add(new int[]{nextX, nextY});
                    }
                }
            }
            return minDist;
        }
    }
}
