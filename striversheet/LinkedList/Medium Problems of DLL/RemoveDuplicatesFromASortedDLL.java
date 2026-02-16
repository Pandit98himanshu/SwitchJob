/**
 * Remove duplicates from a sorted doubly linked list
 * https://www.geeksforgeeks.org/problems/remove-duplicates-from-a-sorted-doubly-linked-list/1
 */


class Node{
    int data;
    Node next, prev;
    Node(int x){
        this.data = x;
        this.next = null;
        this.prev = null;
    }
}

class Solution {
	Node removeDuplicates(Node head) {
		// edge cases
		if (head == null || head.next == null)
			return head;

		Node curr = head;
		while (curr.next != null) {
			Node next = curr.next;
			if (curr.data == next.data) {
				curr.next = next.next;		// skip next node having same value as 'curr' node
				next = next.next;
				if (next != null)			// while removing duplicate at last node
					next.prev = curr;		// maintain 'prev' pointer too
			} else {
				curr = curr.next;			// move forward if there's no duplicate for 'curr' node
			}
		}
		return head;
	}
}

public class RemoveDuplicatesFromASortedDLL {
	
}
