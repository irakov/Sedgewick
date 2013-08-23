//2.5.12

import java.util.Arrays;

public class SPT
{
	public static void main(String[] args)
	{
		int size=StdIn.readInt();
		Job[] jobs=new Job[size];
		for(int i=0;i<size;i++)
		{
			String jobName=StdIn.readString();
			double jobDuration=StdIn.readDouble();
			jobs[i]=new Job(jobName,jobDuration);
		}
		
		Arrays.sort(jobs);
		
		StdOut.println();
		StdOut.println("sorted jobs");
		for(int i=0;i<size;i++)
			StdOut.print(jobs[i]+" ");
	}
}