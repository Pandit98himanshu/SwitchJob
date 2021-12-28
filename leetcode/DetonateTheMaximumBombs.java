package leetcode;

import java.util.*;

/**
 * Created at : 12/12/21
 * <p>
 * <a href=https://leetcode.com/problems/detonate-the-maximum-bombs/>2101. Detonate the Maximum Bombs</a>
 *
 * @author Himanshu Shekhar
 */

public class DetonateTheMaximumBombs {
    /**
     * <strong>BFS</strong>
     * <p>Time Complexity: O(n<sup>3</sup>) : 86 ms
     * <br>Space Complexity: O(n)
     */
    public int maximumDetonation(int[][] bombs) {
        int n = bombs.length;
        int maxDetonatedBombs = 0;
        // pre-calculate other in-range bombs for each bomb
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++)
            graph[i] = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j)
                    continue;
                if (inRange(bombs[i], bombs[j]))
                    graph[i].add(j);
            }
        }

        for (int i = 0; i < n; i++) {
            boolean[] detonated = new boolean[n];
            Queue<Integer> q = new LinkedList<>();

            q.add(i);               // start detonating current bomb
            detonated[i] = true;    // remove detonated bomb
            int detonatedBombs = 1; // total bombs detonated till now is 1

            // iterate until no bomb will be left to detonate
            while (!q.isEmpty()) {
                // take out detonated bomb
                int curr = q.poll();
                // detonated other bombs in-range of current bomb
                for (int j : graph[curr]) {
                    // skip detonated bomb
                    if (detonated[j])
                        continue;
                    detonated[j] = true;// detonate bomb in range
                    q.add(j);           // add detonated bomb into the queue
                    detonatedBombs++;   // increase the number of detonated bomb by one
                }
            }
            // keep track of maximum bomb detonated
            maxDetonatedBombs = Math.max(maxDetonatedBombs, detonatedBombs);
        }
        return maxDetonatedBombs;
    }

    /**
     * <strong>BFS</strong>
     * <p>Time Complexity: O(n<sup>3</sup>) : 121 ms
     * <br>Space Complexity: O(n)
     */
    public int maximumDetonation1(int[][] bombs) {
        int n = bombs.length;
        int maxDetonatedBombs = 0;
        for (int i = 0; i < n; i++) {
            boolean[] detonated = new boolean[n];
            Queue<int[]> q = new LinkedList<>();

            q.add(bombs[i]);        // start detonating current bomb
            detonated[i] = true;    // remove detonated bomb
            int detonatedBombs = 1; // total bombs detonated till now is 1

            // iterate until no bomb will be left to detonate
            while (!q.isEmpty()) {
                // take out detonated bomb
                int[] curr = q.poll();
                // check how many bombs are in radius of current detonated bomb
                for (int j = 0; j < n; j++) {
                    // skip detonated bomb
                    if (detonated[j])
                        continue;
                    if (inRange(curr, bombs[j])) {
                        detonated[j] = true;// detonate bomb in range
                        q.add(bombs[j]);    // add detonated bomb into the queue
                        detonatedBombs++;   // increase the number of detonated bomb by one
                    }
                }
            }
            // keep track of maximum bomb detonated
            maxDetonatedBombs = Math.max(maxDetonatedBombs, detonatedBombs);
        }
        return maxDetonatedBombs;
    }

    private static boolean inRange(int[] a, int[] b) {
        double x = b[0]-a[0];
        double y = b[1]-a[1];
        double dist = Math.sqrt(x*x + y*y);

        return dist <= a[2];
    }
}
