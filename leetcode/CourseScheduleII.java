package leetcode;

import java.util.*;

/**
 * Created at : 24/12/21
 * <p>
 * <a href=https://leetcode.com/problems/course-schedule-ii/>210. Course Schedule II</a>
 *
 * @author Himanshu Shekhar
 */

public class CourseScheduleII {
    /**
     * <strong>Topological Sorting using BFS</strong>
     * <p>Time Complexity: O(numCourses + prerequisites.length) - 5 ms
     * <br>Space Complexity: O(4 * numCourses) - 40 MB
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> adj = new HashMap<>();
        int[] inDegree = new int[numCourses];
        int[] topologicalOrder = new int[numCourses];
        Queue<Integer> q = new LinkedList<>();

        // create adjacency list, curr -> [prerequisites]
        for (int i = 0; i < prerequisites.length; i++) {
            int src = prerequisites[i][1];
            int des = prerequisites[i][0];

            List<Integer> list = adj.getOrDefault(src, new ArrayList<>());
            list.add(des);
            adj.put(src, list);

            inDegree[des]++;
        }

        // put all those nodes into queue which has no parent node
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0)
                q.offer(i);
        }

        // use bfs
        int i = 0;
        while (!q.isEmpty()) {
            // queue contains nodes who have no parent,
            // or we already traversed its parent(s)
            int curr = q.poll();
            topologicalOrder[i++] = curr;
            // reduce in-degree for all succeeding nodes by 1
            if (adj.containsKey(curr)) {
                for (int j : adj.get(curr)) {
                    inDegree[j]--;
                    if (inDegree[j] == 0)
                        q.offer(j);
                }
            }
        }
        return (i == numCourses) ? topologicalOrder : new int[]{};
    }

    /**
     * <strong>BFS</strong>
     * <p>Time Complexity: O(numCourses + numCourses<sup>2</sup> + prerequisites.length) - 35 ms
     * <br>Space Complexity: O(4 * numCourses) - 40 MB
     */
    public int[] findOrder1(int numCourses, int[][] prerequisites) {
        List<Integer>[] list = new List[numCourses];
        boolean[] completed = new boolean[numCourses];
        Queue<Integer> q = new LinkedList<>();
        int[] ans = new int[numCourses];

        // create adjacency list of curr -> [prerequisites]
        for (int i = 0; i < prerequisites.length; i++) {
            int curr = prerequisites[i][0];
            int prereq = prerequisites[i][1];

            List<Integer> pre = list[curr];
            if (pre == null)
                pre = new ArrayList<>();
            pre.add(prereq);
            list[curr] = pre;
        }

        // put all courses into queue which has no prerequisites
        // mark them as completed in a boolean array
        for (int i = 0; i < list.length; i++) {
            if (list[i] == null) {
                q.offer(i);
                completed[i] = true;
            }
        }

        // start adding new courses from adj. list into
        // queue whose prerequisites are completed
        int i = 0;
        while (!q.isEmpty()) {
            int curr = q.poll();
            ans[i++] = curr;
            // for all courses, if any courses' all prerequisites
            // are completed then add it into the queue
            for (int j = 0; j < numCourses; j++) {
                if (completed[j])
                    continue;
                boolean flag = true;
                for (int pre : list[i]) {
                    if (!completed[pre])
                        flag = false;
                }
                if (flag) {
                    q.offer(j);
                    completed[j] = true;
                }
            }
        }

        // if we could not complete all courses then there
        // must be a loop, otherwise, return ans in array
        return (i == numCourses) ? ans : new int[]{};
    }
}
