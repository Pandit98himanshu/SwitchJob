package leetcode;

import java.util.Arrays;

/**
 * Created at : 17/11/21
 * <p>
 * <a href=https://leetcode.com/problems/minimized-maximum-of-products-distributed-to-any-store/>2064. Minimized Maximum of Products Distributed to Any Store</a>
 *
 * @author Himanshu Shekhar
 */

public class MinimizedMaximumOfProductsDistributedToAnyStore {
    public static void main(String[] args) {
        int n = 22;
        int[] quantities = {25, 11, 29, 6, 24, 4, 29, 18, 6, 13, 25, 30};

        System.out.println(new MinimizedMaximumOfProductsDistributedToAnyStore().minimizedMaximum(n, quantities));
    }

    /**
     * <strong>Binary Search</strong>
     * <p>Minimum we can distribute items to any store is {@code 1} in quantity
     * and maximum is the {@code max} value of {@code quantities} array
     */
    public int minimizedMaximum(int n, int[] quantities) {
        // finding max element in given array
        int max = (int) Arrays.stream(quantities).max().getAsInt();
        // if number of items is equal to number of stores
        // we have to distribute 1 type of item to each store
        if (quantities.length == n)
            return max;
        // our range starts from 1 to maximum element of given array
        int l = 1, h = max;
        while (l <= h) {
            int mid = l + (h - l) / 2;
            // if we can distribute items to more stores than provided i.e. (> n)
            // it means we distributed in less quantity of items,
            // and hence we should distribute more, therefore, increase "l"
            // otherwise, decrease "h"
            if (distributedMore(quantities, n, mid)) {
                l = mid + 1;
            } else {
                h = mid - 1;
            }
        }
        // return lower-bound
        return l;
    }

    /**
     * distribute maximum {@code i} quantity of items to a store
     */
    private boolean distributedMore(int[] nums, int totalStores, int i) {
        int requiredStores = 0;
        for (int x : nums) {
            // if we distribute at-max "i" number of items to any store, then
            // the number of stores required to distribute all type of items
            requiredStores += (int) Math.ceil(x * 1.0 / i);
        }
        return totalStores < requiredStores;
    }
}
