/**
 * 10. Regular Expression Matching
 * https://leetcode.com/problems/regular-expression-matching/
 */

package leetcode;

class _RegularExpressionMatching {
    static class Solution {
        public boolean isMatch(String s, String p) {
            // Converting string to character array
            char[] str = s.toCharArray();
            char[] pat = p.toCharArray();

            // p = ""
            if (pat.length == 0) {
                if (str.length == 0)        // s = ""
                    return true;
                return false;
            }
            // s = ""
            else if (str.length == 0) {
                for (int idx = 1; idx < pat.length; idx += 2) {
                    if (pat[idx] != '*')    // p = "ab*c*"
                        return false;
                }
            }

            else {
                int i = 0, j = 0;
                int count_str = 0, count_pat = 0;
                while (true) {
                /*if (pat[j] == '.') {
                    j++;
                    if (j < pat.length && pat[j] == '*')
                        return true;
                    else
                        i++;
                }*/
                    if (pat[j] == str[i]) {
                        char c = pat[j];
                        j++;
                        if (j < pat.length && pat[j] == '*') {
                            while (i < str.length && str[i] == c) {
                                i++;
                                count_str++;
                            }
                        }
                        else {
                            i++;
                        }
                    }
                }
            }
            return true;
        }
    }

    public static void main(String[] args) {

        String s = "ab";
        String p = "a*";

        System.out.println(new Solution().isMatch(s, p));
    }
}