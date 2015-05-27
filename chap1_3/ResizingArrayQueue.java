package chap1_3;//page 163
//1.3.14

import java.io.BufferedInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ResizingArrayQueue<Item> implements Iterable<Item> {
    private Item[] queue = (Item[]) new Object[1];
    private int leftIndex, rightIndex;

    public static void main(String[] args) throws EmptyQueueException {
        Scanner input = new Scanner(new BufferedInputStream(System.in));
        PrintWriter output = new PrintWriter(new OutputStreamWriter(System.out), true);

        ResizingArrayQueue<String> s = new ResizingArrayQueue<String>();

        while (input.hasNext()) {
            String item = input.next();
            if (!item.equals("-"))
                s.enqueue(item);
            else if (!s.isEmpty())
                output.print(s.dequeue() + " ");
        }

        output.println("(" + s.size() + " left in queue)");
        for (String st : s)
            output.print(st + " ");
        output.println();
    }

    private void resize(int newSize) {
        Item[] newQueue = (Item[]) new Object[newSize];
        int j = 0;
        for (int i = 0; i < queue.length; i++) {
            if (queue[i] != null) {
                newQueue[j] = queue[i];
                j++;
            }
        }
        queue = newQueue;
        leftIndex = 0;
        rightIndex = j;
    }

    public void enqueue(Item item) {
        if (queue.length == rightIndex)
            resize(2 * queue.length);
        queue[rightIndex] = item;
        rightIndex++;
    }

    public Item dequeue() throws EmptyQueueException {
        if (leftIndex == rightIndex)
            throw new EmptyQueueException();
        Item item = queue[leftIndex];
        queue[leftIndex] = null;
        leftIndex++;
        if (rightIndex - leftIndex <= queue.length / 4)
            resize(queue.length / 2);
        return item;
    }

    public boolean isEmpty() {
        return leftIndex == rightIndex;
    }

    public int size() {
        return rightIndex - leftIndex;
    }

    public Iterator<Item> iterator() {
        return new ArrayQueueIterator();
    }

    private class ArrayQueueIterator implements Iterator<Item> {
        private int position = leftIndex;

        public boolean hasNext() {
            return position < rightIndex;
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            Item item = queue[position];
            position++;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}