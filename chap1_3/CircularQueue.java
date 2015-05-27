package chap1_3;//page 165
//1.3.29

import java.io.BufferedInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Scanner;

public class CircularQueue<Item extends Comparable<Item>> implements Iterable<Item> {
    private CircularLinkedList<Item> list = new CircularLinkedList<Item>();

    public static void main(String[] args) {
        Scanner input = new Scanner(new BufferedInputStream(System.in));
        PrintWriter output = new PrintWriter(new OutputStreamWriter(System.out), true);

        CircularQueue<String> s = new CircularQueue<String>();
        while (input.hasNext()) {
            String item = input.next();
            if (!item.equals("-"))
                s.enqueue(item);
            else if (!s.isEmpty())
                output.print(s.dequeue() + " ");
        }

        output.println("(" + s.size() + " left in queue)");
    }

    public boolean isEmpty() {
        return list.size() == 0;
    }

    public int size() {
        return list.size();
    }

    public void enqueue(Item item) {
        list.insertLast(item);
    }

    public Item dequeue() {
        return list.removeFirst();
    }

    public Iterator<Item> iterator() {
        return new CircularLinkedListIterator<Item>(list);
    }
}