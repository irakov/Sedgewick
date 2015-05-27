package chap4_1;//4.1.16(page 559),4.1.17,4.1.18

//4.chap1.chap1_1.17 The Wiener index of a graph is the sum of the lengths of the shortest paths between all pairs of vertices.
//Mathematical chemists use this quantity to analyze molecular graphs, where vertices correspond to atoms and edges correspond to chemical 
//bonds. Add a method wiener() to GraphProperties that returns the Wiener index of a graph.

import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class GraphProperties {
    private Graph g;

    public GraphProperties(Graph g) {
        ConnectedComponent cc = new ConnectedComponent(g);
        if (cc.count() != 1) throw new IllegalArgumentException();
        this.g = g;
    }

    public static void main(String[] args) {
        String fileName = args[0];
        Graph g = new Graph(fileName);

        PrintWriter output = new PrintWriter(new OutputStreamWriter(System.out), true);

        GraphProperties gp = new GraphProperties(g);
        for (int i = 0; i < g.V(); i++) {
            output.printf("eccentricity of vertex %d: %d", i, gp.eccentricity(i));
            output.println();
        }
        output.println("graph diameter: " + gp.diameter());
        output.println("graph radius: " + gp.radius());
        output.println("graph center: " + gp.center());
        output.println("graph wiener index: " + gp.wiener());
        output.println("graph girth: " + gp.girth());
    }

    public int eccentricity(int v) {
        BreadthFirstPaths bfp = new BreadthFirstPaths(g, v);
        int max = -1;
        for (int i = 0; i < g.V(); i++) {
            if (i == v) continue;
            int dist = bfp.distTo(i);
            if (dist > max) max = dist;
        }
        return max;
    }

    public int diameter() {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < g.V(); i++) {
            int ecc = eccentricity(i);
            if (ecc > max) max = ecc;
        }
        return max;
    }

    public int radius() {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < g.V(); i++) {
            int rad = eccentricity(i);
            if (rad < min) min = rad;
        }
        return min;
    }

    public int center() {
        int radius = radius();
        for (int i = 0; i < g.V(); i++)
            if (eccentricity(i) == radius) return i;
        return Integer.MIN_VALUE;
    }

    public int wiener() {
        int sum = 0;
        for (int i = 0; i < g.V() - 1; i++)
            for (int j = i + 1; j < g.V(); j++) {
                BreadthFirstPaths bfp = new BreadthFirstPaths(g, i);
                sum += bfp.distTo(j);
            }
        return sum;
    }

    public int girth() {
        Cycle cycle = new Cycle(g);
        if (!cycle.hasCycle()) return Integer.MAX_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < g.V(); i++) {
            BreadthFirstCycle bfc = new BreadthFirstCycle(g, i);
            int minCycle = bfc.distToCycle();
            if (minCycle < min) min = minCycle;
        }

        return min;
    }
}