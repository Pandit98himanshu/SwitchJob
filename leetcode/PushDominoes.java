/*
 * 838. Push Dominoes
 * https://leetcode.com/problems/push-dominoes/
 */

package leetcode;

import java.util.Arrays;

public class PushDominoes {
    static class Solution {
        /**
         * The basic idea is, domino will fell in that side where force is greater.
         * <strong>Considering right side as +ve and left as -ve.</strong>
         * <p>Similar to {@link leetcode.TrappingRainWater}</p>
         * <p>{@code Time Complexity: O(n)}</p>
         * <p>{@code Space Complexity: O(n)}</p>
         *
         * @param dominoes initial situation of dominoes
         * @return final state of dominoes after applying forces
         */
        public String pushDominoes(String dominoes) {
            int n = dominoes.length();
            StringBuilder ans = new StringBuilder();
            int[] resultant = new int[n];
            // sweep from left to right (only considering forces which are pushing right)
            int force = 0;
            for (int i = 0; i < n; i++) {
                // force rejuvenates at point of application
                if (dominoes.charAt(i) == 'R')
                    force = n;
                    // force nullifies at opposing force
                else if (dominoes.charAt(i) == 'L')
                    force = 0;
                else
                    force = Math.max(force - 1, 0);
                // moving right is +ve (by convention)
                resultant[i] += force;
            }
            printArray(resultant);
            // sweep from right to left (only considering forces which are pulling left)
            force = 0;
            for (int i = n - 1; i >= 0; i--) {
                // force rejuvenates at point of application
                if (dominoes.charAt(i) == 'L')
                    force = n;
                    // force nullifies at opposing force
                else if (dominoes.charAt(i) == 'R')
                    force = 0;
                else
                    force = Math.max(force - 1, 0);
                // moving left is -ve (by convention)
                resultant[i] -= force;
            }
            printArray(resultant);
            for (int res : resultant) {
                ans.append(res > 0 ? 'R' : res < 0 ? 'L' : '.');
            }
            return ans.toString();
        }

        public void printArray(Object... O) {
            System.out.println(Arrays.deepToString(O));
        }
    }

    public static void main(String[] args) {
        String dominoes = "RR.L";
        System.out.println(new Solution().pushDominoes(dominoes));
    }
}
