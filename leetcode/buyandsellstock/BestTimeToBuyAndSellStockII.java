/*
 * 122. Best Time to Buy and Sell Stock II
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
 */

package leetcode.buyandsellstock;

public class BestTimeToBuyAndSellStockII {
    public int maxProfit1(int[] prices) {
        int cost = prices[0];
        int currProfit = 0, maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            // if selling at current day makes me loss then
            // sell the stock on previous day and buy a new stock today
            if (prices[i] - cost < currProfit) {
                cost = prices[i];
                maxProfit += currProfit;
                currProfit = 0;
            }
            else
                // sell the stock today but don't buy another today,
                // maybe next day we'll get better price
                currProfit = prices[i] - cost;
        }
        maxProfit += currProfit;
        return maxProfit;
    }
}
