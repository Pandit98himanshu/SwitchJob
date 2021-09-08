/*
 * 206. Reverse Linked List
 * https://leetcode.com/problems/reverse-linked-list/
 */

package leetcode;

import datastructure.ListNode;

public class ReverseLinkedList {
    /**
     * <p>Time Complexity: O(n); n = number of nodes
     * <br>Space Complexity: O(1); doing everything in-place
     * @return reversed linked list
     */
    public ListNode reverseList(ListNode head) {
        // edge cases
        if (head == null || head.next == null)
            return head;
        // "next" always points to head of sublist
        ListNode next = head.next;
        ListNode nextHead = next.next;

        head.next = null;       // head will become last node after reversing
        while (nextHead != null) {
            next.next = head;   // start reversing from 2nd node
            head = next;
            next = nextHead;
            nextHead = nextHead.next;
        }
        // finally, add last node at front
        next.next = head;
        head = next;

        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);

        head.printList(new ReverseLinkedList().reverseList(head));
    }
}
