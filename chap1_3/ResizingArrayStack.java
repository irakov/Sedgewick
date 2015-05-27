package chap1_3;//page 141
//algorithm 1.1

import java.io.BufferedInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ResizingArrayStack<Item> implements Iterable<Item> {
    private Item[] stack = (Item[]) new Object[1];
    private int size;

    public static void main(String[] args) throws EmptyStackException {
        Scanner input = new Scanner(new BufferedInputStream(System.in));
        PrintWriter output = new PrintWriter(new OutputStreamWriter(System.out), true);

        ResizingArrayStack<String> stack = new ResizingArrayStack<String>();
        while (input.hasNext()) {
            String s = input.next();
            if (!s.equals("-"))
                stack.push(s);
            else if (!stack.isEmpty())
                output.println(stack.pop());
        }
        output.println("stack contains " + stack.size() + " items");
        for (String s : stack)
            output.println(s);
    }

    private void resize(int newSize) {
        Item[] newStack = (Item[]) new Object[newSize];
        for (int i = 0; i < size; i++)
            newStack[i] = stack[i];
        stack = newStack;
    }

    public void push(Item item) {
        if (size == stack.length)
            resize(2 * stack.length);
        stack[size] = item;
        size++;
    }

    public Item pop() throws EmptyStackException {
        if (size == 0)
            throw new EmptyStackException();
        size--;
        Item item = stack[size];
        stack[size] = null;//avoid loitering!
        if (size <= stack.length / 4)
            resize(stack.length / 2);
        return item;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public Iterator<Item> iterator() {
        return new ArrayStackIterator();
    }

    private class ArrayStackIterator implements Iterator<Item> {
        private int position = size;

        public boolean hasNext() {
            return position > 0;
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            position--;
            return stack[position];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}