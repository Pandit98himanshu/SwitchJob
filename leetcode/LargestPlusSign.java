/*
 * 764. Largest Plus Sign
 * https://leetcode.com/problems/largest-plus-sign/
 */

package leetcode;

import java.util.*;

public class LargestPlusSign {
    /**
     * <strong>Binary Search</strong> : 26 ms
     * <p>Copied from leetcode submission
     *
     * <p>Time Complexity: O(N*logN)
     * <br>Space Complexity: O(N)
     */
    public int orderOfLargestPlusSign(int n, int[][] mines) {
        TreeSet<Integer>[] row2zeros = new TreeSet[n];
        TreeSet<Integer>[] col2zeros = new TreeSet[n];

        for (int[] mine : mines) {
            if (row2zeros[mine[0]] == null) {
                row2zeros[mine[0]] = new TreeSet<>();
            }
            if (col2zeros[mine[1]] == null) {
                col2zeros[mine[1]] = new TreeSet<>();
            }
            row2zeros[mine[0]].add(mine[1]);
            col2zeros[mine[1]].add(mine[0]);
        }
        int l = 1, r = (n + 1) / 2;
        int max = 0;
        while (l <= r) {
            int m = (l + r) / 2;
            if (isPossible(row2zeros, col2zeros, n, m)) {
                max = Math.max(max, m);
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return max;
    }

    private boolean isPossible(TreeSet<Integer>[] row2zeros, TreeSet<Integer>[] col2zeros, int n, int order) {
        for (int i = order - 1; i <= n - order; i++) {
            if (row2zeros[i] == null) {
                for (int j = order - 1; j <= n - order; j++) {
                    if (col2zeros[j] == null) return true;
                    int lo = col2zeros[j].lower(i) == null ? 0 : col2zeros[j].lower(i) + 1;
                    int hi = col2zeros[j].higher(i) == null ? n - 1 : col2zeros[j].higher(i) - 1;
                    if (i - lo + 1 >= order && hi - i + 1 >= order) {
                        return true;
                    }
                }
            } else {
                int left = -1;
                row2zeros[i].add(n);
                for (int right : row2zeros[i]) {
                    for (int j = left + order; j <= right - order; j++) {
                        if (col2zeros[j] == null) return true;
                        int lo = col2zeros[j].lower(i) == null ? 0 : col2zeros[j].lower(i) + 1;
                        int hi = col2zeros[j].higher(i) == null ? n - 1 : col2zeros[j].higher(i) - 1;
                        if (i - lo + 1 >= order && hi - i + 1 >= order) {
                            return true;
                        }
                    }
                    left = right;
                }
            }
        }
        return false;
    }

    /**
     * <strong>Dynamic Programming</strong> : 55 ms
     * <p>Copied from leetcode submission
     *
     * <p>Time Complexity: O(N<sup>2</sup>)
     * <br>Space Complexity: O(N<sup>2</sup>)
     */
    public int orderOfLargestPlusSign2(int n, int[][] mines) {
        int[][] dp = new int[n][n];     // store order of axis-aligned plus sign
        int res = 0;                    // store order of largest axis-aligned plus sign
        for (int[] row : dp)
            Arrays.fill(row, n);

        for (int[] mine : mines)         // plant mines
            dp[mine[0]][mine[1]] = 0;

        for (int i = 0; i < n; ++i) {
            System.out.println("i = " + i);
            int left = 0, upper = 0, right = 0, down = 0;
            for (int j = 0, k = n - 1; j < n && k >= 0; ++j, --k) {
                dp[i][j] = Math.min(dp[i][j], left = (dp[i][j] == 0 ? 0 : left + 1));
                dp[j][i] = Math.min(dp[j][i], upper = (dp[j][i] == 0 ? 0 : upper + 1));
                dp[i][k] = Math.min(dp[i][k], right = (dp[i][k] == 0 ? 0 : right + 1));
                dp[k][i] = Math.min(dp[k][i], down = (dp[k][i] == 0 ? 0 : down + 1));
                print(dp);
            }
        }
        // find max axis-aligned plus sign order
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                res = Math.max(res, dp[i][j]);
            }
        }
        return res;
    }

    private void print(int[][] arr) {
        for (int[] row : arr)
            System.out.println(Arrays.toString(row));
        System.out.println();
    }

    /**
     * <strong>Naïve Approach</strong> : 665 ms
     * <p>Create complete grid, plant mines and
     * find order of largest axis-aligned plus sign
     *
     * <p>Time Complexity: O(N<sup>3</sup>)
     * <br>Space Complexity: O(N<sup>2</sup>)
     */
    private int helper(int n, int[][] grid, int r, int c, int order) {
        int upper = r - order;  // upper hand
        int right = c + order;  // right hand
        int down = r + order;   // down hand
        int left = c - order;   // left hand
        boolean inBound = (upper >= 0 && upper < n) && (right >= 0 && right < n) && (down >= 0 && down < n) && (left >= 0 && left < n);
        if (inBound) {      // if hands are in-bound
            boolean mine = grid[upper][c] == 0 || grid[r][right] == 0 || grid[down][c] == 0 || grid[r][left] == 0;
            if (!mine)      // and there are no mines in any of the hand
                return helper(n, grid, r, c, order + 1);    // expand hands
        }
        // return order of axis-aligned plus sign from (r, c)
        return order;
    }

    public int orderOfLargestPlusSign1(int n, int[][] mines) {
        // create whole grid
        int[][] grid = new int[n][n];
        for (int[] row : grid) {
            Arrays.fill(row, 1);
        }
        // put mines
        for (int[] mine : mines) {
            grid[mine[0]][mine[1]] = 0;
        }
        int ans = 0;    // initially we have no clear field
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    // find max axis-aligned plus sign order if there is no mines
                    ans = Math.max(ans, helper(n, grid, i, j, 1));
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int n = 5;
        int[][] mines = {{4, 2}, {1, 4}};
        System.out.println(new LargestPlusSign().orderOfLargestPlusSign2(n, mines));
    }
}
