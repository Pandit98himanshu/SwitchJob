/**
 * 328. Odd Even Linked List
 * https://leetcode.com/problems/odd-even-linked-list
 */

// Definition for singly-linked list.
class ListNode {
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
	 * Detach all odd position nodes to a separate ll,
	 * then attach it to the end of original ll
	 */
	public ListNode oddEvenList(ListNode head) {
		if (head == null || head.next == null)
			return head;
		
		ListNode even = head;
		ListNode odd = head.next;
		ListNode oddhead = head.next;
		while (odd != null && odd.next != null) {
			even.next = even.next.next;
			even = even.next;
			
			odd.next = odd.next.next;
			odd = odd.next;
		}
		even.next = oddhead;
		return head;
	}
	
	/*
	 * TC: O(2n)
	 * SC: O(1)
	 * Moving all odd position nodes at the end of the linked list
	 */
	public ListNode oddEvenList1(ListNode head) {
		if (head == null || head.next == null)
			return head;
		// move to last node of the ll
		ListNode last = head;
		while (last.next != null)
			last = last.next;
		ListNode oddStarts = null; // stores where odd position nodes starts
		ListNode even = head; // always on even position nodes from original ll
		
		while (even != oddStarts && even.next != oddStarts) {
			ListNode odd = even.next; // odd position will be next to even
			if (odd.next == null)
				break; // break on edge cases
			// move odd position nodes to end of the linked list
			even.next = odd.next;
			last.next = odd;
			odd.next = null;
			last = odd; // update last node
			// mark where odd nodes starts
			if (oddStarts == null)
				oddStarts = last;
			// since we moved odd position node to the end,
			// next node will be even position node
			even = even.next;
		}
		
		return head;
	}
}

public class OddEvenLinkedList {
	
}
