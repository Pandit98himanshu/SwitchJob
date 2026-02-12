/**
* 206. Reverse Linked List
* https://leetcode.com/problems/reverse-linked-list
*/

// Definition for singly-linked list.
public class ReverseLinkedList {
	int val;
	ReverseLinkedList next;
	ReverseLinkedList() {}
	ReverseLinkedList(int val) { this.val = val; }
	ReverseLinkedList(int val, ReverseLinkedList next) { this.val = val; this.next = next; }
}

class Solution {
	/*
	 * TC: O(n)
	 * SC: O(n)	// recursive stack
	 */
	public ReverseLinkedList reverseList(ReverseLinkedList head) {
		if (head == null || head.next == null) {
			return head;
		}
		ReverseLinkedList newHead = reverseList(head.next);
		ReverseLinkedList next = head.next;
		next.next = head;
		head.next = null;
		return newHead;
	}
}