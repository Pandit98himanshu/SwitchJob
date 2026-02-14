/**
 * 148. Sort List
 * https://leetcode.com/problems/sort-list/
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
	 * TC: O(2n logn)
	 * SC: O(logn) // creating dummy head in each merging operation
	 * Merge-sort
	 */
	public ListNode sortList(ListNode head) {
		return mergeSort(head);
	}
	private ListNode mergeSort(ListNode head) {
		if (head == null || head.next == null)
			return head;
		// find mid of the linked-list
		// using Floyd-cycle algorithm
		ListNode mid = head, end = head;
		while (end.next != null && end.next.next != null) {
			mid = mid.next;
			end = end.next.next;
		}
		ListNode midNext = mid.next;
		mid.next = null;					// disconnect next-half of the ll
		ListNode left = mergeSort(head);
		ListNode right = mergeSort(midNext);
		return mergeTwoSortedLists(left, right);	// merge both halves
	}
	private ListNode mergeTwoSortedLists(ListNode head1, ListNode head2) {
		ListNode head = new ListNode(Integer.MIN_VALUE), end = head;
		
		while (head1 != null && head2 != null) {
			if (head1.val <= head2.val) {
				end.next = head1;
				head1 = head1.next;
			} else {
				end.next = head2;
				head2 = head2.next;
			}
			end = end.next;
			end.next = null;
		}
		// copy rest of the elements
		if (head1 != null) {
			end.next = head1;
		}
		if (head2 != null) {
			end.next = head2;
		}
		return head.next;
	} 
}
public class SortList {
	
}
