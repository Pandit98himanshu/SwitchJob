/*
 * 79. Word Search
 * https://leetcode.com/problems/word-search
 */

package leetcode;

public class WordSearch {
    private boolean isFound = false;
    private final int[] d = {0, 1, 0, -1, 0};

    /**
     * <strong>DFS</strong>
     * <p>Time Complexity: O(n<sup>4</sup>)
     * <br>Space Complexity: O(n<sup>2</sup>)
     *
     * @param board   board of characters in which we need to search our {@code word}
     * @param word    word we need to search
     * @param i       current index at word which we are searching
     * @param x       x coordinate
     * @param y       y coordinate
     * @param visited already visited cell
     */
    private void dfs(char[][] board, String word, int i, int x, int y, boolean[][] visited) {
        int m = board.length, n = board[0].length;
        // make current cell visited, to avoid dead loop
        visited[x][y] = true;
        // when we reach the end of the word, we must have found it
        if (i == word.length() - 1) {
            isFound = true;
            return;
        }

        // try to search for next character of word (in board) in 4 co-ordinal directions
        for (int j = 0; j < 4; j++) {
            int nextX = x + d[j];
            int nextY = y + d[j + 1];
            boolean inBound = (nextX >= 0 && nextX < m) && (nextY >= 0 && nextY < n);
            if (inBound && board[nextX][nextY] == word.charAt(i + 1) && !visited[nextX][nextY])
                dfs(board, word, i + 1, nextX, nextY, visited);
        }
        // make current cell unvisited, so that we can visit it again
        visited[x][y] = false;
    }

    public boolean exist(char[][] board, String word) {
        int m = board.length, n = board[0].length;
        // our search for word will start from the cell
        // where we will find 1st character of our word
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!isFound && board[i][j] == word.charAt(0)) {
                    dfs(board, word, 0, i, j, new boolean[m][n]);
                }
            }
        }
        return isFound;
    }

    public static void main(String[] args) {
        char[][] board = {{'a','b','c'},{'a','e','d'},{'a','f','g'}};
        String word = "eaabcdgfa";
        System.out.println(new WordSearch().exist(board, word));
    }
}
