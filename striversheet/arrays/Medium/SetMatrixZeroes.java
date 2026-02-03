import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
 * 73. Set Matrix Zeroes
 * https://leetcode.com/problems/set-matrix-zeroes
 */

class Solution {
	/*
	 * TC: O(m*n + m + n)
	 * SC: O(m + n)
	 */
	public void setZeroes(int[][] matrix) {
		int m = matrix.length;
		int n = matrix[0].length;
		Set<Integer> rows = new HashSet<>();
		Set<Integer> cols = new HashSet<>();
		
		for (int i = 0; i < m; i++) {			// O(m*n)
			for (int j = 0; j < n; j++) {
				if (matrix[i][j] == 0) {
					rows.add(i);
					cols.add(j);
				}
			}
		}
		for (int row : rows) {					// O(n)
			Arrays.fill(matrix[row], 0);
		}
		for (int col : cols) {					// O(m)
			for (int i = 0; i < m; i++) {
				matrix[i][col] = 0;
			}
		}
	}
}

public class SetMatrixZeroes {
	public static void main(String[] args) {;
		int[][] matrix = {
			{0,1,2,0},
			{3,4,5,2},
			{1,3,1,5}
		};
		new Solution().setZeroes(matrix);
		for (int[] row : matrix) {
			System.out.println(Arrays.toString(row));
		}
	}
}
