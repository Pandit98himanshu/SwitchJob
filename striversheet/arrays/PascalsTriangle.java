package striversheet.Arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * Created at : 18/02/22
 * <p>
 * <a href=https://leetcode.com/problems/pascals-triangle/>118. Pascal's Triangle</a>
 *
 * @author Himanshu Shekhar
 */

public class PascalsTriangle {
    /**
     * Add corresponding values of previous rows and generate current row
     * <p>Time Complexity: O(numRows<sup>2</sup>); 0 ms, faster than 100.00%
     * <br>Space Complexity: O(numRows<sup>2</sup>)
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> row = new ArrayList<>();
        row.add(1);
        res.add(row);

        if (numRows == 1)
            return res;

        for (int i = 1; i < numRows; i++) {
            List<Integer> prevRow = row;
            row = new ArrayList<>();
            row.add(1);     // 1 is there at the beginning of every row
            for (int j = 1; j < i; j++) {
                row.add(prevRow.get(j-1) + prevRow.get(j));
            }
            row.add(1);     // 1 is there at the end of every row

            res.add(row);
        }
        return res;
    }
}
