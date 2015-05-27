package chap3_5;//3.5.1 (507)

import chap4_2.RedBlackBST;

import java.io.BufferedInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class SET<Key extends Comparable<Key>> {
    private chap4_2.RedBlackBST<Key, Integer> bst;

    public SET() {
        bst = new RedBlackBST<Key, Integer>();
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(new BufferedInputStream(System.in));
        PrintWriter output = new PrintWriter(new OutputStreamWriter(System.out), true);

        SET<String> set = new SET<String>();
        while (input.hasNext())
            set.add(input.next());

        set.delete("S".intern());

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

    public Iterable<Key> keys() {
        return bst.keys();
    }
}
