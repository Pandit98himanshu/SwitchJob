package striversheet.Arrays;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created at : 17/02/22
 * <p>
 * <a href=https://leetcode.com/problems/set-matrix-zeroes/>73. Set Matrix Zeroes</a>
 *
 * @author Himanshu Shekhar
 */

public class SetMatrixZeroes {
    /**
     * The idea is to use hashset to remove repetition.
     * <p>Time Complexity: O(2 * (m + n)); 1 ms, faster than 93.63 %
     * <br>Space Complexity: O(m + n); 43.5 MB, less than 72.97%
     */
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;  // number of rows
        int n = matrix[0].length;   // number of columns
        Set<Integer> rows = new HashSet<>();    // set contains row numbers having "0"
        Set<Integer> cols = new HashSet<>();    // set contains column numbers having "0"

        // iterate whole matrix and store
        // row & column numbers having "0"s
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    rows.add(i);
                    cols.add(j);
                }
            }
        }

        // make the whole row "0"
        for (int row : rows) {
            Arrays.fill(matrix[row], 0);
        }
        // make the whole column "0"
        for (int col : cols) {
            for (int i = 0; i < m; i++) {
                matrix[i][col] = 0;
            }
        }
    }
}
