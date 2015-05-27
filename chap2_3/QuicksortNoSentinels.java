package chap2_3;//2.3.17
//page 305

import java.io.BufferedInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

public class QuicksortNoSentinels {
    private static Random random;

    static {
        random = new Random(System.currentTimeMillis());
    }

    public static <T extends Comparable<T>> void sort(T[] items) {
        int size = items.length;
        for (int i = 0; i < size; i++) {
            int index = i + random.nextInt(size - i);
            T temp = items[i];
            items[i] = items[index];
            items[index] = temp;
        }

        int maxPos = 0;
        for (int i = 0; i <= items.length - 1; i++)
            if (items[i].compareTo(items[maxPos]) > 0)
                maxPos = i;

        T temp = items[maxPos];
        items[maxPos] = items[items.length - 1];
        items[items.length - 1] = temp;

        sort(items, 0, items.length - 1);
    }

    private static <T extends Comparable<T>> void sort(T[] items, int left, int right) {
        if (right <= left) return;
        int i = partition(items, left, right);
        sort(items, left, i - 1);
        sort(items, i + 1, right);
    }

    private static <T extends Comparable<T>> int partition(T[] items, int left, int right) {
        int i = left;
        int j = right + 1;
        T pivot = items[left];
        while (true) {
            while (items[++i].compareTo(pivot) < 0) ;
            while (items[--j].compareTo(pivot) > 0) ;

            if (i >= j) break;

            T temp = items[i];
            items[i] = items[j];
            items[j] = temp;
        }

        T temp = items[left];
        items[left] = items[j];
        items[j] = temp;

        return j;
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