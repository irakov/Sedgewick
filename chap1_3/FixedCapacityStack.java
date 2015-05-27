package chap1_3;//page 135

import java.io.BufferedInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class FixedCapacityStack<Item> {
    private Item[] stack;
    private int size;

    public FixedCapacityStack(int capacity) {
        stack = (Item[]) new Object[capacity];
    }

    public static void main(String[] args) throws FullStackException, EmptyStackException {
        Scanner input = new Scanner(new BufferedInputStream(System.in));
        PrintWriter output = new PrintWriter(new OutputStreamWriter(System.out), true);

        FixedCapacityStack<String> stack = new FixedCapacityStack<String>(100);
        while (input.hasNext()) {
            String s = input.next();
            if (!s.equals("-"))
                stack.push(s);
            else if (!stack.isEmpty())
                output.println(stack.pop());
        }
        output.println("stack contains " + stack.size() + " items");
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void push(Item item) throws FullStackException {
        if (size >= stack.length)
            throw new FullStackException();
        stack[size] = item;
        size++;
    }

    public Item pop() throws EmptyStackException {
        if (size == 0)
            throw new EmptyStackException();
        size--;
        return stack[size];
    }
}