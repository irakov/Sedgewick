package chap4_2;//page 578+581(algorithm 4.5)

import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class Topological {
    private Iterable<Integer> order;

    public Topological(Digraph g) {
        DirectedCycle dc = new DirectedCycle(g);
        if (!dc.hasCycle()) {
            DepthFirstOrder dfo = new DepthFirstOrder(g);
            order = dfo.reversePostOrder();
        }
    }

    public static void main(String[] args) {
        PrintWriter output = new PrintWriter(new OutputStreamWriter(System.out), true);

        String fileName = args[0];
        String delim = args[1];
        SymbolDigraph sd = new SymbolDigraph(fileName, delim);
        Topological t = new Topological(sd.G());

        for (int i : t.order())
            output.println(sd.name(i));

    }

    public boolean isDAG() {
        return order != null;
    }

    public Iterable<Integer> order() {
        return order;
    }
}