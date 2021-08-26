/*
 * 56. Merge Intervals
 * https://leetcode.com/problems/merge-intervals/
 */

package leetcode;

import java.util.*;

public class _MergeIntervals {
    /**
     * <strong>Linear Search</strong>
     * <p>Time Complexity: O(n)
     * <br>Space Complexity: O(n)
     */
    private int[][] merge1(int[][] intervals) {
        // sort asc a/c to end time
        Arrays.sort(intervals, (int[] o1, int[] o2) -> o1[1] - o2[1]);
        // sort asc a/c to start time
        Arrays.sort(intervals, (int[] o1, int[] o2) -> o1[0] - o2[0]);

        int k = 0;
        int[][] mergedIntervals = new int[intervals.length][2];
        mergedIntervals[k++][0] = intervals[0][0];

        int i, maxEnd = intervals[0][1];
        for (i = 1; i < intervals.length; i++) {
            // keep eye on maxEnd interval
            maxEnd = Math.max(maxEnd, intervals[i - 1][1]);
            // if starting time of current interval is less than
            // or equal to ending time of max ending time, our interval doesn't end here
            if (intervals[i][0] <= maxEnd)
                continue;
            //
            mergedIntervals[k - 1][1] = maxEnd;
            mergedIntervals[k++][0] = intervals[i][0];
        }
        mergedIntervals[k - 1][1] = Math.max(maxEnd, intervals[i - 1][1]);

        int[][] finalResult = new int[k][2];
        for (i = 0; i < k; i++) {
            finalResult[i][0] = mergedIntervals[i][0];
            finalResult[i][1] = mergedIntervals[i][1];
        }
        return finalResult;
    }

    /**
     * <strong>Divide & Conquer</strong>
     */
    public int[][] merge(int[][] intervals) {
        return merge(intervals, 0, intervals.length - 1);
    }

    private int[][] merge(int[][] intervals, int start, int end) {
        if (start < end) {
            int mid = (start + end) / 2;

            int[][] interval1 = merge(intervals, start, mid);
            int[][] interval2 = merge(intervals, mid + 1, end);

            return merge2Intervals(interval1, interval2, mid);
        }
        return intervals;
    }

    private int[][] merge2Intervals(int[][] interval1, int[][] interval2, int i) {
        if (interval1[i][1] > interval2[i][0]) {}
        // todo: write a method to merge 2 intervals copy from MergeKLists
        return new int[][]{{-1, -1}};
    }
}
