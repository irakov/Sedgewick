package chap1_5;//algorithm 1.5
//page 222

import java.io.BufferedInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class QuickFind {
    private int[] id;
    private int count;

    public QuickFind(int size) {
        count = size;
        id = new int[count];
        for (int i = 0; i < count; i++)
            id[i] = i;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(new BufferedInputStream(System.in));
        PrintWriter output = new PrintWriter(new OutputStreamWriter(System.out), true);

        int size = input.nextInt();
        QuickFind qf = new QuickFind(size);
        while (input.hasNext()) {
            int p = input.nextInt();
            int q = input.nextInt();
            if (qf.connected(p, q))
                continue;
            qf.union(p, q);
            output.println(p + " " + q);
        }

        output.println("There are " + qf.count() + " components");
    }

    public int count() {
        return count;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int find(int p) {
        return id[p];
    }

    public void union(int p, int q) {
        int idP = find(p);
        int idQ = find(q);

        if (idP == idQ) return;
        for (int i = 0; i < id.length; i++)
            if (id[i] == idP)
                id[i] = idQ;
        count--;
    }
}