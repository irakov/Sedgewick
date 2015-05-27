package chap3_2;//taken from http://algs4.cs.princeton.edu/32bst/TestBST.java.html

import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class TestBST {
    public static void main(String[] args) {
        PrintWriter output = new PrintWriter(new OutputStreamWriter(System.out), true);

        String test = "S E A R C H E X A M P L E";
        String[] keys = test.split(" ");
        int N = keys.length;
        BSTThreading<String, Integer> st = new BSTThreading<String, Integer>();
        for (int i = 0; i < N; i++)
            st.put(keys[i], i);

        output.println("size = " + st.size());
        output.println("min  = " + st.min());
        output.println("max  = " + st.max());
        output.println();


        // print keys in order using allKeys()
        output.println("Testing keys()");
        output.println("--------------------------------");
        for (String s : st.keys())
            output.println(s + " " + st.get(s));
        output.println();

        // print keys in order using select
        output.println("Testing select");
        output.println("--------------------------------");
        for (int i = 0; i <= st.size(); i++)
            output.println(i + " " + st.select(i));
        output.println();

        // test rank, floor, ceiling
        output.println("key rank floor ceil");
        output.println("-------------------");
        for (char i = 'A'; i <= 'Z'; i++) {
            String s = i + "";
            output.printf("%2s %4d %4s %4s\n", s, st.rank(s), st.floor(s), st.ceiling(s));
        }
        output.println();

        // test range search and range count
        String[] from = {"A", "Z", "X", "0", "B", "C"};
        String[] to = {"Z", "A", "X", "Z", "G", "L"};
        output.println("range search");
        output.println("-------------------");
        for (int i = 0; i < from.length; i++) {
            output.printf("%s-%s (%2d) : ", from[i], to[i], st.size(from[i], to[i]));
            for (String s : st.keys(from[i], to[i]))
                output.print(s + " ");
            output.println();
        }
        output.println();

        // delete the smallest keys
        for (int i = 0; i < st.size() / 2; i++) {
            st.deleteMin();
        }

        output.println("After deleting the smallest " + st.size() / 2 + " keys");
        output.println("--------------------------------");
        for (String s : st.keys())
            output.println(s + " " + st.get(s));
        output.println();

        // delete all the remaining keys
        while (!st.isEmpty()) {
            st.delete(st.select(st.size() / 2));
        }
        output.println("After deleting the remaining keys");
        output.println("--------------------------------");
        for (String s : st.keys())
            output.println(s + " " + st.get(s));
        output.println();

        output.println("After adding back N keys");
        output.println("--------------------------------");
        for (int i = 0; i < N; i++)
            st.put(keys[i], i);
        for (String s : st.keys())
            output.println(s + " " + st.get(s));
        output.println();

        output.println(st.next("C".intern()));
    }
}