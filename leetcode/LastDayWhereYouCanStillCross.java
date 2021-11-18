package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Queue;

/**
 * <a href=https://leetcode.com/problems/last-day-where-you-can-still-cross/>1970. Last Day Where You Can Still Cross</a>
 *
 * @author Himanshu Shekhar
 */

public class LastDayWhereYouCanStillCross {
    public int latestDayToCross3(int row, int col, int[][] cells) {
        int currentDay = 0;
        int[][][] matrix = new int[row][col][2];
        // 8 adjacent cells
        int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
        for (int[] curr : cells) {
            int x = curr[0], y = curr[1];
            for (int[] d : dir) {
                int adjX = x + d[0], adjY = y + d[1];
                boolean outOfBound = (adjX < 0 || adjX >= row) || (adjY < 0 || adjY >= col);
                if (outOfBound || matrix[adjX][adjY] == null) continue;

                int[] parentAdj = matrix[adjX][adjY];
                // todo: track-down the starting col && ending col of adj
                //  cells, if it is from 0 to col-1, stop the program and return
                //  current day, else make parent as one of the adjacent cell(s)

                currentDay++;
            }
        }
        return -1;
    }

    /**
     * <strong>Binary Search and BFS</strong>
     * Copied from <a href=https://leetcode.com/problems/last-day-where-you-can-still-cross/discuss/1403907/C++JavaPython-Binary-Search-and-BFS-Clean-and-Concise>leetcode discuss</a>
     */
    public int latestDayToCross1(int row, int col, int[][] cells) {
        int l = 1, r = cells.length - 1, ans = 0;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (canWalk(mid, row, col, cells)) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }

    /**
     * @param cells given binary matrix
     * @param dayAt current day
     * @param row   number of rows
     * @param col   number of columns
     * @return whether we can cross from the top to the bottom on {@code dayAt}
     */
    public boolean canWalk(int dayAt, int row, int col, int[][] cells) {
        int[][] matrix = new int[row][col];

        for (int i = 0; i < dayAt; i++) {
            matrix[cells[i][0] - 1][cells[i][1] - 1] = 1;
        }

        Queue<int[]> bfs = new ArrayDeque<>();
        for (int j = 0; j < col; j++) {
            if (matrix[0][j] == 0) {
                bfs.offer(new int[]{0, j});
                matrix[0][j] = 1;       // mark as visited
            }
        }

        int[] d = {0, 1, 0, -1, 0};
        while (!bfs.isEmpty()) {
            int[] curr = bfs.poll();
            if (curr[0] == row - 1)
                return true;
            for (int i = 0; i < 4; i++) {
                int nextX = curr[0] + d[i], nextY = curr[1] + d[i+1];
                boolean outOfBound = (nextX < 0 || nextX >= row) || (nextY < 0 || nextY >= col);
                if (outOfBound || matrix[nextX][nextY] == 1)
                    continue;
                bfs.offer(new int[]{nextX, nextY});
                matrix[nextX][nextY] = 1;     // mark as visited
            }
        }
        return false;
    }

    /**
     * NOT WORKING
     * <strong>Union-find</strong>
     */
    public int latestDayToCross2(int row, int col, int[][] cells) {
        HashSet<E> set = new HashSet<>();
        int latestDay = 0;          // stores the latest day when, we can cross
        // 8 adjacent cells
        int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

        for (int[] curr : cells) {
            boolean addedToExisting = false;
            int x = curr[0], y = curr[1];
            // try to combine current cell with existing
            // set(s) and union connecting cells
            for (E i : set) {
                ArrayList<E> list = new ArrayList<>();
                if (i.start == y || i.end == y
                        || (i.start > 0 && i.start - 1 == y)
                        || (i.end < col - 1 && i.end + 1 == y)) {
                    // check whether any cell is adjacent to current cell
                    for (int[] cell : i.cells) {
                        for (int[] d : dir) {
                            int nX = x + d[0], nY = y + d[1];
                            if (cell[0] == nX && cell[1] == nY) {
                                i.cells.add(curr);
                                list.add(i);
                                addedToExisting = true;
                            }
                        }
                    }
                }

                if (list.size() > 1) {
                    int start = Integer.MAX_VALUE, end = Integer.MIN_VALUE;
                    HashSet<int[]> unionCells = new HashSet<>();
                    for (E j : list) {
                        start = Math.min(j.start, start);   // find start column
                        end = Math.max(j.end, end);         // find end column
                        unionCells.addAll(i.cells);         // combining all cells into one
                        set.remove(j);                      // to avoid redundancy, delete previous data
                    }
                    set.add(new E(start, end, unionCells));
                }
            }
            // create new set, because current cell
            // didn't merge with any existing set
            if (!addedToExisting) {
                int start = y, end = y;
                HashSet<int[]> tempList = new HashSet<>();
                tempList.add(new int[]{x, y});
                set.add(new E(start, end, tempList));
            }
            // if any set has starting column as 1st column and
            // ending column as last column, then we can't cross
            // because, it stretches from left to right of the matrix
            for (E i : set)
                if (i.start == 0 && i.end == col - 1)
                    return latestDay;
            latestDay++;            // we can cross on current day
        }
        return -1;
    }

    private static class E {
        public int start, end;
        public HashSet<int[]> cells;

        public E() {
        }

        public E(int start, int end, HashSet<int[]> cells) {
            this.start = start;     // starting column
            this.end = end;         // ending column
            this.cells = cells;     // cells in the union set
        }
    }
}
