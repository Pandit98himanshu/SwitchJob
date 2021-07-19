/*
 * Equal Subset Sum Partition : (0/1 Knapsack [Top-down approach])
 */
package educative.KnapsackPattern.EqualSubsetSumPartition;

class EqualSubsetSumPartitionTopDown {
    // method to find sum of an array
    public static int sumArray(int[] arr) {
        int sum = 0;
        for (int i : arr)
            sum += i;

        return sum;
    }

    public static boolean canPartition(int[] set) {
        int sum = sumArray(set);
        // we can't divide the given set into 2 subsets if sum of elements is odd
        if (sum % 2 != 0 || set.length == 0)
            return false;
        else {
            Boolean[][] dp = new Boolean[set.length][sum / 2 + 1];
            return canPartitionRecursive(set, dp, sum / 2, 0);
        }
    }

    private static boolean canPartitionRecursive(int[] set, Boolean[][] dp, int sum, int currentIndex) {
        // base case
        if (sum == 0)
            return true;
        // edge case
        if (sum < 0 || currentIndex >= set.length)
            return false;
        // if we haven't already processed a similar problem
        if (dp[currentIndex][sum] == null) {
            // recursive call after including the number at the currentIndex
            // if the number at currentIndex exceeds the sum, we shouldn't have processed this
            if (set[currentIndex] <= sum &&
                    canPartitionRecursive(set, dp, sum - set[currentIndex], currentIndex + 1)) {
                // mark as visited
                dp[currentIndex][sum] = true;
                return true;
            } else {
                // recursive call after excluding the number at the currentIndex
                dp[currentIndex][sum] = canPartitionRecursive(set, dp, sum, currentIndex + 1);
            }
        }
        // return already processed result
        return dp[currentIndex][sum];
    }

    public static void main(String[] args) {
        int[] set = {3, 3, 4, 6};

        System.out.println(canPartition(set));
    }
}