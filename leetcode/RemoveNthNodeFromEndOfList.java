package leetcode;

import datastructure.ListNode;

/**
 * Created at : 05/10/21
 * <p>
 * <a href=https://leetcode.com/problems/remove-nth-node-from-end-of-list/>19. Remove Nth Node From End of List</a>
 *
 * @author Himanshu Shekhar
 */

public class RemoveNthNodeFromEndOfList {
    /**
     * <strong>One-pass solution</strong>
     * <p>Time Complexity: O(n); n = length of the given list
     * <br>Space Complexity: O(1)
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // edge case
        if (head == null)
            return head;
        // move n steps forward
        int n1 = n;
        int len = 1;    // calculates length of the list
        ListNode end = head;
        while (n1-- > 0 && end.next != null) {
            end = end.next;
            len++;
        }
        // we can't remove the node which is not in the list
        if (n1 > 0)
            return null;
        // reach one node previous to the deleting node
        ListNode lastN = head;
        while (end.next != null) {
            end = end.next;
            lastN = lastN.next;
            len++;
        }
        // removing the head node
        if (len == n)
            return head.next;
        // removing node which is not the head.
        lastN.next = lastN.next.next;

        return head;
    }
}
