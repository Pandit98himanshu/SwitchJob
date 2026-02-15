/**
 * Delete all occurrences of a given key in a doubly linked list
 * https://www.geeksforgeeks.org/problems/delete-all-occurrences-of-a-given-key-in-a-doubly-linked-list/1
 */

// Structure of Doubly Linked List
class Node
{
	int data;
	Node next;
	Node prev;
}
class Solution {
	static Node deleteAllOccurOfX(Node head, int x) {
		Node curr = head;
		// delete until we reach end of the DLL
		while (curr != null) {
			// delete current node
			if (curr.data == x) {
				// situation 1) deleting node is a head node
				if (curr == head) {
					curr = curr.next;
					if (curr != null)		// when only 1 node in the list
						curr.prev = null;
					head = curr;
				}
				// situation 2) deleting node is a middle or end node
				else {
					Node next = curr.next;
					curr.prev.next = next;
					// handle edge-case when current node is end node
					if (next != null)
						next.prev = curr.prev;
					curr = next;
				}
			}
			// skip when current node is not to be deleted
			else
				curr = curr.next;
		}
		return head;
	}
}