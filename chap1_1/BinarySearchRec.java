package chap1_1;

//page 25

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

public class BinarySearchRec {
    public static int rank(int key, int[] a) {
        return rank(key, a, 0, a.length - 1);
    }

    private static int rank(int key, int[] a, int left, int right) {
        if (left > right)
            return -1;
        int middle = left + (right - left) / 2;
        if (a[middle] < key)
            return rank(key, a, middle + 1, right);
        else if (a[middle] > key)
            return rank(key, a, left, middle - 1);
        else
            return middle;
    }

    public static void main(String[] args) {
        int[] whitelist = readInts(args[0]);
        Arrays.sort(whitelist);

        Scanner input = new Scanner(new BufferedInputStream(System.in));
        PrintWriter output = new PrintWriter(new OutputStreamWriter(System.out), true);

        while (input.hasNext()) {
            int key = input.nextInt();
            if (rank(key, whitelist) == -1)
                output.println(key);
        }
    }

    private static int[] readInts(String fileName) {
        Pattern whitespacePattern = Pattern.compile("\\p{javaWhitespace}+");
        Pattern everythingPattern = Pattern.compile("\\A");

        File file = new File(fileName);
        try {
            int[] result = null;
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String input = scanner.useDelimiter(everythingPattern).next();
                scanner.useDelimiter(whitespacePattern);

                ArrayList<String> tokens = new ArrayList<String>(Arrays.asList(whitespacePattern.split(input)));
                if (tokens.get(0).length() == 0) tokens.remove(0);

                result = new int[tokens.size()];
                for (int i = 0; i < result.length; i++) result[i] = Integer.parseInt(tokens.get(i));
            }

            return result;
        } catch (IOException ex) {
            System.err.println("Cannot open " + fileName);
            return null;
        }
    }
}