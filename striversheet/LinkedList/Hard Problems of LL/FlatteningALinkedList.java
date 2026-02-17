import java.util.PriorityQueue;

/**
 * Flattening a Linked List
 * https://www.geeksforgeeks.org/problems/flattening-a-linked-list/1
 */


class Node {
	int data;
	Node next;
	Node bottom;

	Node(int x) {
		data = x;
		next = null;
		bottom = null;
	}
}

class Solution {
	/*
	 * TC: O((n * m) * n log(n)) -> For each element, we are heapifying for n node elements
	 * SC: O(n)
	 * Time Taken: 1.01 sec for all test-cases
	 */
	public Node flatten1(Node root) {
		PriorityQueue<Node> heads = new PriorityQueue<>((a, b) -> a.data - b.data);

		Node temp = root;
		while (temp != null) {
			heads.offer(temp);
			temp = temp.next;
		}

		Node flattenList = new Node(Integer.MIN_VALUE);
		// keep polling the nodes from minHeap 'heads'
		// and append in another list 'flattenList'
		temp = flattenList;
		while (!heads.isEmpty()) {
			Node curr = heads.poll();
			temp.bottom = curr;
			if (curr.bottom != null) {
				heads.offer(curr.bottom);
			}
			temp = temp.bottom;
			temp.bottom = null;
			temp.next = null;
		}
		return flattenList.bottom;
	}

	/*
	 * TC: O((n * m) * n)	-> For each element, i.e., n * m, we are traversing complete 'root' level nodes
	 * SC: O(n)
	 * Time Taken: 0.89 sec for all test-cases
	 */
	public Node flatten(Node root) {
		Node flattenList = new Node(Integer.MIN_VALUE);
		Node end = flattenList;
		Node dummyHead = new Node(Integer.MAX_VALUE);
		// create a 'dummyHead' before 'root' for easy tracking of previous node
		dummyHead.next = root;
		root = dummyHead;

		while (root.next != null) {
			Node min = root;			// stores min value node at 'root' level of all sub-list
			Node minPrev = root;		// previous pointer of 'min' node
			for (Node i = root; i.next != null; i = i.next) {
				if (i.next.data < min.data) {
					min = i.next;
					minPrev = i;
				}
			}

			end.bottom = min;			// place in answer list
			// move bottom nodes UP of 'min' sub-list,
			// if NO bottom nodes, bridge to next sub-list
			if (min.bottom == null) {
				minPrev.next = min.next;
			} else {
				minPrev.next = min.bottom;
				min.bottom.next = min.next;
			}

			end = end.bottom;
			end.bottom = null;
			end.next = null;
		}
		return flattenList.bottom;
	}
}