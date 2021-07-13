/**
 * 1642. Furthest Building You Can Reach
 * https://leetcode.com/problems/furthest-building-you-can-reach/
 */

package leetcode;

class FurthestBuildingYouCanReach {
    // Brute-Force
    static class Solution {
        public int furthestBuilding(int[] heights, int bricks, int ladders) {
            return furthestBuildingUtil(heights, bricks, ladders, 0);
        }
        public int furthestBuildingUtil(int[] heights, int bricks, int ladders, int i) {

            // base cases
            if (i >= heights.length-1 || (heights[i+1] - heights[i] > bricks && ladders == 0)) {
                return 0;
            }

            int diff = heights[i+1] - heights[i];
            // heights of adjacent buildings are equal or lesser than previous simply move forward
            if (diff <= 0) {
                return 1 + furthestBuildingUtil(heights, bricks, ladders, i+1);
            }
            else {
                int useBricks = 0, useLadders = 0;
                // if possible use bricks
                if (diff <= bricks)
                    useBricks = furthestBuildingUtil(heights, bricks - diff, ladders, i+1);
                // or use ladders
                if (ladders > 0)
                    useLadders = furthestBuildingUtil(heights, bricks, ladders - 1, i+1);
                // return 1 + max-of-both
                return 1 + Math.max(useBricks, useLadders);
            }
        }
    }

    // Could not be solved using Dynamic Programming
    // because we could not find next values using calculated values
    static class Solution1 {
        public int furthestBuilding(int[] heights, int bricks, int ladders) {
            int[][] dp = new int[bricks+1][ladders+1];
            return 0;
        }
    }

	public static void main(String[] args) {
		int[] heights = {14,3,19,3};
		int bricks = 17, ladders = 0;

		System.out.println(new Solution().furthestBuilding(heights, bricks, ladders));
	}
}