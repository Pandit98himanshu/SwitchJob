/*
 * 565. Array Nesting
 * https://leetcode.com/problems/array-nesting/
 */

package leetcode;

public class ArrayNesting {

    /**
     * <p>Time Complexity: O(n) : 5 ms
     * <br>Space Complexity: O(1)
     */
    public int arrayNesting(int[] nums) {
        int n = nums.length;
        int inf = Integer.MAX_VALUE;    // represents infinity
        int maxLen = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == inf)
                continue;
            int j = nums[i], count = 1;
            nums[i] = inf;
            while (nums[j] != inf) {
                int temp = nums[j];
                nums[j] = inf;      // we mark current position visited, so that we'll not visit it again
                j = temp;           // next index
                count++;
            }
            maxLen = Math.max(maxLen, count);       // keep track of maximum length set
        }
        return maxLen;
    }

    /**
     * <p>Time Complexity: O(n<sup>2</sup>) : 327 ms
     */
    private int helper(int[] nums, int i, boolean[] visited) {
        int len = 1;
        visited[i] = true;
        int curr = nums[i];
        while (!visited[curr]) {
            visited[curr] = true;
            curr = nums[curr];
            len++;
        }
        return len;
    }

    public int arrayNesting1(int[] nums) {
        int n = nums.length;
        int maxS = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            maxS = Math.max(maxS, helper(nums, i, new boolean[n]));
            // if we exceed more than the length of array, we got our max length set
            if (maxS > n / 2)
                break;
        }
        return maxS;
    }

    public static void main(String[] args) {
        int[] nums = {0, 2, 1};
        System.out.println(new ArrayNesting().arrayNesting(nums));
    }
}
