package leetcode;

import java.util.Scanner;

/**
 * <a href=https://leetcode.com/problems/count-good-numbers/>1922. Count Good Numbers</a>
 *
 * @author Himanshu Shekhar
 */

class CountGoodNumbers {
    /**
     * Time Complexity: O(log y)
     * @return (x^y) % p
     */
    private static long power(long x, long y, long p) {
        long res = 1;

        x = x % p;
        if (x == 0)
            return 0;

        while (y > 0) {
            if ((y & 1) != 0)
                res = (res * x) % p;
            y >>= 1; // y = y/2
            x = (x * x) % p;
        }
        return res;
    }

    /**
     * Time Complexity: O(log b)
     * @returns (a * b) % mod
     */
    private static int moduloMultiplication(long a, long b, long mod) {
        long res = 1;

        a %= mod;
        while (b > 0) {
            if ((b & 1) > 0)
                res = (res + a) % mod;
            b >>= 1; // b = b / 2
            a = (a + a) % mod;
        }
        return (int) res;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        long n = sc.nextLong();

        System.out.println(new CountGoodNumbers().countGoodNumbers(n));
    }

    public int countGoodNumbers(long n) {
        long evens, odds;
        if ((n % 2) == 0)
            evens = n / 2;
        else
            evens = n / 2 + 1;

        odds = n / 2;

        long mod = (long) 1e9+7;
        long evensRes = 1, oddsRes = 1;

        evensRes = power(5, evens, mod);
        if (odds > 0)
            oddsRes = power(4, odds, mod);

        int res = 1;
        if (oddsRes > 0)
            res = moduloMultiplication(evensRes, oddsRes, mod) - 1;

        return res;
    }
}