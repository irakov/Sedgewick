package chap4_1;//4.1.36(page 562)

import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class Bridge {
    Queue<Pair> bridges;
    private int[] order;
    private int[] lowest;
    private int counter;

    public Bridge(Graph g) {
        bridges = new Queue<Pair>();
        order = new int[g.V()];
        lowest = new int[g.V()];

        for (int i = 0; i < g.V(); i++) {
            order[i] = -1;
            lowest[i] = -1;
        }

        for (int i = 0; i < g.V(); i++)
            if (order[i] == -1)
                dfs(g, i, i);
    }

    public static void main(String[] args) {
        String fileName = args[0];
        Graph g = new Graph(fileName);
        Bridge bridge = new Bridge(g);

        PrintWriter output = new PrintWriter(new OutputStreamWriter(System.out), true);
        output.println("bridges...");
        for (Pair pair : bridge.bridges())
            output.println(pair.getFirst() + " - " + pair.getSecond());
    }

    private void dfs(Graph g, int u, int v) {
        order[v] = counter++;
        lowest[v] = order[v];

        for (int w : g.adj(v)) {
            if (order[w] == -1) {
                dfs(g, v, w);
                lowest[v] = Math.min(lowest[v], lowest[w]);
                if (lowest[w] == order[w])
                    bridges.enqueue(new Pair(v, w));
            } else if (w != u)
                lowest[v] = Math.min(lowest[v], order[w]);
        }
    }

    public Iterable<Pair> bridges() {
        return bridges;
    }

    public class Pair implements Comparable<Pair> {
        private Integer first;
        private Integer second;

        public Pair(Integer first, Integer second) {
            this.first = first;
            this.second = second;
        }

        public Integer getFirst() {
            return first;
        }

        public Integer getSecond() {
            return second;
        }

        public int compareTo(Pair other) {
            int result = this.first.compareTo(other.first);
            if (result != 0) return result;
            return this.second.compareTo(other.second);
        }
    }
}