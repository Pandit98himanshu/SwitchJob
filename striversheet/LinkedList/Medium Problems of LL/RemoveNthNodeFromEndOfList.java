/**
 * 19. Remove Nth Node From End of List
 * https://leetcode.com/problems/remove-nth-node-from-end-of-list
 */


// Definition for singly-linked list.
public class ListNode {
	int val;
	ListNode next;
	ListNode() {}
	ListNode(int val) { this.val = val; }
	ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 }
 
class Solution {
	/*
	 * TC: O(n)
	 * SC: O(1)
	 * Create 2 nodes & makes a diff of n in b/w them
	 * move both nodes simultaneously until last node reaches end
	 * the next of the prior node is to be deleted.
	 */
	public ListNode removeNthFromEnd(ListNode head, int n) {
		ListNode last = head;
		while (n-- > 0)
			last = last.next;
		if (last == null)
			return head.next;

		ListNode delNext = head;
		while (last.next != null) {
			last = last.next;
			delNext = delNext.next;
		}
		delNext.next = delNext.next.next;
		return head;
	}
}

public class RemoveNthNodeFromEndOfList {
	
}
