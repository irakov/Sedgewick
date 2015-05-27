package chap4_1;//page 155
//algorithm chap1.chap1_1.4

import chap4_2.LinkedList;
import chap4_2.LinkedListIterator;

import java.io.BufferedInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Scanner;

public class Bag<Item extends Comparable<Item>> implements Iterable<Item> {
    private chap4_2.LinkedList<Item> list = new LinkedList<Item>();

    public static void main(String[] args) {
        Scanner input = new Scanner(new BufferedInputStream(System.in));
        PrintWriter output = new PrintWriter(new OutputStreamWriter(System.out), true);

        chap4_2.Bag<String> b = new chap4_2.Bag<String>();

        while (input.hasNext())
            b.add(input.next());

        output.println("(" + b.size() + " items in bag)");

        for (String s : b)
            output.print(s + " ");

        output.println();
    }

    public boolean isEmpty() {
        return list.size() == 0;
    }

    public int size() {
        return list.size();
    }

    public void add(Item item) {
        list.insertFirst(item);
    }

    public Iterator<Item> iterator() {
        return new LinkedListIterator<Item>(list);
    }
}