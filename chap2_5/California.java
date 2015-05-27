package chap2_5;//2.5.16(page 355)

import java.io.BufferedInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.regex.Pattern;

public class California {
    public static final Comparator<String> CANDIDATES_ORDER = new CandidateComparator();

    public static void main(String[] args) {
        Pattern everythingPattern = Pattern.compile("\\A");
        Pattern whitespacePattern = Pattern.compile("\\p{javaWhitespace}+");

        Scanner scanner = new Scanner(new BufferedInputStream(System.in));
        PrintWriter output = new PrintWriter(new OutputStreamWriter(System.out), true);

        String input = "";
        while (scanner.hasNext()) {
            input = scanner.useDelimiter(everythingPattern).next();
            scanner.useDelimiter(whitespacePattern);
        }

        String[] candidates = input.toUpperCase().split("\\n+");
        int count = candidates.length;
        Arrays.sort(candidates, California.CANDIDATES_ORDER);
        for (int i = 0; i < count; i++)
            output.println(candidates[i]);
    }

    private static class CandidateComparator implements Comparator<String> {
        private static String order = "RWQOJMVAHBSGZXNTCIEKUPDYFL";

        public int compare(String a, String b) {
            if (a == b) return 0;
            int size = Math.min(a.length(), b.length());
            for (int i = 0; i < size; i++) {
                int indexA = order.indexOf(a.charAt(i));
                int indexB = order.indexOf(b.charAt(i));
                if (indexA < indexB) return -1;
                if (indexB < indexA) return 1;
            }
            return a.length() - b.length();
        }
    }
}