/**
 * Sort a linked list of 0s, 1s and 2s
 * https://www.geeksforgeeks.org/problems/given-a-linked-list-of-0s-1s-and-2s-sort-it/1
 */


class Node {
	int data;
	Node next;

	Node(int d)
	{
		data = d;
		next = null;
	}
}

class Solution {
	public Node segregate(Node head) {
		// create 3 linked-lists for each 0s, 1s & 2s
		Node zeroes = new Node(Integer.MIN_VALUE), ones = new Node(Integer.MIN_VALUE),
				twos = new Node(Integer.MIN_VALUE);
		// create end node pointers for each of 2 above linked-lists
		Node end0 = zeroes, end1 = ones, end2 = twos;
		
		Node curr = head;
		// iterate given list once and fill all 3 above lists
		// as per their respective values
		while (curr != null) {
			switch (curr.data) {
				case 0 -> {
					end0.next = curr;
					end0 = curr;
					curr = curr.next;
					end0.next = null;
				}
				case 1 -> {
					end1.next = curr;
					end1 = curr;
					curr = curr.next;
					end1.next = null;
				}
				case 2 -> {
					end2.next = curr;
					end2 = curr;
					curr = curr.next;
					end2.next = null;
				}
			}
		}
		// if no 1s are present in the given list
		if (ones.next == null)
			end0.next = twos.next;
		else {
			end0.next = ones.next;		// 1s start where 0s ends
			end1.next = twos.next;		// 2s start where 1s ends
		}
		head = zeroes.next;
		return head;
	}
}
public class SortALinkedListOf0s1sAnd2s {
	public static void main(String[] args) {
		/* 
		TC1: 1 1 1 1 1
		TC2: 0 0 0 2 2 2
		TC3: 2 2 2 2 2
		*/
	}
}
