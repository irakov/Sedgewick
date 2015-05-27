package chap3_5;//page 490

import java.io.BufferedInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class Dedup {
    public static void main(String[] args) {
        Scanner input = new Scanner(new BufferedInputStream(System.in));
        PrintWriter output = new PrintWriter(new OutputStreamWriter(System.out), true);

        HashSET<String> hash = new HashSET<String>();
        while (input.hasNext()) {
            String s = input.next();
            if (!hash.contains(s)) {
                hash.add(s);
                output.println(s);
            }
        }
    }
}