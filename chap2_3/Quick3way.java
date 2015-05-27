package chap2_3;//page 299

import java.io.BufferedInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Quick3way {
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
        if (left >= right) return;

        int lt = left;
        int gt = right;
        int i = left + 1;
        T pivot = items[left];

        while (i <= gt) {
            int result = items[i].compareTo(pivot);
            if (result < 0) {
                T temp = items[i];
                items[i] = items[lt];
                items[lt] = temp;
                i++;
                lt++;
            } else if (result > 0) {
                T temp = items[i];
                items[i] = items[gt];
                items[gt] = temp;
                gt--;
            } else
                i++;
        }
        sort(items, left, lt - 1);
        sort(items, gt + 1, right);
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