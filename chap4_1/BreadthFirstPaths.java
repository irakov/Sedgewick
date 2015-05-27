package chap4_1;//algorithm 4.2(540)
//with 4.chap1.chap1_1.13(559)

import chap1_3.Queue;
import chap1_3.Stack;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class BreadthFirstPaths {
    private boolean[] marked;
    private int[] edgeTo;
    private int[] distTo;
    private int source;

    public BreadthFirstPaths(Graph g, int s) {
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        distTo = new int[g.V()];
        for (int i = 0; i < g.V(); i++) distTo[i] = Integer.MIN_VALUE;
        source = s;
        bfs(g, s);
    }

    public static void main(String[] args) {
        String fileName = args[0];
        int s = Integer.parseInt(args[1]);
        Graph g = new Graph(fileName);
        BreadthFirstPaths bfp = new BreadthFirstPaths(g, s);

        PrintWriter output = new PrintWriter(new OutputStreamWriter(System.out), true);

        for (int i = 0; i < g.V(); i++) {
            if (bfp.hasPathTo(i)) {
                output.printf("from %d to %d - distance %d: ", s, i, bfp.distTo(i));
                for (int j : bfp.pathTo(i))
                    output.print(j + " ");
                output.println();
            }
        }
    }

    private void bfs(Graph g, int v) {
        marked[v] = true;
        distTo[v] = 0;
        Queue<Integer> queue = new Queue<Integer>();
        queue.enqueue(v);

        while (!queue.isEmpty()) {
            int i = queue.dequeue();
            for (int j : g.adj(i)) {
                if (!marked[j]) {
                    marked[j] = true;
                    edgeTo[j] = i;
                    distTo[j] = distTo[i] + 1;
                    queue.enqueue(j);
                }
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public int distTo(int v) {
        return distTo[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>();
        for (int i = v; i != source; i = edgeTo[i])
            path.push(i);
        path.push(source);
        return path;
    }
}