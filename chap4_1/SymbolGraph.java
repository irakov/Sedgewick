package chap4_1;//page 548+552

import chap4_2.RedBlackBST;

import java.io.*;
import java.util.Scanner;

public class SymbolGraph {
    private Graph g;
    private chap4_2.RedBlackBST<String, Integer> st;
    private String[] keys;

    public SymbolGraph(String fileName, String delim) {
        st = new RedBlackBST<String, Integer>();

        File file = new File(fileName);
        if (!file.exists()) throw new IllegalArgumentException();

        Scanner fileInput = null;
        try {
            fileInput = new Scanner(file);
        } catch (IOException ex) {
            System.err.println(ex);
            throw new IllegalArgumentException(ex);
        }

        while (fileInput.hasNext()) {
            String input = fileInput.nextLine();
            String[] items = input.split(delim);
            for (int i = 0; i < items.length; i++)
                if (!st.contains(items[i]))
                    st.put(items[i], st.size());
        }

        keys = new String[st.size()];
        for (String s : st.keys())
            keys[st.get(s)] = s;

        g = new Graph(st.size());

        file = new File(fileName);
        fileInput = null;
        try {
            fileInput = new Scanner(file);
        } catch (IOException ex) {
            System.err.println(ex);
            throw new IllegalArgumentException(ex);
        }

        while (fileInput.hasNextLine()) {
            String input = fileInput.nextLine();
            String[] items = input.split(delim);
            for (int i = 1; i < items.length; i++)
                g.addEdge(st.get(items[0]), st.get(items[i]));
        }
    }

    public static void main(String[] args) {
        String fileName = args[0];
        String delim = args[1];
        SymbolGraph sg = new SymbolGraph(fileName, delim);

        Scanner input = new Scanner(new BufferedInputStream(System.in));
        PrintWriter output = new PrintWriter(new OutputStreamWriter(System.out), true);
        Graph g = sg.G();
        while (input.hasNextLine()) {
            String source = input.nextLine();
            if (!sg.contains(source))
                output.println("doesn't contain " + source);
            else {
                int index = sg.index(source);
                for (int i : g.adj(index))
                    output.println(" " + sg.name(i));
            }
        }
    }

    public boolean contains(String key) {
        return st.contains(key);
    }

    public int index(String key) {
        return st.get(key);
    }

    public String name(int v) {
        return keys[v];
    }

    public Graph G() {
        return g;
    }
}