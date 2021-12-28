package gfg;

/**
 * Created at : 21/12/21
 * <p>
 * <a href=https://www.geeksforgeeks.org/subset-sum-problem-dp-25/>Subset Sum</a>
 *
 * @author Himanshu Shekhar
 */

public class SubsetSum {
    /**
     * <strong>Dynamic Programming</strong>
     * <p>Time Complexity: O(n * sum)
     * <br>Space Complexity: O(sum)
     */
    static boolean isSubsetSumDP(int set[], int n, int sum) {
        boolean[] dp = new boolean[sum+1];
        dp[0] = true;
        for (int i = 1; i <= sum; i++) {
            for (int j : set) {
                dp[i] = (i - j >= 0) ? (dp[i] || dp[i - j]) : dp[i];
            }
        }
        return dp[sum];
    }

    /**
     * <strong>Recursion</strong>
     * <p>Time Complexity: O(2<sup>n</sup>)
     * <br>Space Complexity: O(2<sup>n</sup>)
     */
    static boolean isSubsetSumRecur(int set[], int n, int sum) {
        if (n < 0)
            return false;
        if (sum == 0)
            return true;
        boolean inc = isSubsetSumRecur(set, n - 1, sum - set[n]);
        boolean exc = isSubsetSumRecur(set, n - 1, sum);

        return inc || exc;
    }

    public static void main(String[] args) {
        int set[] = {3, 34, 4, 12, 5, 2};
        int sum = 46;
        System.out.println(isSubsetSumDP(set, set.length - 1, sum));
    }
}
