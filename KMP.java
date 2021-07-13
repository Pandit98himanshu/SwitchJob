/**
 * Date 31/08/2019
 * <p>Doing substring matching in given string using KMP Algorithm</p>
 * <p>Time complexity = O(str.length + pattern.length)</p>
 * <p>Space complexity = O(pattern.length)</p>
 *
 * @author pandithimanshu
 */

public class KMP {
	private int[] computeTempArray(char []pattern) {
		int m = pattern.length, j = 0;
		int []lps = new int[m];
		for (int i = 1; i < m; ) {
			if(pattern[i] == pattern[j]) {
				lps[i] = j + 1;
				j++;
				i++;
			}
			else {
				if(j != 0) 
					j = lps[j-1];
				else {
					lps[j] = 0;
					i++;
				}
			}
		}
		return lps;
	}
	public int KMPSearch(char []str, char []pattern) {
		int lps[] = computeTempArray(pattern);
		int i = 0, j = 0;
		while(i < str.length && j < pattern.length) {
			if(str[i] == pattern[j]) {
				i++;
				j++;
				if(j == pattern.length)
					return (i - j);
			}
			else {
				if(j != 0)
					j = lps[j-1];
				else
					i++;
			}
		}
		return -1;
		/*
		 * if(j == pattern.length) return true;
		 * 
		 * return false;
		 */		 
	}
	public static void main(String []args)
	{
		String str = "aba chtnhdhing ab cabs";
		String pattern = "chtnh";
		KMP search = new KMP();
		int startIndex = search.KMPSearch(str.toCharArray(), pattern.toCharArray());
		System.out.println(startIndex);
	}
}