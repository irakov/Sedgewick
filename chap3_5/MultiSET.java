package chap3_5;//3.5.11 (508)

import java.io.BufferedInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class MultiSET<Key extends Comparable<Key>> {
    private RedBlackBSTDuplicate<Key, Integer> bst;

    public MultiSET() {
        bst = new RedBlackBSTDuplicate<Key, Integer>();
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(new BufferedInputStream(System.in));
        PrintWriter output = new PrintWriter(new OutputStreamWriter(System.out), true);

        MultiSET<String> set = new MultiSET<String>();
        while (input.hasNext())
            set.add(input.next());
        output.println(set.toString());
        output.println("deleting E");
        set.delete("E".intern());

        output.println(set.toString());
    }

    public void add(Key key) {
        bst.put(key, 1);
    }

    public void delete(Key key) {
        bst.delete(key);
    }

    public boolean contains(Key key) {
        return bst.contains(key);
    }

    public boolean isEmpty() {
        return bst.isEmpty();
    }

    public int size() {
        return bst.size();
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Key key : bst.keys()) {
            builder.append(key);
            builder.append(" ");
        }
        return builder.toString();
    }
}
