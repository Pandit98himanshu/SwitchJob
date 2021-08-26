/*
 * Subset Sum - Brute Force
 */

package educative.knapsackpattern.SubsetSum;

class PartitionSet {

    static boolean canPartition(int[] num, int sum) {
        // finding sum of given set
        int sumNum = 0;
        for (int i : num)
            sumNum += i;
        // if sum of given set is less than given sum, we can't find subset
        if (sumNum < sum || sum < 0 || num.length == 0)
            return false;

        return canPartitionUtil(num, sum, 0);
    }

    // construct binary including-excluding tree, to understand properly
    private static boolean canPartitionUtil(int[] num, int sum, int currentIndex) {
        // base case
        if (sum == 0)
            return true;
            // edge case
        else if (sum < 0 || currentIndex >= num.length)
            return false;
        // subtracting every value form num array in all possible combinations
        // (i.e., making all possible subsets) and find whether sum of subset
        // is equal to given sum
        return canPartitionUtil(num, sum - num[currentIndex], currentIndex + 1)        // including current element
                || canPartitionUtil(num, sum, currentIndex + 1);                // excluding current element
    }
}

class SubsetSumBruteForce {
    public static void main(String[] args) {
        int[] set = {1, 2, 3, 4};
        int sum = 10;
        System.out.println(new PartitionSet().canPartition(set, sum));
    }
}