/**
 * <a href=https://leetcode.com/problems/range-sum-query-mutable/>307. Range Sum Query - Mutable</a>
 *
 * @author Pandit Himanshu
 */

public class _SegmentTree {
	int n;
    int[] segTree;
    int[] nums;
    
    /**
     * O(2 * n)
     */
    private int buildSegTree(int l, int r, int i) {
		// end-node
        if (l == r) {
            segTree[i] = nums[l];
            return segTree[i];
        }
        
        int mid = (l + r)/2;
        
        int leftSum = buildSegTree(l, mid, 2*i + 1);
        int rightSum = buildSegTree(mid + 1, r, 2*i + 2);
        
        return segTree[i] = leftSum + rightSum;
    }
    
    /**
     * O(log n)
     */
    private void updateSegTree(int index, int diff, int l, int r, int i) {

        if (index < l || index > r) {
            return;
        }
        segTree[i] += diff;
        
        if (l != r) {
            int mid = (l + r)/2;

            updateSegTree(index, diff, l, mid, 2*i + 1);
            updateSegTree(index, diff, mid + 1, r, 2*i + 2);
        }
    }
    
    /**
     * O(log n)
     */
    private int querySegTree(int queryLeft, int queryRight, int l, int r, int i) {
        
        if (r < queryLeft || l > queryRight)
            return 0;
        
        if (queryLeft <= l && r <= queryRight)
            return segTree[i];
        
        int mid = (l + r)/2;
        int leftSum = querySegTree(queryLeft, queryRight, l, mid, 2*i + 1);
        int rightSum = querySegTree(queryLeft, queryRight, mid + 1, r, 2*i + 2);
        
        return leftSum + rightSum;
    }
    
    public SegmentTree(int[] nums) {
        this.n = nums.length;
        this.nums = nums;
        
        //Height of segment tree
        int x = (int) (Math.ceil(Math.log(n) / Math.log(2)));

        //Maximum size of segment tree
        int maxSize = 2 * (int) Math.pow(2, x) - 1;
        
        segTree = new int[maxSize];
        
        buildSegTree(0, n - 1, 0);
    }
    
    public void update(int index, int val) {
        int diff = val - nums[index];
		// update in original array
        nums[index] = val;
		// update values in segment tree
        updateSegTree(index, diff, 0, n - 1, 0);
    }
    
    public int sumRange(int left, int right) {
        return querySegTree(left, right, 0, n-1, 0);
    }
}
