package leetcode;

/**
 * Created at : 20/08/21
 * <p>
 * <a href=https://leetcode.com/problems/median-of-two-sorted-arrays/>4. Median of Two Sorted Arrays</a>
 *
 * @author Himanshu Shekhar
 */

public class MedianOfTwoSortedArrays {
    /**
     * Copied from <a href=https://youtu.be/LPFhl65R7ww>Tushar Roy</a>
     * <p>Time Complexity: O(log(min(m, n)))
     * <br>Space Complexity: O(1)
     *
     * @return median of 2 sorted array {@code nums1} & {@code nums2}
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        // length of nums1 should be less than that of nums2
        if (m > n)
            return findMedianSortedArrays(nums2, nums1);

        int l = 0, r = m;
        while (l <= r) {
            // middle element in nums1
            int mid1 = (l + r) / 2;
            // middle element in nums2 such that,
            // no. of elements in left_part == no. of elements in right_part
            int mid2 = (m + n + 1) / 2 - mid1;

            // MIN_VALUE serves as -∞ & MAX_VALUE serves as +∞
            // if no element left in left_part of nums1 it should be -∞
            int maxLeft1 = (mid1 <= 0) ? Integer.MIN_VALUE : nums1[mid1 - 1];
            // if no element left in right_part of nums1 it should be +∞
            int minRight1 = (mid1 >= m) ? Integer.MAX_VALUE : nums1[mid1];
            // similarly, for nums2
            int maxLeft2 = (mid2 <= 0) ? Integer.MIN_VALUE : nums2[mid2 - 1];
            int minRight2 = (mid2 >= n) ? Integer.MAX_VALUE : nums2[mid2];

            if (maxLeft1 <= minRight2 && maxLeft2 <= minRight1) {
                // for even, median is average of 2 middle elements
                if ((m + n) % 2 == 0)
                    return ((double) Math.max(maxLeft1, maxLeft2) + Math.min(minRight1, minRight2)) / 2;
                // for odd, median is middle element
                else
                    return (double) Math.max(maxLeft1, maxLeft2);
            } else if (maxLeft2 > minRight1)    // we need to move left in nums2
                l = mid1 + 1;
            else                                // we need to move right in nums2
                r = mid1 - 1;                   // or increasing no. of elements in left_part of nums1
        }
        return Double.MIN_VALUE;                // array is not sorted
    }

    public static void main(String[] args) {
        int[] nums1 = {2}, nums2 = {};
        System.out.println(new MedianOfTwoSortedArrays().findMedianSortedArrays(nums1, nums2));
    }
}
