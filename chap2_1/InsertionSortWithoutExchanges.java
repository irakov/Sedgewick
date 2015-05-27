package chap2_1;//2.1.25
//page 267

import java.io.BufferedInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

public class InsertionSortWithoutExchanges {
    public static <T extends Comparable<T>> void sort(T[] a) {
        for (int i = 1; i < a.length; i++) {
            if (a[i].compareTo(a[0]) < 0) {
                T item = a[i];
                a[i] = a[0];
                a[0] = item;
            }
            T temp = a[i];
            int j = i - 1;
            while (j >= 0 && a[j].compareTo(temp) > 0) {
                a[j + 1] = a[j];
                j--;
            }
            a[++j] = temp;
        }
    }

    public static void main(String[] args) {
        PrintWriter output = new PrintWriter(new OutputStreamWriter(System.out), true);

        String[] a = readStrings();
        sort(a);
        for (int i = 0; i < a.length; i++)
            output.print(a[i] + " ");

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