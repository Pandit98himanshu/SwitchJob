package leetcode;

/**
 * Created at : 02/08/21
 * <p>
 * <a href=https://leetcode.com/problems/sum-of-digits-of-string-after-convert/>5823. Sum of Digits of String After Convert</a>
 *
 * @author Himanshu Shekhar
 */

public class SumOfDigitsOfStringAfterConvert {
        /**
         * Get digit sum of n.
         *
         * @param n number in String format
         */
        private static String getSumOfDigits(String n) {
            int sum = 0;
            for (int i = 0; i < n.length(); i++) {
                sum += n.charAt(i) - '0';   // find sum character wise
            }
            // again convert into String and return
            return Integer.toString(sum);
        }

        /**
         * Get digit sum of k, k times.
         *
         * @param s number which sum needs to be returned
         * @param k times we need to perform operation
         */
        public int getLucky(String s, int k) {
            StringBuilder sb = new StringBuilder();
            // convert character to mapping integer values
            for (int i = 0; i < s.length(); i++) {
                sb.append((s.charAt(i) - 'a') + 1);
            }
            String str = sb.toString();
            // perform addition of digits k times
            while (k-- > 0) {
                str = getSumOfDigits(str);
            }
            return Integer.parseInt(str);
        }

    public static void main(String[] args) {
        String s = "leetcode";
        int k = 2;
        System.out.println(new SumOfDigitsOfStringAfterConvert().getLucky(s, k));
    }
}
