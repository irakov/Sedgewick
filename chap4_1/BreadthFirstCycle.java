package chap4_1;//for 4.1.18(page 559)

import chap1_3.Queue;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class BreadthFirstCycle {
    private boolean[] marked;
    private int[] edgeTo;
    private int[] distTo;
    private int source;
    private Pair cycleLimits;

    public BreadthFirstCycle(Graph g, int s) {
        source = s;
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        distTo = new int[g.V()];
        for (int i = 0; i < g.V(); i++) distTo[i] = Integer.MIN_VALUE;
        bfs(g, s);
    }

    public static void main(String[] args) {
        String fileName = args[0];
        int s = Integer.parseInt(args[1]);
        Graph g = new Graph(fileName);
        BreadthFirstCycle bfc = new BreadthFirstCycle(g, s);

        PrintWriter output = new PrintWriter(new OutputStreamWriter(System.out), true);
        output.println(bfc.distToCycle());
    }

    private void bfs(Graph g, int v) {
        marked[v] = true;
        distTo[v] = 0;
        Queue<Integer> queue = new Queue<Integer>();
        queue.enqueue(v);

        while (!queue.isEmpty()) {
            int i = queue.dequeue();
            for (int j : g.adj(i)) {
                if (marked[j] && j != edgeTo[i]) {
                    cycleLimits = new Pair(i, j);
                    return;
                }

                if (!marked[j]) {
                    marked[j] = true;
                    distTo[j] = distTo[i] + 1;
                    edgeTo[j] = i;
                    queue.enqueue(j);
                }
            }
        }
    }

    public int distToCycle() {
        if (cycleLimits == null) return Integer.MAX_VALUE;
        return distTo[cycleLimits.first] + distTo[cycleLimits.second] + 1;
    }

    public class Pair {
        private int first;
        private int second;

        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }

        public int getFirst() {
            return first;
        }

        public int getSecond() {
            return second;
        }
    }
}