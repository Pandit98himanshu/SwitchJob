package leetcode;

import java.util.*;

public class _KClosestPointsToOrigin {

    /**
     * <strong>Quick Sort</strong>
     * <p>Since we can return the closest points <b>in any order</b>,
     * we can use quick sort to solve the problem
     * <p>Time Complexity: O(n)
     */
    private int partition(int[][] points, int l, int r) {
        int[] pivot = points[l];        // pick 1st element at pivot
        while (l < r) {
            while (l < r && compare(points[r], pivot) >= 0)
                r--;
            points[l] = points[r];  // points[r]'s distance < pivot's distance from origin

            while (l < r && compare(points[l], pivot) <= 0)
                l++;
            points[r] = points[l];
        }
        points[l] = pivot;      // place pivot at correct position
        return l;               // return pivot index
    }

    private int compare(int[] p1, int[] p2) {
        return distSquared(p1) - distSquared(p2);
    }

    public int[][] kClosest(int[][] points, int k) {
        int l = 0, r = points.length - 1;
        while (l <= r) {
            int pivot = partition(points, l, r);
            if (pivot == k)
                break;
            // num of ele on left of pivot < k,
            // partition more from right side
            else if (pivot < k)
                l = pivot + 1;
            else
                r = pivot - 1;
        }
        return Arrays.copyOfRange(points, 0, k);
    }

    /**
     * <strong>PriorityQueue</strong>
     * <p>Time Complexity: O(n(log(n)))
     *
     * @param points given coordinates
     * @param k      number of the closest points
     * @return k closest points to the origin (0, 0)
     */
    public int[][] kClosest1(int[][] points, int k) {
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> distSquared(b) - distSquared(a));
        for (int[] point : points) {
            maxHeap.add(point);
            if (maxHeap.size() > k)
                maxHeap.poll();
        }

        print(maxHeap);

        int[][] ans = new int[k][2];
        for (int i = 0; i < k; i++)
            ans[i] = maxHeap.poll();

        return ans;
    }

    private int distSquared(int[] p) {
        return p[0] * p[0] + p[1] * p[1];
    }

    private void print(PriorityQueue<int[]> pq) {
        pq.forEach(i -> System.out.print("(" + i[0] + "," + i[1] + ") "));
    }
}
