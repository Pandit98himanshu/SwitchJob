package leetcode;

/**
 * <a href=https://leetcode.com/problems/koko-eating-bananas/>875. Koko Eating Bananas</a>
 */

public class KokoEatingBananas {
    /**
     * @return max from array {@code piles}
     */
    public int findMax(int[] piles) {
        int max = Integer.MIN_VALUE;
        for (int pile : piles)
            max = Math.max(max, pile);
        return max;
    }

    /**
     * @return time taken to finish all {@code piles} of bananas if the rate of eating bananas is {@code k}
     */
    public int timeTaken(int[] piles, int k) {
        int time = 0;
        for (int bananas : piles) {
            time += (bananas - 1) / k + 1;  // we could use Math.ceil(bananas / (k * 1d));
            // but it takes a lot of runtime
        }
        return time;
    }

    /**
     * <strong>Binary Search</strong>
     * <p>She must eat all bananas
     *
     * @param piles amount of bananas
     * @param h     time taken by guards to come back (in hrs)
     * @return the minimum integer {@code k/hr} such that she can eat all the bananas within {@code h} hours
     */
    public int minEatingSpeed(int[] piles, int h) {
        int l = 1, r = findMax(piles), mid;

        while (l < r) {
            mid = (l + r) / 2;
            if (timeTaken(piles, mid) > h)
                l = mid + 1;
            else
                r = mid;
        }
        return r;
    }

    public static void main(String[] args) {
        int[] piles = {1000000000};//, 1000000000};
        int h = 2;
        System.out.println(new KokoEatingBananas().minEatingSpeed(piles, h));
    }
}
