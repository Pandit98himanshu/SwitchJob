/**
 * 79. Word Search
 * https://leetcode.com/problems/word-search/
 */

class Solution {
	private final int[] dirs = new int[]{-1, 0, 1, 0, -1};

	/*
	 * TC: O(m * n * 4^len(word))
	 *		-> traversing whole board = O(m * n)
	 *		-> for each cell, running DFS of depth "len(word)" in 4 directions = O(4^len(word))
	 * 
	 * SC: O(len(word))		-> depth of recursive stack
	 */
	public boolean exist(char[][] board, String word) {
		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board[0].length; j++)
				if (board[i][j] == word.charAt(0) && isExistDFS(board, word, 0, i, j))	// get a starting point & matching word
					return true;
		return false;
	}
	private boolean isExistDFS(char[][] board, String word, int i, int x, int y) {
		if (i == word.length())				// complete word matched
			return true;

		if (x < 0 || x >= board.length || y < 0 || y >= board[0].length)	// out-of-bound
			return false;
		if (board[x][y] == '#' || board[x][y] != word.charAt(i))	// either already visited or char not matching
			return false;

		boolean temp = false;

		char persistValue = board[x][y];	// retain the value
		board[x][y] = '#';					// mark as visited	[NOTE: can also use visited data-structure, but it will increase space-complexity]

		for (int d = 0; d < 4; d++) {
			int nextX = x + dirs[d];
			int nextY = y + dirs[d + 1];
			temp |= isExistDFS(board, word, i + 1, nextX, nextY);	// check 'word' existant in all 4 directions
		}

		board[x][y] = persistValue;			// mark as unvisited, so that it can be visited again from diff. direction
		return temp;
	}
}