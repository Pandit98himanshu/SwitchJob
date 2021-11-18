package leetcode;

/**
 * Created at : 19/08/21
 * <p>
 * <a href=https://leetcode.com/problems/sort-array-by-parity-ii/>922. Sort Array By Parity II</a>
 *
 * @author Himanshu Shekhar
 */

class SortArrayByParityII {
    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 1, 4, 0, 0, 4, 3, 3};
        int[] res = new SortArrayByParityII().sortArrayByParityII(nums);
        for (int i : res)
            System.out.print(i + ", ");
    }

    /**
     * Space Complexity: O(1)
     */
    public int[] sortArrayByParityII(int[] nums) {
        int x = 0, y = 1;

        while (x < nums.length && y < nums.length) {
            int evenPlace = (nums[x] + x) % 2;
            int oddPlace = (nums[y] + y) % 2;
            if (evenPlace == 1 && oddPlace == 1) {
                int temp = nums[x];
                nums[x] = nums[y];
                nums[y] = temp;
                x += 2;
                y += 2;
            } else {
                if (evenPlace == 0)
                    x += 2;
                if (oddPlace == 0)
                    y += 2;
            }
        }
        return nums;
    }
}