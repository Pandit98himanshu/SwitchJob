/*
 * 6. ZigZag Conversion
 * https://leetcode.com/problems/zigzag-conversion/
 */
package leetcode;

import java.util.*;

class ZigZagConversion {
    static class Solution {
        public String convert(String s, int numRows) {
            StringBuilder str = new StringBuilder();

            if (s.length() == 1 || numRows == 1) {
                return s;
            }
            int n = numRows + (numRows - 2);
            int k = n;
            for (int i = 0; i < numRows; i++) {
                for (int j = i; j < s.length(); j += n) {
                    str.append(s.charAt(j));
                    if ((i > 0 && i < numRows - 1) && (j + k < s.length())) {
                        str.append(s.charAt(j + k));
                    }
                }
                k -= 2;
            }

            return str.toString();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String s = sc.nextLine();
        int numRows = sc.nextInt();

        System.out.println(new Solution().convert(s, numRows));
    }
}