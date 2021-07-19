/*
 * 42. Trapping Rain Water
 * https://leetcode.com/problems/trapping-rain-water/
 */

package leetcode;

public class TrappingRainWater {
    static class Solution {
        /**
         * <p>{@code Time Complexity: O(n)}</p>
         * <p>{@code Space Complexity: O(2 * n)}</p>
         * @param height heights of walls
         * @return total water trapped between walls
         */
        public int trap(int[] height) {
            int n = height.length;
            int[] left = new int[n], right = new int[n];
            // edge case
            if(n == 0)
                return 0;

            // max water level if moving from left to right
            left[0] = height[0];
            for (int i = 1; i < n; i++) {
                left[i] = Math.max(left[i-1], height[i]);
            }
            // max water level if moving from right to left
            right[n-1] = height[n-1];
            for (int i = n-2; i >= 0; i--) {
                right[i] = Math.max(right[i+1], height[i]);
            }
            int waterQuantity = 0;
            // finding actual water level stored and summing up
            for (int i = 0; i < n; i++) {
                waterQuantity += Math.min(left[i], right[i]) - height[i];
            }
            return waterQuantity;
        }
    }

    public static void main(String[] args) {
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(new Solution().trap(height));
    }
}
