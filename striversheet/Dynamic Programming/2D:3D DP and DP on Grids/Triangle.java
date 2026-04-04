import java.util.List;

/**
 * 120. Triangle
 * https://leetcode.com/problems/triangle/
 */

class Solution {
	/*
	 * TC: O(n²)
	 * SC: O(1) -> no auxiliary space
	 */
	public int minimumTotal(List<List<Integer>> triangle) {
		int n = triangle.size();
		for (int i = 1; i < n; i++) {
			for (int j = 0; j < triangle.get(i).size(); j++) {
				int currVal = triangle.get(i).get(j);
				int path1 = (j > 0) ? triangle.get(i - 1).get(j - 1) : Integer.MAX_VALUE;
				int path2 = (j < triangle.get(i).size() - 1) ? triangle.get(i - 1).get(j) : Integer.MAX_VALUE;
				triangle.get(i).set(j, currVal + Math.min(path1, path2));
			}
		}
		return triangle.get(n - 1).stream().mapToInt(Integer::intValue).min().getAsInt();
	}
}