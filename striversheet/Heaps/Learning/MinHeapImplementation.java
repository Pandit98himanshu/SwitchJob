import java.util.ArrayList;
import java.util.List;

/**
 * Min Heap implementation
 * https://www.geeksforgeeks.org/problems/min-heap-implementation/1
 */

class minHeap {
	List<Integer> heap;
	// Constructor
	public minHeap() {
		heap = new ArrayList<>();
	}

	/*
	 * TC: O(log n)
	 */
	public void push(int x) {
		// Insert x into the heap
		int index = heap.size();
		heap.add(x);
		// rearrange "x" position
		while (true) {
			int parentIndex = (index - 1)/2;
			if (parentIndex >= 0 && heap.get(parentIndex) > heap.get(index)) {
				heap.set(index, heap.get(parentIndex));
				heap.set(parentIndex, x);
				index = parentIndex;
			} else
				break;
		}
	}

	/*
	 * TC: O(log n)
	 */
	public void pop() {
		// Remove the top (minimum) element
		if (heap.size() <= 0)
			return;
		// System.out.println("Before:" + heap);
		int lastEle = heap.get(heap.size() - 1);
		heap.set(0, lastEle);				// replace min (first) element with last element
		heap.remove(heap.size() - 1);				// remove last element

		// heapify process
		int index = 0;
		while (size() > 0) {
			int lChild = 2 * index + 1;
			int rChild = 2 * index + 2;

			int replacingVal = heap.get(index);
			int replacingIndex = -1;

			if (lChild < heap.size() && heap.get(lChild) < replacingVal) {
				replacingVal = heap.get(lChild);
				replacingIndex = lChild;
			}
			if (rChild < heap.size() && heap.get(rChild) < replacingVal) {
				replacingVal = heap.get(rChild);
				replacingIndex = rChild;
			}
			// System.out.println(index + ":" + heap);
			if (replacingIndex >= 0) {
				heap.set(replacingIndex, heap.get(index));
				heap.set(index, replacingVal);
				index = replacingIndex;
			} else {
				break;
			}
		}
	}

	/*
	 * TC: O(1)
	 */
	public int peek() {
		// Return the top element or -1 if empty
		return size() > 0 ? heap.get(0) : -1;
	}

	/*
	 * TC: O(1)
	 */
	public int size() {
		// Return the number of elements in the heap
		return heap.size();
	}
}