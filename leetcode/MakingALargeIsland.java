package leetcode;

import java.util.*;

/**
 * <a href=https://leetcode.com/problems/making-a-large-island/>827. Making A Large Island</a>
 */

public class MakingALargeIsland {
    static class Solution {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        /**
         * Number all islands and get their size in area
         *
         * @param grid         depicting islands
         * @param islands      map stores size of islands where key is island number
         * @param islandNumber name of island
         * @param x            current cell's x coordinate
         * @param y            current cell's y coordinate
         */
        public void dfs(int[][] grid, HashMap<Integer, Integer> islands, int islandNumber, int x, int y) {
            int m = grid.length, n = grid[0].length;
            if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1) {
                islands.put(islandNumber, islands.getOrDefault(islandNumber, 0) + 1);
                grid[x][y] = islandNumber;     // name each island with a number starting from 100
                for (int i = 0; i < 4; i++) {
                    dfs(grid, islands, islandNumber, x + dx[i], y + dy[i]);
                }
            }
        }

        /**
         * Copied from <a href=https://leetcode.com/problems/making-a-large-island/discuss/1375940/Python-dfs-with-connected-components-explained>leetcode discuss</a>
         * <p>Time Complexity: O(n * m)
         * <br>Space Complexity: O(n * m); where m = rows, n = columns
         *
         * @return the size of the largest island in {@code grid}
         * after changing at most one 0 to 1
         * @see <a href=https://leetcode.com/problems/number-of-islands-ii/>305. Number of Islands II</a>,
         * <a href=https://leetcode.com/problems/number-of-distinct-islands/>694. Number of Distinct Islands</a>,
         * <a href=https://leetcode.com/problems/max-area-of-island/>695. Max Area of Island</a>
         */
        public int largestIsland(int[][] grid) {
            int m = grid.length, n = grid[0].length;
            HashMap<Integer, Integer> islands = new HashMap<>();
            int islandNumber = 100;

            // numbering every island with a unique number
            // starting from 100 because 0 & 1 is already taken
            for (int x = 0; x < m; x++) {
                for (int y = 0; y < n; y++) {
                    if (grid[x][y] == 1) {
                        dfs(grid, islands, islandNumber, x, y);
                        islandNumber++;
                    }
                }
            }

            int maxArea = 0;        // stores result
            for (int x = 0; x < m; x++) {
                for (int y = 0; y < n; y++) {
                    // we need to change 0 to 1 i.e., from empty to a land,
                    // therefore skip those cells which is already an island
                    if (grid[x][y] != 0)
                        continue;

                    HashSet<Integer> neighs = new HashSet<>();
                    for (int i = 0; i < 4; i++) {
                        int next_x = x + dx[i];
                        int next_y = y + dy[i];
                        if (next_x >= 0 && next_x < m && next_y >= 0 && next_y < n && grid[next_x][next_y] != 0)
                            neighs.add(grid[next_x][next_y]);    // store numbers of all connected islands with cell (x, y)

                        int currArea = 1;       // initially we are on cell (x, y) which takes 1 unit of area
                        // calculate area of island if current cell (x, y) changed to 1
                        for (int j : neighs)
                            currArea += islands.get(j);

                        maxArea = Math.max(maxArea, currArea);
                    }
                }
            }
            return maxArea != 0 ? maxArea : m * n;
        }

        /**
         * <strong>DFS</strong> - NOT WORKING
         * <p>My Approach: if we encounter empty land i.e., {@code 0} then make it a land
         * and find area of modified island using DFS while keeping track of maximum area
         *
         * @implNote Don't forget to undone to empty land
         */
        public int getArea(int[][] grid, boolean[][] visited, int x, int y, int area) {
            System.out.println(x + "," + y + ":" + area);
            // don't visit already visited cell
            if (visited[x][y]) {
                return area;
            }
            visited[x][y] = true;       // mark as visited
            int m = grid.length, n = grid[0].length;
            for (int i = 0; i < 4; i++) {
                int next_x = x + dx[i];
                int next_y = y + dy[i];
                if (next_x >= 0 && next_y >= 0 && next_x < m && next_y < n && grid[next_x][next_y] == 1)
                    return getArea(grid, visited, next_x, next_y, area + 1);
            }
            return area;
        }

        public int largestIsland1(int[][] grid) {
            int m = grid.length, n = grid[0].length;
            int maxArea = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 0) {
                        grid[i][j] = 1;     // change from empty to land
                        int currArea = getArea(grid, new boolean[m][n], i, j, 1);
                        maxArea = Math.max(maxArea, currArea);
                        System.out.println("maxArea–" + maxArea + " currArea–" + currArea + "\n");
                        grid[i][j] = 0;     // undone
                    }
                }
            }
            return maxArea;
        }
    }

    public static void main(String[] args) {
        int[][] grid = {{1, 0, 1}, {0, 1, 0}, {1, 1, 0}};
        System.out.println(new Solution().largestIsland(grid));
    }
}
