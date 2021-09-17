package leetcode;

/**
 * Created at : 16/09/21
 * <p>
 * <a href=https://leetcode.com/problems/longest-turbulent-subarray/>978. Longest Turbulent Subarray</a>
 *
 * @author Himanshu Shekhar
 */

public class LongestTurbulentSubarray {
    public int maxTurbulenceSize(int[] arr) {
        int n = arr.length;
        if (n == 1)
            return n;

        int ans = 1, count = 1, prev = 0;
        for (int i = 0; i < n - 1; i++) {
            // current difference
            int curr = arr[i + 1] - arr[i];
            // skip continuous equal numbers
            if (curr == 0) {
                count = 1;
                prev = 0;
            }
            // if either we start fresh or there are turbulence in values
            // we need to increase count
            else if (prev == 0 || prev < 0 && curr > 0 || prev > 0 && curr < 0) {
                count++;
                prev = curr;        // current will become prev in next iteration
                ans = Math.max(ans, count); // don't forget to update the answer
            }
            // in any other case, we need to start fresh
            // and reset everything
            else {
                count = 1;
                prev = 0;
                i--;    // "i" is increasing in every iteration
            }
        }
        return ans;
    }
}
