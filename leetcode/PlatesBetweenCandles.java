package leetcode;

import java.util.Arrays;

/**
 * Created at : 18/11/21
 * <p>
 * <a href=https://leetcode.com/problems/plates-between-candles/>2055. Plates Between Candles</a>
 *
 * @author Himanshu Shekhar
 */

public class PlatesBetweenCandles {
    public static void main(String[] args) {
        String s = "***|**|*****|**||**|*";
        int[][] queries = {{1, 17}, {4, 5}, {14, 17}, {5, 11}, {15, 16}};

        int[] res = new PlatesBetweenCandles().platesBetweenCandles(s, queries);
        print(res);
    }

    private static void print(Object... o) {
        System.out.println(Arrays.deepToString(o));
    }

    /**
     * <strong>Prefix Sum + Binary Search</strong>
     */
    public int[] platesBetweenCandles(String s, int[][] queries) {
        int n = s.length();
        int[] ans = new int[queries.length];
        int[] prefixSum = new int[n];
        // make prefix-sum array on candles
        prefixSum[0] = s.charAt(0) == '|' ? 1 : 0;
        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i - 1];
            if (s.charAt(i) == '|')
                prefixSum[i]++;
        }
        // now find number of plates between candles
        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0], h = queries[i][1];
            // find the lowest index, starts with candle within given range
            if (s.charAt(l) != '|') {
                l = lowerBound(prefixSum, l, h, prefixSum[l] + 1);
                l = l < 0 ? Integer.MAX_VALUE : l;
            }
            // find the highest index, ends with candle within given range
            if (s.charAt(h) != '|') {
                h = lowerBound(prefixSum, l, h, prefixSum[h]);
                h = h < 0 ? Integer.MIN_VALUE : h;
            }
            if (l < h) {
                // formula to find number of plates in between candles
                ans[i] = (h - l + 1);   // total no. of valid plates and candles, i.e., plates b/w candles
                ans[i] -= (prefixSum[h] - prefixSum[l] + 1);    // subtract number of candles
            }
        }
        return ans;
    }

    /**
     * @return lowest index of {@code key} in given array {@code a}
     */
    private int lowerBound(int[] a, int l, int h, int key) {
        int ans = -1;
        while (l <= h) {
            int mid = l + (h - l) / 2;
            if (a[mid] >= key) {
                ans = mid;
                h = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return ans;
    }
}
