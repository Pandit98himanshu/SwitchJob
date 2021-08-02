/*
 * 25. Reverse Nodes in k-Group
 * https://leetcode.com/problems/reverse-nodes-in-k-group/
 */

package leetcode;

public class ReverseNodesInKGroup {

    /**
     * Definition for singly-linked list.
     */
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
            this.next = null;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    static class Solution {
        /**
         * Reverse the nodes of a linked list k at a time.
         *
         * @param head points to the 1st element of the linked list
         * @param k    group size
         */
        public ListNode reverseKGroup(ListNode head, int k) {
            // edge case
            if (head == null || k <= 1) return head;

            int count = 0;                          // keeps count of size of group
            ListNode dummyHead = new ListNode();
            ListNode n = dummyHead;
            ListNode currHead = head;               // starting node of the current group
            while (head != null) {
                count++;                            // increase group size until we reach k
                if (count == k) {
                    ListNode nextGroup = head.next;
                    head.next = null;               // break the link (separate the group)
                    n.next = reverseLL(currHead);   // reverse and rejoin the link
                    n = currHead;
                    currHead = nextGroup;
                    head = nextGroup;       // proceed with next group
                    count = 0;              // reset counter to zero
                } else
                    head = head.next;       // move the head to end of the current group of size k
            }
            // merge the last part with group size < k
            if (count < k)
                n.next = currHead;

            return dummyHead.next;
        }

        /**
         * Reverses a singly-linked-list
         *
         * @param head points to the 1st element of the linked list
         */
        public ListNode reverseLL(ListNode head) {
            ListNode dummyHead = head;
            ListNode next = head.next;
            while (head.next != null) {
                head.next = next.next;
                next.next = dummyHead;
                dummyHead = next;
                next = head.next;
            }
            return dummyHead;
        }
    }

    /**
     * Prints all values of the linked list from head to the end
     */
    public static void printLL(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1,
                new ListNode(2,
                        new ListNode(3,
                                new ListNode(4,
                                        new ListNode(5,
                                                new ListNode(6))))));

        int k = 4;
//        printLL(new Solution().reverseLL(head));
        printLL(new Solution().reverseKGroup(head, k));
    }
}
