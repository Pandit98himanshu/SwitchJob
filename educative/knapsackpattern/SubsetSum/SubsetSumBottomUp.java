/*
 * Subset Sum : (0/1 Knapsack [Bottom-up approach])
 */

package educative.knapsackpattern.SubsetSum;

class SubsetSumBottomUp {
    public static int sumArray(int[] arr) {
        int sum = 0;
        for (int i : arr)
            sum += i;

        return sum;
    }

    public static boolean canPartition(int[] num, int sum) {
        int sumNum = sumArray(num);
        // we can't find subset of given num array,
        // if sum of elements of num array is less than given sum
        if (sumNum < sum || sum < 0 || num.length == 0)
            return false;
        else {
            boolean[][] dp = new boolean[num.length][sum + 1];
            // we can always have '0' sum without including any element
            for (int i = 0; i < num.length; i++)
                dp[i][0] = true;
            // with only one number, we can form a subset only when
            // the required sum is equal to its value i.e., j == num[0]
            for (int j = 1; j <= sum; j++)
                dp[0][j] = (j == num[0]) ? true : false;

            for (int i = 1; i < num.length; i++)
                for (int j = 1; j <= sum; j++)
                    if (dp[i - 1][j])
                        dp[i][j] = dp[i - 1][j];            // excluding current element
                    else if (num[i] <= j)
                        dp[i][j] = dp[i - 1][j - num[i]];    // including current element

            return dp[num.length - 1][sum];
        }
    }

    public static void main(String[] args) {
        int[] num = {3, 3, 4, 6};
        int sum = -17;
        System.out.println(canPartition(num, sum));
    }
}