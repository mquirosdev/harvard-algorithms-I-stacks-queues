import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    public static void main(String[] args) {
        int totalItems = Integer.parseInt(args[0]);

        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            rq.enqueue(StdIn.readString());
        }

        for (int i = 0; i < totalItems; i++) {
            System.out.println(rq.dequeue());
        }
    }
}
