/*
 * 5815. Maximum Number of Points with Cost
 * https://leetcode.com/problems/maximum-number-of-points-with-cost/
 */

package leetcode;

public class _MaximumNumberOfPointsWithCost {
    static class Solution {
        /**
         * <strong>Brute Force</strong>
         * <p>{@code Time Complexity: O(m * n * n)};
         * where m = number of rows, n = number of columns</p>
         * <p>{@code Space Complexity: O(1)}</p>
         *
         * @param points
         * @return maximum points can be earned moving from top to bottom
         * @see _MinimumFallingPathSumII
         */
        public long maxPoints(int[][] points) {
            int rows = points.length, columns = points[0].length;
            int max;
            for (int i = 1; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    max = -1;
                    // find maximum points can be earned to reach (i, j)
                    for (int k = 0; k < columns; k++) {
                        max = Math.max(max, points[i][j] + points[i - 1][k] - Math.abs(j - k));
                    }
                    points[i][j] = max;
                }
            }
            max = -1;
            // find max value in last row
            for (int j = 0; j < columns; j++) {
                max = Math.max(max, points[rows - 1][j]);
            }
            return max;
        }
    }

    public static void main(String[] args) {
        int[][] points = {{1, 2, 3}, {1, 5, 1}, {3, 1, 1}};
        System.out.println(new Solution().maxPoints(points));
    }
}
