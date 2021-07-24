/*
 * 1938. Maximum Genetic Difference Query
 * https://leetcode.com/problems/maximum-genetic-difference-query/
 */

package leetcode;

import java.util.Arrays;

public class _MaximumGeneticDifferenceQuery {

    static class Solution {
        /**
         * <strong>Brute Force</strong>
         * <p>Time Complexity: O(n * m)
         * <br>
         * Space Complexity: O(n);
         * where n = queries.length, m = parents.length
         *
         * @param parents array which stores parent node of each nodes
         * @param queries
         * @return maximum genetic difference for all queries
         */
        public int[] maxGeneticDifference(int[] parents, int[][] queries) {
            int[] ans = new int[queries.length];

            for (int i = 0; i < queries.length; i++) {
                int node = queries[i][0];
                int val = queries[i][1];
                int max = -1;
                // heading towards root node and finding maximum xor with val each node in the path
                while (node != -1) {
                    max = Math.max(max, val ^ node);
                    node = parents[node];
                }
                ans[i] = max;
            }
            return ans;
        }
    }

    static void print(Object... O) {
        System.out.println(Arrays.deepToString(O));
    }

    public static void main(String[] args) {
        int[] parents = {3, 7, -1, 2, 0, 7, 0, 2};
        int[][] queries = {{4, 6}, {1, 15}, {0, 5}};

        print(new Solution().maxGeneticDifference(parents, queries));
    }
}
