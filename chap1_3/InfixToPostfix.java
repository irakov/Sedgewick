package chap1_3;//page 162
//chap1.chap1_1.3.10
//usage java InfixToPostfix < operations.txt

import chap4_2.Stack;

import java.io.BufferedInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class InfixToPostfix {
    public static void main(String[] args) {
        Scanner input = new Scanner(new BufferedInputStream(System.in));
        PrintWriter output = new PrintWriter(new OutputStreamWriter(System.out), true);

        chap4_2.Stack<String> operators = new Stack<String>();

        while (input.hasNext()) {
            String s = input.next();
            if (s.equals("+")) operators.push(s);
            else if (s.equals("-")) operators.push(s);
            else if (s.equals("*")) operators.push(s);
            else if (s.equals("/")) operators.push(s);
            else if (s.equals(")")) output.print(operators.pop() + " ");
            else if (s.equals("(")) output.print("");
            else {
                output.print(s + " ");
            }
        }

        output.println();
    }
}