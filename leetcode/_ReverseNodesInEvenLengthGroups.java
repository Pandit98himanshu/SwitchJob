package leetcode;

import datastructure.ListNode;

/**
 * Created at : 16/11/21
 * <p>
 * <a href=https://leetcode.com/problems/reverse-nodes-in-even-length-groups/>2074. Reverse Nodes in Even Length Groups</a>
 *
 * @author Himanshu Shekhar
 */

public class _ReverseNodesInEvenLengthGroups {
    public static void main(String[] args) {
        ListNode head = new ListNode(5);
        head.next = new ListNode(2);
        head.next.next = new ListNode(6);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(9);
        head.next.next.next.next.next = new ListNode(1);
        head.next.next.next.next.next.next = new ListNode(7);
        head.next.next.next.next.next.next.next = new ListNode(3);
        head.next.next.next.next.next.next.next.next = new ListNode(8);
        head.next.next.next.next.next.next.next.next.next = new ListNode(4);

//        int[] nums = {5,2,6,3,9,1,7,3,8,4};
//        ListNode head2 = new ListNode();
//        head2.buildList(head2, nums);

        head.printList(head);
        new _ReverseNodesInEvenLengthGroups().reverseEvenLengthGroups(head);
//        head.printList(head);
    }

    /**
     * Reverses singly-linked-list starting from node {@code curr} till length {@code size}
     */
    private static void reverseLL(ListNode curr, int size) {
        ListNode last = curr, next = curr.next, nNext = next.next;
        while (size-- > 1) {
            next.next = curr;
            curr = next;
            next = nNext;
            if (nNext.next == null)
                break;
            nNext = nNext.next;
            System.out.println("curr = " + curr.val + ", next = " + next.val + ", nNext = " + nNext.val + ", last = " + last.val);
        }
        last.next = next;
    }

    /**
     * <strong>Wrong answer</strong>
     */
    public ListNode reverseEvenLengthGroups(ListNode head) {
        ListNode prev = head;
        for (int len = 1; prev.next != null; len++) {
            // if length of current group is odd
            // skip this group
            if (len % 2 != 0) {
                for (int i = 1; i < len; i++) {
                    if (prev == null)
                        return head;
                    prev = prev.next;
                }
            } else {
                ListNode l = prev.next;    // last element in current group will be first after reversing
                ListNode f = prev.next;    // first element in current group will be last after reversing
                int size = 1;           // find length of current group
                for (int i = 1; i < len; i++) {
                    size++;
                    f = f.next;
                    if (f == null) {    // we reached last element of the given linked list
                        // if length of current group is odd
                        // then we should not reverse this
                        // (this must be last group)
                        if (size % 2 != 0)
                            return head;
                        else
                            break;
                    }
                }
                System.out.println("size = " + size + ", prev = " + prev.val + ", curr = " + prev.next.val + ", l = " + l.val);
                reverseLL(prev.next, size);
                prev.next = f;
                head.printList(head);
                prev = l.next;
            }
        }
        return head;
    }
}
