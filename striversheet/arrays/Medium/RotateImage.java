package striversheet.Arrays.Medium;

import java.util.Arrays;

/*
 * 48. Rotate Image
 * https://leetcode.com/problems/rotate-image/
 */

class Solution {
    static {
        for (int i = 0; i < 500; i++) rotate(new int[0][0]);
    }

    /*
     * TC: O(2n²)
     * SC: O(1)
     */
    public static void rotate(int[][] matrix) {
        transpose(matrix);      // transpose the matrix around main-diagonal
        System.out.println(Arrays.deepToString(matrix));
        flipVertically(matrix); // flip the matix vertically around the midle column
        // above 2 steps will rotate the matrix by 90° clockwise
    }
	/*
	 * TC: O(n(n+1)/2) = O(n²)
	 */
    private static void transpose(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i + 1; j < matrix.length; j++) {
                swap(matrix, i, j, j, i);
            }
        }
    }
	/*
	 * TC: O(n²/2)
	 */
    private static void flipVertically(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n/2; j++) {
                swap(matrix, i, j, i, n - 1 - j);
            }
        }
    }
    private static void swap(int[][] matrix, int i1, int j1, int i2, int j2) {
        int temp = matrix[i1][j1];
        matrix[i1][j1] = matrix[i2][j2];
        matrix[i2][j2] = temp;
    }
}

public class RotateImage {
	public static void main(String[] args) {
		// TC 1
		int[][] matrix = {
				{00,01,02,03,04,05},
				{10,11,12,13,14,15},
				{20,21,22,23,24,25},
				{30,31,32,33,34,35},
				{40,41,42,43,44,45},
				{50,51,52,53,54,55}
		};
		// TC 2
/* 		
		int[][] matrix = {
			{00,01,02,03,04},
			{10,11,12,13,14},
			{20,21,22,23,24},
			{30,31,32,33,34},
			{40,41,42,43,44}
		};
 */		
		Solution.rotate(matrix);
		System.out.println(Arrays.deepToString(matrix));
	}
}
