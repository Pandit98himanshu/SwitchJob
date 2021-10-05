package leetcode;

/**
 * Created at : 26/09/21
 * <p>
 * <a href=https://leetcode.com/problems/check-if-word-can-be-placed-in-crossword/>5883. Check if Word Can Be Placed In Crossword</a>
 *
 * @author Himanshu Shekhar
 */

public class _CheckIfWordCanBePlacedInCrossword {
    /**
     * <a href=https://leetcode.com/problems/check-if-word-can-be-placed-in-crossword/discuss/1486536/C%2B%2B-Iterative>Similar but better approach</a>
     */
    private int m;      // # rows
    private int n;      // # columns
    private int len;    // length of the word

    /**
     * <strong>Not working</strong>
     */
    private boolean verticallyDown(char[][] board, String word, int x, int y, int p) {
        for (; x < m && p < len; x++, p++) {
            // cell is empty or corresponding
            // character is already placed
            if (board[x][y] == ' ' || board[x][y] == word.charAt(p))
                continue;
            else
                return false;
        }
        // we could not fill whole word
        if (p < len)
            return false;
        return true;
    }

    private boolean verticallyUp(char[][] board, String word, int x, int y, int p) {
        for (; x >= 0 && p < len; x--, p++) {
            // cell is empty or corresponding
            // character is already placed
            if (board[x][y] == ' ' || board[x][y] == word.charAt(p))
                continue;
            else
                return false;
        }
        // we could not fill whole word
        if (p < len)
            return false;
        return true;
    }

    private boolean horizontallyRight(char[][] board, String word, int x, int y, int p) {
        for (; y < n && p < len; y++, p++) {
            // cell is empty or corresponding
            // character is already placed
            if (board[x][y] == ' ' || board[x][y] == word.charAt(p))
                continue;
            else
                return false;
        }
        // we could not fill whole word
        if (p < len)
            return false;
        return true;
    }

    private boolean horizontallyLeft(char[][] board, String word, int x, int y, int p) {
        for (; y >= 0 && p < len; y--, p++) {
            // cell is empty or corresponding
            // character is already placed
            if (board[x][y] == ' ' || board[x][y] == word.charAt(p))
                continue;
            else
                return false;
        }
        // we could not fill whole word
        if (p < len)
            return false;
        return true;
    }

    private boolean validateCol(char[][] board, int start, int end, int col) {
        if ((start - 1 < 0 || board[start - 1][col] == '#') && (end + 1 >= m || board[end + 1][col] == '#'))
            return true;
        return false;
    }

    private boolean validateRow(char[][] board, int start, int end, int row) {
        if ((start - 1 < 0 || board[row][start - 1] == '#') && (end + 1 >= n || board[row][end + 1] == '#'))
            return true;
        return false;
    }

    private boolean tryToFill(char[][] board, String word, int x, int y) {
        // filled cell but not contains starting letter of the word,
        // we can't start filling the word from that cell
        if (board[x][y] != ' ' && board[x][y] != word.charAt(0))
            return false;
        // try to fill vertically down
        if (verticallyDown(board, word, x + 1, y, 1) &&
                validateCol(board, x, x + (len - 1), y))
                return true;
        // try to fill vertically up
        else if (verticallyUp(board, word, x + 1, y, 1) &&
                validateCol(board, x - (len - 1), x, y))
                return true;
        // try to fill horizontally right
        else if (verticallyUp(board, word, x + 1, y, 1) &&
                validateRow(board, y, y + (len - 1), x))
                return true;
        // try to fill vertically up
        else if (verticallyUp(board, word, x + 1, y, 1) &&
                validateRow(board, y - (len - 1), y, x))
                return true;
        return false;
    }

    public boolean placeWordInCrossword(char[][] board, String word) {
        m = board.length;
        n = board[0].length;
        len = word.length();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] != '#')
                    if (tryToFill(board, word, i, j))
                        return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        char[][] board = {{' ', '#', 'a'}, {'#', ' ', 'b'}, {' ', '#', 'a'}};
        String word = "ac";
        System.out.println(new _CheckIfWordCanBePlacedInCrossword().placeWordInCrossword(board, word));
    }
}
