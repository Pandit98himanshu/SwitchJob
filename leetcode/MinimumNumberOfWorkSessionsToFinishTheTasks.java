/*
 * 1986. Minimum Number of Work Sessions to Finish the Tasks
 * https://leetcode.com/problems/minimum-number-of-work-sessions-to-finish-the-tasks/
 */

package leetcode;

import java.util.*;

public class MinimumNumberOfWorkSessionsToFinishTheTasks {
    /**
     * <strong>Bit Masking with DP</strong>
     * <p>Copied from <a href=https://leetcode.com/problems/minimum-number-of-work-sessions-to-finish-the-tasks/discuss/1432155/Easier-than-top-voted-ones-or-LegitClickbait-or-c++>leetcode discuss</a>
     * <p>Time Complexity: O(n * 2<sup>n</sup>)
     */
    private int findMinSessions(int[] tasks, int sessionTime, int mask, int currTime, int[][] dp) {
        int n = tasks.length;
        if (currTime > sessionTime)
            return Integer.MAX_VALUE;
        if (mask == (1 << n) - 1)           // we completed all tasks hence all bits are "1" in "mask"
            return 1;
        if (dp[mask][currTime] != -1)
            return dp[mask][currTime];

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            // mask tells which task has been done
            if ((mask & (1 << i)) == 0) {       // check whether ith task is undone
                // either add current task in one of the active session
                int includeInCurrentSession = findMinSessions(tasks, sessionTime, mask | (1 << i), currTime + tasks[i], dp);
                // or create a new session
                int includeInNewSession = 1 + findMinSessions(tasks, sessionTime, mask | (1 << i), tasks[i], dp);
                // keep track of minimum sessions required
                ans = Math.min(ans, Math.min(includeInCurrentSession, includeInNewSession));
            }
        }
        dp[mask][currTime] = ans;
        return ans;
    }

    public int minSessions(int[] tasks, int sessionTime) {
        int[][] dp = new int[1 << 15][16];
        for (int[] i : dp)
            Arrays.fill(i, -1);

        return findMinSessions(tasks, sessionTime, 0, 0, dp);
    }

    /**
     * <strong>Bit Masking</strong> - TLE
     * <p>Copied from <a href=https://leetcode.com/problems/minimum-number-of-work-sessions-to-finish-the-tasks/discuss/1432155/Easier-than-top-voted-ones-or-LegitClickbait-or-c++>leetcode discuss</a>
     * <p>Time Complexity: O(n<sup>n</sup>)
     */
    private int findMinSessions(int[] tasks, int sessionTime, int mask, int currTime) {
        int n = tasks.length;
        if (currTime > sessionTime)     // we can't spend more than "sessionTime" in one session
            return Integer.MAX_VALUE;
        if (mask == (1 << n) - 1)       // we selected all tasks
            return 1;

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if ((mask & (1 << i)) == 0) {   // check whether ith bit is set, if not, make it set and check for possibilities
                int includeInCurrentSession = findMinSessions(tasks, sessionTime, mask | (1 << i), currTime + tasks[i]);
                int includeInNewSession = 1 + findMinSessions(tasks, sessionTime, mask | (1 << i), tasks[i]);
                ans = Math.min(ans, Math.min(includeInCurrentSession, includeInNewSession));
            }
        }
        return ans;
    }

    public int minSessions2(int[] tasks, int sessionTime) {
        int[][] dp = new int[1 << 15][16];
        for (int[] i : dp) {
            Arrays.fill(i, -1);
        }
        return findMinSessions(tasks, sessionTime, 0, 0);
    }

    /**
     * <strong>Backtracking</strong> - TLE
     */
    private int findMinSessions(int[] tasks, int sessionTime, int index, LinkedList<Integer> sessions) {
        if (index >= tasks.length)
            return 0;

        // start a new session for current task
        sessions.add(tasks[index]);
        int ans = 1 + findMinSessions(tasks, sessionTime, index + 1, sessions);
        sessions.removeLast();      // don't forget to revert

        // try to allocate current task in active sessions
        for (int i = 0; i < sessions.size(); i++) {
            int temp = sessions.get(i);
            if (temp + tasks[index] <= sessionTime) {
                sessions.set(i, temp + tasks[index]);
                ans = Math.min(ans, findMinSessions(tasks, sessionTime, index + 1, sessions));
                sessions.set(i, temp);  // don't forget to revert
            }
        }

        return ans;
    }

    public int minSessions1(int[] tasks, int sessionTime) {
        return findMinSessions(tasks, sessionTime, 0, new LinkedList<>());
    }

    public static void main(String[] args) {
        int[] tasks = {9, 8, 8, 4, 6};
        int sessionTime = 14;
        System.out.println(new MinimumNumberOfWorkSessionsToFinishTheTasks().minSessions(tasks, sessionTime));
    }
}
