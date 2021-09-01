/*
 * 207. Course Schedule
 * https://leetcode.com/problems/course-schedule/
 */

package leetcode;

import java.util.*;

public class CourseSchedule {
    // todo: implement topological sort algorithm

    boolean isPossible = true;

    /**
     * <strong>Preorder DFS</strong>
     * <p>Copied from <a href=https://leetcode.com/problems/course-schedule/solution/>leetcode solution</a>
     * <p>Time Complexity: O(E+V)
     * <br>Space Complexity: O(E+V)
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {

        // course -> list of next courses
        HashMap<Integer, List<Integer>> courseDict = new HashMap<>();

        // build the graph first
        for (int[] relation : prerequisites) {
            // relation[0] depends on relation[1]
            if (courseDict.containsKey(relation[1])) {
                courseDict.get(relation[1]).add(relation[0]);
            } else {
                List<Integer> nextCourses = new LinkedList<>();
                nextCourses.add(relation[0]);
                courseDict.put(relation[1], nextCourses);
            }
        }

        boolean[] checked = new boolean[numCourses];
        boolean[] path = new boolean[numCourses];

        for (int currCourse = 0; currCourse < numCourses; ++currCourse) {
            if (this.isCyclic(currCourse, courseDict, checked, path))
                return false;
        }
        return true;
    }


    /**
     * Postorder DFS check that no cycle would be formed starting from currCourse
     */
    protected boolean isCyclic(Integer currCourse, HashMap<Integer, List<Integer>> courseDict,
            boolean[] checked, boolean[] path) {

        // bottom cases
        if (checked[currCourse])
            // this node has been checked, no cycle would be formed with this node.
            return false;
        if (path[currCourse])
            // come across a previously visited node, i.e. detect the cycle
            return true;

        // no following courses, no loop.
        if (!courseDict.containsKey(currCourse))
            return false;

        // before backtracking, mark the node in the path
        path[currCourse] = true;

        boolean ret = false;
        // postorder DFS, to visit all its children first.
        for (Integer child : courseDict.get(currCourse)) {
            ret = this.isCyclic(child, courseDict, checked, path);
            if (ret)
                break;
        }

        // after the visits of children, we come back to process the node itself
        // remove the node from the path
        path[currCourse] = false;

        // Now that we've visited the nodes in the downstream,
        // we complete the check of this node.
        checked[currCourse] = true;
        return ret;
    }

    /**
     * <strong>Backtracking - Gives TLE for large input</strong>
     * <p>Time Complexity: O(E+V<sup>2</sup>)
     * <br>Space Complexity: O(E+V)
     */
    private void backtrack(List<Integer>[] list, int i, HashSet<Integer> visited) {
        if (list[i] == null)
            return;
        visited.add(i);
        for (int j : list[i]) {
            if (visited.contains(j)) {
                isPossible = false;
                return;
            }

            visited.add(j);
            backtrack(list, j, visited);
            visited.remove(j);
        }
        visited.remove(i);
    }

    public boolean canFinish1(int numCourses, int[][] prerequisites) {
        List<Integer>[] list = new ArrayList[numCourses];
        for (int[] pre : prerequisites) {
            List<Integer> l = list[pre[0]];
            if (l == null)
                l = new ArrayList<>();

            l.add(pre[1]);
            list[pre[0]] = l;
        }
        // print(list);
        for (int i = 0; i < numCourses; i++) {
            backtrack(list, i, new HashSet<>());
        }
        return isPossible;
    }

    private void print(Object... o) {
        System.out.println(Arrays.deepToString(o));
    }

    public static void main(String[] args) {
        int numCourses = 5;
        int[][] prerequisites = {{1, 0}, {0, 2}, {0, 4}, {2, 4}};
        System.out.println(new CourseSchedule().canFinish(numCourses, prerequisites));
    }
}
