package leetcode;

import java.util.Arrays;

/**
 * Created at : 03/10/21
 * <p>
 * <a href=https://leetcode.com/problems/find-missing-observations/>2028. Find Missing Observations</a>
 *
 * @author Himanshu Shekhar
 */

public class FindMissingObservations {
    /**
     * <p>Time Complexity: O(n); n = length of required array
     */
    public int[] missingRolls(int[] a, int mean, int n) {
        int m = a.length;
        // sum of given array
        int aSum = Arrays.stream(a).sum();
        // sum of missing array
        int bSum = mean * (m + n) - aSum;
        // we can't create array if sum of missing array is
        // 1. less than n, because min value in dice is 1
        // 2. greater than 6 * n, because max value in dice is 6
        if (bSum < n || bSum > 6 * n)
            return new int[0];
        // create missing array
        int[] b = new int[n];
        // fill equally
        Arrays.fill(b, bSum / n);
        // extra values
        int mod = bSum % n;
        // fill extra values
        for (int i = 0; i < mod; i++)
            b[i]++;

        return b;
    }
}
