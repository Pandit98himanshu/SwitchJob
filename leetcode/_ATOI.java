/*
 * 8. String to Integer (atoi)
 * https://leetcode.com/problems/string-to-integer-atoi/
 */

package leetcode;

class _ATOI {
	static class Solution {
		public int myAtoi(String s) {
			StringBuilder sb = new StringBuilder();
			// 1, 2, 3
			for (int i = 0; i < s.length(); i++) {
				if ((s.charAt(i) >= 48 && s.charAt(i) < 58)
						|| (s.charAt(i) == 43 || s.charAt(i) == 45))
					sb.append(s.charAt(i));
				else if (s.charAt(i) == ' ')
					if (sb.length() > 0)
						break;
					else
						continue;
				else
					break;
			}

			if (sb.length() == 0) {
				return 0;
			}
			// 4
			long ans = 0;

			try {
				ans = Long.parseLong(sb.toString());
				System.out.println("Int is : " + ans);
			}
			catch (Exception e) {
				ans = 0;
			}
			// 5
			if (ans < Integer.MIN_VALUE) {
				ans = Integer.MIN_VALUE;
			}
			if (ans > Integer.MAX_VALUE) {
				ans = Integer.MAX_VALUE;
			}

			return (int)ans;
		}
	}

    public static void main(String[] args) {
        String s = "20000000000000000000";

        System.out.println(new Solution().myAtoi(s));
    }
}