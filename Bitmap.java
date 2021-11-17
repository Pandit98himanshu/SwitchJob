import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Created at : 23/10/21
 * <p>
 * <a href=https://www.spoj.com/problems/BITMAP/>BITMAP - Bitmap</a>
 *
 * @author Himanshu Shekhar
 */

public class Bitmap {
    /**
     * <strong>Multi-source BFS</strong>
     * Similar to {@link leetcode.RottingOranges#orangesRotting(int[][])}
     */
    private static int[][] bitmap(int[][] map, int m, int n) {
        int[][] res = new int[m][n];
        int[] d = {0, 1, 0, -1, 0};
        Queue<int[]> src = new LinkedList<>();
        // adding all sources i.e., "1's" into a queue
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 1)
                    src.offer(new int[]{i, j});
                else
                    res[i][j] = Integer.MAX_VALUE;
            }
        }

        int currDist = 1;   // dist. of adjacent cells containing "1" is 1
        while (!src.isEmpty()) {
            int size = src.size();
            while (size-- > 0) {
                int[] currSrc = src.poll();
                int x = currSrc[0], y = currSrc[1];
                for (int i = 0; i < 4; i++) {
                    int nextX = x + d[i], nextY = y + d[i + 1];
                    boolean inBound = (nextX >= 0 && nextX < m) && (nextY >= 0 && nextY < n);
                    // neighboring cells is 1 more than current distance from "1"
                    // store minimum distance from "1"
                    if (inBound && map[nextX][nextY] == 0 && res[nextX][nextY] > currDist) {
                        res[nextX][nextY] = currDist;
                        src.offer(new int[]{nextX, nextY});
                    }
                }
            }
            // distance of neighboring cells increases as we move far from cell containing "1"
            currDist++;
        }
        return res;
    }

    /**
     * Method to print a 2-d array
     */
    private static void print2DArray(int[][] arr) {
        for (int[] row : arr) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            // taking inputs
            int t = sc.nextInt();
            while (t-- > 0) {
                int m = sc.nextInt();
                int n = sc.nextInt();
                int[][] map = new int[m][n];
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < n; j++) {
                        map[i][j] = sc.nextInt();
                    }
                }
                // solving the problem
                int[][] res = bitmap(map, m, n);
                // printing the output
                print2DArray(res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
