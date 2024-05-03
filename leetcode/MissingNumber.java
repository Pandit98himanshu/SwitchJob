package leetcode;

/**
 * Created at : 03/05/24
 * <p>
 * <a href=https://leetcode.com/problems/missing-number/description/>
 * 268. Missing Number
 * </a>
 *
 * @author Himanshu Shekhar
 */

public class MissingNumber {
    static class Solution {
        public int missingNumber(int[] nums) {
            int missingNum = 0;
            for (int i = 1; i <= nums.length; i++) {
                missingNum = missingNum ^ i;
            }
            for (int val : nums)
                missingNum = missingNum ^ val;
            return missingNum;
        }
    }
    public static void main(String[] args) {
        int[] input = {8,9,6,4,2,3,7,0,1};
        System.out.println(new Solution().missingNumber(input));
    }
}
