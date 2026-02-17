/**
 * 25. Reverse Nodes in k-Group
 * https://leetcode.com/problems/reverse-nodes-in-k-group
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
	 */
	public ListNode reverseKGroup(ListNode head, int k) {
		ListNode l = head, r = head, prev = null;
		head = null; 				// 'head' will be changed after reversal operation of the list
	
		while (r != null) {
			// 'l' will point to start and,
			// 'r' will point to the end of the curr k-group
			for (int i = 1; i < k && r != null; i++)
				r = r.next;
			if (r == null)
				break;
	
			ListNode nextL = r.next;// mark the start of rest of the list
			r.next = null; 			// separate the block from the list
	
			reverseLL(l); 			// NOTE: after reversing, l = end, r = head
	
			if (prev != null)
				prev.next = r; 		// link previous part
			prev = l;
	
			if (head == null)
				head = r; 			// reestablish the head
	
			l.next = nextL; 		// link later part
			// repeat same operations for next block
			l = nextL;
			r = nextL;
		}
		return head;
	}
	
	/*
	 * Reverses the given linked list
	 */
	private void reverseLL(ListNode head) {
		ListNode prev = null, curr = head;
		while (curr != null) {
			ListNode next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}
	}
}
public class ReverseNodesInKGroup {
	
}
