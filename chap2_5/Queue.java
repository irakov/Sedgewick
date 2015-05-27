package chap2_5;//page 151
//algorithm chap1.chap1_1.3

import chap4_2.LinkedList;
import chap4_2.LinkedListIterator;

import java.io.BufferedInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Scanner;

public class Queue<Item extends Comparable<Item>> implements Iterable<Item> {
    private chap4_2.LinkedList<Item> list = new LinkedList<Item>();

    public static void main(String[] args) {
        Scanner input = new Scanner(new BufferedInputStream(System.in));
        PrintWriter output = new PrintWriter(new OutputStreamWriter(System.out), true);

        chap4_2.Queue<String> s = new chap4_2.Queue<String>();

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

    public Item peek() {
        return list.getFirst().item;
    }

    public Iterator<Item> iterator() {
        return new LinkedListIterator<Item>(list);
    }
}