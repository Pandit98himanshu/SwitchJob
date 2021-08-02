/*
 * 542. 01 Matrix
 * https://leetcode.com/problems/01-matrix/
 */

package leetcode;

import java.util.*;

public class _ZeroOneMatrix {
    static class Solution {
        /**
         * <strong>Using DP</strong>
         */
        public int[][] updateMatrix(int[][] mat) {
            //TODO: solve using DP
            // https://leetcode.com/problems/01-matrix/solution/

            return new int[][]{};
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
                    }
                    else {
                        res[i][j] = Integer.MAX_VALUE;  // if it is not zero, mark it as infinite
                    }
                }
            }

            while (!q.isEmpty()) {
                Pair curr = q.poll();
                int[][] moves = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
                for (int i = 0; i < 4; i++) {
                    int nextRow = curr.x + moves[i][0], nextCol = curr.y+moves[i][1];
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
    }

    public static void print(Object...O) {
        System.out.println(Arrays.deepToString(O));
    }
    public static void main(String[] args) {
        int[][] mat = {{0,0,0},{0,1,0},{1,1,1}};
        print(new Solution().updateMatrix(mat));
    }
}
