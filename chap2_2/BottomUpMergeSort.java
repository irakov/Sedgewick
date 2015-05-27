package chap2_2;//page 271+278

import java.io.BufferedInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

public class BottomUpMergeSort {
    public static <T extends Comparable<T>> void sort(T[] a) {
        T[] aux = (T[]) new Comparable[a.length];
        for (int size = 1; size < a.length; size += size) {
            for (int i = 0; i + size < a.length; i += 2 * size)
                merge(a, aux, i, i + size - 1, Math.min(i + 2 * size - 1, a.length - 1));
        }
    }

    private static <T extends Comparable<T>> void merge(T[] a, T[] aux, int left, int middle, int right) {
        for (int i = left; i <= right; i++)
            aux[i] = a[i];
        int i = left;
        int j = middle + 1;
        for (int k = left; k <= right; k++) {
            if (i > middle) {
                a[k] = aux[j];
                j++;
            } else if (j > right) {
                a[k] = aux[i];
                i++;
            } else if (aux[i].compareTo(aux[j]) < 0) {
                a[k] = aux[i];
                i++;
            } else {
                a[k] = aux[j];
                j++;
            }
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