package chap2_2;//2.2.14+2.2.15
//page 285

import java.io.BufferedInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

public class BottomUpQueueMergeSort<Item extends Comparable<Item>> {
    public static void main(String[] args) {
        PrintWriter output = new PrintWriter(new OutputStreamWriter(System.out), true);

        String[] a = readStrings();
        BottomUpQueueMergeSort<String> sort = new BottomUpQueueMergeSort<String>();
        Queue<String> result = sort.sort(a);
        for (String s : result)
            output.print(s + " ");

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

    public Queue<Item> sort(Item[] a) {
        Queue<Queue<Item>> megaQueue = new Queue<Queue<Item>>();
        for (int i = 0; i < a.length; i++) {
            Queue<Item> queue = new Queue<Item>();
            queue.enqueue(a[i]);
            megaQueue.enqueue(queue);
        }
        sort(megaQueue);
        return megaQueue.dequeue();
    }

    private void sort(Queue<Queue<Item>> queue) {
        while (queue.size() > 1) {
            queue.enqueue(merge(queue.dequeue(), queue.dequeue()));
        }
    }

    private Queue<Item> merge(Queue<Item> left, Queue<Item> right) {
        Queue<Item> result = new Queue<Item>();
        while (left.size() > 0 && right.size() > 0) {
            Item leftItem = left.peek();
            Item rightItem = right.peek();
            if (leftItem.compareTo(rightItem) < 0)
                result.enqueue(left.dequeue());
            else
                result.enqueue(right.dequeue());
        }
        while (left.size() > 0)
            result.enqueue(left.dequeue());
        while (right.size() > 0)
            result.enqueue(right.dequeue());

        return result;
    }
}