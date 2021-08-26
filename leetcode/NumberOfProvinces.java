/*
 * 547. Number of Provinces
 * https://leetcode.com/problems/number-of-provinces/
 */

package leetcode;

import datastructure.disjointset.OptimizedDisjointSet;

public class NumberOfProvinces {
    /**
     * <p>Time Complexity: O(n<sup>2</sup>); where n = isConnected.length
     *
     * @param isConnected cities which are connected
     * @return number of provinces
     */
    public int findCircleNum(int[][] isConnected) {
        if (isConnected == null || isConnected.length == 0)
            return 0;

        int n = isConnected.length;
        OptimizedDisjointSet ds = new OptimizedDisjointSet(n);

        // traverse in only lower triangle of the matrix
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                // if 2 cities are connected then make it a province
                if (isConnected[i][j] == 1)
                    ds.union(i, j);
            }
        }
        int provinces = 0;
        for (int i = 0; i < n; i++)
            if (i == ds.root[i])        // number of distinct roots equals to number of provinces
                provinces++;

        return provinces;
    }

    public static void main(String[] args) {
        int[][] isConnected = {{1, 0, 0, 1}, {0, 1, 1, 0}, {0, 1, 1, 1}, {1, 0, 1, 1}};
        System.out.println(new NumberOfProvinces().findCircleNum(isConnected));
    }
}
