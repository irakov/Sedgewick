package chap2_4;//page 324
//algorithm 2.7

import java.io.BufferedInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Heapsort {
    private static <T extends Comparable<T>> void sink(T[] items, int position, int size) {
        while (position * 2 <= size) {
            int lookup = 2 * position;
            if (lookup < size && items[lookup - 1].compareTo(items[lookup]) < 0)
                lookup++;
            if (items[position - 1].compareTo(items[lookup - 1]) >= 0)
                break;
            exchange(items, position, lookup);
            position = lookup;
        }
    }

    private static <T extends Comparable<T>> void exchange(T[] items, int i, int j) {
        T temp = items[i - 1];
        items[i - 1] = items[j - 1];
        items[j - 1] = temp;
    }

    private static <T extends Comparable<T>> void sort(T[] items) {
        int size = items.length;
        for (int i = size / 2; i >= 1; i--)
            sink(items, i, size);
        while (size > 1) {
            exchange(items, 1, size--);
            sink(items, 1, size);
        }
    }

    public static void main(String[] args) {
        PrintWriter output = new PrintWriter(new OutputStreamWriter(System.out), true);

        String[] items = readStrings();
        sort(items);
        for (int i = 0; i < items.length; i++)
            output.print(items[i] + " ");
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
}