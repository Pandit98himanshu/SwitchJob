/*
 * 899. Orderly Queue
 * https://leetcode.com/problems/orderly-queue/
 */

package leetcode;

import java.util.Arrays;

public class OrderlyQueue {
    /**
     * <p>Time Complexity: O(N(logN)) beats 100 %
     *
     * @return the lexicographically smallest string after moving
     * any of the first "k" letters at the end, any number of times
     */
    public String orderlyQueue(String s, int k) {
        // if k > 1, we can get smallest lexicographically possible
        // in other words, we can simply sort the string
        if (k > 1) {
            char[] c = s.toCharArray();
            Arrays.sort(c);
            return new String(c);
        }
        // NOTE: after moving 1st letter at end n times we will get the same string as original.
        // String starts with the smallest alphabet in the given string, is not the
        // lexicographically smallest string
        // for example - s = "azaakc" => ans = "aakcaz"
        // above given string starts with "a" but that is not our answer
        int n = s.length();
        String s2 = s + s, min = s2.substring(0, n);
        for (int i = 0; i < n; i++) {
            String curr = s2.substring(i, i + n);
            min = min.compareTo(curr) > 0 ? curr : min;
        }
        return min;
    }

    public static void main(String[] args) {
        String s = "acbdgf";
        int k = 1;
        System.out.println(new OrderlyQueue().orderlyQueue(s, k));
    }
}
