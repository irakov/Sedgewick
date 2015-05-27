package chap2_5;//2.5.12(page 355)

import java.io.BufferedInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class SPT {
    public static void main(String[] args) {
        Scanner input = new Scanner(new BufferedInputStream(System.in));
        PrintWriter output = new PrintWriter(new OutputStreamWriter(System.out), true);

        int size = input.nextInt();
        Job[] jobs = new Job[size];
        for (int i = 0; i < size; i++) {
            String jobName = input.next();
            double jobDuration = input.nextDouble();
            jobs[i] = new Job(jobName, jobDuration);
        }

        Arrays.sort(jobs);

        output.println();
        output.println("sorted jobs");
        for (int i = 0; i < size; i++)
            output.print(jobs[i] + " ");

        output.println();
    }
}