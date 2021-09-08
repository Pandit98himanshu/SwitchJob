/*
 * 322. Coin Change
 * https://leetcode.com/problems/coin-change/
 */

package leetcode;

import java.util.*;

public class CoinChange {
    /**
     * <strong>Dynamic Programming</strong> : 10 ms
     * <p>Time Complexity: O(n * amount)
     * <br>Space Complexity: O(amount)
     * @see <a href=https://leetcode.com/problems/coin-change/discuss/77360/C++-O(n*amount)-time-O(amount)-space-DP-solution>leetcode discuss</a>
     */
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        // for coin "0" we need "0" coins
        // initially, consider it is impossible to get change
        for (int i = 1; i <= amount; i++)
            dp[i] = Integer.MAX_VALUE - 1;

        // add coins one-by-one into denominations list
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }
        if (dp[amount] == Integer.MAX_VALUE - 1)
            return -1;

        return dp[amount];
    }

    /**
     * <strong>Dynamic Programming</strong> : 1000 ms
     * <p>Time Complexity: O(n * amount<sup>2</sup>)
     * <br>Space Complexity: O(n * amount)
     */
    public int coinChange1(int[] coins, int amount) {
        final int inf = Integer.MAX_VALUE;      // represents particular amount is not possible for given denominations
        Arrays.sort(coins);                     // sort the array, otherwise we'll get wrong answers
        int n = coins.length;
        int[][] dp = new int[n][amount + 1];

        for (int i = 0; i < n; i++) {           // add coins one-by-one into coins list
            for (int amt = 1; amt <= amount; amt++) { // check for all amounts i.e., from 1 to "amount"
                dp[i][amt] = inf;               // initially consider it is impossible to get change for current amount
                if (amt % coins[i] == 0) {
                    // current amount can get simply by using ith coin,
                    // coins are sorted, so we'll get min value here
                    dp[i][amt] = amt / coins[i];
                    continue;
                }
                int coinI = amt / coins[i];
                // check how many coins[i] is required to get change
                // check from max number of coins[i] to "0"
                while (coinI >= 0) {
                    int rem = (i - 1 < 0) ? inf : dp[i - 1][amt - (coinI * coins[i])];
                    // if we've no change(coin) for remainder, we can't get current amount
                    // the answer we got previous was minimum or current result is minimum
                    dp[i][amt] = Math.min(dp[i][amt], (rem == inf) ? inf : (coinI + rem));
                    coinI--;    // check whether we can get change by reducing number of current coin
                }
            }
        }
        int ans = dp[n - 1][amount];
        return ans == inf ? -1 : ans;
    }

    public static void main(String[] args) {
        int[] coins = {3, 5, 7};
        int amount = 11;
        System.out.println(new CoinChange().coinChange(coins, amount));
    }
}
