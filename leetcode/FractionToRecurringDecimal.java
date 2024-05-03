package leetcode;

import java.util.*;
import datastructure.*;
import datastructure.disjointset.*;
import algorithms.*;

/**
 * <a href=https://leetcode.com/problems/fraction-to-recurring-decimal/description/>
 * 166. Fraction to Recurring Decimal
 * </a>
 * <p>
 * Created at : 03/05/24
 *
 * @author Himanshu Shekhar
 */

public class FractionToRecurringDecimal {
    /*
        Runtime: 1 ms
        Beats 100.00% of users with Java
     */
    static class Solution {
        public String fractionToDecimal(int numerator, int denominator) {
            StringBuilder ans = new StringBuilder();
            // handle positive/negative
            if ((numerator < 0 && denominator > 0) || (numerator > 0 && denominator < 0))
                ans.append("-");
            // to avoid overflow, type-cast to long
            long num = Math.abs((long)numerator);
            long den = Math.abs((long)denominator);
            // decimal part
            ans.append(num / den);
            num %= den;

            System.out.println(num);
            if (num == 0)                           // numerator is completely divisible by denominator
                return ans.toString();
            // fractional part
            ans.append(".");
            HashMap<Long, Integer> map = new HashMap<>();   // map is required to store the index where "(" appears
            map.put(num, ans.length());
            while (num != 0) {
                num *= 10;
                ans.append(num / den);
                num %= den;
                if (map.containsKey(num)) {
                    ans.insert(
                            map.get(num), "(");
                    ans.append(")");                    // append closing bracket at the end
                    break;
                } else {
                    map.put(num, ans.length());
                }
            }
            return ans.toString();
        }
    }

    public static void main(String[] args) {
        int numerator = 4, denominator = 333;
        System.out.println(new Solution().fractionToDecimal(numerator, denominator));
    }
}
