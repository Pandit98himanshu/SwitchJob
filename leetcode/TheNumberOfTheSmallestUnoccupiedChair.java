package leetcode;

import java.util.*;

/**
 * Created at : 19/08/21
 * <p>
 * <a href=https://leetcode.com/problems/the-number-of-the-smallest-unoccupied-chair/>5805. The Number of the Smallest Unoccupied Chair</a>
 *
 * @author Himanshu Shekhar
 */

public class TheNumberOfTheSmallestUnoccupiedChair {

    /**
     * Copied from <a href=https://leetcode.com/problems/the-number-of-the-smallest-unoccupied-chair/discuss/1359790/C++-Set-+-Priority-Queue-or-Commented/1022805>leetcode discuss comment</a>
     * <p>Time Complexity: O(n)
     * <br>Space Complexity: O(n); where n = times.length
     *
     * @param times        arrival and leaving times of the ith friend
     * @param targetFriend
     * @return the chair number that the friend numbered targetFriend will sit on
     */
    public static int smallestChair(int[][] times, int targetFriend) {
        // arrival time of target friend
        int arrivalTargetFriend = times[targetFriend][0];

        Comparator<int[]> compareFirstColumn = (a, b) -> a[0] - b[0];
        Arrays.sort(times, compareFirstColumn);     // sort times a/c to starting time

        Queue<Integer> availableSeats = new PriorityQueue<>();
        for (int i = 0; i < times.length; i++)
            availableSeats.offer(i);        // initially we have all seats available

        // put all occupied seats in a min-heap a/c to departure time
        Queue<int[]> occupiedSeats = new PriorityQueue<>(compareFirstColumn);
        for (int[] time : times) {
            int arrivalCurrentFriend = time[0];
            int departureCurrentFriend = time[1];
            // first we put all seats in available queue which are empty
            while (!occupiedSeats.isEmpty() && occupiedSeats.peek()[0] <= arrivalCurrentFriend)
                availableSeats.offer(occupiedSeats.poll()[1]);
            // our target friend arrives & he'll seat at top available seat
            if (arrivalTargetFriend == arrivalCurrentFriend)
                break;
            // otherwise, make current friend seat at 1st available seat
            occupiedSeats.offer(new int[]{departureCurrentFriend, availableSeats.poll()});
        }

        return availableSeats.peek();
    }

    /**
     * <strong>NOT WORKING</strong>
     */
    public static int smallestChair1(int[][] times, int targetFriend) {
        int n = times.length;
        int[][] friendAndTimes = new int[n][3];
        for (int i = 0; i < n; i++) {
            friendAndTimes[i][0] = i;           // friend number
            friendAndTimes[i][1] = times[i][0]; // friend's arrival time
            friendAndTimes[i][2] = times[i][1]; // friend's departure time
        }
        // sort according to arrival time
        Arrays.sort(friendAndTimes, Comparator.comparingInt((int[] o) -> o[1]));

        int[] chair = new int[n];
        int maxAllottedChairs = 0;
        Arrays.fill(chair, -1);
        int allottedChair = -1;
        for (int i = 0; i < n; i++) {
            int currentFriend = friendAndTimes[i][0];
            int arrivalOfCurrentFriend = friendAndTimes[i][1];
            allottedChair = maxAllottedChairs++;
            for (int j = 0; j < i; j++) {
                if (friendAndTimes[j][2] <= arrivalOfCurrentFriend) {
                    if (chair[j] >= 0) {
                        if (friendAndTimes[j][2] <= arrivalOfCurrentFriend) {
                            chair[j] = currentFriend;
                            allottedChair = j;
                            maxAllottedChairs--;
                        }
                    }
                } else if (chair[j] < 0) {
                    chair[j] = currentFriend;
                    allottedChair = j;
                    break;
                }
            }
            if (currentFriend == targetFriend) {
                return allottedChair;
            }
        }
        print(chair);
        return allottedChair;
    }

    public static void print(Object... O) {
        System.out.println(Arrays.deepToString(O));
    }
}