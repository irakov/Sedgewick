package chap1_3;
//page 162
//1.3.9

import java.io.BufferedInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class LeftParentheses {
    public static void main(String[] args) {
        Scanner input = new Scanner(new BufferedInputStream(System.in));
        PrintWriter output = new PrintWriter(new OutputStreamWriter(System.out), true);

        Stack<String> reversedInputStack = new Stack<String>();
        Stack<String> correctedStack = new Stack<String>();

        while (input.hasNext()) {
            String s = input.next();
            reversedInputStack.push(s);
        }

        boolean leftTermFollows = false;
        boolean leftGroupFollows = false;
        boolean valuesStarted = false;
        int parenToClose = 0;

        while (!reversedInputStack.isEmpty()) {
            String s = reversedInputStack.pop();
            if (s.equals(")")) {
                leftTermFollows = false;
                if (valuesStarted)
                    leftGroupFollows = true;
                parenToClose++;
                correctedStack.push(s);
            } else if (s.equals("+")) {
                leftTermFollows = true;
                correctedStack.push(s);
            } else if (s.equals("-")) {
                leftTermFollows = true;
                correctedStack.push(s);
            } else if (s.equals("*")) {
                leftTermFollows = true;
                correctedStack.push(s);
            } else if (s.equals("/")) {
                leftTermFollows = true;
                correctedStack.push(s);
            } else {
                //values here
                correctedStack.push(s);
                if (leftTermFollows == true) {
                    if (parenToClose > 0) {
                        correctedStack.push("(");
                        parenToClose--;

                        if (parenToClose > 0)
                            if (leftGroupFollows) {
                                correctedStack.push("(");
                                parenToClose--;
                            }
                    }

                    leftTermFollows = false;
                    valuesStarted = true;
                }
            }
        }

        while (!correctedStack.isEmpty()) {
            String s = correctedStack.pop();
            output.print(s + " ");
        }
        output.println();
    }
}