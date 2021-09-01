package leetcode;

public class SearchInRotatedSortedArray {
    /**
     * <strong>Typical Binary search</strong>
     *
     * @return index of target, otherwise returns -1
     */
    private int binarySearch(int[] nums, int l, int r, int target) {
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target)
                return mid;
            else if (nums[mid] < target)
                l = mid + 1;
            else
                r = mid - 1;
        }
        return -1;
    }

    /**
     * Next element after the largest element is always smaller
     *
     * @return index of the largest element in the {@code nums} array
     */
    private int findLargest(int[] nums, int l, int r) {
        while (l <= r) {
            int mid = (l + r) / 2;
            // if mid == 0
            if (mid < r && nums[mid + 1] < nums[mid])
                return mid;
                // if mid == nums.length - 1
            else if (mid > l && nums[mid - 1] > nums[mid])
                return mid - 1;
            if (nums[l] < nums[mid])        // trickiest part
                l = mid + 1;
            else
                r = mid - 1;
        }
        return 0;
    }

    /**
     * <p>Time Complexity: O(log(n))
     * <br>Space Complexity: O(1)
     *
     * @param nums   rotated sorted array
     * @param target number we need to find
     * @return index of {@code target} otherwise returns -1
     */
    public int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        int lastEle = findLargest(nums, l, r);
        // search for target in left side
        int ans = binarySearch(nums, l, lastEle, target);
        // search in right side, if it's not in left side
        ans = (ans == -1) ? binarySearch(nums, lastEle + 1, r, target) : ans;
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {3, 4, 5, 6, 1, 2};
        int target = 2;
        System.out.println(new SearchInRotatedSortedArray().search(nums, target));
    }
}
