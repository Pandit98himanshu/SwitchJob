/*
 * 121. Best Time to Buy and Sell Stock
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 */

package leetcode.buyandsellstock;

public class BestTimeToBuyAndSellStock {
    /**
     * Copied from <a href=https://leetcode.com/problems/best-time-to-buy-and-sell-stock/solution/>leetcode solution</a>
     * @see <a href=https://leetcode.com/problems/best-time-to-buy-and-sell-stock/discuss/39038/Kadane's-Algorithm-Since-no-one-has-mentioned-about-this-so-far-:)-(In-case-if-interviewer-twists-the-input)>leetcode discuss</a>
     */
    public int maxProfit2(int prices[]) {
        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minprice)
                minprice = prices[i];
            else if (prices[i] - minprice > maxprofit)
                maxprofit = prices[i] - minprice;
        }
        return maxprofit;
    }

    public int maxProfit1(int[] prices) {
        int n = prices.length;
        int max = prices[n-1], ans = 0;
        // if we buy on last day and
        // sell on last day, we'll get 0 profit
        prices[n-1] = 0;

        for (int i = n - 2; i >= 0; i--) {
            // stock is greater today or in future
            max = Math.max(max, prices[i]);
            // prices will now store max profits.
            // the profit we get if we are selling current day stock
            // when stock will be highest in future
            prices[i] = max - prices[i];
            // store max profit
            ans = Math.max(ans, prices[i]);
        }
        return ans;
    }
}
