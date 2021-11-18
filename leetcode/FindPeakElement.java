package leetcode;

/**
 * Created at : 02/08/21
 * <p>
 * <a href=https://leetcode.com/problems/find-peak-element/>162. Find Peak Element</a>
 *
 * @author Himanshu Shekhar
 */

public class FindPeakElement {
    public static void main(String[] args) {
        int[] nums = {3, 4, 3, 2, 1};
        System.out.println(new FindPeakElement().findPeakElement(nums));
    }

    public int findPeakElement(int[] nums) {
        int n = nums.length;
        int l = 0, r = n - 1, mid;
        if (n == 1) {
            return nums[0];
        }
        if (n == 2) {
            if (nums[0] > nums[1]) return 0;
            else return 1;
        }
        while (true) {
            mid = l + (r - l) / 2;
            // if 1st element is peak
            if (mid == 0 && nums[mid] > nums[mid + 1]) {
                break;
            }
            // if last element is peak
            else if (mid == n - 1 && nums[mid] > nums[mid - 1]) {
                break;
            }
            // if we are at peak
            else if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) {
                break;
            }
            // if peak is at right of "mid"
            else if (nums[mid] > nums[mid - 1] && nums[mid] < nums[mid + 1]) {
                l = mid + 1;
            }
            // if peak is at left of "mid"
            else {
                r = mid;
            }
        }
        return mid;
    }
}
