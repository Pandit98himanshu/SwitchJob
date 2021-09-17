package leetcode;

import java.util.*;

/**
 * Created at : 16/09/21
 * <p>
 * <a href=https://leetcode.com/problems/spiral-matrix/>54. Spiral Matrix</a>
 *
 * @author Himanshu Shekhar
 */

public class SpiralMatrix {
    final int inf = Integer.MAX_VALUE;
    List<Integer> ans = new ArrayList<>();

    /**
     * Mark visited (make infinite) the cells we visit, to avoid repetition
     * <p>Time Complexity: O(n<sup>2</sup>); traversing each cell
     * <br>Space Complexity: O(n<sup>2</sup>); storing answer
     */
    private void spiralRecur(int[][] matrix, int left, int bottom, int top, int right) {
        // return when we traverse whole matrix in spiral way
        if (left > bottom || top > right)
            return;
        // traverse top row
        for (int i = top; i <= right; i++) {
            if (matrix[left][i] != inf) {
                ans.add(matrix[left][i]);
                matrix[left][i] = inf;
            }
        }
        // traverse right column
        for (int i = left; i <= bottom; i++) {
            if (matrix[i][right] != inf) {
                ans.add(matrix[i][right]);
                matrix[i][right] = inf;
            }
        }
        // traverse bottom row
        for (int i = right; i >= top; i--) {
            if (matrix[bottom][i] != inf) {
                ans.add(matrix[bottom][i]);
                matrix[bottom][i] = inf;
            }
        }
        // traverse left column
        for (int i = bottom; i >= left; i--) {
            if (matrix[i][top] != inf) {
                ans.add(matrix[i][top]);
                matrix[i][top] = inf;
            }
        }
        // decrease the boundary and traverse inner spiral circle
        spiralRecur(matrix, left + 1, bottom - 1, top + 1, right - 1);
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        // since we need to traverse whole matrix in same pattern
        // we use recursion to accomplish it
        spiralRecur(matrix, 0, m - 1, 0, n - 1);
        return ans;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        matrix = new int[][]{{1, 2, 3}, {4, 5, 6}};
        matrix = new int[][]{{1, 2, 3, 4}};
        matrix = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println(new SpiralMatrix().spiralOrder(matrix));
    }
}
