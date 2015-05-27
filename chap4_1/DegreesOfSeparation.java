package chap4_1;//page 555

import java.io.BufferedInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class DegreesOfSeparation {
    public static void main(String[] args) {
        PrintWriter output = new PrintWriter(new OutputStreamWriter(System.out), true);
        Scanner input = new Scanner(new BufferedInputStream(System.in));

        String fileName = args[0];
        String delim = args[1];
        String source = args[2];

        SymbolGraph sg = new SymbolGraph(fileName, delim);
        if (!sg.contains(source)) {
            output.println("doesn't contain source");
            return;
        }

        BreadthFirstPaths bfp = new BreadthFirstPaths(sg.G(), sg.index(source));

        while (input.hasNext()) {
            String line = input.nextLine();
            if (!sg.contains(line)) {
                output.println("doesn't contain input");
                continue;
            }
            int index = sg.index(line);
            if (!bfp.hasPathTo(index)) {
                output.println("not connected");
                continue;
            }
            for (int i : bfp.pathTo(index))
                output.println(" " + sg.name(i));
        }
    }
}