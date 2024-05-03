package leetcode;

import datastructure.*;
import datastructure.disjointset.*;

/**
 * <a href=https://leetcode.com/problems/lemonade-change/description/>
 * 860. Lemonade Change
 * </a>
 * <p>
 * Created at : 03/05/24
 *
 * @author Himanshu Shekhar
 */

public class LemonadeChange {
    static class Solution {
        public boolean lemonadeChange(int[] bills) {
            int five = 0, ten = 0, twenty = 0;
            for (int bill : bills) {
                if (bill == 5)
                    five++;
                if (bill == 10) {
                    if (five < 1)
                        return false;
                    five--;
                    ten++;
                }
                if (bill == 20) {
                    // return three 5's
                    if (ten == 0) {
                        if (five < 3)
                            return false;
                        five -= 3;
                    }
                    // return one 5 and one 10
                    else {
                        if (five < 1)
                            return false;
                        five--;
                        ten--;
                    }
                    twenty++;
                }
            }
            return true;
        }
    }
    public static void main(String[] args) {
        int[] input = {5,5,5,10,20};
        System.out.println(new Solution().lemonadeChange(input));
    }
}
