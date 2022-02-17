package standard;

import java.util.*;

/**
 * <a href=https://practice.geeksforgeeks.org/problems/minimum-platforms-1587115620/1#>Minimum Platforms</a>
 */

public class MinimumPlatforms {
    /**
     * Function to find the minimum number of platforms required
     * at the railway station such that no train waits.
     * <p>Time Complexity: O(nlog(n))
     * <br>Space Complexity: O(n)
     */
    static int findPlatform(int arr[], int dep[], int n) {
        int[][] times = new int[n][2];
        for (int i = 0; i < n; i++) {
            times[i][0] = arr[i];
            times[i][1] = dep[i];
        }

        // sort according to dep then arr time
        Arrays.sort(times, (x, y) -> {
            if (x[0] != y[0]) return x[0] - y[0];
            else return x[1] - y[1];
        });

        int platformsRequired = 0;      // initially zero platforms required
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            // add current train's departure time into the queue
            pq.add(times[i][1]);
            // remove trains whose departed from station
            // current time = arrival of current train
            while (!pq.isEmpty() && pq.peek() < times[i][0]) {
                pq.poll();
            }
            // check how many trains are there in the station currently
            platformsRequired = Math.max(platformsRequired, pq.size());
        }
        return platformsRequired;
    }
}
