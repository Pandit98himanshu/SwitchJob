package leetcode;

import java.util.HashSet;

/**
 * Created at : 07/11/21
 * <p>
 * <a href=https://leetcode.com/contest/weekly-contest-266/problems/vowels-of-all-substrings/>5919. Vowels of All Substrings</a>
 *
 * @author Himanshu Shekhar
 */

public class VowelsOfAllSubstrings {
    public static void main(String[] args) {
        String word = "abce";
        System.out.println(new VowelsOfAllSubstrings().countVowels(word));
    }

    /**
     * <p>Time Complexity: O(n)
     * <br>Space Complexity: O(1)
     * <p>Copied from <a href=https://leetcode.com/problems/vowels-of-all-substrings/discuss/1563780/JavaC++Python-Easy-and-Concise-O(n)>leetcode discuss</a>
     */
    public long countVowels(String word) {
        int n = word.length();
        int ans = 0;
        String vowels = "aeiou";
        for (int i = 0; i < n; i++) {
            if (vowels.indexOf(word.charAt(i)) >= 0) {
                // (i + 1) before ith position and
                // (n - i) after ith position
                ans += (i + 1) * (n - i);
            }
        }
        return ans;
    }

    /**
     * <strong>Wrong Answer</strong>
     */
    public long countVowels1(String word) {
        HashSet<Character> vowels = new HashSet<>();
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');

        int n = word.length();

        int vowelsTillNow = 0;
        long ans = 0;
        for (int i = 0; i < n; i++) {
            if (vowels.contains(word.charAt(i))) {
                vowelsTillNow++;
                int nums = vowelsTillNow;
                for (int j = 0; j <= i; j++) {
                    ans += nums;
                    if (vowels.contains(word.charAt(j)))
                        nums--;
                }
            } else {
                ans += vowelsTillNow;
            }
        }
        return ans;
    }
}
