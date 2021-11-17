package leetcode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created at : 30/10/21
 * <p>
 * <a href=https://leetcode.com/problems/two-best-non-overlapping-events/>5899. Two Best Non-Overlapping Events</a>
 *
 * @author Himanshu Shekhar
 */

public class _TwoBestNonOverlappingEvents {
    /**
     * <strong>Brute Force</strong>
     * <p>Time Complexity: O(n<sup>2</sup>)
     * <br>Space Complexity: O(1)
     */
    public int maxTwoEvents(int[][] events) {
        // sort
        // 1st col -> asc
        // 2nd col -> asc
        // 3rd col -> desc
        Arrays.sort(events, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if (a[0] < b[0])
                    return -1;
                else if (a[0] == b[0]) {
                    if (a[1] < b[1])
                        return -1;
                    else if (a[1] == b[1])
                        if (a[2] > b[2])
                            return -1;
                        else
                            return 1;
                    else
                        return 1;
                } else
                    return 1;
            }
        });

        int max = 0;
        // attend only 1 event
        for (int i = 0; i < events.length; i++) {
            max = Math.max(max, events[i][2]);
        }
        // attend 2 events
        for (int i = 0; i < events.length; i++) {
            for (int j = i + 1; j < events.length; j++) {
                if (events[i][1] < events[j][0]) {
                    max = Math.max(max, events[i][2] + events[j][2]);
                }
            }
        }
        return max;
    }
}
