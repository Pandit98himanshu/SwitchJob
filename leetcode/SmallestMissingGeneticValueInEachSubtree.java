package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created at : 25/09/21
 * <p>
 * <a href=https://leetcode.com/problems/smallest-missing-genetic-value-in-each-subtree/>2003. Smallest Missing Genetic Value in Each Subtree</a>
 *
 * @author Himanshu Shekhar
 */

public class SmallestMissingGeneticValueInEachSubtree {
    private int[] ans;
    private List<Integer>[] adjList;

    private HashSet<Integer> helper(int[] nums, int i) {
        HashSet<Integer> visited = new HashSet<>();

        // traverse all children and merge all visited nodes' value
        if (adjList[i] != null)
            for (int child : adjList[i])
                visited.addAll(helper(nums, child));

        // add current value to the visited set
        visited.add(nums[i]);

        // find smallest missing genetic value
        for (int num = 1; num < (int) 10e5 + 1; num++) {
            if (!visited.contains(num)) {
                ans[i] = num;   // fill the answer and,
                break;          // come out of the loop
            }
        }

        return visited;
    }

    public int[] smallestMissingValueSubtree(int[] parents, int[] nums) {
        int n = parents.length;
        adjList = new ArrayList[n];
        ans = new int[n];
        // create adjacency list from parents array
        for (int i = 1; i < parents.length; i++) {
            List<Integer> children = adjList[parents[i]];
            if (children == null)
                children = new ArrayList<>();
            children.add(i);
        }

        helper(nums, 0);
        return ans;
    }
}
