/*
 * 8. String to Integer (atoi)
 * https://leetcode.com/problems/string-to-integer-atoi/
 */

package leetcode;

class ATOI {
    public int myAtoi(String s) {
        // 1
        s = s.trim();
        if (s.length() == 0)
            return 0;
        // 2
        int i = 0;
        boolean neg = false;
        if (s.charAt(i) == '-') {
            neg = true;
            i++;
        } else if (s.charAt(i) == '+')
            i++;
        // 3
        if (i >= s.length() || s.charAt(i) < '0' || s.charAt(i) > '9')
            return 0;

        int l = i, r = i;
        while (r < s.length() && s.charAt(r) >= '0' && s.charAt(r) <= '9') {
            r++;
        }
        // 4
        int res;
        try {
            res = Integer.parseInt(s.substring(l, r));
        } catch (NumberFormatException nfe) {
        // 5
            return neg ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        }
        if (neg)
            res *= -1;
        // 6
        return res;
    }

    public static void main(String[] args) {
        String s = "  -098";
        System.out.println(new ATOI().myAtoi(s));
    }
}