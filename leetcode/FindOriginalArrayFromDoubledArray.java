package leetcode;

import java.util.*;

/**
 * Created at : 18/09/21
 * <p>
 * <a href=https://leetcode.com/problems/find-original-array-from-doubled-array/>2007. Find Original Array From Doubled Array</a>
 *
 * @author Himanshu Shekhar
 */

public class FindOriginalArrayFromDoubledArray {
    /**
     * Copied from <a href=https://leetcode.com/problems/find-original-array-from-doubled-array/discuss/1470959/JavaC++Python-Match-from-the-Smallest-or-Biggest-100>leetcode discuss</a>
     */
    public int[] findOriginalArray(int[] changed) {
        int n = changed.length;
        if (n % 2 != 0)
            return new int[0];

        int[] res = new int[n / 2];
        int j = 0;
        Map<Integer, Integer> freq = new TreeMap<>();
        for (int x : changed)
            freq.put(x, freq.getOrDefault(x, 0) + 1);
        for (int x : freq.keySet()) {
            if (freq.getOrDefault(2 * x, 0) < freq.get(x))
                return new int[0];
            for (int i = 0; i < freq.get(2 * x); i++) {
                res[j++] = x;
                freq.put(2 * x, freq.get(2 * x) - 1);
            }
        }
        return res;
    }

    private static void print(Object... o) {
        System.out.println(Arrays.deepToString(o));
    }

    public static void main(String[] args) {
        int[] changed = {1, 3, 4, 2, 6, 8};
        print(new FindOriginalArrayFromDoubledArray().findOriginalArray(changed));
    }
}
