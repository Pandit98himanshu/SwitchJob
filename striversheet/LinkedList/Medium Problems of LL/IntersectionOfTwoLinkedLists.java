/**
 * 160. Intersection of Two Linked Lists
 * https://leetcode.com/problems/intersection-of-two-linked-lists
 */


// Definition for singly-linked list.
class ListNode {
	int val;
	ListNode next;
	ListNode(int x) {
		val = x;
		next = null;
	}
}

class Solution {
	/*
	 * TC: O(m + n)
	 * SC: O(1)
	 * Approach:
		1. find the length of both lists
		2. skip the nodes of difference length from longer list
		3. move until both lists converge or encounter null
	*/
	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		// 1.
		int lenA = 0, lenB = 0;
		ListNode tempA = headA, tempB = headB;
		while(tempA != null) {
			tempA = tempA.next;
			lenA++;
		}
		while(tempB != null) {
			tempB = tempB.next;
			lenB++;
		}
		// 2.
		tempA = headA; tempB = headB;
		while (lenA > lenB) {
			tempA = tempA.next;
			lenA--;
		}
		while (lenB > lenA) {
			tempB = tempB.next;
			lenB--;
		}
		// 3.
		while (tempA != null && tempB != null && tempA != tempB) {
			tempA = tempA.next;
			tempB = tempB.next;
		}

		return tempA;	// if not intersected, it will be null
	}
}
public class IntersectionOfTwoLinkedLists {
	
}
