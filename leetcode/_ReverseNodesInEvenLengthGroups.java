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
        head.printList(head);
    }

    private static ListNode reverseLL(ListNode curr, int size) {
        ListNode last = curr, next = curr.next, nNext = next.next;
        for (int i = 0; i < size; i++) {
            next.next = curr;
            curr = next;
            next = nNext;
            nNext = nNext.next;
        }
        last.next = nNext;
        return curr;
    }

    public ListNode reverseEvenLengthGroups(ListNode head) {
        ListNode p = head;
        for (int i = 1; p.next != null; i++) {
            if (i % 2 != 0) {
                for (int j = 0; j < i - 1; j++) {
                    if (p == null) return head;
                    p = p.next;
                }
            } else {
                ListNode l = p.next, f = p.next;
                int size = 0;
                for (int j = 0; j < i; j++) {
                    if (f == null) {
                        if (size % 2 == 0) break;
                        else return head;
                    }
                    f = f.next;
                    size++;
                }
                System.out.println("prev = " + p.val + " curr = " + p.next.val);
                p.next = reverseLL(p.next, size);
                p = l;
            }
        }
        return head;
    }
}
