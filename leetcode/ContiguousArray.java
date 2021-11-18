package leetcode;

import java.util.*;

/**
 * <a href=https://leetcode.com/problems/contiguous-array/>525. Contiguous Array</a>
 *
 * @author Himanshu Shekhar
 */

public class ContiguousArray {
    /**
     * <p>Time Complexity: O(n)
     * <br>Space Complexity: O(n)
     * @return
     */
    public int findMaxLength(int[] nums) {
        int sum = 0;
        // treat "0" as "-1" and create a prefix-sum array
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0)
                sum--;
            else
                sum++;
            nums[i] = sum;
        }
        // initialize empty hashmap
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;        // store the final result
        for (int i = 0; i < nums.length; i++) {
            // if "0" occurs in the modified array,
            // it means no. of 1s and 0s till that index are equal
            if (nums[i] == 0) {
                res = Math.max(res, i + 1);
                continue;
            }
            // if current element is in the hashmap, it means
            // number of 0s and 1s are equal in between, therefore,
            // we are getting same prefix-sum again
            if (map.containsKey(nums[i])) {
                int j = map.get(nums[i]);
                res = Math.max(res, i - j);
            } else {        // if current prefix-sum is not in the hashmap, put it into the map
                // do not override previous mapping, then we'll lose
                // max-length sub-array, therefore, we need to put it in "else"
                map.put(nums[i], i);
            }
        }
        return res;
    }
    private void print(Object...o) {
        System.out.println(Arrays.deepToString(o));
    }
}
