package leetcode;

/**
 * Created at : 19/08/21
 * <p>
 * <a href=https://leetcode.com/problems/single-row-keyboard/>1165. Single-Row Keyboard</a>
 *
 * @author Himanshu Shekhar
 */

class SingleRowKeyboard {
    public static void main(String[] args) {
        String keyboard = "bcadfe";
        String word = "aae";

        System.out.println(new SingleRowKeyboard().calculateTime(keyboard, word));
    }

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