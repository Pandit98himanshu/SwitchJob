package leetcode;

import java.util.*;

/**
 * Created at : 27/08/21
 * <p>
 * <a href=https://leetcode.com/problems/generate-parentheses/>22. Generate Parentheses</a>
 *
 * @author Himanshu Shekhar
 */

public class GenerateParentheses {
    /**
     * <strong>Backtracking</strong>
     * <p>Time Complexity: O(2<sup>n</sup>)
     * <br> Space Complexity: O(2<sup>n</sup>)
     *
     * @param n total number of matching parentheses
     * @return generate all combinations of well-formed parentheses
     */
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        if (n != 0)
            generateParenthesis(n, "", 0, 0, ans);
        return ans;
    }

    private void generateParenthesis1(int n, String curr, int o, int c, List<String> ans) {
        if (o == n) {
            if (c == n)         // opening paren == closing paren == n
                ans.add(curr);
            else                // opening paren == n && closing paren < n
                // we already opened required number of
                // paren, all we need to just close
                generateParenthesis(n, curr + ")", o, c + 1, ans);
        } else {
            if (o == c)         // opening paren == closing paren < n
                // we can only add opening paren,
                // if we add closing paren then it will not remain balanced
                generateParenthesis(n, curr + "(", o + 1, c, ans);
            else if (o > c) {   // n > opening paren > closing paren
                // we can either add opening or closing paren
                generateParenthesis(n, curr + "(", o + 1, c, ans);
                generateParenthesis(n, curr + ")", o, c + 1, ans);
            }
        }
    }

    private void generateParenthesis(int n, String curr, int o, int c, List<String> ans) {
        if (curr.length() == 2 * n) {
            ans.add(curr);
            return;
        }
        if (o < n)         // if opening paren is less than required, add one
            generateParenthesis(n, curr + "(", o + 1, c, ans);
        if (c < o)         // if closing paren is less than number of opening paren, add one
            generateParenthesis(n, curr + ")", o, c + 1, ans);
    }

    public static void main(String[] args) {
        System.out.println(new GenerateParentheses().generateParenthesis(3));
    }
}
