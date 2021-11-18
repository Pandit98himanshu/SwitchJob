package leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created at : 05/11/21
 * <p>
 * <a href=https://leetcode.com/contest/weekly-contest-265/problems/minimum-operations-to-convert-number/>2059. Minimum Operations to Convert Numbe</a>
 *
 * @author Himanshu Shekhar
*/

public class MinimumOperationsToConvertNumber {
    /**
     * <strong>BFS</strong>
     * <p>Copied from <a href=https://leetcode.com/problems/minimum-operations-to-convert-number/discuss/1549960/C++-or-BFS-or-Clean-code>leetcode discuss</a>
     */
    public int minimumOperations(int[] nums, int start, int goal) {
        Queue<Integer> q = new LinkedList<>();
        HashSet<Integer> visited = new HashSet<>();
        int steps = 0;
        q.add(start);
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                // take values one-by-one
                int val = q.poll();
                // our goal value is reached
                if (val == goal)
                    return steps;
                // if current value is out-of-bound or, it is visited
                // then we'll move forward with other values
                if (val < 0 || val > 1000 || visited.contains(val))
                    continue;
                // check for further numbers
                for (int i : nums) {
                    q.add(val + i);
                    q.add(val - i);
                    q.add(val ^ i);
                }
                visited.add(val);
            }
            // we'll try in next step
            steps++;
        }
        // we could not find our goal
        return -1;
    }
}
