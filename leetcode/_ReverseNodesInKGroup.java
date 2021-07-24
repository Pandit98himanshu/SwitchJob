/*
 * 25. Reverse Nodes in k-Group
 * https://leetcode.com/problems/reverse-nodes-in-k-group/
 */

package leetcode;

import java.util.ArrayList;

public class _ReverseNodesInKGroup {

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
         * <p><strong>NOT WORKING</strong></p>
         */
        public ListNode reverseKGroup1(ListNode head, int k) {
            if (k == 1) return head;
            ListNode start = head, last = head, i = head, j;
            ArrayList<ListNode> possibleHeads = new ArrayList<>();
            possibleHeads.add(head);
            boolean isAltered = false;

            while (start != null) {
                int len = 1;
                // move last to kth node of the current group
                while (last != null && len != k) {
                    last = last.next;
                    len++;
                }
                // total number of nodes in linked list is not a
                // multiple of k leave remaining nodes as it is
                if (len < k) {
                    if (isAltered)
                        return possibleHeads.get(1);
                    else
                        return possibleHeads.get(0);
                }

                // reverse k nodes
                j = i.next;
                start = i;
                while (j != null && last != null && j != last.next) {
                    i.next = last.next;
                    last.next = i;
                    i = j;
                    j = j.next;
                    isAltered = true;
                }
                possibleHeads.add(last);
                i = start.next;      // starting node of next group
                last = start.next;
            }
            return possibleHeads.get(0);
        }

        /**
         * Reverse the nodes of a linked list k at a time.
         *
         * @param head pointer to a first node of the linked list
         * @param k    group size
         */
        public ListNode reverseKGroup(ListNode head, int k) {
            if (head == null || k <= 1) return head;
            ListNode dummy = new ListNode(-1, head);
            reverseKGroupUtil(dummy, head, k);
            return dummy.next;
        }

        public void reverseKGroupUtil(ListNode parent, ListNode start, int k) {
            ListNode last = group(start, k);

            if (last != null) {
                ListNode nextGroup = last.next;
                ListNode temp = start;
                reverseLL(start, last);
                last.next = nextGroup;
                parent.next = last;
                start = nextGroup;
                reverseKGroupUtil(parent, nextGroup, k);
            }
        }

        /**
         * @param start head pointer
         * @param size  size of the group
         * @return last node in a group of size {@code size}
         */
        public ListNode group(ListNode start, int size) {
            int len = 1;
            while (start != null && len != size) {
                start = start.next;
                len++;
            }

            if (len == size) {
                return start;
            }
            return null;
        }

        public void reverseLL(ListNode start, ListNode end) {
            ListNode next = start.next;
            while (next != null) {
                end.next = start;
                start.next = null;
                start = next;
                next = next.next;
            }
        }
    }

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

        int k = 2;
        printLL(new Solution().reverseKGroup(head, k));
    }
}
