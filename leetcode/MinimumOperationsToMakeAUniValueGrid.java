package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created at : 10/10/21
 * <p>
 * <a href=https://leetcode.com/problems/minimum-operations-to-make-a-uni-value-grid/>5895. Minimum Operations to Make a Uni-Value Grid</a>
 *
 * @author Himanshu Shekhar
 */

public class MinimumOperationsToMakeAUniValueGrid {
    /**
     * The uni-value is median, we've to turn each value into median of values of grid
     * <p>Copied from <a href=https://leetcode.com/problems/minimum-operations-to-make-a-uni-value-grid/discuss/1513301/Java-or-Make-all-median>leetcode discuss</a>
     */
    public int minOperations(int[][] grid, int x) {
        int m = grid.length, n = grid[0].length;

        // put all elements into an arraylist
        List<Integer> list = new ArrayList<>(m * n);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                list.add(grid[i][j]);
            }
        }
        // sort in increasing order
        Collections.sort(list);
        // find median of the numbers, which will be our uni-value of the grid
        int median = list.get((list.size() - 1) / 2);
        int ans = 0;                // stores minimum steps
        // trying to turn each element into the median
        for (int i : list) {
            if (i != median) {
                int diff = Math.abs(i - median);
                // return -1 if we can't reach median using "x"
                if (diff % x != 0)
                    return -1;
                // store into "ans" if it's possible
                ans += diff / x;
            }
        }
        return ans;
    }

    /**
     * <strong>Wrong Answer</strong>
     * <p>I was trying to find mean but we need to find median
     */
    public int minOperations1(int[][] grid, int x) {
        int m = grid.length, n = grid[0].length;
        if (m * n == 1)
            return 0;
        int mean = 0;
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                mean += grid[i][j];

        // find mean of the array
        mean = mean / (m * n);

        // value from the grid which is near to the mean is our required value
        int minDiff = Integer.MAX_VALUE;
        int req = 0;                        // value in which we have to turn all values into
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (minDiff > Math.abs(mean - grid[i][j])) {
                    minDiff = Math.abs(mean - grid[i][j]);
                    req = grid[i][j];
                }
            }
        }
        // check whether we can change all values into required value
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // return -1, if we can't transform into required value using "x"
                if (Math.abs(req - grid[i][j]) % x != 0)
                    return -1;
                // store minimum steps required to transform
                else
                    ans += Math.abs(req - grid[i][j]) / x;
            }
        }

        return ans;
    }
}
