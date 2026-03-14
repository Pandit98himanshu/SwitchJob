/**
 * 42. Trapping Rain Water
 * https://leetcode.com/problems/trapping-rain-water
 */

class Solution {
	/*
	 * TC: O(n)
	 * SC: O(n)
	 */
	public int trap1(int[] height) {
		int[] maxRight = new int[height.length];	// precomputes max wall height to the right
		int maxLeft = 0;
		maxRight[height.length - 1] = height[height.length - 1];
		for (int i = height.length - 2; i >= 0; i--) {
			maxRight[i] = Math.max(maxRight[i + 1], height[i]);
		}
		int waterTrapped = 0;
		for (int i = 0; i < height.length; i++) {
			maxLeft = Math.max(maxLeft, height[i]);	// update the max left wall height
			waterTrapped += Math.min(maxLeft, maxRight[i]) - height[i];		// computes the water trapped at ith index
		}
		return waterTrapped;
	}

	/*
	 * TC: O(n)
	 * SC: O(1)
	 */
	public int trap(int[] height) {
		int left = 0;
		int right = height.length - 1;
		int leftMax = height[left];
		int rightMax = height[right];
		int water = 0;
		// water trapped will never be more than max wall heights to the left & right
		while (left < right) {
			if (leftMax < rightMax) {
				left++;
				leftMax = Math.max(leftMax, height[left]);
				water += leftMax - height[left];
			} else {
				right--;
				rightMax = Math.max(rightMax, height[right]);
				water += rightMax - height[right];
			}
		}

		return water; 
	}
}