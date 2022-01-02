package leetcode;

import java.util.*;

/**
 * <a href=https://leetcode.com/problems/integer-to-roman/>12. Integer to Roman</a>
 */
class IntegerToRoman {
    /**
     * Copied from <a href=https://leetcode.com/problems/integer-to-roman/discuss/6310/My-java-solution-easy-to-understand>leetcode discuss</a>
     */
    public String intToRoman(int num) {
        // map integer values with its corresponding roman literals
        int[] values = {1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};
        String[] romanValues = {"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M"};

        StringBuilder roman = new StringBuilder();
        
        for (int i = 0; i < values.length; i++) {
            while (num >= values[i]) {
                num -= values[i];
                roman.append(romanValues[i]);
            }
        }
        return roman.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int num = sc.nextInt();

        System.out.println(new IntegerToRoman().intToRoman(num));
    }
}