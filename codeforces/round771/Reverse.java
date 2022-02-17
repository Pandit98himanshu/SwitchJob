package codeforces.round771;

import java.io.*;
import java.util.*;

/**
 * Created at : 15/02/22
 * <p>
 * <a href=https://codeforces.com/contest/1638/problem/A>A. Reverse</a>
 *
 * @author Himanshu Shekhar
 */

public class Reverse {

    private void reverseArray(int[] p, int i, int j) {
        while (i < j) {
            int temp = p[i];
            p[i] = p[j];
            p[j] = temp;

            i++;
            j--;
        }
    }
    private int[] solution(int n, int[] p) {
        int i = 0;
        for (i = 0; i < n; i++) {
            if (p[i] != i+1)
                break;
        }
        int j = i;
        while (j < n && p[j] != i+1) {
            j++;
        }
        reverseArray(p, i, j);

        return p;
    }

    public static void main(String[] args) {
        Reverse obj = new Reverse();
        FastReader sc = new FastReader();
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int[] p = new int[n];
            for (int i = 0; i < n; i++)
                p[i] = sc.nextInt();

            int[] ans = obj.solution(n, p);
            for (int i : ans)
                System.out.print(i + " ");
            System.out.println();
        }
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

