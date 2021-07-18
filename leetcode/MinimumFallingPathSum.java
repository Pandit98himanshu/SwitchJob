/*
 * 931. Minimum Falling Path Sum
 * https://leetcode.com/problems/minimum-falling-path-sum/
 */

package leetcode;

public class MinimumFallingPathSum {
    static class Solution {
        public int minFallingPathSum(int[][] matrix) {
            int n = matrix.length, m = matrix[0].length;
            for (int i = 1; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (j == 0)             // can reach (i, j) either from top or right from previous row, if we are in left boundary
                        matrix[i][j] += Math.min(matrix[i-1][j], matrix[i-1][j+1]);
                    else if (j == m-1)      // can reach (i, j) either from left or top from previous row, if we are in right boundary
                        matrix[i][j] += Math.min(matrix[i-1][j-1], matrix[i-1][j]);
                    else                    // can reach (i, j) either from left, top or right from previous row
                        matrix[i][j] += Math.min(matrix[i-1][j-1], Math.min(matrix[i-1][j], matrix[i-1][j+1]));
                }
            }
            int min = Integer.MAX_VALUE;
            // find min value in last row
            for (int j = 0; j < m; j++) {
                min = Math.min(min, matrix[n - 1][j]);
            }
            return min;
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{2,1,3},{6,5,4},{7,8,9}};
        System.out.println(new Solution().minFallingPathSum(matrix));
    }
}
