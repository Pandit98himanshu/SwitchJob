package leetcode;

import java.util.*;

/**
 * <a href=https://leetcode.com/problems/maximum-matrix-sum/>1975. Maximum Matrix Sum</a>
 *
 * @author Himanshu Shekhar
 */

public class MaximumMatrixSum {
    /**
     * Copied from <a href=https://leetcode.com/problems/maximum-matrix-sum/discuss/1417606/C++-The-basic-idea-is-to-eliminate-negative-signs>leetcode discuss</a>
     */
    public long maxMatrixSum1(int[][] matrix) {
        long negNums = 0, totalSum = 0, min = 0;
        for (int[] row : matrix) {
            for (int i : row) {
                int curr = Math.abs(i);
                if (i < 0)
                    negNums++;
                totalSum += curr;
                min = curr < min ? curr : min;
            }
        }
        if (negNums % 2 == 0)
            return totalSum;
        return totalSum - 2 * min;
    }

    /**
     * <strong>Wrong Answer</strong>
     */
    private static class Pair {
        int val;        // stores value
        int[] xy;       // stores position in the matrix
        Pair (int val, int[] xy) {
            this.val = val;
            this.xy = xy.clone();
        }
    }

    /**
     * @return sum of elements of {@code arr}
     */
    private long arraySum(int[][] arr) {
        long sum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                sum += arr[i][j];
            }
        }
        return sum;
    }

    private int[][] changeSign(int[][] arr, int[] x, int[] y) {
        arr[x[0]][x[1]] = -1 * arr[x[0]][x[1]];
        arr[y[0]][y[1]] = -1 * arr[y[0]][y[1]];
        return arr;
    }

    public long maxMatrixSum(int[][] matrix) {
        int n = matrix.length;
        // min heap based on value
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.val - b.val);
        // put all negative values into the min heap
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (matrix[i][j] < 0)
                    pq.add(new Pair(matrix[i][j], new int[]{i, j}));

        long maxSum = arraySum(matrix);
        int[] dir = {0, 1, 0, -1, 0};
        while(!pq.isEmpty()) {
            // pick-out each negative element one-by-one
            Pair p = pq.poll();
            // iterate all 4 adjacent elements
            for (int i = 0; i < 4; i++) {
                int[] adj = new int[]{dir[i], dir[i+1]};
                boolean inBound = (adj[0] >= 0 && adj[0] < n) && (adj[1] >= 0 && adj[1] < n);
                if (inBound) {
                    // change signs of adjacent elements and check if by
                    // the change maximum sum is being greater than previous
                    long currSum = arraySum(changeSign(matrix, p.xy, adj));
                    if (maxSum <= currSum) {
                        maxSum = currSum;
                    }
                    // if maximum sum reduces, then revert the change
                    else {
                        changeSign(matrix, p.xy, adj);
                    }
                }
            }
        }

        return maxSum;
    }

    public static void main(String[] args) {
        int[][] matrix = {{-10000,-10000,-10000},{-10000,-10000,-10000},{-10000,-10000,-10000}};
//        matrix = new int[][]{{6,-3,6},{6,-1,-6},{6,6,6}};
        System.out.println(new MaximumMatrixSum().maxMatrixSum(matrix));
    }
}
