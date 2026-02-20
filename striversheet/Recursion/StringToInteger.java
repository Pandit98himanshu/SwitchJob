/**
 * 8. String to Integer (atoi)
 * https://leetcode.com/problems/string-to-integer-atoi
 */

class Solution {
	public int myAtoi(String s) {
		StringBuilder sb = new StringBuilder();
		atoiHelper(s, 0, sb);
		// System.out.println(sb.toString());
		
		boolean isNeg = sb.length() > 0 && sb.charAt(0) == '-';
		long ans = 0;
		for (int i = 0; i < sb.length(); i++) {
			if (sb.charAt(i) == '+' || sb.charAt(i) == '-')		// skip +/- at the beginning
				continue;
			ans *= 10;
			ans += (int)(sb.charAt(i) - '0');

			if (ans > Integer.MAX_VALUE) {
				return isNeg ? Integer.MIN_VALUE : Integer.MAX_VALUE;	// handling overflow of long
			}
		}
		if (isNeg)
			ans *= -1;

		return (int)ans;
	}

	private void atoiHelper(String s, int i, StringBuilder sb) {
		// base case
		if (i >= s.length())
			return;
		char curr = s.charAt(i);
		// add to string builder if integer digits
		if (curr >= '0' && curr <= '9') {
			sb.append(curr);
			atoiHelper(s, i + 1, sb);
		}
		
		if (sb.length() == 0) {				// allow only leading " ", "+", "-"
			if (curr == ' ')
				atoiHelper(s, i + 1, sb);
			else if (curr == '-' || curr == '+') {	// add +/- into the stringbuilder for ease of handling edge-cases
				sb.append(curr);
				atoiHelper(s, i + 1, sb);
			}
		}
	}
}

public class StringToInteger {
	String s = "0-1";		// 0
	s = "-.987";			// 0
	s = "-91283472332";		// -2147483648
	s = "+-12";				// 0
	s = "9223372036854775808";	// 2147483647
	s = "-5-";				// -5
	s = "words and 987";	// 0
}
