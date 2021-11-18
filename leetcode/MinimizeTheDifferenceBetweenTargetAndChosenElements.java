package leetcode;

import java.util.*;

/**
 * Created at : 23/08/21
 * <p>
 * <a href=https://leetcode.com/problems/minimize-the-difference-between-target-and-chosen-elements/>5852. Minimize the Difference Between Target and Chosen Elements</a>
 *
 * @author Himanshu Shekhar
 */

public class MinimizeTheDifferenceBetweenTargetAndChosenElements {
    private int helper(int[][] mat, int target, int row, int sum, int minDiff) {
        if (row >= mat.length) {
            minDiff = Math.min(minDiff, Math.abs(sum - target));
        } else {
            int l = 0, r = mat[row].length - 1;
            while (l <= r) {
                int mid = (l + r) / 2;
                if (helper(mat, target, row + 1, sum + mat[row][mid], minDiff) < 0) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return minDiff;
    }

    public int minimizeTheDifference(int[][] mat, int target) {
        for (int[] row : mat) {
            Arrays.sort(row);
        }
        // todo:
        //  1. implement binary search
        //  2. solve using DP
        return helper(mat, target, 0, 0, Integer.MAX_VALUE);
    }

    /**
     * <p>Copied from <a href=https://leetcode.com/problems/minimize-the-difference-between-target-and-chosen-elements/discuss/1418602/Python-4-lines-solution-explained>leetcode discuss</a>
     * <p>Time Complexity: O(target * n<sup>2</sup>)
     * <br>Space Complexity: O(target)
     */
    public int minimizeTheDifference3(int[][] mat, int target) {
        int minOfAll = 0;
        for (int[] row : mat) {
            minOfAll += Arrays.stream(row).min().getAsInt();
        }
        System.out.println(minOfAll);

        if (minOfAll >= target)
            return minOfAll - target;

        HashSet<Integer> set = new HashSet<>();
        set.add(0);
        for (int[] row : mat) {
            HashSet<Integer> tempSet = new HashSet<>();
            for (int x : row)
                for (int i : set)
                    if (x + i <= 2 * target - minOfAll)
                        tempSet.add(x + i);

            set = tempSet;
            System.out.println(set);
        }

        int minDiff = Integer.MAX_VALUE;
        for (int x : set) {
            minDiff = Math.min(minDiff, Math.abs(x - target));
        }
        return minDiff;
    }

    /**
     * <strong>Gives TLE</strong>
     * <p>Copied from <a href=https://leetcode.com/problems/minimize-the-difference-between-target-and-chosen-elements/discuss/1418602/Python-4-lines-solution-explained>leetcode discuss</a>
     * <p>Time Complexity: O(n<sup>4</sup>)
     * <br>Space Complexity: O(n<sup>3</sup>)
     */
    public int minimizeTheDifference2(int[][] mat, int target) {
        HashSet<Integer> set = new HashSet<>();
        set.add(0);
        // O(n^4)
        for (int[] row : mat) {
            HashSet<Integer> tempSet = new HashSet<>();
            for (int x : row) {
                for (int i : set) {
                    System.out.println(x + " + " + i);
                    tempSet.add(x + i);
                }
            }
            set = tempSet;
            System.out.println(set);
        }
        int minDiff = Integer.MAX_VALUE;
        // O(n^3)
        for (int x : set) {
            minDiff = Math.min(minDiff, Math.abs(x - target));
        }
        return minDiff;
    }

    /**
     * <strong>Brute Force - Gives TLE</strong>
     * <p>Time Complexity: O(m<sup>n</sup>); m = no. of rows, n = no. of columns
     * <br>Space Complexity: O(1)
     *
     * @return minimum absolute difference b/w sum of chosen elements in each row & the target
     */
    private int helper1(int[][] mat, int target, int row, int sum, int minDiff) {
        if (row >= mat.length) {
            minDiff = Math.min(minDiff, Math.abs(sum - target));
        } else {
            for (int i = 0; i < mat[row].length; i++) {
                minDiff = helper(mat, target, row + 1, sum + mat[row][i], minDiff);
            }
        }
        return minDiff;
    }

    public int minimizeTheDifference1(int[][] mat, int target) {
        return helper1(mat, target, 0, 0, Integer.MAX_VALUE);
    }

    public static void main(String[] args) {
        int[][] mat = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int target = 13;
        System.out.println(new MinimizeTheDifferenceBetweenTargetAndChosenElements().minimizeTheDifference3(mat, target));
    }
}
