package chap2_3;//2.3.22
//page 306

import java.io.BufferedInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

public class BentleyMcIlroy {
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
        sort(items, 0, items.length - 1);
    }

    private static <T extends Comparable<T>> void sort(T[] items, int left, int right) {
        if (left >= right)
            return;
        Pair<Integer, Integer> pivot = partition(items, left, right);
        sort(items, left, pivot.x);
        sort(items, pivot.y, right);
    }

    private static <T extends Comparable<T>> Pair<Integer, Integer> partition(T[] items, int left, int right) {
        T pivot = items[left];
        int i = left;
        int j = right + 1;
        int p = left;
        int q = right + 1;

        while (true) {
            while (items[++i].compareTo(pivot) <= 0) {
                if (items[i].compareTo(pivot) == 0) {
                    p++;
                    T temp = items[p];
                    items[p] = items[i];
                    items[i] = temp;
                }
                if (i == right)
                    break;
            }

            while (items[--j].compareTo(pivot) >= 0) {
                if (items[j].compareTo(pivot) == 0) {
                    q--;
                    T temp = items[q];
                    items[q] = items[j];
                    items[j] = temp;
                }
                if (j == left)
                    break;
            }

            if (i >= j)
                break;

            T temp = items[i];
            items[i] = items[j];
            items[j] = temp;
        }

        //structure: [left p][p+chap1.chap1_1 j][j+chap1.chap1_1 q-chap1.chap1_1][q right]
        //				 =     <        >         =
        if (p > q)
            p = q;
        T[] temps = (T[]) new Comparable[p - left + 1];
        for (int k = left; k <= p; k++)
            temps[k - left] = items[k];
        for (int k = p + 1; k <= j; k++)
            items[k - p - 1 + left] = items[k];
        for (int k = 0; k <= p - left; k++)
            items[k + j - p + left] = temps[k];

        temps = (T[]) new Comparable[right - q + 1];
        for (int k = q; k <= right; k++)
            temps[k - q] = items[k];
        for (int k = q - 1; k >= j + 1; k--)
            items[right - q + 1 + k] = items[k];
        for (int k = 0; k <= right - q; k++)
            items[j + 1 + k] = temps[k];

        return new Pair<Integer, Integer>(j - p - 1 + left, right - q + j + 2);
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

    private static class Pair<X, Y> {
        public final X x;
        public final Y y;

        public Pair(X x, Y y) {
            this.x = x;
            this.y = y;
        }
    }
}