package chap2_2;//2.2.10
//page 285

import java.io.BufferedInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

public class FasterMerge {
    public static <T extends Comparable<T>> void sort(T[] a) {
        T[] aux = (T[]) new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);
    }

    private static <T extends Comparable<T>> void sort(T[] a, T[] aux, int left, int right) {
        if (left < right) {
            int middle = left + (right - left) / 2;
            sort(a, aux, left, middle);
            sort(a, aux, middle + 1, right);
            merge(a, aux, left, middle, right);
        }
    }

    private static <T extends Comparable<T>> void merge(T[] a, T[] aux, int left, int middle, int right) {
        for (int i = left; i <= middle; i++)
            aux[i] = a[i];
        for (int i = middle + 1; i <= right; i++)
            aux[i] = a[right - i + middle + 1];
        int i = left;
        int j = right;
        for (int k = left; k <= right; k++) {
            if (aux[i].compareTo(aux[j]) < 0) {
                a[k] = aux[i];
                i++;
            } else {
                a[k] = aux[j];
                j--;
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