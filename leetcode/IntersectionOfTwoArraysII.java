package leetcode;

import java.util.*;

/**
 * Created at : 17/09/21
 * <p>
 * <a href=https://leetcode.com/problems/intersection-of-two-arrays-ii/>350. Intersection of Two Arrays II</a>
 *
 * @author Himanshu Shekhar
 */

public class IntersectionOfTwoArraysII {
    /**
     * Iterate over first list nums1 and increase the count,
     * then decrease the count for every element in second list nums2
     * <p>Time Complexity: O(m + n)
     * <br>Space Complexity: O(m ⋂ n)
     *
     * @param nums1 first list
     * @param nums2 second list
     * @return intersection of first and second array
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> freq = new HashMap<>();
        // store frequencies of all elements from nums1
        for (int i : nums1)
            freq.put(i, freq.getOrDefault(i, 0) + 1);

        List<Integer> intersection = new ArrayList<>();
        for (int i : nums2) {
            // if element "i" is in nums1
            if (freq.containsKey(i) && freq.get(i) > 0) {
                intersection.add(i);            // add to the intersection
                freq.put(i, freq.get(i) - 1);   // decrease count by one
            }
        }
        return intersection.stream().mapToInt(Integer::intValue).toArray();
    }

    private static void print(Object... o) {
        System.out.println(Arrays.deepToString(o));
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 2, 1}, nums2 = {10, 5};
        print(new IntersectionOfTwoArraysII().intersect(nums1, nums2));
    }
}
