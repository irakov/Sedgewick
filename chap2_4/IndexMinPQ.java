package chap2_4;//page 320
//page 322
//+2.4.33+2.4.34+2.4.26

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class IndexMinPQ<Item extends Comparable<Item>> {
    private int maxSize;
    private int size;
    private int[] pq;
    private int[] qp;
    private Item[] keys;

    public IndexMinPQ(int maxN) {
        if (maxN < 0)
            throw new IllegalArgumentException();
        maxSize = maxN;
        pq = new int[maxN + 1];
        qp = new int[maxN + 1];
        keys = (Item[]) new Comparable[maxSize + 1];
        for (int i = 0; i <= maxSize; i++)
            qp[i] = -1;
    }

    public static void main(String[] args) {
        int n = args.length;

        Scanner[] streams = new Scanner[n];
        try {
            for (int i = 0; i < n; i++) {
                File file = new File(args[i]);
                streams[i] = new Scanner(file);
            }
        } catch (IOException ex) {
            System.err.println("Cannot open file");
            return;
        }
        merge(streams);
    }

    private static void merge(Scanner[] streams) {
        PrintWriter output = new PrintWriter(new OutputStreamWriter(System.out), true);

        int n = streams.length;
        IndexMinPQ<String> pq = new IndexMinPQ<String>(n);
        for (int i = 0; i < n; i++) {
            if (streams[i].hasNext())
                pq.insert(i, streams[i].next());
        }

        while (!pq.isEmpty()) {
            output.println(pq.min());
            int i = pq.delMin();
            if (streams[i].hasNext())
                pq.insert(i, streams[i].next());
        }
    }

    public void insert(int k, Item item) {
        if (k < 0 || k >= maxSize)
            throw new IndexOutOfBoundsException();
        if (contains(k))
            throw new IllegalArgumentException();
        size++;
        pq[size] = k;
        qp[k] = size;
        keys[k] = item;
        swim(size);
    }

    public void change(int k, Item item) {
        if (k < 0 || k >= maxSize)
            throw new IndexOutOfBoundsException();
        if (contains(k))
            throw new IllegalArgumentException();
        keys[k] = item;
        swim(qp[k]);
        sink(qp[k]);
    }

    public boolean contains(int k) {
        if (k < 0 || k >= maxSize)
            throw new IndexOutOfBoundsException();
        return qp[k] != -1;
    }

    public void delete(int k) {
        if (k < 0 || k >= maxSize)
            throw new IndexOutOfBoundsException();
        if (!contains(k))
            throw new NoSuchElementException();
        int index = qp[k];
        exchange(k, size--);
        swim(index);
        sink(index);
        keys[k] = null;
        qp[k] = -1;
    }

    public Item min() {
        if (size == 0)
            throw new NoSuchElementException();
        return keys[pq[1]];
    }

    public int minIndex() {
        if (size == 0)
            throw new NoSuchElementException();
        return pq[1];
    }

    public int delMin() {
        if (size == 0)
            throw new NoSuchElementException();
        int min = pq[1];
        exchange(1, size--);
        sink(1);
        qp[min] = -1;
        keys[pq[size + 1]] = null;
        return min;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void exchange(int i, int j) {
        int temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }

    private void swim(int k) {
        while (k > 1 && keys[pq[k / 2]].compareTo(keys[pq[k]]) > 0) {
            exchange(k / 2, k);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= size) {
            int j = 2 * k;
            if (j < size && keys[pq[j]].compareTo(keys[pq[j + 1]]) > 0)
                j++;
            if (keys[pq[k]].compareTo(keys[pq[j]]) <= 0)
                break;
            exchange(k, j);
            k = j;
        }
    }
}