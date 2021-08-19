/*
 * 791. Custom Sort String
 * https://leetcode.com/problems/custom-sort-string/
 */

package leetcode;

import java.util.*;

public class CustomSortString {
    // Less efficient
    static class Solution1 {
        /**
         * This methods finds 1st occurrence of char {@code c} in given char array {@code str}.
         * Here we used the technique of sorting an array according to the order defined by another array.
         * <p>Time Complexity: O(n + mlogn)}
         * <br>Space Complexity: O(n); where n = str.length() and m = order.length()</p>
         *
         * @param str is given array in which char {@code c} has to be searched
         * @param c   is the character whose 1st occurrence needs to be found
         * @return first occurrence of char {@code c}
         */
        public int firstOccurrence(char[] str, char c) {
            int l = 0, r = str.length - 1, mid, result = -1;
            while (l <= r) {
                mid = l + (r - l) / 2;
                if ((mid == 0 || str[mid - 1] < c) && str[mid] == c) {
                    result = mid;
                    break;
                } else if (str[mid] < c)
                    l = mid + 1;
                else
                    r = mid - 1;
            }
            return result;
        }

        public String customSortString(String order, String str) {
            int n = str.length();                       // find length of string
            char[] strChar = str.toCharArray();        // convert str to char array

            char[] strCharSorted = str.toCharArray();   // sorted str will be stored here
            boolean[] visited = new boolean[n];         // we'll mark every visited element element as true
            Arrays.sort(strCharSorted);                 // sorting strCharSorted array
            int i = 0;
            for (int j = 0; j < order.length(); j++) {
                char c = order.charAt(j);
                int ind = firstOccurrence(strCharSorted, c);
                if (ind != -1) {                        // if c is present in str
                    while (ind < n && strCharSorted[ind] == c) {
                        strChar[i++] = c;               // putting character in strChar according to order
                        visited[ind++] = true;          // mark the position as visited
                    }
                }
            }
            // store unvisited elements at the end of strChar
            for (int k = 0; k < n; k++) {
                if (!visited[k]) {
                    strChar[i++] = strCharSorted[k];
                }
            }
            // return strChar as a String
            return new String(strChar);
        }
    }

    // Most efficient
    static class Solution {
        /**
         * Sort String {@code str} based on the order defined on String {@code order}.
         * Here we are using simple hashing.
         * <p>Time Complexity: O(max(m, n, 26))
         * <br>Space Complexity: O(max(n, 128); where n = str.length() and m = order.length()</p>
         *
         * @param order the string according to which {@code str} needs to be sorted
         * @param str   the string to be sorted according to order defined in {@code order}
         * @return sorted string {@code str} as required
         */
        public String customSortString(String order, String str) {
            int[] freq = new int[128];
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < str.length(); i++) {
                freq[str.charAt(i)]++;
            }
            // iterate over "order" and put elements according to that in "sb
            for (int i = 0; i < order.length(); i++) {
                char c = order.charAt(i);
                while (freq[c]-- > 0) {
                    sb.append(c);
                }
            }
            // put left elements at the end
            for (char c = 'a'; c <= 'z'; c++) {
                while (freq[c]-- > 0) {
                    sb.append(c);
                }
            }
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        String order = "kqep", str = "pekeq";

        System.out.println(new Solution().customSortString(order, str));
    }
}
