package chap4_2;//page 573

import chap1_3.Stack;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class DepthFirstDirectedPaths {
    private boolean[] marked;
    private int[] edgeTo;
    private int s;

    public DepthFirstDirectedPaths(Digraph g, int s) {
        this.s = s;
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        dfs(g, s);
    }

    public static void main(String[] args) {
        PrintWriter output = new PrintWriter(new OutputStreamWriter(System.out), true);

        String fileName = args[0];
        int source = Integer.parseInt(args[1]);
        Digraph g = new Digraph(fileName);
        DepthFirstDirectedPaths dfdp = new DepthFirstDirectedPaths(g, source);

        for (int i = 0; i < g.V(); i++) {
            if (!dfdp.hasPathTo(i)) output.println(i + " is not connected to " + source);
            else {
                for (int j : dfdp.pathTo(i))
                    output.print(j + " ");
                output.println();
            }
        }
    }

    private void dfs(Digraph g, int s) {
        marked[s] = true;
        for (int i : g.adj(s))
            if (!marked[i]) {
                edgeTo[i] = s;
                dfs(g, i);
            }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = v; i != s; i = edgeTo[i])
            stack.push(i);
        stack.push(s);
        return stack;
    }
}