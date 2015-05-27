package chap1_3;//page 167
//chap1.chap1_1.3.32

import chap4_2.LinkedList;
import chap4_2.LinkedListIterator;

import java.io.BufferedInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Scanner;


public class Steque<Item extends Comparable<Item>> implements Iterable<Item> {
    private chap4_2.LinkedList<Item> list = new LinkedList<Item>();

    public static void main(String[] args) {
        Scanner input = new Scanner(new BufferedInputStream(System.in));
        PrintWriter output = new PrintWriter(new OutputStreamWriter(System.out), true);

        Steque<String> s = new Steque<String>();

        while (input.hasNext()) {
            String item = input.next();
            if (item.substring(0, 1).equals("+"))
                s.enqueue(item.substring(1, item.length()));
            else if (!item.equals("-"))
                s.push(item);
            else if (!s.isEmpty())
                output.print(s.pop() + " ");
        }

        output.println("(" + s.size() + " left on steque)");

        for (String st : s)
            output.print(st + " ");
        output.println();
    }

    public boolean isEmpty() {
        return list.size() == 0;
    }

    public int size() {
        return list.size();
    }

    public void push(Item item) {
        list.insertFirst(item);
    }

    public Item pop() {
        return list.removeFirst();
    }

    public void enqueue(Item item) {
        list.insertLast(item);
    }

    public Iterator<Item> iterator() {
        return new LinkedListIterator<Item>(list);
    }
}