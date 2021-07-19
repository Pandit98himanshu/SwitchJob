/*
 * 5815. Maximum Number of Points with Cost
 * https://leetcode.com/problems/maximum-number-of-points-with-cost/
 */

package leetcode;

public class MaximumNumberOfPointsWithCost {
    static class Solution {
        /**
         * <strong>Brute Force</strong> - Dynamic Programming
         * <p>Similar to {@link leetcode._MinimumFallingPathSumII}</p>
         * <p>{@code Time Complexity: O(m * n * n)};
         * where m = number of rows, n = number of columns</p>
         * <p>{@code Space Complexity: O(1)}</p>
         *
         * @param points
         * @return maximum points can be earned moving from top to bottom
         */
        public long maxPoints1(int[][] points) {
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

        /**
         * <strong>Efficient Approach</strong> - Dynamic Programming
         * <p>Based on {@link leetcode.TrappingRainWater}, left sweep and right sweep to get maximum value for each index.</p>
         * Copied from <a href=https://leetcode.com/problems/maximum-number-of-points-with-cost/discuss/1344948/Clean-Java>leetcode discuss</a>
         * <p>{@code Time Complexity: O(m * n)}</p>
         * <p>{@code Space Complexity: O(m * n)};
         * where m = number of rows, n = number of columns</p>
         *
         * @param points
         * @return maximum points can be earned moving from top to bottom
         * @see <a href=https://leetcode.com/problems/maximum-number-of-points-with-cost/discuss/1344888/C++-dp-from-O(m-*-n-*-n)-to-O(m-*-n)>From O(m * n * n) to O(m * n)</a>
         * @see <a href=https://leetcode.com/problems/maximum-number-of-points-with-cost/discuss/1344888/C++-dp-from-O(m-*-n-*-n)-to-O(m-*-n)/1014241>Easy to understand</a>
         */
        public long maxPoints(int[][] points) {
            int m = points.length, n = points[0].length;
            long ans = Long.MIN_VALUE;
            if (m == 1) {
                for (int j = 0; j < n; j++) {
                    ans = Math.max(ans, points[0][j]);
                }
                return ans;
            }
            // converting from int[][] to long[][] for avoiding overflow
            long[][] dp = new long[m][n];
            // we've to use deep copy here 'cause Arrays.copyOf(), .clone(), etc. doesn't work
            for (int i = 0; i < m; i++)
                for (int j = 0 ; j < n; j++)
                    dp[i][j] = points[i][j];

            for (int i = 1; i < m; i++) {
                long[] temp = new long[n];
                // finding maximum by left sweep
                long pre = dp[i - 1][0];
                temp[0] = pre;
                for (int j = 1; j < n; j++) {
                    pre--;
                    pre = Math.max(pre, dp[i - 1][j]);
                    temp[j] = Math.max(temp[j], pre);
                }
                // finding maximum by right sweep
                pre = Math.max(pre, dp[i - 1][n - 1]);
                temp[n - 1] = pre;
                for (int j = n - 2; j >= 0; j--) {
                    pre--;
                    pre = Math.max(pre, dp[i - 1][j]);
                    temp[j] = Math.max(temp[j], pre);
                }
                // find path which gives maximum points
                for (int j = 0; j < n; j++) {
                    dp[i][j] += temp[j];
                    ans = Math.max(ans, dp[i][j]);
                }
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        int[][] points = {{1, 2, 3}, {1, 5, 1}, {3, 1, 1}};
        System.out.println(new Solution().maxPoints(points));
    }
}
