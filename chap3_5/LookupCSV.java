package chap3_5;//page 491

import chap3_3.RedBlackBST;

import java.io.*;
import java.util.Scanner;

public class LookupCSV {
    public static void main(String[] args) {
        String fileName = args[0];
        Integer keyField = Integer.parseInt(args[1]);
        Integer valueField = Integer.parseInt(args[2]);

        File file = new File(fileName);
        if (!file.exists()) return;

        Scanner fileInput = null;
        try {
            fileInput = new Scanner(file);
        } catch (IOException ex) {
            System.err.println(ex);
        }

        RedBlackBST<String, String> st = new RedBlackBST<String, String>();
        while (fileInput.hasNextLine()) {
            String line = fileInput.nextLine();
            String[] items = line.split(",");
            st.put(items[keyField], items[valueField]);
        }

        Scanner input = new Scanner(new BufferedInputStream(System.in));
        PrintWriter output = new PrintWriter(new OutputStreamWriter(System.out), true);
        while (input.hasNext()) {
            String s = input.next();
            if (st.contains(s))
                output.println(st.get(s));
        }
    }
}