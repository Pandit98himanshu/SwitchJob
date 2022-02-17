package codeforces.round771;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Created at : 15/02/22
 * <p>
 * <a href=https://codeforces.com/contest/1638/problem/B>B. Odd Swap Sort</a>
 *
 * @author Himanshu Shekhar
 */

public class OddSwapSort {
    /**
     * Copied from <a href=https://codeforces.com/contest/1638/submission/146653014>Yang Li (IKnowNothing)</a>
     * <p>Idea is: Even nos. and odd nos. should be sorted separately,
     * odd + odd = even
     * <br>
     * even + even = even
     */
    private String canSort(int n, int[] a) {
        int prevEven = 0, prevOdd = 0;
        boolean canOddSwap = true;
        for (int i = 0; i < n; i++) {
            if (a[i] % 2 == 0) {
                if (prevEven <= a[i])
                    prevEven = a[i];
                else
                    canOddSwap = false;
            } else {
                if (prevOdd <= a[i])
                    prevOdd = a[i];
                else
                    canOddSwap = false;
            }
        }
        return canOddSwap ? "Yes\n" : "No\n";
    }

    public static void main(String[] args) {
        OddSwapSort obj = new OddSwapSort();
        FastReader sc = new FastReader();
        StringBuffer ans = new StringBuffer();

        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; i++)
                a[i] = sc.nextInt();
            ans.append(obj.canSort(n, a));
        }
        System.out.println(ans);
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader
                    (new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();

        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}
