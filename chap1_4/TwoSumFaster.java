package chap1_4;//1.4.15
//page 210

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

public class TwoSumFaster {
    public static void main(String[] args) {
        PrintWriter output = new PrintWriter(new OutputStreamWriter(System.out), true);

        int[] a = readInts(args[0]);
        TwoSumFaster sum = new TwoSumFaster();
        output.println(sum.count(a));
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

    public int count(int[] a) {
        int result = 0;
        Arrays.sort(a);    //n log n
        int left = 0;
        int right = a.length - 1;
        while (left != right)//n
        {
            if (a[left] + a[right] > 0)
                right--;
            else if (a[left] + a[right] < 0)
                left++;
            else {
                result++;
                right--;
            }
        }

        return result;
    }
}