/**
 * Queue Using Array
 * https://www.geeksforgeeks.org/problems/implement-queue-using-array/1
 */

// Implementation of circular queue
class myQueue {

	private int[] q;
	private int n;
	private int size;
	private int front, rear;

	// initialization
	public myQueue(int n) {
		q = new int[n];
		this.n = n;
		size = 0;
		front = 0;
		rear = 0;
	}
	// returns true when queue is empty, otherwise false
	public boolean isEmpty() {
		return size == 0;
	}

	// returns true when queue is full, otherwise false
	public boolean isFull() {
		return size == n;
	}

	// adds element 'x' at the rear of the queue when not full
	public void enqueue(int x) {
		if (!isFull()) {
			q[rear] = x;
			rear = (rear + 1) % n;
			size++;
		}
	}

	// remove element in FIFO order
	public void dequeue() {
		if (!isEmpty()) {
			front = (front + 1) % n;
			size--;
		}
	}

	// get first element present into the queue
	public int getFront() {
		if (!isEmpty())
			return q[front];
		return -1;
	}

	// get last element added into the queue if not removed
	public int getRear() {
		if (!isEmpty())
			return q[(rear + n - 1) % n];
		return -1;
	}
}
