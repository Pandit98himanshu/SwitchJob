/*
 * 1165. Single-Row Keyboard
 * https://leetcode.com/problems/single-row-keyboard/
 */
package leetcode;

class SingleRowKeyboard {
    static class Solution {
        public int calculateTime(String keyboard, String word) {
            int[] chars = new int[26];
            for (int i = 0; i < keyboard.length(); i++) {
                int asciiRelative = keyboard.charAt(i) - 'a';
                chars[asciiRelative] = i;
            }

            int time = 0, prev = 0;
            for (int i = 0; i < word.length(); i++) {
                int asciiRelative = word.charAt(i) - 'a';
                time += Math.abs(chars[asciiRelative] - prev);
                prev = chars[asciiRelative];
            }

            return time;
        }
    }

    public static void main(String[] args) {
        String keyboard = "bcadfe";
        String word = "aae";

        System.out.println(new Solution().calculateTime(keyboard, word));
    }
}