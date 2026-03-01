/**
 * 37. Sudoku Solver
 * https://leetcode.com/problems/sudoku-solver/
 */

class Solution {
	/*
	 * TC: O(9ⁿˣⁿ)  ; n <= 9
	 * SC: O(n * n)
	 * Start filling row-by-row. If curr row is done, move to start of the next row
	 */
	public void solveSudoku(char[][] board) {
		sudokuSolver(board, 0, 0);		// start solving from (0, 0)
	}
	private boolean sudokuSolver(char[][] board, int x, int y) {
		// no more rows to fill
		if (x >= board.length) return true;
		// row 'x' is filled, move to next row
		if (y >= board.length)
			return sudokuSolver(board, x + 1, 0);
		// move to next cell in the current row 'x'
		if (board[x][y] != '.')
			return sudokuSolver(board, x, y + 1);
		// fill the current cell starting from [1-9]
		for (char val = '1'; val <= '9'; val++) {
			if (isValid(board, x, y, val)) {
				board[x][y] = val;		// fill the cell by valid value (as per current status)
				if (sudokuSolver(board, x, y + 1))
					return true;
				board[x][y] = '.';		// revert is required to backtrack
			}
		}
		return false;
	}
	private boolean isValid(char[][] board, int x, int y, char val) {
		// check row 'x' & column 'y'
		for (int i = 0; i < board.length; i++) {
			if (board[i][y] == val) return false;
			if (board[x][i] == val) return false;
		}
		// check current sub-box
		int startX = (x/3) * 3, startY = (y/3) * 3;
		for (int i = startX; i < startX + 3; i++) {
			for (int j = startY; j < startY + 3; j++) {
				if (board[i][j] == val) return false;
			}
		}
		return true;
	}
}