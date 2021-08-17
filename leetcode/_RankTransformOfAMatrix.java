/*
 * 1632. Rank Transform of a Matrix
 * https://leetcode.com/problems/rank-transform-of-a-matrix/
 */

package leetcode;

import java.util.*;

// Will be solved using UNION-FIND data structure
public class _RankTransformOfAMatrix {
    static class Pair {
        Integer x, y;

        Pair(Integer x, Integer y) {
            this.x = x;
            this.y = y;
        }
    }

    /**
     * <strong>NOT WORKING</strong>
     */
    public static int[][] matrixRankTransform(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        TreeMap<Integer, ArrayList<Pair>> map = new TreeMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ArrayList<Pair> list = map.get(matrix[i][j]);
                if (list == null)
                    list = new ArrayList<>();
                list.add(new Pair(i, j));
                map.put(matrix[i][j], list);
            }
        }

        int[][] dp = new int[m][n];

        for (Map.Entry<Integer, ArrayList<Pair>> e : map.entrySet()) {
            int value = e.getKey();
            ArrayList<Pair> posList = e.getValue();

            // For Debugging
                System.out.print(value + " : ");
                posList.forEach(i -> System.out.print("(" + i.x + "," + i.y + "), "));
                System.out.println();

            int maxRank;
            for (Pair pos : posList) {
                maxRank = 0;
                Pair maxRankPos = new Pair(0, 0);
                // search for maxRank in current row
                for (int x = 0; x < m; x++) {
                    if (maxRank < dp[x][pos.y]) {
                        maxRank = dp[x][pos.y];
                        maxRankPos.x = x;
                        maxRankPos.y = pos.y;
                    }
                }
                // search for maxRank in current column
                for (int y = 0; y < n; y++) {
                    if (maxRank < dp[pos.x][y]) {
                        maxRank = dp[pos.x][y];
                        maxRankPos.x = pos.x;
                        maxRankPos.y = y;
                    }
                }
                if (value > matrix[maxRankPos.x][maxRankPos.y] || maxRank == 0)
                    maxRank++;
                dp[pos.x][pos.y] = maxRank;
                System.out.println("(" + pos.x + "," + pos.y + ") :: " + maxRank);
            }

            for (int i = 0; i < posList.size(); i++) {
                int max = dp[posList.get(i).x][posList.get(i).y];
                for (int j = i; j < posList.size(); j++) {
                    if (posList.get(i).x == posList.get(j).x || posList.get(i).y == posList.get(j).y) {
                        max = Math.max(max, dp[posList.get(j).x][posList.get(j).y]);
                    }
                }
                for (int j = i; j < posList.size(); j++) {
                    if (posList.get(i).x == posList.get(j).x || posList.get(i).y == posList.get(j).y) {
                        dp[posList.get(j).x][posList.get(j).y] = max;
                    }
                }
            }
        }
        return dp;
    }

    public static void main(String[] args) {
        int[][] matrix = {{-37, -50, -3, 44}, {-37, 46, 13, -32}, {47, -42, -3, -40}, {-17, -22, -39, 24}};
//        int[][] matrix = {{-37, -26, -47, -40, -13}, {22, -11, -44, 47, -6}, {-35, 8, -45, 34, -31}, {-16, 23, -6, -43, -20}, {47, 38, -27, -8, 43}};
        System.out.println(Arrays.deepToString(matrixRankTransform(matrix)));
    }
}
