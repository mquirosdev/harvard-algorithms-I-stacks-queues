import java.util.NoSuchElementException;

public class Deque<DequeType> {
    private class Node<NodeType> {
        public NodeType value;
        public Node<NodeType> next;
        public Node<NodeType> prev;
        public Node(NodeType value) {
            this.value = value;
        }
        public Node(
                NodeType value,
                Node<NodeType> next,
                Node<NodeType> prev
        ) {
            this.value = value;
            this.next = next;
            this.prev = prev;
        }
    }

    private Node<DequeType> head;
    private Node<DequeType> tail;
    private int totalNodes;

    public Deque() {
        head = null;
        tail = null;
        totalNodes = 0;
    }

    public void addFirst(DequeType value) {
        if (value == null) {
            throw new IllegalArgumentException("Can't add null");
        }

        Node<DequeType> node = new Node<DequeType>(value, head, null);

        if (head != null) {
            head.prev = node;
        } else {
            tail = node;
        }

        head = node;
        totalNodes++;
    }

    public void addLast(DequeType value) {
        if (value == null) {
            throw new IllegalArgumentException("Can't add null");
        }

        Node<DequeType> node = new Node<DequeType>(value, null, tail);

        if (tail != null) {
            tail.next = node;
        } else {
            head = node;
        }

        tail = node;
        totalNodes++;
    }

    public DequeType removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("No elements to remove");
        }

        Node<DequeType> node = head;
        head = head.next;

        if (head != null) {
            head.prev = null;
        } else {
            tail = null;
        }

        totalNodes--;
        return node.value;
    }

    public DequeType removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("No elements to remove");
        }

        Node<DequeType> node = tail;
        tail = tail.prev;

        if(tail != null) {
            tail.next = null;
        } else {
            head = null;
        }

        totalNodes--;
        return node.value;
    }

    public int size() {
        return totalNodes;
    }

    public boolean isEmpty() {
        return totalNodes == 0;
    }

    public void print() {
        Node<DequeType> current = head;
        while (current != null) {
            System.out.printf("(" + current.value + ") ->");
            current = current.next;
        }
        System.out.println("size " + totalNodes);
    }

    public void printBackwards() {
        Node<DequeType> current = tail;
        while (current != null) {
            System.out.printf("(" + current.value + ") ->");
            current = current.prev;
        }
        System.out.println("size " + totalNodes);
    }

    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<Integer>();

        deque.addFirst(3);
        System.out.println("Add 3 on head");
        deque.print();
        deque.printBackwards();

        System.out.println("Remove from tail: " + deque.removeLast());
        deque.print();
        deque.printBackwards();

        deque.addLast(3);
        System.out.println("Add 3 on tail");
        deque.addFirst(2);
        System.out.println("Add 2 on head");
        deque.addLast(4);
        System.out.println("Add 4 on tail");
        deque.addFirst(1);
        System.out.println("Add 1 on head");
        deque.print();
        deque.printBackwards();

        System.out.println("Remove from head: " + deque.removeFirst());
        System.out.println("Remove from head: " + deque.removeFirst());
        deque.print();
        deque.printBackwards();

        System.out.println("Remove from head: " + deque.removeFirst());
        System.out.println("Remove from head: " + deque.removeFirst());
        deque.print();
        deque.printBackwards();
    }
}
