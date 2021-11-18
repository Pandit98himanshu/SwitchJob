package leetcode;

import java.util.TreeMap;

/**
 * Created at : 08/08/21
 * <p>
 * <a href=https://leetcode.com/problems/find-the-longest-valid-obstacle-course-at-each-position/>1964. Find the Longest Valid Obstacle Course at Each Position</a>
 *
 * @author Himanshu Shekhar
 */

public class FindTheLongestValidObstacleCourseAtEachPosition {
    public int[] longestObstacleCourseAtEachPosition(int[] obstacle) {
        int n = obstacle.length;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            int temp = 1;
            // get largest element after current element
            Integer floor = map.floorKey(obstacle[i]);
            if (floor != null) {
                temp = obstacle[map.get(floor)] + 1;
            }
            // remove all element greater than or equal to current element
            Integer ceil = map.ceilingKey(obstacle[i]);
            while (ceil != null && obstacle[map.get(ceil)] < temp) {
                map.remove(ceil);
                ceil = map.ceilingKey(obstacle[i]);
            }
            map.put(obstacle[i], i);
            obstacle[i] = temp;
        }
        return obstacle;
    }
}
