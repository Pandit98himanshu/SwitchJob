/*
 * 1289. Minimum Falling Path Sum II
 * https://leetcode.com/problems/minimum-falling-path-sum-ii/
 */

package leetcode;

public class __MinimumFallingPathSumII {

    static class Solution {
        /**
         * <strong>Brute Force</strong> - Dynamic Programming
         * <p>Time Complexity: O(n * m * m);
         * where n = number of rows, m = number of columns
         * <br>Space Complexity: O(1)
         *
         * @param arr
         * @return minimum sum of a falling path with non-zero shifts.
         * @see MaximumNumberOfPointsWithCost
         */
        public int minFallingPathSum1(int[][] arr) {
            int rows = arr.length, columns = arr[0].length;
            int min;
            for (int i = 1; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    min = Integer.MAX_VALUE;
                    for (int k = 0; k < columns; k++) {
                        if (k == j) continue;
                        min = Math.min(min, arr[i - 1][k]);
                    }
                    arr[i][j] += min;
                }
            }
            min = Integer.MAX_VALUE;
            // find min value in last row
            for (int j = 0; j < columns; j++) {
                min = Math.min(min, arr[rows - 1][j]);
            }
            return min;
        }

        /**
         * <strong>Efficient Approach</strong>
         * <p>Copied from <a href=https://leetcode.com/problems/minimum-falling-path-sum-ii/discuss/1272688/O(N*N)-Time-c++-code>leetcode discuss</a>
         * <p>Time Complexity: 😵‍💫;
         * where n = number of rows, m = number of columns
         * <br>Space Complexity: 😵‍💫
         *
         * @param arr
         * @return minimum sum of a falling path with non-zero shifts.
         */
        public int minFallingPathSum(int[][] arr) {
            int[][] dp = new int[arr.length][arr[0].length];
            for (int[] r : dp)
                java.util.Arrays.fill(r, -1);
            return helper(arr, 0, -1, dp);
        }

        int helper(int[][] arr, int i, int f, int[][] dp) {
            if (i == arr.length) return 0;
            int min = Integer.MAX_VALUE;
            if (f != -1 && dp[i][f] != -1) return dp[i][f];
            for (int j = 0; j < arr[0].length; j++) {
                if (f == j) continue;
                int sum = 0;
                sum += arr[i][j] + helper(arr, i + 1, j, dp);
                min = Math.min(sum, min);
            }
            if (f != -1) dp[i][f] = min;
            return min;
        }

    }

    public static void main(String[] args) {
        int[][] arr = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println(new Solution().minFallingPathSum(arr));
    }
}

