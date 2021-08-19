/*
 * 7. Reverse Integer
 * https://leetcode.com/problems/reverse-integer/
 */
package leetcode;

import java.util.*;

class ReverseInteger {
    static class Solution {
        public int reverse(int x) {
            StringBuilder sb = new StringBuilder(String.valueOf(x));

            if (x < 0) {
                sb.delete(0, 1);
            }

            sb.reverse();
            int ans = 0;
            try {
                ans = Integer.parseInt(sb.toString());
            } catch (Exception e) {
                ans = 0;
            }

            if (x < 0) {
                return -1 * ans;
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();

        System.out.println(new Solution().reverse(x));
    }
}