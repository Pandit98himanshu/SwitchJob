/*
 * 5. Longest Palindromic Substring
 * https://leetcode.com/problems/longest-palindromic-substring/
 */

package leetcode;

public class LongestPalindromicSubstring {
    /**
     * <strong>Dynamic Programming - Basic of Manacher's Algo</strong>
     * <p>Copied from <a href=https://leetcode.com/problems/longest-palindromic-substring/solution/>leetcode solution</a>
     * <p>Time Complexity: O(n<sup>2</sup>)
     * <br>Space Complexity: O(1)
     *
     * @return length of the longest palindromic substring
     * @see <a href=https://youtu.be/V-sEwsca1ak>Tushar Roy</a>
     */
    public String longestPalindrome(String s) {
        // edge case
        if (s == null || s.length() == 0)
            return "";

        int start = 0, end = 0; // storing starting and ending index of the longest palindromic substring
        for (int i = 0; i < s.length(); i++) {
            int len1 = palindromeLen(s, i, i);          // for odd length of palindrome
            int len2 = palindromeLen(s, i, i + 1);    // for even length of palindrome
            int len = Math.max(len1, len2);
            if ((end - start) < len) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int palindromeLen(String s, int start, int end) {
        // expand till we are getting palindrome
        while (start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) {
            start--;
            end++;
        }
        return (end - start) - 1;
    }

    public static void main(String[] args) {
        String s = "abaxabaxabybaxabyb";

        System.out.println(new LongestPalindromicSubstring().longestPalindrome(s));
    }
}
