/*
 * Write 0, 1, ..., n in binary and add as if they were decimal numbers.
 * https://oeis.org/A067894
 */

import java.util.Stack;

public class DecimalSumOfBinaryNumbersFrom1ToN {
    /**
     * Add 2 numbers represented as {@code Strings}
     * @param a first number
     * @param b second number
     * @return sum of a and b
     */
    private static String add2Numbers(String a, String b) {
        int m = a.length(), n = b.length();
        Stack<Integer> res = new Stack<>();

        int carry = 0;
        while (m > 0 && n > 0) {
            int sum = (a.charAt(--m) - '0') + (b.charAt(--n) - '0') + carry;
            res.push(sum % 10);         // stores unit digit
            carry = sum / 10;                // and forward carry
        }
        // add rest of the digits if any
        while (m > 0) {
            int sum = (a.charAt(--m) - '0') + carry;
            res.push(sum % 10);
            carry = sum / 10;
        }
        // add rest of the digits if any
        while (n > 0) {
            int sum = (b.charAt(--n) - '0') + carry;
            res.push(sum % 10);
            carry = sum / 10;
        }
        StringBuilder ans = new StringBuilder();
        while (!res.isEmpty()) {
            ans.append(res.pop());
        }
        return ans.toString();
    }

    /**
     * <strong>Brute Force</strong> - can handle upto 10<sup>10^9</sup> digits
     */
    public static String decimalSum(int n) {
        String sum = "0";
        for (int i = 1; i <= n; i++) {
            sum = add2Numbers(sum, Integer.toBinaryString(i));
        }
        return sum;
    }

    /**
     * @param n integer number in decimal format
     * @return decimal format of binary representation of {@code n}
     */
    public static long representBinaryAsInteger(int n) {
        long bin2int = 0, digitPlace = 1;
        while (n > 0) {
            if ((n & 1) == 1) {
                bin2int += digitPlace;
            }
            n >>= 1;
            digitPlace *= 10;
        }
        return bin2int;
    }

    /**
     * <strong>Brute Force</strong> - can handle upto 10<sup>18</sup> digits
     */
    public static long decimalSum1(int n) {
        long sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += representBinaryAsInteger(i);
        }
        return sum;
    }

    public static void main(String[] args) {
        int n = 1;
        System.out.println(decimalSum(n));
    }
}
