/*
 * 1921. Eliminate Maximum Number of Monsters
 * https://leetcode.com/problems/eliminate-maximum-number-of-monsters/
 */
package leetcode;

import java.util.*;

class EliminateMaximumNumberOfMonsters {
    static class Solution {
        public int eliminateMaximum(int[] dist, int[] speed) {
            int n = dist.length;
            int[] time = new int[n];        // time when each monster reaches the city

            for (int i = 0; i < n; i++)
                time[i] = (int)Math.ceil((1.0* dist[i]) / speed[i]);


            Arrays.sort(time);

            for (int i = 0, t = 1; i < n; i++, t++)
                // if reaching time of monster is less than current time "t",
                // i.e., monster enters the city
                if (time[i] < t)
                    return i;

            return n;
        }
    }

    public static void main(String[] args) {
        int[] dist = {3,5,7,4,5}, speed = {2,3,6,3,2};

        System.out.println(new Solution().eliminateMaximum(dist, speed));
    }
}