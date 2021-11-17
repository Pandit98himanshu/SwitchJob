package leetcode;

import java.util.HashMap;

/**
 * Created at : 07/19/2021
 * <p>
 * <a href=https://leetcode.com/problems/trapping-rain-water/>42. Trapping Rain Water</a>
 *
 * @author Himanshu Shekhar
 */

public class TrappingRainWater {

    public static void main(String[] args) {
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(new TrappingRainWater().trap(height));
    }

    /**
     * <p>Time Complexity: O(n); n = length of height array
     * <br>Space Complexity: O(1)
     *
     * @param height heights of walls
     * @return total water trapped between walls
     */
    public int trap(int[] height) {
        int res = 0; // stores result
        // current left and right wall respectively
        int l = 0, r = height.length - 1;
        // stores max height of wall previous to and after
        // to the current left and right wall respectively
        int lMax = 0, rMax = 0;

        while (l <= r) {
            // move that pointer whose height is smaller
            if (height[l] < height[r]) {
                // if current left wall is max-till-now, then
                // water cannot be stored at this position
                if (lMax < height[l])
                    lMax = height[l];
                    // water at current position = max - curr
                else
                    res += lMax - height[l];
                // move to next right wall
                l++;
            } else {
                // if current right wall is max-till-now, then
                // water cannot be stored at this position
                if (rMax < height[r])
                    rMax = height[r];
                    // water at current position = max - curr
                else
                    res += rMax - height[r];
                // move to next left wall
                r--;
            }
        }
        return res;
    }

    /**
     * <p>Time Complexity: O(n)
     * <br>Space Complexity: O(2 * n); n = length of height array
     *
     * @param height heights of walls
     * @return total water trapped between walls
     */
    public int trap1(int[] height) {
        int n = height.length;
        int[] left = new int[n], right = new int[n];
        // edge case
        if (n == 0)
            return 0;

        // max water level if moving from left to right
        left[0] = height[0];
        for (int i = 1; i < n; i++) {
            left[i] = Math.max(left[i - 1], height[i]);
        }
        // max water level if moving from right to left
        right[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            right[i] = Math.max(right[i + 1], height[i]);
        }
        int waterQuantity = 0;
        // finding actual water level stored and summing up
        for (int i = 0; i < n; i++) {
            waterQuantity += Math.min(left[i], right[i]) - height[i];
        }
        return waterQuantity;
    }
}
