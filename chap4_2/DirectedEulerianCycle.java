package chap4_2;//4.2.20 (page 598)

import chap4_1.Bag;
import chap1_3.Stack;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Iterator;

public class DirectedEulerianCycle {
    Bag<Integer> cycle = new Bag<Integer>();

    public DirectedEulerianCycle(Digraph d) {
        KosarajuSCC scc = new KosarajuSCC(d);
        if (scc.count() != 1)
            throw new RuntimeException("There are more strongly connected components");

        Degrees deg = new Degrees(d);
        for (int i = 0; i < d.V(); i++)
            if (deg.indegree(i) != deg.outdegree(i))
                throw new RuntimeException("The indegrees and outdegrees do not match");

        Iterator<Integer>[] adjencies = (Iterator<Integer>[]) new Iterator[d.V()];
        for (int i = 0; i < d.V(); i++) adjencies[i] = d.adj(i).iterator();

        Stack<Integer> nodes = new Stack<Integer>();
        nodes.push(0);
        while (!nodes.isEmpty()) {
            int node = nodes.pop();
            cycle.add(node);
            while (adjencies[node].hasNext())
                nodes.push(adjencies[node].next());
        }

    }

    public static void main(String[] args) {
        PrintWriter output = new PrintWriter(new OutputStreamWriter(System.out), true);

        Digraph d = new Digraph(args[0]);
        DirectedEulerianCycle dec = new DirectedEulerianCycle(d);
        Iterable<Integer> cycle = dec.eulerianCycle();

        output.println("eulerian cycle:");
        for (Integer i : cycle) output.print(i + " ");
        output.println();
    }

    public Iterable<Integer> eulerianCycle() {
        return cycle;
    }
}
