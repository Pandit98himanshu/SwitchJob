/*
 * 675. Cut Off Trees for Golf Event
 * https://leetcode.com/problems/cut-off-trees-for-golf-event/
 */

package leetcode;

import java.util.*;

public class CutOffTreesForGolfEvent {
    /**
     * <strong>BFS</strong>
     * <p>Time Complexity: O((m * n)<sup>2</sup>)
     * <br>Space Complexity: O(m * n)
     * <p>
     *     NOTE: Since 2D grid is actually a unweighted graph,
     *     to find a shortest path, the most recommended way is to use BFS.
     *     It is noted that DFS can ALSO find the shortest path,
     *     but it needs to find all feasible paths and find the shortest one.
     *     In this regard, BFS is much faster than DFS!
     *     <br>src:<a href=https://leetcode.com/problems/cut-off-trees-for-golf-event/discuss/251517/Why-BFS-AND-NOT-DFS>leetcode discuss comment</a>
     * </p>
     * @return total number of minimum steps required to
     * cut all trees of forest in increasing order
     */
    public int cutOffTree(List<List<Integer>> forest) {
        // store all values & it's indexes in sorted order
        // we can also use array and sort
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        for (int i = 0; i < forest.size(); i++) {
            for (int j = 0; j < forest.get(i).size(); j++) {
                if (forest.get(i).get(j) > 1)
                    pq.add(new int[]{i, j, forest.get(i).get(j)});
            }
        }
        int[] start = new int[2];       // initially we are at (0, 0)
        int totalSteps = 0;
        while (!pq.isEmpty()) {
            int[] next = pq.poll();
            int minSteps = bfs(forest, start, next);
            // edge case
            if (minSteps == -1)
                return -1;
            totalSteps += minSteps; // add min steps to total steps
            start[0] = next[0];     // x-coordinate
            start[1] = next[1];     // y-coordinate
        }
        return totalSteps;
    }

    /**
     * <strong>BFS</strong>
     * <p>Time Complexity: O(m * n)
     * <br>Space Complexity: O(m * n)
     *
     * @param start starting position
     * @param des   destination
     * @return minimum number of steps required to reach destination from starting position
     */
    private final int[] dir = {0, 1, 0, -1, 0};

    private int bfs(List<List<Integer>> forest, int[] start, int[] des) {
        int steps = 0;
        int m = forest.size(), n = forest.get(0).size();
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> q = new LinkedList<>();

        q.add(start);
        visited[start[0]][start[1]] = true;

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] curr = q.poll();          // bring current cell from the queue
                // if we reach "des", return steps involved
                if (curr[0] == des[0] && curr[1] == des[1])
                    return steps;
                // add neighbouring cells to the queue and visit them
                for (int j = 0; j < 4; j++) {
                    int nextX = curr[0] + dir[j];
                    int nextY = curr[1] + dir[j + 1];
                    if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n &&
                            forest.get(nextX).get(nextY) != 0 && !visited[nextX][nextY]) {
                        q.add(new int[]{nextX, nextY});
                        visited[nextX][nextY] = true;
                    }
                }
            }
            steps++;
        }
        // we cannot reach the destination
        return -1;
    }

    public static void main(String[] args) {
        int[][] arr = {{1,2,3},{0,0,4},{7,6,5}};
        List<List<Integer>> forest = new ArrayList<>();
        for (int[] ints : arr) {
            List<Integer> l = new ArrayList<>();
            for (int i : ints)
                l.add(i);
            forest.add(l);
        }
        System.out.println(new CutOffTreesForGolfEvent().cutOffTree(forest));
    }
}
