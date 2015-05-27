package chap2_3;//2.3.20
//page 306

import java.io.BufferedInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.*;
import java.util.Stack;
import java.util.regex.Pattern;

public class NonRecursiveQuicksort {
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

        T[] result = (T[]) new Comparable[items.length];
        Stack<Params> stack = new Stack<Params>();
        stack.push(new Params(0, items.length - 1));
        sort(stack, items);
    }

    private static <T extends Comparable<T>> void sort(Stack<Params> stack, T[] items) {
        while (stack.size() != 0) {
            Params params = stack.pop();
            if (params.Right > params.Left) {
                int i = partition(items, params.Left, params.Right);
                if (i - params.Left > params.Right - i) {
                    stack.push(new Params(params.Left, i - 1));
                    stack.push(new Params(i + 1, params.Right));
                } else {
                    stack.push(new Params(i + 1, params.Right));
                    stack.push(new Params(params.Left, i - 1));
                }
            }
        }
    }

    private static <T extends Comparable<T>> int partition(T[] items, int left, int right) {
        int i = left;
        int j = right + 1;
        T pivot = items[left];
        while (true) {
            while (items[++i].compareTo(pivot) < 0)
                if (i == right)
                    break;
            while (items[--j].compareTo(pivot) > 0)
                if (j == left)
                    break;

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

    private static class Params {
        public final int Left;
        public final int Right;

        public Params(int left, int right) {
            Left = left;
            Right = right;
        }
    }
}