import java.util.Arrays;

/**
 * 1901. Find a Peak Element II
 * https://leetcode.com/problems/find-a-peak-element-ii
 */

class Solution {
	/*
	 * TC: O(m * log(n))
	 * SC: O(1)
	 */
	public int[] findPeakGrid(int[][] mat) {
		return divide(mat, 0, mat[0].length);
	}

	// divide in a column-wise
	private int[] divide(int[][] mat, int l, int r) {
		int[] retv = new int[] { -1, -1 };
		if (l < r) {
			int mid = (l + r) / 2;
			// check peak element at mid column
			for (int i = 0; i < mat.length; i++) {
				if (isPeak(mat, i, mid))
					return new int[] { i, mid };
			}
			// check for peak element in left side
			int[] left = divide(mat, l, mid);
			if (!Arrays.equals(left, retv))
				return left;
			// check for peak element in right side
			int[] right = divide(mat, mid + 1, r);
			if (!Arrays.equals(right, retv))
				return right;
		}
		return retv;
	}

	// checks whether element at (x, y) is peak element or not
	private boolean isPeak(int[][] mat, int x, int y) {
		boolean left = true, right = true, top = true, bottom = true;
		if (y > 0 && mat[x][y] < mat[x][y - 1])
			left = false;
		if (y < mat[0].length - 1 && mat[x][y] < mat[x][y + 1])
			right = false;
		if (x > 0 && mat[x][y] < mat[x - 1][y])
			top = false;
		if (x < mat.length - 1 && mat[x][y] < mat[x + 1][y])
			bottom = false;

		return left && right && top && bottom;
	}
}

public class FindAPeakElementII {
	
}
