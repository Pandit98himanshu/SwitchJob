/*
 * 1629. Slowest Key
 * https://leetcode.com/problems/slowest-key/
 */

package leetcode;

public class SlowestKey {
    /**
     * <p>Time Complexity: O(n)
     * <br>Space Complexity: O(1)
     *
     * @return key pressed for longest duration
     */
    public char slowestKey(int[] releaseTimes, String keysPressed) {
        int n = releaseTimes.length;
        // initially, consider 1st key is pressed for longest duration
        int longestDuration = releaseTimes[0];
        char keyPressed = keysPressed.charAt(0);
        // search for other keys in the array
        for (int i = 1; i < n; i++) {
            // check if current duration is greater than my previous duration
            // we'll update our previous duration and key pressed as well
            if (longestDuration < releaseTimes[i] - releaseTimes[i - 1]) {
                longestDuration = releaseTimes[i] - releaseTimes[i - 1];
                keyPressed = keysPressed.charAt(i);
            }
            // if duration is same then we need to take the character
            // which is lexicographically largest
            else if (longestDuration == releaseTimes[i] - releaseTimes[i - 1]) {
                if (keyPressed < keysPressed.charAt(i)) {
                    keyPressed = keysPressed.charAt(i);
                }
            }
        }
        return keyPressed;
    }
}
