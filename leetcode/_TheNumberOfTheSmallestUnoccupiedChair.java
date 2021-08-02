/*
 * 5805. The Number of the Smallest Unoccupied Chair
 * https://leetcode.com/problems/the-number-of-the-smallest-unoccupied-chair/
 */

package leetcode;

import java.util.*;

public class _TheNumberOfTheSmallestUnoccupiedChair {
    static class Solution {
        public int smallestChair(int[][] times, int targetFriend) {
            int n = times.length;
            int[][] friendAndTimes = new int[n][3];
            for (int i = 0; i < n; i++) {
                friendAndTimes[i][0] = i;           // friend number
                friendAndTimes[i][1] = times[i][0]; // friend's arrival time
                friendAndTimes[i][2] = times[i][1]; // friend's departure time
            }
            // sort according to arrival time
            Arrays.sort(friendAndTimes, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return Integer.compare(o1[1], o2[1]);
                }
            });
            int[] chair = new int[n];
            int maxAllotedChairs = 0;
            Arrays.fill(chair, -1);
            int allottedChair = -1;
            for (int i = 0; i < n; i++) {
                int currentFriend = friendAndTimes[i][0];
                int arrivalOfCurrentFriend = friendAndTimes[i][1];
                allottedChair = maxAllotedChairs++;
                for (int j = 0; j < i; j++) {
                    if (friendAndTimes[j][2] <= arrivalOfCurrentFriend) {
                        if (chair[j] >= 0) {
                            if (friendAndTimes[j][2] <= arrivalOfCurrentFriend) {
                                chair[j] = currentFriend;
                                allottedChair = j;
                                maxAllotedChairs--;
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
    }

    public static void print(Object... O) {
        System.out.println(Arrays.deepToString(O));
    }

    public static void main(String[] args) {
        int[][] times = {{33889,98676},{80071,89737},{44118,52565},{52992,84310},{78492,88209},{21695,67063},{84622,95452},{98048,98856},{98411,99433},{55333,56548},{65375,88566},{55011,62821},{48548,48656},{87396,94825},{55273,81868},{75629,91467}};
        int targetFriend = 6;
        print(new Solution().smallestChair(times, targetFriend));
    }
}
