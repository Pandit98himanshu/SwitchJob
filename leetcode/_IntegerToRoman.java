/*
 * https://leetcode.com/problems/integer-to-roman/
 */
package leetcode;

import java.util.*;

class _IntegerToRoman {
    static class Solution {
        public String intToRoman(int num) {
            Map<Integer, String> map = new HashMap<Integer, String>();

            map.put(1, "I");
            map.put(4, "IV");
            map.put(5, "V");
            map.put(9, "IX");
            map.put(10, "X");
            map.put(40, "XL");
            map.put(50, "L");
            map.put(90, "XC");
            map.put(100, "C");
            map.put(400, "CD");
            map.put(500, "D");
            map.put(900, "CM");
            map.put(1000, "M");

            String roman = "";
            int mult = 1;
            while (num > 0) {
                int r = num % 10;

                if (map.containsKey(r * mult)) {
                    roman = map.get(r * mult) + roman;
                } else if (r < 4) {

                } else if (r > 5) {

                }
                mult *= 10;
            }
            return roman;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int num = sc.nextInt();

        System.out.println(new Solution().intToRoman(num));
    }
}