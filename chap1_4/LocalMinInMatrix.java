package chap1_4;//1.4.19
//page 210

import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class LocalMinInMatrix {
    public static void main(String[] args) {
        PrintWriter output = new PrintWriter(new OutputStreamWriter(System.out), true);

        LocalMinInMatrix l = new LocalMinInMatrix();
        int[][] a = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, -1, 11, 12}, {13, 14, 15, 16}};
        Coords result = l.find(a, 4);
        output.println(result.line + " " + result.col);
    }

    //assuming we can also use the margins, we have a guaranteed local minimum
    public Coords find(int[][] a, int n) {
        return find(a, n, new Coords(0, 0));
    }

    private Coords find(int[][] a, int n, Coords coords) {
        int i = coords.line;
        int j = coords.col;
        boolean cond1 = false;
        boolean cond2 = false;
        boolean cond3 = false;
        boolean cond4 = false;

        if (i < n - 1 && a[i][j] < a[i + 1][j])
            cond1 = true;
        if (j < n - 1 && a[i][j] < a[i][j + 1])
            cond2 = true;
        if (i > 0 && a[i][j] < a[i - 1][j])
            cond3 = true;
        if (j > 0 && a[i][j] < a[i][j - 1])
            cond4 = true;
        if (cond1 && cond2 && cond3 && cond4)
            return coords;

        Coords minCoords = null;
        int min = Integer.MAX_VALUE;
        if (i < n - 1 && a[i + 1][j] < min) {
            min = a[i + 1][j];
            minCoords = new Coords(i + 1, j);
        }
        if (j < n - 1 && a[i][j + 1] < min) {
            min = a[i][j + 1];
            minCoords = new Coords(i, j + 1);
        }
        if (i > 0 && a[i - 1][j] < min) {
            min = a[i - 1][j];
            minCoords = new Coords(i - 1, j);
        }
        if (j > 0 && a[i][j - 1] < min) {
            min = a[i][j - 1];
            minCoords = new Coords(i, j - 1);
        }
        a[i][j] = Integer.MAX_VALUE;
        if (minCoords != null)
            return find(a, n, minCoords);

        return null;
    }

    public class Coords {
        int col, line;

        public Coords(int line, int col) {
            this.col = col;
            this.line = line;
        }
    }
}