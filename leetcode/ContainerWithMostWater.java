/*
 * 11. Container With Most Water
 * https://leetcode.com/problems/container-with-most-water/
 */
package leetcode;

import java.util.*;

class ContainerWithMostWater {
    static class Solution {

        public int maxArea(int[] height) {
            int n = height.length;

			/*
			int[][] height_index = new int[n][2];
			for (int i = 0; i < n; i++) {
				height_index[i][0] = height[i];
				height_index[i][1] = i + 1;
			}

			Arrays.sort(height_index, new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					return Integer.compare(o2[0], o1[0]);		// compare in reverse order according to height
				}
			});
			*/
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

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] s = sc.nextLine().split(",");

        int[] height = new int[s.length];
        for (int i = 0; i < s.length; i++) {
            height[i] = Integer.parseInt(s[i]);
        }
        System.out.println(new Solution().maxArea(height));
    }
}