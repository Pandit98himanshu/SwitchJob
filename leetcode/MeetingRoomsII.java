package leetcode;

import java.util.*;

/**
 * Created at : 06/09/21
 * <p>
 * <a href=https://leetcode.com/problems/meeting-rooms-ii/>253. Meeting Rooms II</a>
 *
 * @author Himanshu Shekhar
 */

public class MeetingRoomsII {
    /**
     * <strong>Priority Queue</strong>
     * <p>Time Complexity: O(N(logN))
     * @see <a href=https://www.geeksforgeeks.org/minimum-number-platforms-required-railwaybus-station/>Similar question on GfG</a>
     */
    public int minMeetingRooms(int[][] intervals) {
        // edge case
        if (intervals.length == 0)
            return 0;
        // sort based on starting time, if starting times
        // are equal, sort on the basis of ending time
        Arrays.sort(intervals, (x, y) -> {
            if (x[0] != y[0]) return x[0] - y[0];
            return x[1] - y[1];
        });

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(intervals[0][1]);
        int ans = 1;
        for (int i = 1; i < intervals.length; i++) {
            // free up all rooms which are emptied
            while (!pq.isEmpty() && pq.peek() <= intervals[i][0])
                pq.poll();
            // add ending time of current meeting
            pq.add(intervals[i][1]);
            // everytime check or the number of rooms required
            ans = Math.max(ans, pq.size());
        }
        return ans;
    }
}
