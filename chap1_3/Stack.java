package chap1_3;//page 149
//algorithm chap1.chap1_1.2
//with chap1.chap1_1.3.7.

import chap4_2.LinkedList;
import chap4_2.LinkedListIterator;

import java.io.BufferedInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Scanner;

public class Stack<Item extends Comparable<Item>> implements Iterable<Item> {
    private chap4_2.LinkedList<Item> list = new LinkedList<Item>();

    public static void main(String[] args) {
        Scanner input = new Scanner(new BufferedInputStream(System.in));
        PrintWriter output = new PrintWriter(new OutputStreamWriter(System.out), true);

        chap4_2.Stack<String> s = new chap4_2.Stack<String>();

        while (input.hasNext()) {
            String item = input.next();
            if (!item.equals("-"))
                s.push(item);
            else if (!s.isEmpty())
                output.print(s.pop() + " ");
        }

        output.println("(" + s.size() + " left on stack)");
        if (!s.isEmpty())
            output.println("peeking top item: " + s.peek());
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

    public Item peek() {
        if (!isEmpty())
            return list.getFirst().item;
        return null;
    }

    public Iterator<Item> iterator() {
        return new LinkedListIterator<Item>(list);
    }
}