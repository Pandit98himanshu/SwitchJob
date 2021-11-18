package leetcode;

import java.util.Arrays;

/**
 * Created at : 28/08/21
 * <p>
 * <a href=https://leetcode.com/problems/merge-intervals/>56. Merge Intervals</a>
 *
 * @author Himanshu Shekhar
 */

public class MergeIntervals {
    public static void main(String[] args) {
        int[][] intervals = {{0, 0}, {1, 2}, {5, 5}, {2, 4}, {3, 3}, {5, 6}, {5, 6}, {4, 6}, {0, 0}, {1, 2}, {0, 2}, {4, 5}};
        System.out.println(Arrays.deepToString(new MergeIntervals().merge1(intervals)));
    }

    /**
     * <strong>Linear Search</strong>
     * <p>Time Complexity: O(n)
     * <br>Space Complexity: O(n)
     */
    private int[][] merge1(int[][] intervals) {
        // sort asc a/c to start time,
        // if start time is same, sort a/c to end time
        Arrays.sort(intervals, (int[] x, int[] y) -> {
            if (x[0] != y[0]) return x[0] - y[0];
            return x[1] - y[1];
        });

        int k = 0;
        int[][] mergedIntervals = new int[intervals.length][2];
        mergedIntervals[k++][0] = intervals[0][0];

        int maxEnd = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            // if starting time of current interval is less than
            // or equal to ending time of max ending time, our interval doesn't end here
            if (intervals[i][0] > maxEnd && intervals[i][1] > maxEnd) {
                mergedIntervals[k - 1][1] = maxEnd;
                mergedIntervals[k++][0] = intervals[i][0];
            }
            // update maxEnd if end time of current interval greater
            maxEnd = Math.max(maxEnd, intervals[i][1]);
        }
        mergedIntervals[k - 1][1] = maxEnd;

        int[][] finalResult = new int[k][2];
        for (int i = 0; i < k; i++) {
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
        if (interval1[i][1] > interval2[i][0]) {
        }
        // todo: write a method to merge 2 intervals copy from MergeKLists
        return new int[][]{{-1, -1}};
    }
}
