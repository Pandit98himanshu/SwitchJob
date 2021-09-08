/*
 * 23. Merge k Sorted Lists
 * https://leetcode.com/problems/merge-k-sorted-lists/
 */

package leetcode;

import java.util.*;

public class MergeKSortedLists {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * <strong>Divide and Conquer</strong>
     * <p>Copied from <a href=https://leetcode.com/submissions/detail/541445047/>leetcode submissions</a>
     */
    public ListNode mergeKLists2(ListNode[] lists) {
        int k = lists.length;
        if (k == 0)
            return null;

        return mergeKLists(lists, 0, k - 1);
    }

    public ListNode mergeKLists(ListNode[] lists, int start, int end) {
        if (end == start)
            return lists[start];

        int mid = start + ((end - start) / 2);
        ListNode listA = mergeKLists(lists, start, mid);
        ListNode listB = mergeKLists(lists, mid + 1, end);

        return mergeLists(listA, listB);
    }

    // merge 2 sorted lists
    public ListNode mergeLists(ListNode l1, ListNode l2) {
        ListNode h = new ListNode(0);
        ListNode ans = h;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                h.next = l1;
                h = h.next;
                l1 = l1.next;
            } else {
                h.next = l2;
                h = h.next;
                l2 = l2.next;
            }
        }
        if (l1 == null) {
            h.next = l2;
        }
        if (l2 == null) {
            h.next = l1;
        }
        return ans.next;
    }

    /**
     * <strong>Min-Heap</strong>
     * <p>Copied from <a href=https://leetcode.com/submissions/detail/541445047/>leetcode submissions</a>
     * <p>Time Complexity: O(n*log(k)); where k = lists.length
     * <br>Space Complexity: O(n); where n = number of nodes
     */
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.val, b.val));
        // store 1st element of all k lists in a min-heap
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null)
                pq.offer(lists[i]);
        }
        ListNode res = new ListNode(-1);        // stores result
        ListNode currRes = res;                     // iterator for result list
        while (!pq.isEmpty()) {
            ListNode current = pq.poll();           // bring minimum element
            currRes.next = new ListNode(current.val);   // add min element into result array
            currRes = currRes.next;                 // move iterator of result list forward
            if (current.next != null)
                pq.offer(current.next);             // put next element from that list into min-heap
        }
        return res.next;
    }

    /**
     * <strong>Linear Search</strong>
     * <p>Time Complexity: O(k * n); where k = lists.length, n = longest list size
     * <br>Space Complexity: O(1)
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
