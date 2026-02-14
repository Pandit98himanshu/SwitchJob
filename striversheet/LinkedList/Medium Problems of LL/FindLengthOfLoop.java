/**
 * Find length of Loop
 * https://www.geeksforgeeks.org/problems/find-length-of-loop/1
 */

class Node {
	int data;
	Node next;

	Node(int x) {
		data = x;
		next = null;
	}
}

class Solution {
	/*
	 * TC: O(2*n)
	 * SC: O(1)
	 */
	public int lengthOfLoop(Node head) {
		if (head == null)
			return 0;

		Node slow = head, fast = head;
		// using Floyd's cycle detection algo.
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;

			if (slow == fast) {         // found the cycle
				int len = 1;
				Node chkPoint = slow;   // set a checkpoint
				while (slow.next != chkPoint) {     // rotate once inside the loop
					len++;              // count the length
					slow = slow.next;
				}
				return len;
			}
		}

		return 0;
	}
}
