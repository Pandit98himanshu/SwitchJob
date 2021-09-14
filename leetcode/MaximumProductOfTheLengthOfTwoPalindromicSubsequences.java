package leetcode;

/**
 * Created at : 13/09/21
 * <p>
 * <a href=https://leetcode.com/problems/maximum-product-of-the-length-of-two-palindromic-subsequences/>2002. Maximum Product of the Length of Two Palindromic Subsequences</a>
 *
 * @author Himanshu Shekhar
 */

public class MaximumProductOfTheLengthOfTwoPalindromicSubsequences {
    /**
     * <strong>Bit Masking</strong>
     * <p>Time Complexity: O(2<sup>2^n</sup>) : 877 ms
     * <br>Space Complexity: O(2<sup>n</sup>) : 54.5 MB
     */
    private boolean isPalindrome(String s, int mask) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (((1 << i) & mask) > 0)
                sb.append(s.charAt(i));
        }
        return sb.toString().equals(sb.reverse().toString());
    }

    public int maxProduct(String s) {
        int n = s.length(), ans = 0;
        boolean[] isPalindrome = new boolean[1 << n];
        // finding each subset and check whether it is a palindrome or not
        for (int i = 0; i < (1 << n); i++)
            isPalindrome[i] = isPalindrome(s, i);

        for (int i = 0; i < (1 << n); i++) {
            for (int j = i + 1; j < (1 << n); j++) {
                if ((i & j) == 0) {         // if both subsets (i.e., i and j) are disjoint
                    if (isPalindrome[i] && isPalindrome[j])     // and both are palindromes
                        ans = Math.max(ans, Integer.bitCount(i) * Integer.bitCount(j));     // keep track of max product
                }
            }
        }
        return ans;
    }

    /**
     * <strong>Recursion</strong>
     * <p>Time Complexity: O(3<sup>n</sup>) : 950 ms
     * <br>Space Complexity: O(3<sup>n</sup>) : 39.2 MB
     */
    private boolean isPalindrome(String s) {
        int n = s.length();
        for (int i = 0; i < n / 2; i++) {
            if (s.charAt(i) != s.charAt(n - i - 1))
                return false;
        }
        return true;
    }

    private int ans = 0;

    private void dfs(String s, String s1, String s2, int i) {
        if (i >= s.length()) {
            if (isPalindrome(s1) && isPalindrome(s2))
                ans = Math.max(ans, s1.length() * s2.length()); // keep track of max product
            return;
        }
        dfs(s, s1 + s.charAt(i), s2, i + 1);    // include current element in first string
        dfs(s, s1, s2 + s.charAt(i), i + 1);    // include current element in second string
        dfs(s, s1, s2, i + 1);                      // include in none
    }

    public int maxProduct1(String s) {
        dfs(s, "", "", 0);
        return ans;
    }

    public static void main(String[] args) {
        String s = "leetcodecom";
        System.out.println(new MaximumProductOfTheLengthOfTwoPalindromicSubsequences().maxProduct(s));
    }
}
