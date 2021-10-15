package leetcode;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created at : 05/10/21
 * <p>
 * <a href=https://leetcode.com/problems/permutation-in-string/>567. Permutation in String</a>
 *
 * @author Himanshu Shekhar
 */

public class PermutationInString {
    /**
     * <strong>Sliding Window</strong>
     * <p>Time Complexity: O(m + n)
     */
    public boolean checkInclusion(String s1, String s2) {
        int len1 = s1.length(), len2 = s2.length();
        // converting strings to character array
        char[] c1 = s1.toCharArray(), c2 = s2.toCharArray();
        // length of 1st array should be less than
        // or equal to the length of 2nd array
        if (len1 > len2)
            return false;
        // store freq of each element of "s1"
        int[] freq1 = new int[26];
        for (char c : c1)
            freq1[c - 'a']++;
        // store freq of first "len1" elements of "s2"
        int[] freq2 = new int[26];
        for (int i = 0; i < len1; i++)
            freq2[c2[i] - 'a']++;
        // if freq are equal, then permutations exists
        if (Arrays.equals(freq1, freq2))
            return true;
        // keeping a window size of "len1" and store their freq
        for (int i = len1; i < len2; i++) {
            freq2[c2[i] - 'a']++;
            freq2[c2[i - len1] - 'a']--;
            // permutation exists, if at anytime freq of s1 matches
            // with freq of elements in current window of "len1"
            if (Arrays.equals(freq1, freq2))
                return true;
        }
        // no permutation of "s1" exists in "s2"
        return false;
    }

    /**
     * <strong>TLE</strong>
     */
    public boolean checkInclusion1(String s1, String s2) {
        int len1 = s1.length(), len2 = s2.length();
        if (len1 > len2)
            return false;
        // store freq. of all elements in s1
        HashMap<Character, Integer> map1 = new HashMap<>();
        for (char c : s1.toCharArray())
            map1.put(c, map1.getOrDefault(c, 0) + 1);
        // iterate over s2 and check whether permutation of s1 is present
        for (int i = 0; i < len2; i++) {
            // create duplicate freq. map of s1
            HashMap<Character, Integer> tempMap1 = new HashMap<>(map1);
            for (int j = i; j < len2; j++) {
                char c = s2.charAt(j);
                // remove found element(s)
                if (tempMap1.containsKey(c) && tempMap1.get(c) > 0) {
                    tempMap1.put(c, tempMap1.get(c) - 1);
                }
                // stop if any element is not present in current window
                else {
                    break;
                }
            }
            // check whether our current substring
            // window contains all characters of s1
            boolean flag = true;
            for (int v : tempMap1.values())
                if (v != 0)
                    flag = false;
            // if so return true else continue
            if (flag)
                return true;
        }
        return false;
    }
}
