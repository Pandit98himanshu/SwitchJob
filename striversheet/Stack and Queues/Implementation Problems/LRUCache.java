import java.util.HashMap;

/**
 * 146. LRU Cache
 * https://leetcode.com/problems/lru-cache/
 */

class DLLNode {
	int key, value;
	DLLNode prev, next;

	public DLLNode(int key, int value, DLLNode prev, DLLNode next) {
		this.key = key;
		this.value = value;
		this.prev = prev;
		this.next = next;
	}
}

class LRUCache {
	int capacity, size;
	HashMap<Integer, DLLNode> keyNodeMap;
	DLLNode front, rear;

	public LRUCache(int capacity) {
		this.capacity = capacity;
		this.size = 0;
		this.keyNodeMap = new HashMap<>();
		this.front = null;
		this.rear = null;
	}
	
	/*
	 * TC: O(1)
	 * SC: O(n)
	 */
	public int get(int key) {
		// get node from hash-map
		int retv = -1;
		if (keyNodeMap.containsKey(key)) {
			DLLNode curr = keyNodeMap.get(key);
			retv = curr.value;

			if (curr == front || rear == front) return retv;
			// keep track of "rear" node
			if (curr == rear) {
				rear = curr.prev;
				rear.next = null;
			}
			// move the node to front
			if (curr.prev != null)
				curr.prev.next = curr.next;
			if (curr.next != null)
				curr.next.prev = curr.prev;
			front.prev = curr;
			curr.next = front;
			curr.prev = null;
			front = curr;
		}
		// printCache();
		// return it's value
		return retv;
	}
	
	/*
	 * TC: O(1)
	 * SC: O(n)
	 */
	public void put(int key, int value) {
		// if key already present, update the value & move the node to front
		if (keyNodeMap.containsKey(key)) {
			DLLNode curr = keyNodeMap.get(key);
			curr.value = value;
			get(key);       // move node to front
			return;
		}
		// put the value into the doubly-linked list at the front
		DLLNode newNode = new DLLNode(key, value, null, null);
		newNode.next = front;
		if (front != null)
			front.prev = newNode;
		front = newNode;
		if (rear == null)
			rear = newNode;

		// put node's reference into the hash-map
		keyNodeMap.put(key, newNode);
		// increase the size
		size++;
		// remove element from rear & corresponding value from map if capacity is exceeded
		if (size > capacity) {
			int delKey = rear.key;
			rear = rear.prev;
			rear.next = null;
			if (key != delKey)
				keyNodeMap.remove(delKey);
			size--;
		}
		// printCache();
	}
	private void printCache() {
		if (front == null) {
			System.out.println("null");
			return;
		}
		DLLNode temp = front;
		System.out.print("Cache:");
		while (temp != null) {
			System.out.print(temp.value + ",");
			temp = temp.next;
		}
		System.out.println("\nfront: " + front.value + ", rear: " + rear.value);
	}
}