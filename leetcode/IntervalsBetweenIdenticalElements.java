package leetcode;

import java.util.*;

/**
 * Created at : 26/12/21
 * <p>
 * <a href=https://leetcode.com/problems/intervals-between-identical-elements/>5965. Intervals Between Identical Elements</a>
 *
 * @author Himanshu Shekhar
 */

public class IntervalsBetweenIdenticalElements {
    /**
     * TLE
     */
    public long[] getDistances(int[] arr) {
        long[] ans = new long[arr.length];
        // left to right
        getDistancesHelper(ans, arr, 0, arr.length-1, 1);

        // right to left
        getDistancesHelper(ans, arr, arr.length-1, 0, -1);

        return ans;
    }
    private void getDistancesHelper(long[] ans, int[] arr, int start, int end, int increment) {
        List<Integer>[] map = new List[(int)1e5+1];
        for (int i = start; i <= end; i += increment) {
            List<Integer> indices = map[arr[i]];
            if (indices == null)
                indices = new ArrayList<>();
            else {
                long diff = 0;
                for (int index : indices) {
                    diff += Math.abs(i - index);
                }
                ans[i] += diff;
            }
            indices.add(i);
            map[arr[i]] = indices;
        }
    }

    /**
     * TLE
     */
    public long[] getDistances1(int[] arr) {
        long[] ans = new long[arr.length];
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            List<Integer> list = map.getOrDefault(arr[i], new ArrayList<>());
            list.add(i);
            map.put(arr[i], list);
        }
        map.forEach((val, ind) -> {
            for (int i = 0; i < ind.size(); i++) {
                long diff = 0;
                for (int j = 0; j < ind.size(); j++) {
                    diff += Math.abs(ind.get(i) - ind.get(j));
                }
                ans[ind.get(i)] = diff;
            }
        });
        return ans;
    }
}
