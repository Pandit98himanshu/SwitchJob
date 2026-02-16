import java.util.ArrayList;
import java.util.Arrays;

/**
 * Pair Sum in Doubly Linked List
 * https://www.geeksforgeeks.org/problems/find-pairs-with-given-sum-in-doubly-linked-list/1
 */


// Definition for singly Link List Node
class Node
{
    int data;
    Node next,prev;

    Node(int x){
        data = x;
        next = null;
        prev = null;
    }
}

class Solution {
	/*
	 * TC: O(n)
	 * SC: O(1)
	 * Uses 2 pointer approach
	 */
	public static ArrayList<ArrayList<Integer>> findPairsWithGivenSum(int target,
			Node head) {
		Node l = head, r = head;
		// move right pointer to end of the dll
		while (r.next != null)
			r = r.next;

		ArrayList<ArrayList<Integer>> ans = new ArrayList<>(); // contains pairs list, summed up to target
		// keep moving pointers to each other until they meet
		while (l != r) {
			/*	// as mentioned in the question, elements are distinct, hence it's not required
				// need to skip nodes if elements are duplicated
				if (l.prev != null && l.data == l.prev.data) {
					l = l.next;
					continue;
				}
				if (r.next != null && r.data == r.next.data) {
					r = r.prev;
					continue;
				}
			*/
			// calculate the current sum when 'l' & 'r' pointers are at their respective positions
			int curr = l.data + r.data;
			if (curr == target) {
				ans.add(new ArrayList<>(Arrays.asList(l.data, r.data))); // add to the result list
			}
			// need to decreament 'r' if sum is greater than 'target' value
			if (curr > target)
				r = r.prev;
			else				// increament 'l' otherwise
				l = l.next;
		}
		return ans;
	}
}

public class PairSumInDoublyLinkedList {
	/*
	TC1:
		target = 6
		dll: 1 2 3 3 4 5 8
	 */
}
