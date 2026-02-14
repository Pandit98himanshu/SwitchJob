/**
 * 234. Palindrome Linked List
 * https://leetcode.com/problems/palindrome-linked-list
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
	 *  Time: O(n)
	 *  Space: O(n)
	 */
	private boolean ans;
	private ListNode temp;

	public boolean isPalindrome(ListNode head) {
		ans = true;
		temp = head;
		checkPalindromeRecur(head);
		return ans;
	}

	private ListNode checkPalindromeRecur(ListNode head) {
		if (head.next == null)
			return head;
		ListNode prev = checkPalindromeRecur(head.next);
		// updates value if it's not a palindrome
		if (prev.val != temp.val)
			ans &= false;
		temp = temp.next;
		return head;
	}
}
