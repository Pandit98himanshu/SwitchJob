//package codeforces.round771;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created at : 16/02/22
 * <p>
 * <a href=https://codeforces.com/contest/1638/problem/C>C. Inversion Graph</a>
 *
 * @author Himanshu Shekhar
 */

public class InversionGraph {
    /**
     * Copied from <a href=https://codeforces.com/contest/1638/submission/146654813>nishugrg1435</a>
     */
    private int inversionGraph1(int n, int[] p) {
        int max = Integer.MIN_VALUE;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, p[i]);
            if (i+1 == max)
                ans++;
        }
        return ans;
    }

    /**
     * Idea is: If bigger element comes before smaller elements,
     * then all those smaller elements are connected from that bigger element
     * hence, inverse the mapping, i.e., values of "p" becomes index & vice-versa.
     * <p>If at any index of "map[i]", value is smaller than previous, that means,
     * "i" comes before than some previous "i"s in given array "p", hence there's
     * a node between all those prev "i"s whose values in "map" are greater than "map[i]"
     */
    private int inversionGraph(int n, int[] p) {
        int[] map = new int[n+2];
        for (int i = 0; i < n; i++) {
            map[p[i]] = i;
        }
        map[n+1] = Integer.MAX_VALUE;   // add infinity at the end, so that we can get correct results

        // min heap sorted on 1st column, if it is same, then sorted by 2nd column
        PriorityQueue<int[]> intervals = new PriorityQueue<>((a, b) -> {
            if (a[0] == b[0])
                return a[1] - b[1];
            else
                return a[0] - b[0];
        });

        int newMax = map[1];
        for (int i = 1; i <= n; i++) {
            // greater elements are prev to smaller elements in given array "p"
            // hence "map[i+1]" is becoming smaller than current "map[i]"
            if (map[i] > map[i+1])
                continue;
            else {
                intervals.offer(new int[]{map[i], newMax});
                // max element for next interval
                newMax = map[i+1];
            }
        }

        // count no. of merged intervals
        int lastEnd = 0;
        int ans = 1;
        while (!intervals.isEmpty()) {
            int[] curr = intervals.poll();
            if (lastEnd < curr[0]) {
                ans++;
            }
            lastEnd = Math.max(lastEnd, curr[1]);
        }
        return ans;
    }


    public static void main(String[] args) {
        InversionGraph obj = new InversionGraph();
        FastReader sc = new FastReader();
        StringBuffer ans = new StringBuffer();

        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int[] p = new int[n];
            for (int i = 0; i < n; i++)
                p[i] = sc.nextInt();

            ans.append(obj.inversionGraph1(n, p) + "\n");
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
