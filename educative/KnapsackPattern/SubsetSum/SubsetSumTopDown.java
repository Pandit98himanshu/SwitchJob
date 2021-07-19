/*
 * Subset Sum : (0/1 Knapsack [Top-down approach])
 */

package educative.KnapsackPattern.SubsetSum;

class SubsetSumTopDown {
    // method to find sum of an array
    public static int sumArray(int[] arr) {
        int sum = 0;
        for (int i : arr)
            sum += i;

        return sum;
    }

    public static boolean canPartition(int[] num, int sum) {
        int sumNum = sumArray(num);
        // we can't find subset from the given set 'num',
        // if sum of elements of 'num' is less than given sum
        if (sumNum < sum || sum < 0 || num.length == 0)
            return false;
        else {
            Boolean[][] dp = new Boolean[num.length][sum + 1];
            return canPartitionRecursive(num, dp, sum, 0);
        }
    }

    private static boolean canPartitionRecursive(int[] num, Boolean[][] dp, int sum, int currentIndex) {
        // base case
        if (sum == 0)
            return true;
        // edge case
        if (sum < 0 || currentIndex >= num.length)
            return false;
        // if we haven't already processed a similar problem
        if (dp[currentIndex][sum] == null) {
            // recursive call after including the number at the currentIndex
            // if the number at currentIndex exceeds the sum, we shouldn't have processed this
            if (num[currentIndex] <= sum &&
                    canPartitionRecursive(num, dp, sum - num[currentIndex], currentIndex + 1)) {
                // mark as visited
                dp[currentIndex][sum] = true;
                return true;
            } else {
                // recursive call after excluding the number at the currentIndex
                dp[currentIndex][sum] = canPartitionRecursive(num, dp, sum, currentIndex + 1);
            }
        }
        // return already processed result
        return dp[currentIndex][sum];
    }

    public static void main(String[] args) {
        int[] num = {3, 3, 4, 6};
        int sum = 16;
        System.out.println(canPartition(num, sum));
    }
}