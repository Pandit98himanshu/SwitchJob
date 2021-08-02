/*
 * 5187. Minimum Garden Perimeter to Collect Enough Apples
 * https://leetcode.com/contest/weekly-contest-252/problems/minimum-garden-perimeter-to-collect-enough-apples/
 */

package leetcode;

public class MinimumGardenPerimeterToCollectEnoughApples {
    static class Solution {
        /**
         * @return total number of apples in garden of side length {@code n} (including perimeter)
         */
        public long calculateTotalApples(long n) {
            // apples on one axis: n*(n+1)/2; (1,0), (2,0),..., (0,1), (0,2),..., (-1,0), (-2,0),..., (0,-1), (0,-2),...
            // apples in 1st quadrant except on axes–
            //      parallel to x-axis: n * (n*(n+1)/2); [(1,y), (2,y),...] x n
            //      parallel to y-axis: n * (n*(n+1)/2); [(x,1), (x,2),...] x n
            //                   total: 2 * n*(n*(n+1)/2)
            long ans = (n*(n+1)/2) * (1 + 2*n);
            System.out.println(n + " " + ans);
            return 4*ans;   // total number of quadrants = 4
        }

        /**
         * <strong>Binary search</strong> the number of sides length of the plot
         * @param neededApples number of apples required in the plot (including on perimeter)
         */
        public long minimumPerimeter(long neededApples) {
            long l = 1, r = (long) 1e5;
            while(l < r) {
                long mid = l + (r-l)/2;
                // if total number of apples in plot of side (2 * mid)
                // is less than neededApples then size of plot must be higher
                if (calculateTotalApples(mid) < neededApples) {
                    l = mid+1;
                }
                else {
                    r = mid;
                }
            }
            return 8*r;     // perimeter is 8 times of a half-part in 1st quadrant
        }
    }

    public static void main(String[] args) {
        long n = (long)13;
        System.out.println(new Solution().minimumPerimeter(n));
    }
}
