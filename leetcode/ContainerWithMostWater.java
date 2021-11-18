package leetcode;

import java.util.Scanner;

/**
 * <a href=https://leetcode.com/problems/container-with-most-water/>11. Container With Most Water</a>
 *
 * @author Himanshu Shekhar
 */

class ContainerWithMostWater {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] s = sc.nextLine().split(",");

        int[] height = new int[s.length];
        for (int i = 0; i < s.length; i++) {
            height[i] = Integer.parseInt(s[i]);
        }
        System.out.println(new ContainerWithMostWater().maxArea(height));
    }

    public int maxArea(int[] height) {
        int n = height.length;
        int l = 0, r = n - 1, maxArea = Integer.MIN_VALUE;
        while (l < r) {
            int area = Math.min(height[l], height[r]) * (r - l);
            maxArea = Math.max(maxArea, area);
            if (height[l] < height[r]) {
                l++;
            } else {
                r--;
            }
        }

        return maxArea;
    }
}