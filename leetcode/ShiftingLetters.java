package leetcode;

/**
 * Created at : 10/09/21
 * <p>
 * <a href=https://leetcode.com/problems/shifting-letters/>848. Shifting Letters</a>
 *
 * @author Himanshu Shekhar
 */

public class ShiftingLetters {
    /**
     * <p>Time Complexity: O(n)
     *
     * @return the final string after shifting
     */
    public String shiftingLetters(String s, int[] shifts) {
        int n = shifts.length;
        // suffix sum
        for (int i = n - 2; i >= 0; i--)
            shifts[i] = (shifts[i] + shifts[i + 1]) % 26;
        StringBuilder sb = new StringBuilder(); // stores result
        // do exactly the question says
        for (int i = 0; i < s.length(); i++) {
            sb.append((char) (((s.charAt(i) - 'a') + shifts[i]) % 26 + 'a'));
        }
        return sb.toString();
    }
}
