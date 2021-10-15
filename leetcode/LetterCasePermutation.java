package leetcode;

import java.util.*;

/**
 * Created at : 13/10/21
 * <p>
 * <a href=https://leetcode.com/problems/letter-case-permutation/>784. Letter Case Permutation</a>
 *
 * @author Himanshu Shekhar
 */

public class LetterCasePermutation {
    /**
     * <strong>BFS</strong> : 7 ms
     * <p>Copied from <a href=https://leetcode.com/problems/letter-case-permutation/discuss/115485/Java-Easy-BFS-DFS-solution-with-explanation>leetcode discuss</a>
     */
    public List<String> letterCasePermutation(String str) {
        if (str == null)
            return new ArrayList<>();
        // convert given string into character array
        char[] s = str.toCharArray();
        Queue<char[]> ans = new LinkedList<>();
        // add given word in the queue
        ans.offer(s);
        // change each letter of given string
        for(int i = 0; i < s.length; i++) {
            // skip digits
            if (s[i] >= '0' && s[i] <= '9')
                continue;
            int size = ans.size();
            // iterate for all elements in queue
            while(size-- > 0) {
                // took out the first one
                char[] curr = ans.poll();
                // add with current character as lowercase
                curr[i] = Character.toLowerCase(curr[i]);
                ans.offer(curr.clone());
                // add with curr character as uppercase
                curr[i] = Character.toUpperCase(curr[i]);
                ans.offer(curr.clone());
            }
        }
        // convert answer into required format
        List<String> finalAns = new ArrayList<>();
        for (char[] c : ans)
            finalAns.add(new String(c));

        return finalAns;
    }

    /**
     * <strong>DFS</strong> : 3 ms
     * <p>Copied from <a href=https://leetcode.com/problems/letter-case-permutation/discuss/115485/Java-Easy-BFS-DFS-solution-with-explanation>leetcode discuss</a>
     */
    public List<String> letterCasePermutation2(String s) {
        if (s == null)
            return new ArrayList<>();

        List<String> finalAns = new ArrayList<>();
        // begin recursion
        permute(s.toCharArray(), 0, finalAns);
        return finalAns;
    }

    private void permute(char[] s, int i, List<String> ans) {
        // add to the answer list
        if (i >= s.length) {
            ans.add(new String(s));
            return;
        }
        // ignore digits and move forward
        if (s[i] >= '0' && s[i] <= '9') {
            permute(s, i + 1, ans);
        } else {
            // convert current character into uppercase
            s[i] = Character.toUpperCase(s[i]);
            permute(s, i + 1, ans);
            // convert current character into lowercase
            s[i] = Character.toLowerCase(s[i]);
            permute(s, i + 1, ans);
        }
    }


    /**
     * <strong>Backtrack</strong> : 50 ms
     */
    public List<String> letterCasePermutation1(String s) {
        if (s == null)
            return new ArrayList<>();

        // This approach produces in n-times(length of string "s")
        // duplicates. Hence, we have to use hash-set to store unique results
        Set<String> ans = new HashSet<>();
        permute(s.toCharArray(), 0, new StringBuilder(), ans);

        return new ArrayList<>(ans);
    }

    private void permute(char[] s, int i, StringBuilder curr, Set<String> ans) {
        // when we're finished, add to resultant set
        if (curr.length() == s.length) {
            ans.add(curr.toString());
            return;
        }
        // iterate whole string from current position
        for (int j = i; j < s.length; j++) {
            // add current character as-it-is
            curr.append(s[j]);
            // move to next character
            permute(s, j + 1, curr, ans);
            // remove last character
            curr.setLength(curr.length() - 1);
            // make current character uppercase
            if (s[j] >= 'a' && s[j] <= 'z')
                curr.append((char) (s[j] - 32));
                // make current character lowercase
            else if (s[j] >= 'A' && s[j] <= 'Z')
                curr.append((char) (s[j] + 32));
                // if it's a digit, add as-it-is
            else
                curr.append(s[j]);
            // move to next character
            permute(s, j + 1, curr, ans);
            // remove last character
            curr.setLength(curr.length() - 1);
        }
    }
}
