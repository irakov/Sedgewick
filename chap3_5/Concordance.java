package chap3_5;//3.5.20(510)


import java.io.*;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Concordance {
    public static void main(String[] args) {
        final int CONTEXT = 5;
        Scanner input = new Scanner(new BufferedInputStream(System.in));
        PrintWriter output = new PrintWriter(new OutputStreamWriter(System.out), true);
        RedBlackBST<String, SET<Integer>> st = new RedBlackBST<String, SET<Integer>>();

        output.println("Building concordance");
        String[] words = null;
        try {
            words = readStrings(args[0]);
        } catch (FileNotFoundException ex) {
            System.err.println(ex);
            return;
        }

        for (int i = 0; i < words.length; i++) {
            if (!st.contains(words[i]))
                st.put(words[i], new SET<Integer>());
            SET<Integer> set = st.get(words[i]);
            set.add(i);
        }
        output.println();

        output.println("Querying");

        while (input.hasNext()) {
            String query = input.next();
            if (!st.contains(query)) continue;
            SET<Integer> set = st.get(query);
            for (Integer position : set.keys()) {
                for (int i = Math.max(0, position - CONTEXT + 1); i < position; i++)
                    output.print(words[i] + " ");
                output.print("<<" + query + ">> ");
                for (int i = position + 1; i < Math.min(position + CONTEXT, words.length); i++)
                    output.print(words[i] + " ");
                output.println();
            }
            output.println();
        }
    }

    public static String[] readStrings(String fileName) throws FileNotFoundException {
        Pattern everythingPattern = Pattern.compile("\\A");
        Pattern whitespacePattern = Pattern.compile("\\p{javaWhitespace}+");

        Scanner scanner = new Scanner(new File(fileName));
        String input = "";

        if (scanner.hasNextLine()) {
            input = scanner.useDelimiter(everythingPattern).next();
            scanner.useDelimiter(whitespacePattern);
        }

        return input.split("\\s+");
    }
}