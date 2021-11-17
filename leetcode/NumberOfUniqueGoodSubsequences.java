package leetcode;

import java.util.HashSet;

/**
 * Created at : 28/10/21
 * <p>
 * <a href=https://leetcode.com/contest/weekly-contest-256/problems/number-of-unique-good-subsequences/>1987. Number of Unique Good Subsequences</a>
 *
 * @author Himanshu Shekhar
 */

public class NumberOfUniqueGoodSubsequences {
    private int mod = (int) 1e9+7;

    /**
     * Copied from <a href=https://leetcode.com/problems/number-of-unique-good-subsequences/discuss/1431819/JavaC++Python-DP-4-lines-O(N)-Time-O(1)-Space>leetcode discuss</a>
     */
    public int numberOfUniqueGoodSubsequences(String binary) {
        int n = binary.length(), ends0 = 0, ends1 = 0, ans = 0;
        boolean hasZero = false;

        for (int i = 0; i < n; i++) {
            if (binary.charAt(i) == '0') {
                hasZero = true;
                ends0 = (ends0 + ends1) % mod;
            } else
                ends1 = (ends0 + ends1 + 1) % mod;
        }
        ans = (ends0 + ends1) % mod;
        if (hasZero)
            ans = (ans + 1) % mod;
        return ans;
    }
}
