// IMPOSSIBLE:
// If, no. of single duplicate element > no. of diff elements

import java.util.*;

class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();
        sc.nextLine();
        for (int t = 1; t <= tc; t++) {
            System.out.println("Case #" + t + ": ");
            char[] s = sc.nextLine().trim().toCharArray();
            int n = s.length;
            int[][] c = new int[n][2];
            for (int i = 0; i < n; i++) {
                c[i][0] = s[i];
                c[i][1] = i;
            }
            Arrays.sort(c, (x, y) -> {
                if (x[0] != y[0])       // sort in alphabetical order
                    return x[0] - y[0];
                else                    // if alphabets are same
                    return x[1] - y[1]; // sort in asc order of indices
            });

            char[] res = new char[n];
            for (int i = 0; i < n; i++) {
                res[c[(i + n / 2) % n][1]] = (char) c[i][0];
            }

            for (int i = 0; i < n; i++) {
                if (res[i] == s[i]) {
                    System.out.println("IMPOSSIBLE");
                    return;
                }
            }
            System.out.println(new String(res));
            /*
            ArrayList<Integer>[] freq = new ArrayList[26];
            Arrays.fill(freq, new ArrayList());
            for (int i = 0; i < s.length; i++) {
                freq[s[i] - 'a'].add(i);
            }
            // IMPOSSIBLE case
            for (ArrayList<Integer> list : freq) {
                if (list.size() > (s.length - list.size())) {
                    System.out.println("Case #" + t + ": IMPOSSIBLE");
                    return;
                }
            }
            // POSSIBLE case
            char[] res = new char[s.length];
            for (int i = 0; i < freq.length; i++) {
                if (freq[i].size() > 1) {
                    for (int j = 0; j < freq.length; j++) {
                        if (j != i && freq[j].size() > 0) {
                            res[freq[i]]
                        }
                    }
                }
            }
             */
        }
    }
}

public class Anagram_KickStart {
    public static void main(String[] args) {

    }
}
