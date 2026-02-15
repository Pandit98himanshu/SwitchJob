/**
 * Add 1 to a Linked List Number
 * https://www.geeksforgeeks.org/problems/add-1-to-a-number-represented-as-linked-list/1
 */


class Node{
	int data;
	Node next;

	Node(int x){
		data = x;
		next = null;
	}
}


class Solution {
	/*
	 * TC: O(3*n)
	 * SC: O(1)
	 */
	public Node addOne(Node head) {
		// reverse the list
		head = reverseLL(head);
		// add 1
		int carry = 1;
		Node temp = head;
		while (temp != null && carry > 0) {
			int val = temp.data;
			val += carry;
			temp.data = val % 10;
			carry = val / 10;
			temp = temp.next;
		}
		// again reverse
		head = reverseLL(head);
		// add carry to the head of the ll, since the list is reversed
		if (carry > 0) {
			Node newHead = new Node(carry);
			newHead.next = head;
			return newHead;
		}

		return head;
	}

	/*
		Reverses the givenlinked list
		TC: O(n)
	*/
	private Node reverseLL(Node head) {
		if (head == null || head.next == null)
			return head;

		Node prev = null;
		Node curr = head;
		while (curr != null) {
			Node next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}

		return prev;
	}
}