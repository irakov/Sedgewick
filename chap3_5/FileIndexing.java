package chap3_5;//page 501

import chap4_2.RedBlackBST;

import java.io.*;
import java.util.Scanner;

public class FileIndexing {
    public static void main(String[] args) {
        chap4_2.RedBlackBST<String, SET<File>> bst = new RedBlackBST<String, SET<File>>();
        for (String fileName : args) {
            File file = new File(fileName);
            if (!file.exists()) continue;

            Scanner fileInput = null;
            try {
                fileInput = new Scanner(file);
            } catch (IOException ex) {
                System.err.println(ex);
                return;
            }

            while (fileInput.hasNext()) {
                String s = fileInput.next();
                if (!bst.contains(s)) bst.put(s, new SET<File>());
                SET<File> set = bst.get(s);
                set.add(file);
            }
        }

        Scanner input = new Scanner(new BufferedInputStream(System.in));
        PrintWriter output = new PrintWriter(new OutputStreamWriter(System.out), true);

        while (input.hasNext()) {
            String query = input.next();
            if (bst.contains(query))
                output.println(bst.get(query));
        }
    }
}