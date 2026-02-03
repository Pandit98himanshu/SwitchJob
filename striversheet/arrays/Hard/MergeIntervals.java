import java.util.Arrays;
import java.util.Stack;

/**
 * 56. Merge Intervals
 * https://leetcode.com/problems/merge-intervals/
 */
class Solution {
	/*
	 * TC: O(n logn + n)
	 * SC: O(n)
	 * Using Stack data-structure
	 */
	public int[][] merge(int[][] intervals) {
		Stack<int[]> stk = new Stack<>();
		// sort intervals by start-time then end-time
		Arrays.sort(intervals, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);

		for (int[] interval : intervals) {
			// put current interval as-it-is if there's no overlap
			if (stk.isEmpty() || stk.peek()[1] < interval[0]) {
				stk.push(interval);
			} else {
				// merge current interval with previous one
				int[] prev = stk.pop();
				stk.push(new int[]{prev[0], Math.max(prev[1], interval[1])});
			}
		}
		int[][] ans = new int[stk.size()][];
		int i = 0;
		for (int[] interval : stk) {
			ans[i++] = interval;
		}
		return ans;
	}
}

public class MergeIntervals {
	public static void main(String[] args) {
		int[][] intervals = {
				{1,4},{2,3}
		};
		int[][] merged = new Solution().merge(intervals);
		System.out.println(Arrays.deepToString(merged));
	}
}
