import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 51. N-Queens
 * https://leetcode.com/problems/n-queens/
 */

class Solution {
	/*
	 * TC: O(n * n!)	-> placing 'n' queens, each in 'n' columns + checking 'canPlace'
	 * SC: O(n² + n)	-> auxiliary space (board) + depth of recursion tree
	 */
	public List<List<String>> solveNQueens(int n) {
		List<List<String>> ans = new ArrayList<>();
		char[][] board = new char[n][n];
		for (char[] row : board)
			Arrays.fill(row, '.');			// initialize with empty board
		placeQueensRecur(n, 0, board, ans);

		return ans;
	}

	/*
	 * Places queen row-wise
	 */
	private void placeQueensRecur(int n, int row, char[][] board, List<List<String>> ans) {
		// base-case; successfully placed all n queens on the board
		if (n == 0) {
			// store board status into ans in appropriate return format
			List<String> curr = new ArrayList<>();
			for (char[] r : board)
				curr.add(new String(r));
			ans.add(curr);
			return;
		}
		// for current row, start checking each column
		for (int col = 0; col < board[row].length; col++) {
			if (canPlace(board, row, col)) {
				board[row][col] = 'Q';
				placeQueensRecur(n - 1, row + 1, board, ans);	// move to next row to place next queen
				board[row][col] = '.';
			}
		}
	}

	/*
	 * Checks whether we can place a queen at place (x, y)
	 * NOTE: Can reduce this time complexity by using extra column, diagonal, anti-diagonal arrays
	 */
	private boolean canPlace(char[][] board, int x, int y) {
		int m = board.length, n = board[0].length;

		// check column
		for (int i = 0; i < m; i++)
			if (board[i][y] == 'Q')
				return false;

		// check row [NOT REQUIRED]				-> placing queens row-wise, ensures no 2 queens will be in same row

		// check top-right
		for (int i = x - 1, j = y + 1; i >= 0 && j < n; i--, j++)
			if (board[i][j] == 'Q')
				return false;

		// check bottom-left [NOT REQUIRED]		-> queens not yet placed in below rows

		// check top-left
		for (int i = x - 1, j = y - 1; i >= 0 && j >= 0; i--, j--)
			if (board[i][j] == 'Q')
				return false;

		// check bottom-right [NOT REQUIRED]	-> queens not yet placed in below rows
		
		return true;
	}
}