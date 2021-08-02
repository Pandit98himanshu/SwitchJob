/*
 * The Rainy Season
 * https://www.hackerearth.com/problem/algorithm/bfs-waali-7409c2ca-c1be890b/
 */

import java.util.Arrays;

public class TheRainySeason {
    static class TestClass {
        /**
         * Depth first search
         */
        public void dfs(char[][] island, int row, int column) {
            int m = island.length, n = island[0].length;
            island[row][column] = 'v';      // mark current cell as visited
            int[][] moves = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
            // moves to all 4 sides from current cell
            for (int i = 0; i < 4; i++) {
                int next_row = row + moves[i][0];
                int next_column = column + moves[i][1];
                // if next cell is also empty, move to that cell
                if (next_row < m && next_row >= 0 && next_column < n && next_column >= 0 && island[next_row][next_column] == '.')
                    dfs(island, next_row, next_column);
            }
        }

        /**
         * Water can escape from island iff they reach to the perimeter.
         * We are walking on perimeter and tracing all cells (and marking them visited).
         *
         * @param island '.' means empty field and '*' means blocked
         * @return the volume of water trapped inside island
         */
        public int trappedWater(char[][] island) {
            int m = island.length, n = island[0].length;
            // walk on top row of island
            for (int i = 0; i < m; i++)
                if (island[i][0] == '.')            // if there is escape point in corner of island
                    dfs(island, i, 0);      // start searching all connected empty fields from (i, 0)
            // walk on bottom row of island
            for (int i = 0; i < m; i++)
                if (island[i][n - 1] == '.')        // if there is escape point in corner of island
                    dfs(island, i, n - 1);  // start searching all connected empty fields from (i, n - 1)
            // walk on left-most column of island
            for (int j = 0; j < n; j++)
                if (island[0][j] == '.')            // if there is escape point in corner of island
                    dfs(island, 0, j);         // start searching all connected empty fields from (0, j)
            // walk on right-most column of island
            for (int j = 0; j < n; j++)
                if (island[m - 1][j] == '.')        // if there is escape point in corner of island
                    dfs(island, m - 1, j);     // start searching all connected empty fields from (m - 1, j)
            // see if there is any empty fields left within island
            // after figuring out all escape points
            int ans = 0;
            for (char[] chars : island)
                for (char c : chars)
                    if (c == '.')
                        ans++;

            return ans;
        }
    }

    private static void print(Object... O) {
        System.out.println(Arrays.deepToString(O));
    }

    public static void main(String[] args) {
        char[][] island = {{'.', '*', '*', '.'},
                {'*', '.', '*', '.'},
                {'*', '.', '.', '*'},
                {'*', '*', '*', '*'}};
        print(new TestClass().trappedWater(island));
    }
}
