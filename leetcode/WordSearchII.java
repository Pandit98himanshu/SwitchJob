/*
 * 212. Word Search II
 * https://leetcode.com/problems/word-search-ii
 */

package leetcode;

import java.util.*;

public class WordSearchII {
    // todo: implement trie approach

    private boolean isFound = false;
    private final int[] d = {0, 1, 0, -1, 0};

    /**
     * <strong>DFS/Backtracking</strong> - Gives TLE
     * <p>Time Complexity: O(n<sup>4</sup>)
     * <br>Space Complexity: O(n<sup>2</sup>)
     *
     * @param board   board of characters in which we need to search our {@code word}
     * @param word    we need to find in given {@code board}
     * @param i       current index at word which we are searching
     * @param x       x coordinate
     * @param y       y coordinate
     * @param visited already visited cell
     */
    private void dfs(char[][] board, String word, int i, int x, int y, boolean[][] visited, List<String> ans) {
        int m = board.length, n = board[0].length;
        // make current cell visited, to avoid dead loop
        visited[x][y] = true;
        // when we reach the end of the word, we must have found it
        if (i == word.length() - 1) {
            isFound = true;
            ans.add(word);
            return;
        }

        // try to search for next character of word (in board) in 4 co-ordinal directions
        for (int j = 0; j < 4; j++) {
            int nextX = x + d[j];
            int nextY = y + d[j + 1];
            boolean inBound = (nextX >= 0 && nextX < m) && (nextY >= 0 && nextY < n);
            if (inBound && !isFound && board[nextX][nextY] == word.charAt(i + 1) && !visited[nextX][nextY])
                dfs(board, word, i + 1, nextX, nextY, visited, ans);
        }
        // make current cell unvisited, so that we can visit it again
        visited[x][y] = false;
    }

    public List<String> findWords(char[][] board, String[] words) {
        int m = board.length, n = board[0].length;
        List<String> ans = new ArrayList<>();
        // our search for word will start from the cell
        // where we will find 1st character of our word
        for (String word : words) {
            isFound = false;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (board[i][j] == word.charAt(0)) {
                        if (!isFound) {
                            dfs(board, word, 0, i, j, new boolean[m][n], ans);
                        }
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        char[][] board = {{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}};
        String[] word = {"oath","pea","eat","rain"};
        board = new char[][]{{'o','a','b','n'},{'o','t','a','e'},{'a','h','k','r'},{'a','f','l','v'}};
        word = new String[]{"oa","oaa"};
        board = new char[][]{{'a','b','c'},{'a','e','d'},{'a','f','g'}};
        word = new String[]{"abcdefg","gfedcbaaa","eaabcdgfa","befa","dgc","ade"};
        board = new char[][]{{'a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a'}};
        word = new String[]{"a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"};
        System.out.println(new WordSearchII().findWords(board, word));
    }
}
