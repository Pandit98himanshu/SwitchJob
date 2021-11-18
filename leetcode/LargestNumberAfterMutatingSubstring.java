package leetcode;

/**
 * Created at : 19/08/21
 * <p>
 * <a href=https://leetcode.com/problems/largest-number-after-mutating-substring/>5824. Largest Number After Mutating Substring</a>
 *
 * @author Himanshu Shekhar
 */

public class LargestNumberAfterMutatingSubstring {

    public static void main(String[] args) {
        String num = "334111";
        int[] change = {0, 9, 2, 3, 3, 2, 5, 5, 5, 5};
        System.out.println(new LargestNumberAfterMutatingSubstring().maximumNumber(num, change));
    }

    /**
     * Mutates any substring of num (i.e. replace num[i] with change[num[i]])
     * if (num[i] < change[num[i]])
     *
     * @param num    a large integer value wrapped in String
     * @param change corresponding changing values
     * @return a string representing the largest possible integer
     * after mutating (or choosing not to) any substring of num
     */
    public String maximumNumber(String num, int[] change) {
        StringBuilder res = new StringBuilder();
        int n = num.length();
        for (int i = 0; i < n; i++) {
            int num1 = num.charAt(i) - '0';
            // skip those numbers whose corresponding "change" value is equal
            if (num1 == change[num1]) {
                int j = i;
                while (j < n && num1 == change[num1]) {
                    res.append(num1);       // since num1 == change[num1], you can either
                    j++;
                    num1 = j < n ? (num.charAt(j) - '0') : Integer.MAX_VALUE;
                }
                i = j - 1;        // decrease by 1 because j was increased by 1 in previous while loop
            } else if (num1 < change[num1]) {
                int j = i;
                // here our changed values substring starts
                while (j < n && num1 <= change[num1]) {
                    res.append(change[num1]);       // since changed values are greater use changed values
                    j++;
                    num1 = j < n ? (num.charAt(j) - '0') : Integer.MAX_VALUE;
                }
                // add rest of the values
                while (j < n) {
                    res.append(num.charAt(j) - '0');
                    j++;
                }
                // we are done
                break;
            } else {
                res.append(num1);
            }
        }
        return res.toString();
    }
}
