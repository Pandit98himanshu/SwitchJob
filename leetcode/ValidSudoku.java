/*
 * 36. Valid Sudoku
 * https://leetcode.com/problems/valid-sudoku/
 */

package leetcode;

import java.util.*;

public class ValidSudoku {
    /**
     * <strong>More Readable Solution</strong>
     * <p>Time Complexity: O(n<sup>2</sup>)
     * <br>Space Complexity: O(n<sup>2</sup>)
     * <p>Copied from <a href=https://leetcode.com/problems/valid-sudoku/discuss/15472/Short+Simple-Java-using-Strings>leetcode discuss</a>
     */
    public boolean isValidSudoku1(char[][] board) {
        Set seen = new HashSet<String>();
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                if (board[i][j] != '.') {
                    String b = "(" + board[i][j] + ")";
                    /*
                    '4' in row 7 -> "(4)7".
                    '4' in column 7 -> "7(4)".
                    '4' in the top-right block -> "0(4)2".
                     */
                    if (!seen.add(b + i) || !seen.add(j + b) || !seen.add(i / 3 + b + j / 3))
                        return false;
                }
            }
        }
        return true;
    }

    /**
     * <strong>Brute Force</strong>
     * <p>Time Complexity: O(n<sup>2</sup>)
     * <br>Space Complexity: O(n<sup>2</sup>)
     *
     * @param board partially filled sudoku board
     * @return true if sudoku board is valid otherwise false
     */
    public boolean isValidSudoku(char[][] board) {
        int size = 9;
        // check each 3x3 boxes
        Set[][] setBox = new HashSet[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                setBox[i][j] = new HashSet<Character>();
            }
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                char curr = board[i][j];
                if (curr == '.')
                    continue;
                else if (setBox[i / 3][j / 3].contains(curr)) {
                    return false;
                }
                setBox[i / 3][j / 3].add(curr);
            }
        }
        // check each row and column
        Set[] setRow = new HashSet[size];
        Set[] setColumn = new HashSet[size];

        for (int i = 0; i < size; i++) {
            setRow[i] = new HashSet<Character>();
            setColumn[i] = new HashSet<Character>();
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                char curr = board[i][j];
                if (curr == '.')
                    continue;
                else if (setRow[i].contains(curr) || setColumn[j].contains(curr)) {
                    return false;
                }
                setRow[i].add(curr);
                setColumn[j].add(curr);
            }
        }
        return true;
    }

    private void print(Object... o) {
        System.out.println(Arrays.deepToString(o));
    }

    public static void main(String[] args) {
        char[][] board = {{'5', '3', '.', '.', '7', '.', '.', '.', '.'}
                , {'6', '.', '.', '1', '9', '5', '.', '.', '.'}
                , {'.', '9', '8', '.', '.', '.', '.', '6', '.'}
                , {'8', '.', '.', '.', '6', '.', '.', '.', '3'}
                , {'4', '.', '.', '8', '.', '3', '.', '.', '1'}
                , {'7', '.', '.', '.', '2', '.', '.', '.', '6'}
                , {'.', '6', '.', '.', '.', '.', '2', '8', '.'}
                , {'.', '.', '.', '4', '1', '9', '.', '.', '5'}
                , {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};

        System.out.println(new ValidSudoku().isValidSudoku(board));
    }
}
