package leetcode;

import datastructure.ListNode;

import java.util.PriorityQueue;

/**
 * Created at : 29/09/21
 * <p>
 * <a href=https://leetcode.com/problems/merge-k-sorted-lists/>23. Merge k Sorted Lists</a>
 *
 * @author Himanshu Shekhar
 */

public class MergeKSortedLists {
    /**
     * <strong>Conquer</strong>
     * <p>Time Complexity: O(n)
     * <br>Space Complexity: O(k) : 40.8 MB
     *
     * @see <a href=https://leetcode.com/submissions/detail/541445047/>leetcode submissions</a>
     */
    public ListNode mergeKLists2(ListNode[] lists) {
        int k = lists.length;
        // edge case
        if (k == 0)
            return null;
        /*
        // Runtime : 114 ms
        for (int i = k-2; i >= 0; i--) {
            lists[i] = merge2Lists(lists[i], lists[i+1]);
        }
        */

        // Runtime : 1 ms
        while (k > 1) {
            int i = 0;
            for (int j = 0; j < k; j += 2) {
                if (j == k - 1)
                    lists[i] = lists[j];
                else
                    lists[i] = merge2Lists(lists[j], lists[j + 1]);
                i++;
            }
            k = i;
        }

        return lists[0];
    }

    // merge 2 sorted lists in-place
    public ListNode merge2Lists(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode curr = dummyHead;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                curr.next = l1;
                curr = curr.next;
                l1 = l1.next;
            } else {
                curr.next = l2;
                curr = curr.next;
                l2 = l2.next;
            }
        }
        // append remaining nodes
        if (l1 == null) {
            curr.next = l2;
        }
        if (l2 == null) {
            curr.next = l1;
        }
        return dummyHead.next;
    }

    /**
     * <strong>Min-Heap</strong>
     * <p>Copied from <a href=https://leetcode.com/submissions/detail/541445047/>leetcode submissions</a>
     * <p>Time Complexity: O(n*log(k)); where k = lists.length, n = number of nodes
     * <br>Space Complexity: O(k);
     */
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> a.val - b.val);
        // store 1st element of all k lists in a min-heap
        for (ListNode node : lists) {
            if (node != null)
                pq.offer(node);
        }
        ListNode res = new ListNode();              // stores result
        ListNode curr = res;                        // iterator for result list
        while (!pq.isEmpty()) {
            ListNode temp = pq.poll();              // bring minimum element
            curr.next = new ListNode(temp.val);     // add min element into result array
            curr = curr.next;                       // move iterator of result list forward
            if (temp.next != null)
                pq.offer(temp.next);                // put next element from that list into min-heap
        }
        return res.next;
    }

    /**
     * <strong>Linear Search</strong>
     * <p>Time Complexity: O(k * n); where k = lists.length, n = number of values in all lists
     * <br>Space Complexity: O(n)
     *
     * @return merged linked list of K sorted lists
     */
    public ListNode mergeKLists1(ListNode[] lists) {
        ListNode res = new ListNode();
        ListNode currRes = res;

        while (true) {
            // check whether any element is left in the lists
            boolean finished = true;
            for (ListNode curr : lists)
                if (curr != null)
                    finished = false;
            // if none are left then we return the result
            if (finished)
                return res.next;

            // linear search over 1st element in lists and find min element
            // since all individual lists are sorted
            int min = Integer.MAX_VALUE, minIndex = 0;
            for (int i = 0; i < lists.length; i++) {
                if (lists[i] != null && min > lists[i].val) {
                    min = lists[i].val;
                    minIndex = i;
                }
            }
            // pluck out min value node and add it to res list
            currRes.next = lists[minIndex];
            ListNode prev = lists[minIndex];
            lists[minIndex] = lists[minIndex].next;
            prev.next = null;
            currRes = prev;
        }
    }

    public static void main(String[] args) {
        ListNode[] lists = new ListNode[3];
        lists[0] = new ListNode(1, new ListNode(4, new ListNode(5)));
        lists[1] = new ListNode(1, new ListNode(3, new ListNode(4, new ListNode(9))));
        lists[2] = new ListNode(2, new ListNode(6));

        ListNode mergedList = new MergeKSortedLists().mergeKLists(lists);
        while (mergedList != null) {
            System.out.print(mergedList.val + " ");
            mergedList = mergedList.next;
        }
    }
}
