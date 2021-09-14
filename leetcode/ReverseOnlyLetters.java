package leetcode;

/**
 * Created at : 14/09/21
 * <p>
 * <a href=https://leetcode.com/problems/reverse-only-letters/>917. Reverse Only Letters</a>
 *
 * @author Himanshu Shekhar
 */

public class ReverseOnlyLetters {
    /**
     * <p>Time Complexity O(n)
     * <br>Space Complexity: O(n)
     *
     * @return reverse only alphabets of the string
     */
    public String reverseOnlyLetters(String s) {
        int n = s.length();
        char[] str = s.toCharArray();
        char[] revStr = new char[n];
        int i = 0, j = n - 1;
        while (i < n && j >= 0) {
            // place special characters at its own place
            if (!(str[i] >= 65 && str[i] <= 90) && !(str[i] >= 97 && str[i] <= 122)) {
                revStr[i] = str[i];
                i++;
            }
            // don't misplace special characters
            else if (!(str[j] >= 65 && str[j] <= 90) && !(str[j] >= 97 && str[j] <= 122)) {
                j--;
            }
            // place alphabets in reverse order
            else {
                revStr[i++] = str[j--];
            }
        }
        // place left out characters at the end fo the string
        while (i < n) {
            revStr[i] = str[i];
            i++;
        }
        return new String(revStr);
    }

    public static void main(String[] args) {
        String s = "Test1ng-Leet=code-Q!";
        System.out.println(s.length());
        System.out.println(new ReverseOnlyLetters().reverseOnlyLetters(s));
    }
}
