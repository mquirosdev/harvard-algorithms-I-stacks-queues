import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private class DequeIterator implements Iterator<Item> {
        private Node<Item> current;

        public DequeIterator(Node<Item> current) {
            this.current = current;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("no next element");
            }

            Item value = current.value;
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

    private class Node<Item> {
        public Item value;
        public Node<Item> next;
        public Node<Item> prev;

        public Node(Item value) {
            this.value = value;
        }

        public Node(
                Item value,
                Node<Item> next,
                Node<Item> prev
        ) {
            this.value = value;
            this.next = next;
            this.prev = prev;
        }
    }

    private Node<Item> head;
    private Node<Item> tail;
    private int totalNodes;

    public Deque() {
        head = null;
        tail = null;
        totalNodes = 0;
    }

    public void addFirst(Item value) {
        if (value == null) {
            throw new IllegalArgumentException("Can't add null");
        }

        Node<Item> node = new Node<Item>(value, head, null);

        if (head != null) {
            head.prev = node;
        } else {
            tail = node;
        }

        head = node;
        totalNodes++;
    }

    public void addLast(Item value) {
        if (value == null) {
            throw new IllegalArgumentException("Can't add null");
        }

        Node<Item> node = new Node<Item>(value, null, tail);

        if (tail != null) {
            tail.next = node;
        } else {
            head = node;
        }

        tail = node;
        totalNodes++;
    }

    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("No elements to remove");
        }

        Node<Item> node = head;
        head = head.next;

        if (head != null) {
            head.prev = null;
        } else {
            tail = null;
        }

        totalNodes--;
        return node.value;
    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("No elements to remove");
        }

        Node<Item> node = tail;
        tail = tail.prev;

        if (tail != null) {
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

    public Iterator<Item> iterator() {
        return new DequeIterator(head);
    }

//    public void print() {
//        for (Item value : this) {
//            System.out.printf("(" + value + ") ->");
//        }
//        System.out.println("size " + totalNodes);
//    }

//    public void printBackwards() {
//        Node<Item> current = tail;
//        while (current != null) {
//            System.out.printf("(" + current.value + ") ->");
//            current = current.prev;
//        }
//        System.out.println("size " + totalNodes);
//    }

    public static void main(String[] args) {
//        Deque<Integer> deque = new Deque<Integer>();
//
//        deque.addFirst(3);
//        System.out.println("Add 3 on head");
//        deque.print();
//        deque.printBackwards();
//
//        System.out.println("Remove from tail: " + deque.removeLast());
//        deque.print();
//        deque.printBackwards();
//
//        deque.addLast(3);
//        System.out.println("Add 3 on tail");
//        deque.addFirst(2);
//        System.out.println("Add 2 on head");
//        deque.addLast(4);
//        System.out.println("Add 4 on tail");
//        deque.addFirst(1);
//        System.out.println("Add 1 on head");
//        deque.print();
//        deque.printBackwards();
//
//        System.out.println("Remove from head: " + deque.removeFirst());
//        System.out.println("Remove from head: " + deque.removeFirst());
//        deque.print();
//        deque.printBackwards();
//
//        System.out.println("Remove from head: " + deque.removeFirst());
//        System.out.println("Remove from head: " + deque.removeFirst());
//        deque.print();
//        deque.printBackwards();
    }
}
