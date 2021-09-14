package leetcode;

/**
 * Created at : 13/09/21
 * <p>
 * <a href=https://leetcode.com/problems/maximum-number-of-balloons/>1189. Maximum Number of Balloons</a>
 *
 * @author Himanshu Shekhar
 */

public class MaximumNumberOfBalloons {
    /**
     * <p>Time Complexity: O(n)
     * <br>Space Complexity: O(1)
     * @return the maximum number of instances of word "balloon" that can be formed
     */
    public int maxNumberOfBalloons(String text) {
        int[] freq = new int[26];
        // store frequencies of all characters in the "text"
        for (int i = 0; i < text.length(); i++)
            freq[text.charAt(i) - 'a']++;

        int count = Integer.MAX_VALUE;
        // for creating 1 "balloon", we need,
        for (int i = 0; i < freq.length; i++) {
            if ((i + 'a') == 'b')   // 1, "b"
                count = Math.min(freq[i], count);
            if ((i + 'a') == 'a')   // 1, "a"
                count = Math.min(freq[i], count);
            if ((i + 'a') == 'l')   // 2, "l"
                count = Math.min(freq[i] / 2, count);
            if ((i + 'a') == 'o')   // 2, "o"
                count = Math.min(freq[i] / 2, count);
            if ((i + 'a') == 'n')   // and 1, "n"
                count = Math.min(freq[i], count);
        }
        return count;
    }
}
