package leetcode;

import java.util.Stack;

/**
 * Created at : 19/18/21
 * <p>
 * <a href=https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii/>1209. Remove All Adjacent Duplicates in String II</a>
 *
 * @author Himanshu Shekhar
 * @see <a href=https://leetcode.com/discuss/interview-question/625140/Goldman-Sachs-or-OA-2020-or-Array-Burst-Problem-and-Birthday-Party>Array Burst Problem</a>
 */

class RemoveAllAdjacentDuplicatesInStringII {
    public static void main(String[] args) {
        String s = "deeedbbcccbdaa";
        int k = 3;
        System.out.println(new RemoveAllAdjacentDuplicatesInStringII().removeDuplicates(s, k));
    }

    private String removeDuplicates(String s, int k) {
        // final answer will be stored here
        StringBuilder duplicatesRemoved = new StringBuilder();
        // it stores character and its current frequency
        Stack<Pair> stk = new Stack<>();

        int count;
        for (int i = 0; i < s.length(); i++) {
            if (stk.isEmpty()) {
                stk.push(new Pair(s.charAt(i), 1));
            } else if ((stk.peek().c) == s.charAt(i)) {
                count = stk.peek().count + 1;
                stk.push(new Pair(s.charAt(i), count));
                if (count >= k) {
                    while (count-- > 0) {
                        stk.pop();
                    }
                }
            } else {
                stk.push(new Pair(s.charAt(i), 1));
            }
        }
        for (Pair pair : stk) {
            duplicatesRemoved.append(pair.c);
        }

        return duplicatesRemoved.toString();
    }

    // a pair class which stores the character and its count
    private static class Pair {
        char c;
        int count;

        Pair(char c, int count) {
            this.c = c;
            this.count = count;
        }
    }
}
