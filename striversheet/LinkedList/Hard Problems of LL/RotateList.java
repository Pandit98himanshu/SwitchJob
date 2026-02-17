/**
 * 61. Rotate List
 * https://leetcode.com/problems/rotate-list/
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
	public ListNode rotateRight(ListNode head, int k) {
		if (head == null)
			return head;
		// 1. find length of the list (n)
		int n = 0;
		ListNode temp = head;
		while (temp != null) {
			temp = temp.next;
			n++;
		}

		k = k % n;
		if (k == 0) return head;	// no rotation

		ListNode end = head;
		while (k-- > 0)
			end = end.next;

		temp = head;
		// move 'end' pointer to the end of given ll
		// and keep a distance of 'k' b/w them
		while (end.next != null) {
			temp = temp.next;
			end = end.next;
		}
		// 2. move (k % n) nodes from end to the front
		ListNode newHead = temp.next;
		temp.next = null;
		end.next = head;

		return newHead;
	}
}