class Node {
    int data;
    Node next;
    Node prev;

    Node(int data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}

/**
 * Reverse a Doubly Linked List
 * https://www.geeksforgeeks.org/problems/reverse-a-doubly-linked-list/1
 */
class Solution {
    public Node reverse(Node head) {
        if (head == null || head.next == null)
            return head;
        Node curr = head;
        while (curr.next != null) {
            Node temp = curr.next;
            // disconnect next node from middle
            curr.next = curr.next.next;
            if (curr.next != null)
                curr.next.prev = curr;
            // make the node head of dll
            temp.prev = null;
            temp.next = head;
            head = temp;
        }
        return head;
    }
}
