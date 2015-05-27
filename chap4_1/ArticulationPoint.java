package chap4_1;//4.1.35(page 562)

import chap1_3.Queue;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class ArticulationPoint {
    Queue<Integer> points;
    private int[] order;
    private int[] lowest;
    private int counter;

    public ArticulationPoint(Graph g) {
        points = new Queue<Integer>();
        order = new int[g.V()];
        lowest = new int[g.V()];

        for (int i = 0; i < g.V(); i++) {
            order[i] = -1;
            lowest[i] = -1;
        }

        for (int i = 0; i < g.V(); i++)
            if (order[i] == -1) dfs(g, i, i);
    }

    public static void main(String[] args) {
        String fileName = args[0];
        Graph g = new Graph(fileName);
        ArticulationPoint ap = new ArticulationPoint(g);

        PrintWriter output = new PrintWriter(new OutputStreamWriter(System.out), true);
        output.println("articulation points: ");
        for (Integer point : ap.points())
            output.print(point + " ");
        output.println();
    }

    private void dfs(Graph g, int u, int v) {
        int visitedChildren = 0;
        order[v] = counter++;
        lowest[v] = order[v];
        for (int w : g.adj(v)) {
            if (order[w] == -1) {
                visitedChildren++;
                dfs(g, v, w);
                lowest[v] = Math.min(lowest[v], lowest[w]);
                if (v != u && lowest[w] >= order[v]) points.enqueue(v);
            } else if (w != u) lowest[v] = Math.min(lowest[v], order[w]);
        }

        if (u == v && visitedChildren > 1) points.enqueue(v);
    }

    public Iterable<Integer> points() {
        return points;
    }
}