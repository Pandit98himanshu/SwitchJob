package leetcode;

import java.util.*;

/**
 * Created at : 10/09/21
 * <p>
 * <a href=https://leetcode.com/problems/find-median-from-data-stream/>295. Find Median from Data Stream</a>
 *
 * @author Himanshu Shekhar
 */


/**
 * Copied from <a href=https://leetcode.com/problems/find-median-from-data-stream/solution/>leetcode solution</a>
 * <p>Time Complexity: O(logN)
 * <br>Space Complexity: O(N)
 */
class MedianFinder {
    PriorityQueue<Integer> low, high;

    public MedianFinder() {
        low = new PriorityQueue<>((a, b) -> b - a); // max heap
        high = new PriorityQueue<>();               // min heap
    }

    public void addNum(int num) {
        low.add(num);                   // add to max heap
        high.add(low.poll());           // balancing step
        if (low.size() < high.size())   // maintaining size
            low.add(high.poll());
    }

    public double findMedian() {
        if (low.size() > high.size())
            return low.peek();

        return ((double) low.peek() + high.peek()) / 2.0;
    }
}

/**
 * <strong>Giving Wrong Answer</strong>
 * <p>Time Complexity: O(N)
 * <br>Space Complexity: O(N)
 */
class _MedianFinder1 {
    Vector<Integer> list;

    public _MedianFinder1() {
        list = new Vector<>();
    }

    public void addNum(int num) {
        if (list.isEmpty()) {
            list.add(num);
            return;
        }
        int pos = lower_bound(num);
        System.out.println(list);
        System.out.println("lower_bound for " + num + " = " + pos);
        if (pos < 0)
            pos = 0;
        list.add(pos, num);
    }

    public double findMedian() {
        int n = list.size();
        if (n % 2 == 0)
            return (Double.valueOf(list.get(n / 2 - 1)) + Double.valueOf(list.get(n / 2))) / 2;
        return Double.valueOf(list.get(n / 2));
    }

    private int lower_bound(int num) {
        int l = 0, r = list.size() - 1, ans = -1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (list.get(mid) == num) {
                ans = mid;
                r = mid - 1;
            } else if (list.get(mid) > num)
                r = mid - 1;
            else
                l = mid + 1;
        }
        return ans;
    }
}

public class FindMedianFromDataStream {
    public static void main(String[] args) {
        MedianFinder mf = new MedianFinder();
        mf.addNum(6);
        System.out.println(mf.findMedian());
        mf.addNum(10);
        System.out.println(mf.findMedian());
        mf.addNum(2);
        System.out.println(mf.findMedian());
        mf.addNum(6);
        System.out.println(mf.findMedian());
        mf.addNum(5);
        System.out.println(mf.findMedian());
        mf.addNum(0);
        System.out.println(mf.findMedian());
        mf.addNum(6);
        System.out.println(mf.findMedian());
        mf.addNum(3);
        System.out.println(mf.findMedian());
        mf.addNum(1);
        System.out.println(mf.findMedian());
        mf.addNum(0);
        System.out.println(mf.findMedian());
        mf.addNum(0);
        System.out.println(mf.findMedian());
    }
}
