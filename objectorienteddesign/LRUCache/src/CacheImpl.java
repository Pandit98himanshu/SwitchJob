import java.util.*;

public class CacheImpl {
    int capacity;
    HashMap<Integer, Node> map = new HashMap<>();
    Node head = new Node(-1, -1);
    Node tail = new Node(-1, -1);

    public CacheImpl(int capacity) {
        this.capacity = capacity;
        tail.pre = head;
        head.pre = tail;
    }

    public int getValue(int key) {
        Node currNode = map.get(key);

        if (currNode == null) {
            return -1;
        }
        // removing current node
        currNode.pre.next = currNode.next;
        currNode.next.pre = currNode.pre;
        // move current node to tail
        moveToTail(currNode);
        return map.get(key).val;
    }

    public void set(int key, int value) {
        if (getValue(key) != -1) {
            map.get(key).val = value;
            return;
        }
        if (map.size() == capacity) {
            map.remove(head.next.next);
            head.next = head.next.next;
            head.next.pre = head;
        }
        Node insert = new Node(key, value);
        map.put(key, insert);
        moveToTail(insert);
    }

    private void moveToTail(Node current) {
        current.pre = tail.pre;
        tail.pre = current;
        current.pre.next = current;
        current.next = tail;
    }
}
