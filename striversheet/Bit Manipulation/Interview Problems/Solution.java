/**
 * L to R XOR
 * https://www.naukri.com/code360/problems/l-to-r-xor_8160412
 */

public class Solution {
	public static int findXOR(int L, int R){
		return XORtill(L - 1) ^ XORtill(R);
	}
	private static int XORtill(int n) {
		int rem = n % 4;
		switch(rem) {
			case 0 -> { return n; }
			case 1 -> { return 1; }
			case 2 -> { return n + 1; }
			case 3 -> { return 0; }
		}
		return 0;
	}
}
