package chap4_2;
//4.2.7 (page 596)

import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class Degrees {
    private Digraph d;

    public Degrees(Digraph d) {
        this.d = d;
    }

    public static void main(String[] args) {
        PrintWriter output = new PrintWriter(new OutputStreamWriter(System.out), true);

        Digraph d = new Digraph(args[0]);
        Degrees deg = new Degrees(d);
        for (int i = 0; i < d.V(); i++)
            output.println(i + ":  outdegree:" + deg.outdegree(i) + " indegree:" + deg.indegree(i));
        output.println("sinks:");
        for (int i : deg.sinks()) output.print(i + " ");
        output.println();
        output.println("sources:");
        for (int i : deg.sources()) output.print(i + " ");
        output.println();
        output.println("is map:" + deg.isMap());
    }

    public int indegree(int v) {
        Digraph reverse = d.reverse();
        Degrees deg = new Degrees(reverse);
        return deg.outdegree(v);
    }

    public int outdegree(int v) {
        int outdeg = 0;
        for (int i : d.adj(v)) outdeg++;
        return outdeg;
    }

    public Iterable<Integer> sources() {
        Bag<Integer> sources = new Bag<Integer>();
        for (int i = 0; i < d.V(); i++)
            if (indegree(i) == 0)
                sources.add(i);
        return sources;
    }

    public Iterable<Integer> sinks() {
        Bag<Integer> sinks = new Bag<Integer>();
        for (int i = 0; i < d.V(); i++)
            if (outdegree(i) == 0)
                sinks.add(i);
        return sinks;
    }

    public boolean isMap() {
        for (int i = 0; i < d.V(); i++)
            if (outdegree(i) != 1) return false;
        return true;
    }
}
