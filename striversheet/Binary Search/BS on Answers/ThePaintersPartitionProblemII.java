import java.util.Arrays;

/**
 * The Painter's Partition Problem-II
 * https://www.geeksforgeeks.org/problems/the-painters-partition-problem1535/1
 * Same as Allocate Minimum Pages & LC 410. Split Array Largest Sum
 */

class Solution {
	public int minTime(int[] arr, int k) {
		int l = 0, r = Arrays.stream(arr).sum();
		while (l < r) {
			int mid = l + (r - l)/2;
			if (canPaintAllBoards(arr, mid, k))
				r = mid;
			else
				l = mid + 1;
		}
		return l;
	}
	private boolean canPaintAllBoards(int[] arr, int timeAllocated, int k) {
		long currTime = 0;
		for (int time : arr) {
			currTime += time;

			if (currTime > timeAllocated) {
				k--;
				currTime = time;
			}
			
			if (k <= 0 || time > timeAllocated)
				return false;
		}
		return true;
	}
}

public class ThePaintersPartitionProblemII {
	
}
