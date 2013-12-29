//2.5.13(page 355)

import java.util.Arrays;
import java.util.Scanner;
import java.io.BufferedInputStream;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;

public class LPT
{
	public static void main(String[] args)
	{
		Scanner input=new Scanner(new BufferedInputStream(System.in));
		PrintWriter output=new PrintWriter(new OutputStreamWriter(System.out),true);
	
		int cpuCount=input.nextInt();
	
		int size=input.nextInt();
		Job[] jobs=new Job[size];
		for(int i=0;i<size;i++)
		{
			String jobName=input.next();
			double jobDuration=input.nextDouble();
			jobs[i]=new Job(jobName,jobDuration);
		}
		
		Arrays.sort(jobs);
		
		MinPQ<Processor> procs=new MinPQ<Processor>(cpuCount);
		for(int i=0;i<cpuCount;i++)
			procs.insert(new Processor());
			
		for(int i=size-1;i>=0;i--)
		{
			Processor proc=procs.removeMin();
			proc.add(jobs[i]);
			procs.insert(proc);
		}
		
		for(int i=0;i<cpuCount;i++)
			output.println(procs.removeMin());
	}
}