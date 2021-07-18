/*
 * 927. Three Equal Parts
 * https://leetcode.com/problems/three-equal-parts/
 */

package leetcode;

import java.util.Arrays;

public class ThreeEqualParts {
    static class Solution {
        public int lower_bound(int[] arr, int key) {
            int l = 0, r = arr.length - 1, mid, result = 0;
            while (l <= r) {
                mid = l + (r - l) / 2;
                if (arr[mid] == key) {
                    result = mid;
                    r = mid - 1;
                } else if (arr[mid] > key)
                    r = mid - 1;
                else
                    l = mid + 1;
            }
            return result;
        }
        public int[] threeEqualParts(int[] arr) {
            int n = arr.length;
            int[] prefixSum = arr.clone();
            for (int itr = 1; itr < n; ++itr) {
                prefixSum[itr] += prefixSum[itr - 1];
            }
            int countOne = prefixSum[n - 1];
            // edge cases
            if (countOne == 0)              // if all elements are zero
                return new int[]{0, n - 1};
            if (countOne % 3 != 0)          // if there are uneven 1s
                return new int[]{-1, -1};

            int averageOne = prefixSum[n - 1] / 3;  // number of ones in each part

            int i = lower_bound(prefixSum, 1);      // 1st occurrence of 1
            int j = lower_bound(prefixSum, averageOne + 1);
            int k = lower_bound(prefixSum, 2 * averageOne + 1);
/*
            // finding the same lower_bound() in different way
            int i = 0, j = 0, k = 0, count = 0;
            for (int itr = 0; itr < n; ++itr) {
                if (arr[itr] == 0) {
                    continue;
                }
                // 1st occurrence of 1
                if (count == 0) {
                    i = itr;
                }
                count++;
                if (count == averageOne + 1) {
                    j = itr;
                }
                if (count == 2 * averageOne + 1) {
                    k = itr;
                }
            }
*/

            // check whether all three partitions are equal or not
            while (k < n && arr[i] == arr[j] && arr[j] == arr[k]) {
                i++;
                j++;
                k++;
            }
            // if they are equal then k should reach end of the array "arr"
            if (k == n)
                return new int[]{i - 1, j};

            return new int[]{-1, -1};
        }
    }

    public static void print(Object... O) {
        System.out.println(Arrays.deepToString(O));
    }

    public static void main(String[] args) {
        int[] arr = {1,1,0,0,1};
        print(new Solution().threeEqualParts(arr));
    }
}
