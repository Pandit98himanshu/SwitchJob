package leetcode;

/**
 * Created at : 19/08/21
 * <p>
 * <a href=https://leetcode.com/problems/maximum-length-of-repeated-subarray/>718. Maximum Length of Repeated Subarray</a>
 *
 * @author Himanshu Shekhar
 */

class MaximumLengthOfRepeatedSubarray {
    public static void main(String[] args) {
        int[] nums1 = {0, 1, 1, 1, 1}, nums2 = {1, 0, 1, 0, 1};

        System.out.println(new MaximumLengthOfRepeatedSubarray().findLength(nums1, nums2));

    }

    /**
     * @see https://www.geeksforgeeks.org/longest-common-substring-dp-29/
     */
    public int findLength(int[] X, int[] Y) {
        int m = X.length, n = Y.length;
        int[][] LCSuffix = new int[2][n + 1];

        int result = 0;

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0)
                    LCSuffix[i % 2][j] = 0;
                else if (X[i - 1] == Y[j - 1]) {
                    LCSuffix[i % 2][j] = LCSuffix[(i - 1) % 2][j - 1] + 1;
                    result = Integer.max(result, LCSuffix[i % 2][j]);
                } else
                    LCSuffix[i % 2][j] = 0;
            }
        }
        //System.out.println(Arrays.deepToString(LCSuffix));
        return result;
    }
}