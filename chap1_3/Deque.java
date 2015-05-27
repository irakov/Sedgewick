package chap1_3;
//1.3.33(page 167)

import java.io.BufferedInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Scanner;

public class Deque<Item extends Comparable<Item>> implements Iterable<Item> {
    private LinkedList<Item> list = new LinkedList<Item>();

    public static void main(String[] args) {
        Scanner input = new Scanner(new BufferedInputStream(System.in));
        PrintWriter output = new PrintWriter(new OutputStreamWriter(System.out), true);

        Deque<String> deque = new Deque<String>();
        while (input.hasNext()) {
            String s = input.next();
            if (s.substring(0, 2).equals(">|"))
                deque.pushLeft(s.substring(2, s.length()));
            else if (s.substring(0, 2).equals("<|"))
                output.print(deque.popLeft() + " ");
            else if (s.substring(0, 2).equals("|<"))
                deque.pushRight(s.substring(2, s.length()));
            else if (s.substring(0, 2).equals("|>"))
                output.print(deque.popRight() + " ");
        }

        output.println("(" + deque.size() + " items left in deque)");
        for (String s : deque)
            output.print(s + " ");
        output.println();
    }

    public boolean isEmpty() {
        return list.size() == 0;
    }

    public int size() {
        return list.size();
    }

    public void pushLeft(Item item) {
        list.insertFirst(item);
    }

    public void pushRight(Item item) {
        list.insertLast(item);
    }

    public Item popLeft() {
        return list.removeFirst();
    }

    public Item popRight() {
        return list.removeLast();
    }

    public Iterator<Item> iterator() {
        return new LinkedListIterator<Item>(list);
    }
}