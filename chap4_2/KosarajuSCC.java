package chap4_2;//algorithm 4.6 (page 587)

import chap1_3.Bag;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class KosarajuSCC {
    private int[] ids;
    private boolean[] marked;
    private int count;

    public KosarajuSCC(Digraph d) {
        ids = new int[d.V()];
        marked = new boolean[d.V()];

        for (Integer i : reverseDigraphTopoSort(d))
            if (!marked[i]) {
                dfs(d, i);
                count++;
            }
    }

    public static void main(String[] args) {
        PrintWriter output = new PrintWriter(new OutputStreamWriter(System.out), true);

        String fileName = args[0];
        Digraph d = new Digraph(fileName);

        KosarajuSCC scc = new KosarajuSCC(d);
        output.println(scc.count() + " components");

        Bag<Integer>[] bag = (Bag<Integer>[]) new Bag[scc.count()];
        for (int i = 0; i < scc.count(); i++) bag[i] = new Bag<Integer>();
        for (int i = 0; i < d.V(); i++) bag[scc.id(i)].add(i);

        for (int i = 0; i < scc.count; i++) {
            output.println("component " + i);
            for (int j : bag[i]) output.print(j + " ");
            output.println();
        }

    }

    private Iterable<Integer> reverseDigraphTopoSort(Digraph d) {
        Digraph reverse = d.reverse();
        DepthFirstOrder dfo = new DepthFirstOrder(reverse);
        return dfo.reversePostOrder();
    }

    private void dfs(Digraph d, int i) {
        marked[i] = true;
        ids[i] = count;
        for (int j : d.adj(i))
            if (!marked[j])
                dfs(d, j);
    }

    public boolean stronglyConnected(int v, int w) {
        return ids[v] == ids[w];
    }

    public int id(int v) {
        return ids[v];
    }

    public int count() {
        return count;
    }

}




