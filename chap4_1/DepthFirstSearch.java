package chap4_1;//page 531

import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class DepthFirstSearch {
    private boolean[] marked;
    private int count;

    public DepthFirstSearch(Graph g, int s) {
        marked = new boolean[g.V()];
        count = 0;
        dfs(g, s);
    }

    public static void main(String[] args) {
        PrintWriter output = new PrintWriter(new OutputStreamWriter(System.out), true);

        String fileName = args[0];
        Graph g = new Graph(fileName);
        int s = Integer.parseInt(args[1]);
        DepthFirstSearch dfs = new DepthFirstSearch(g, s);
        for (int v = 0; v < g.V(); v++)
            if (dfs.marked(v))
                output.print(v + " ");

        output.println();
        if (dfs.count() != g.V()) output.println("not connected");
        else output.println("connected");
    }

    private void dfs(Graph g, int v) {
        count++;
        marked[v] = true;
        for (int i : g.adj(v))
            if (!marked[i])
                dfs(g, i);
    }

    public boolean marked(int v) {
        return marked[v];
    }

    public int count() {
        return count;
    }
}