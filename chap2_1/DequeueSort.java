package chap2_1;//algorithm 2.1.14
//page 265

import java.io.BufferedInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

public class DequeueSort<Item extends Comparable<Item>> {
    public static void main(String[] args) {
        PrintWriter output = new PrintWriter(new OutputStreamWriter(System.out), true);

        String[] a = readStrings();
        DequeueSort<String> ds = new DequeueSort<String>();
        Deque<String> deque = new Deque<String>();
        for (int i = 0; i < a.length; i++)
            deque.pushRight(a[i]);
        ds.sort(deque);
        for (int i = 0; i < a.length; i++)
            output.print(deque.popLeft() + " ");

        output.println();
    }

    private static String[] readStrings() {
        Pattern everythingPattern = Pattern.compile("\\A");
        Pattern whitespacePattern = Pattern.compile("\\p{javaWhitespace}+");

        Scanner scanner = new Scanner(new BufferedInputStream(System.in));
        String input = "";

        if (scanner.hasNextLine()) {
            input = scanner.useDelimiter(everythingPattern).next();
            scanner.useDelimiter(whitespacePattern);
        }

        ArrayList<String> tokens = new ArrayList<String>(Arrays.asList(whitespacePattern.split(input)));
        if (tokens.get(0).length() == 0) tokens.remove(0);

        return tokens.toArray(new String[tokens.size()]);
    }

    public void sort(Deque<Item> deque) {
        int sorted = 0;
        while (sorted != deque.size()) {
            for (int i = 0; i < deque.size() - sorted - 1; i++) {
                Item item1 = deque.popLeft();
                Item item2 = deque.popLeft();
                if (item1.compareTo(item2) >= 0) {
                    deque.pushRight(item1);
                    deque.pushLeft(item2);
                } else {
                    deque.pushRight(item2);
                    deque.pushLeft(item1);
                }
            }
            Item item = deque.popLeft();
            for (int i = 0; i < sorted; i++)
                deque.pushRight(deque.popLeft());
            deque.pushRight(item);
            sorted++;
        }
    }
}