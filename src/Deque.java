import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<DequeItem> implements Iterable<DequeItem> {
    private class DequeIterator implements Iterator<DequeItem> {
        private Node<DequeItem> current;

        public DequeIterator(Node<DequeItem> current) {
            this.current = current;
        }

        public DequeItem next() {
            if (!hasNext()) {
                throw new NoSuchElementException("no next element");
            }

            DequeItem value = current.value;
            current = current.next;

            return value;
        }

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

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

    private Node<DequeItem> head;
    private Node<DequeItem> tail;
    private int totalNodes;

    public Deque() {
        head = null;
        tail = null;
        totalNodes = 0;
    }

    public void addFirst(DequeItem value) {
        if (value == null) {
            throw new IllegalArgumentException("Can't add null");
        }

        Node<DequeItem> node = new Node<DequeItem>(value, head, null);

        if (head != null) {
            head.prev = node;
        } else {
            tail = node;
        }

        head = node;
        totalNodes++;
    }

    public void addLast(DequeItem value) {
        if (value == null) {
            throw new IllegalArgumentException("Can't add null");
        }

        Node<DequeItem> node = new Node<DequeItem>(value, null, tail);

        if (tail != null) {
            tail.next = node;
        } else {
            head = node;
        }

        tail = node;
        totalNodes++;
    }

    public DequeItem removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("No elements to remove");
        }

        Node<DequeItem> node = head;
        head = head.next;

        if (head != null) {
            head.prev = null;
        } else {
            tail = null;
        }

        totalNodes--;
        return node.value;
    }

    public DequeItem removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("No elements to remove");
        }

        Node<DequeItem> node = tail;
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

    public Iterator<DequeItem> iterator() {
        return new DequeIterator(head);
    }

    public void print() {
        for (DequeItem value : this) {
            System.out.printf("(" + value + ") ->");
        }
        System.out.println("size " + totalNodes);
    }

    public void printBackwards() {
        Node<DequeItem> current = tail;
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
