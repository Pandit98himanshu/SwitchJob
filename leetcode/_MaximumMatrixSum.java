/*
 * 1975. Maximum Matrix Sum
 * https://leetcode.com/problems/maximum-matrix-sum/
 */

package leetcode;

import java.util.*;

public class _MaximumMatrixSum {
    private static class Pair {
        int val;
        int[] xy;
        Pair (int val, int[] xy) {
            this.val = val;
            this.xy = xy.clone();
        }
    }
    private long arraySum(int[][] arr) {
        long sum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                sum += arr[i][j];
            }
        }
        return sum;
    }

    private int[][] revert(int[][] arr, int[] x, int[] y) {
        arr[x[0]][x[1]] = -1 * arr[x[0]][x[1]];
        arr[y[0]][y[1]] = -1 * arr[y[0]][y[1]];
        return arr;
    }

    public long maxMatrixSum(int[][] matrix) {
        int n = matrix.length;
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.val - b.val);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] < 0) {
                    pq.add(new Pair(matrix[i][j], new int[]{i, j}));
                }
            }
        }
        long maxSum = arraySum(matrix);
        int[] dir = {0, 1, 0, -1, 0};
        while(!pq.isEmpty()) {
            Pair p = pq.poll();
            for (int i = 0; i < 4; i++) {
                int[] adj = new int[]{dir[i], dir[i+1]};
                if (adj[0] >= 0 && adj[0] < n && adj[1] >= 0 && adj[1] < n) {
                    long currSum = arraySum(revert(matrix, p.xy, adj));

                    printArray(matrix);
                    System.out.println();

                    if (maxSum < currSum) {
                        maxSum = currSum;
                    } else {
                        revert(matrix, p.xy, adj);
                    }
                }
            }
        }

        return maxSum;
    }

    private static void printArray(int[][] arr) {
        for (int[] brr : arr) {
            print(brr);
        }
    }
    private static void print(Object...o) {
        System.out.println(Arrays.deepToString(o));
    }
    public static void main(String[] args) {
        int[][] matrix = {{-10000,-10000,-10000},{-10000,-10000,-10000},{-10000,-10000,-10000}};
//        matrix = new int[][]{{6,-3,6},{6,-1,-6},{6,6,6}};
        System.out.println(new _MaximumMatrixSum().maxMatrixSum(matrix));
    }
}
