import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int nextItemIndex;
    private Item[] items;

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int nextItemIndex;
        private Item[] items;

        public RandomizedQueueIterator(Item[] items, int nextItemIndex) {
            this.items = items;
            this.nextItemIndex = nextItemIndex;
        }

        public boolean hasNext() {
            return nextItemIndex > 0;
        }

        public Item next() {
            if (nextItemIndex <= 0) {
               throw  new NoSuchElementException();
            }

            int indexToSwap = StdRandom.uniformInt(nextItemIndex);
            Item tmp = items[nextItemIndex  - 1];
            items[nextItemIndex  - 1] = items[indexToSwap];
            items[indexToSwap] = tmp;

            Item item = items[nextItemIndex  - 1];
            nextItemIndex--;

            return item;
        }

        public  void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public RandomizedQueue() {
        nextItemIndex = 0;
        items = (Item[]) new Object[1];
    }

    public boolean isEmpty() {
        return nextItemIndex <= 0;
    }

    public int size() {
        return nextItemIndex;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Can't enqueue null item");
        }

        if (nextItemIndex >= items.length) {
            doubleItemsLength();
        }

        items[nextItemIndex] = item;
        nextItemIndex++;
    }

    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        int indexSample = StdRandom.uniformInt(nextItemIndex);
        return items[indexSample];
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        int indexToSwap = StdRandom.uniformInt(nextItemIndex);
        Item tmp = items[nextItemIndex  - 1];
        items[nextItemIndex  - 1] = items[indexToSwap];
        items[indexToSwap] = tmp;

        Item item = items[nextItemIndex  - 1];
        nextItemIndex--;

        if (items.length > 1 && nextItemIndex <= (items.length / 4)) {
            divideItemsLength();
        }

        return item;
    }

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator(items, nextItemIndex);
    }

//    public void print() {
//        System.out.print("|" + nextItemIndex + "| -> ");
//        for (int i = 0; i < items.length; i++) {
//           if (items[i] == null) {
//               System.out.print("x");
//           } else {
//               System.out.print(items[i]);
//           }
//
//           if (i == nextItemIndex - 1) {
//               System.out.print("|");
//           }
//        }
//
//        System.out.println();
//        System.out.println("*******");
//    }

    private void divideItemsLength() {
        int targetSize = items.length / 2;
        Item[] newItems =  (Item[]) new Object[targetSize];
        for (int i = 0; i < newItems.length; i++) {
            newItems[i] = items[i];
        }
        items = newItems;
    }

    private void doubleItemsLength() {
        int targetSize = items.length * 2;
        Item[] newItems =  (Item[]) new Object[targetSize];
        for (int i = 0; i < items.length; i++) {
            newItems[i] = items[i];
        }
        items = newItems;
    }

    public static void main(String[] args) {
//        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
//        rq.print();
//
//        for (int i = 1; i <= 10; i++) {
//            System.out.println("Enqueue " + i);
//            rq.enqueue(i);
//            rq.print();
//        }

//        System.out.println("**********");
//        for(Integer i : rq) {
//            System.out.println(i);
//        }
//        System.out.println("**********");

//        for (int i = 1; i <= 10; i++) {
//            System.out.println("Dequeue " + rq.dequeue());
//            rq.print();
//        }
    }
}
