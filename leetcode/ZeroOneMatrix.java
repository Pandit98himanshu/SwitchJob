/*
 * 542. 01 Matrix
 * https://leetcode.com/problems/01-matrix/
 */

package leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class _ZeroOneMatrix {
    public static void print(Object... O) {
        System.out.println(Arrays.deepToString(O));
    }

    public static void main(String[] args) {
        int[][] mat = {{0, 0, 0}, {0, 1, 0}, {1, 1, 1}};
        print(new Solution().updateMatrix(mat));
    }

    static class Solution {
        /**
         * <strong>Using DP</strong>
         */
        private int m, n;
        private int[] d = {0, 1, 0, -1, 0};
        private int[][] ans;

        public int[][] updateMatrix(int[][] mat) {
            m = mat.length;
            n = mat[0].length;
            ans = new int[m][n];

            for (int[] row : ans)
                Arrays.fill(row, Integer.MAX_VALUE);

            outer:
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (mat[i][j] == 0) {
                        startFill(mat, i, j, 0);
                    }
                }
            }
            return ans;
        }

        private void startFill(int[][] mat, int x, int y, int val) {
            boolean outOfBound = x < 0 || x >= m || y < 0 || y >= n;
            if (outOfBound)
                return;

            if (mat[x][y] == 1 || val == 0 && ans[x][y] == 0)
                val = 0;

            ans[x][y] = Math.min(ans[x][y], val);

            for (int i = 0; i < 4; i++) {
                int next_x = x + d[i], next_y = y + d[i + 1];
                if ()
                    startFill(mat, next_x, next_y, val + 1);
            }
        }

        /**
         * The idea is, all 4 adjacent cells around cell containing {@code 0} is {@code 1},
         * and all 4 adjacent cells around cell containing {@code 1} is {@code 2} (if {@code 0} is not present)
         */
        public int[][] updateMatrix1(int[][] mat) {
            int rows = mat.length, cols = mat[0].length;
            int[][] res = new int[rows][cols];      // resultant array
            Queue<Pair> q = new LinkedList<>();     // stores cells needs to be processed
            // traverse whole array and mark all zeroes
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (mat[i][j] == 0) {
                        res[i][j] = 0;
                        q.add(new Pair(i, j));          // add all zero positions into queue,
                        // so that we can start BFS operation from that cell
                    } else {
                        res[i][j] = Integer.MAX_VALUE;  // if it is not zero, mark it as infinite
                    }
                }
            }

            while (!q.isEmpty()) {
                Pair curr = q.poll();
                int[][] moves = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
                for (int i = 0; i < 4; i++) {
                    int nextRow = curr.x + moves[i][0], nextCol = curr.y + moves[i][1];
                    if (nextRow >= 0 && nextCol >= 0 && nextRow < rows && nextCol < cols) {
                        if (res[nextRow][nextCol] > res[curr.x][curr.y] + 1) {
                            res[nextRow][nextCol] = res[curr.x][curr.y] + 1;
                            q.add(new Pair(nextRow, nextCol));
                        }
                    }
                }
            }

            return res;
        }

        /**
         * <strong>Using BFS</strong>
         */
        static class Pair {
            int x, y;

            public Pair(int x, int y) {
                this.x = x;
                this.y = y;
            }
        }
    }
}
