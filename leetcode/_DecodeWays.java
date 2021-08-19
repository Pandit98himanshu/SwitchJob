package leetcode;

import java.util.*;

public class _DecodeWays {
    private int count = 0;

    /**
     * <strong>Recursion using memoization - Gives TLE</strong>
     * <p>Time Complexity: O(2<sup>n</sup>)
     * <br>Space Complexity: O(2<sup>n</sup>)
     *
     * @param s   current string
     * @param map stores already calculated result
     */
    public void countNumDecodings(String s, Map<String, Integer> map) {
        int n = s.length();
        if (n == 0 || s.charAt(0) == '0') {
            return;
        }
        // if we have only 1 digit which is non-zero then we can decode it
        if (n == 1) {
            count++;
            map.put(s, count);
            return;
        }
        String rest1 = s.substring(1, n);   // takeout 1st digit
        if (map.containsKey(rest1)) {
            count += map.get(rest1);        // we already know the number of decodings for rest1
        } else {
            countNumDecodings(rest1, map);  // calculate number of decodings for rest of the string
        }
        int first2 = Integer.parseInt(s.substring(0, 2));       // takeout first 2 digits
        if (first2 >= 10 && first2 <= 26) {         // see whether we can decode it
            String rest2 = s.substring(2, n);
            if (rest2.equals("")) {
                count++;
                map.put(s, count);
            } else {
                if (map.containsKey(rest2)) {
                    count += map.get(rest2);        // we already know the number of decodings for rest2
                } else {
                    countNumDecodings(rest2, map);  // calculate number of decodings for rest of the string
                }
            }
        }
    }

    public int numDecodings(String s) {
        countNumDecodings(s, new HashMap<>());
        return count;
    }

    public static void main(String[] args) {
        String s = "226";
        System.out.println(new _DecodeWays().numDecodings(s));
    }
}
