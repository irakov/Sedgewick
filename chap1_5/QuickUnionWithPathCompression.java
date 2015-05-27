package chap1_5;//1.5.12
//page 237

import java.io.BufferedInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class QuickUnionWithPathCompression {
    private int[] id;
    private int count;

    public QuickUnionWithPathCompression(int size) {
        count = size;
        id = new int[count];
        for (int i = 0; i < count; i++)
            id[i] = i;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(new BufferedInputStream(System.in));
        PrintWriter output = new PrintWriter(new OutputStreamWriter(System.out), true);

        int size = input.nextInt();
        QuickUnionWithPathCompression qu = new QuickUnionWithPathCompression(size);
        while (input.hasNext()) {
            int p = input.nextInt();
            int q = input.nextInt();
            if (qu.connected(p, q))
                continue;
            qu.union(p, q);
            output.println(p + " " + q);
        }

        output.println("There are " + qu.count() + " components");
    }

    public int count() {
        return count;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int find(int p) {
        int root = p;
        while (root != id[root])
            root = id[root];
        while (p != root) {
            int temp = id[p];
            id[p] = root;
            p = temp;
        }
        return root;
    }

    public void union(int p, int q) {
        int idP = find(p);
        int idQ = find(q);

        if (idP == idQ) return;
        id[idP] = idQ;
        count--;
    }
}