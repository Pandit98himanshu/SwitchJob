package leetcode;

import java.util.*;

/**
 * Created at : 29/08/21
 * <p>
 * <a href=https://leetcode.com/problems/find-the-kth-largest-integer-in-the-array/>5855. Find the Kth Largest Integer in the Array</a>
 *
 * @author Himanshu Shekhar
 */

class FindTheKthLargestIntegerInTheArray {
    /**
     * <strong>Sorting</strong> - beats 100 %
     * <p>Time Complexity: O(n(log(n)) - 16 ms
     * <br>Space Complexity: O(n) - 46.3 MB
     */
    public String kthLargestNumber(String[] nums, int k) {
        Arrays.sort(nums, (s1, s2) -> {
            int c = Integer.compare(s1.length(), s2.length());
            if (c != 0) return c;
            return s1.compareTo(s2);
        });
        return nums[nums.length - k];
    }

    /**
     * <strong>PriorityQueue</strong> beats 25 %
     * <p>Time Complexity: O(n(log(n)) - 88 ms
     * <br>Space Complexity: O(n(log(n)) - 47.4 MB
     */
    public String kthLargestNumber2(String[] nums, int k) {
        PriorityQueue<String> minHeap = new PriorityQueue<>(this::compare);
        for (String num : nums) {
            minHeap.add(num);
            if (minHeap.size() > k)
                minHeap.poll();
        }

        return minHeap.poll();
    }

    /**
     * @return <pre>
     * -1 : a < b;
     * 0 : a == b;
     * 1 : a > b
     * </pre>
     */
    private int compare(String a, String b) {
        int m = a.length(), n = b.length();

        if (m != n) return m - n;
        if (a.equals(b)) return 0;
        return a.compareTo(b);
    }

    /**
     * <strong>Quick Sort</strong> - gives TLE
     * <p>Time Complexity: O(n<sup>2</sup>)
     */
    public String kthLargestNumber1(String[] nums, int k) {
        int n = nums.length;
        quickSort(nums, 0, n - 1);
        return nums[n - k];
    }

    /**
     * @return <pre>
     * 1 : a < b;
     * 0 : a == b;
     * -1 : a > b
     * </pre>
     */
    private int compare1(String a, String b) {
        int m = a.length(), n = b.length();

        if (m != n) return n - m;
        if (a.equals(b)) return 0;
        return b.compareTo(a);
    }

    private void swap(String[] arr, int i, int j) {
        String temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private int partition(String[] arr, int low, int high) {
        String pivot = arr[high];

        int i = (low - 1);
        for (int j = low; j <= high - 1; j++) {
            if (compare1(arr[j], pivot) > 0) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return (i + 1);
    }

    private void quickSort(String[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    public static void main(String[] args) {
        String[] nums = {"82", "58", "21", "2", "12", "982"};
        int k = 2;
        FindTheKthLargestIntegerInTheArray obj = new FindTheKthLargestIntegerInTheArray();
        System.out.println(obj.kthLargestNumber(nums, k));
    }
}

