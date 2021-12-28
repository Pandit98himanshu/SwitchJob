package leetcode;

import java.util.*;

/**
 * Created at : 21/12/21
 * <p>
 * <a href=https://leetcode.com/problems/letter-combinations-of-a-phone-number/>17. Letter Combinations of a Phone Number</a>
 *
 * @author Himanshu Shekhar
 */

public class LetterCombinationsOfAPhoneNumber {
    private char[][] given = {
            {' '},
            {' '},
            {'a','b','c'},
            {'d','e','f'},
            {'g','h','i'},
            {'j','k','l'},
            {'m','n','o'},
            {'p','q','r','s'},
            {'t','u','v'},
            {'w','x','y','z'}
    };
    private String[] num;
    private List<String> ans;

    public void possibleCombinations(int i, String curr) {
        if (i == num.length) {
            ans.add(curr);
            return;
        }
        for (char j : given[Integer.parseInt(num[i])]) {
            possibleCombinations(i + 1, curr + j);
        }
    }
    public List<String> letterCombinations(String digits) {
        num = digits.split("");
        ans = new ArrayList<>();
        possibleCombinations(0, "");
        return ans;
    }
}
